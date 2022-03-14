package com.cares.s1.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	@Autowired
	private ServletContext servletContext;
	// Tomcat 내장객체 Application에 해당하는 객체임
	
	public String save(MultipartFile multipartFile, String path) throws Exception {
		// 1. 파일을 어느 폴더에 저장 할 것인가?
		// path = /resources/upload/member 를 외부에서 받아 올 것임
		String realPath = servletContext.getRealPath(path);
		// 다 만든 프로젝트를 배포하게 되면, war압축파일로 프로젝트 파일이 생성됨
		// 이 war 압축파일을 톰캣폴더의 webapp폴더에 넣으면, 서버가 실핼 될 때 자동으로 압축이 해제됨
		// 그럼 서버가 실행되면 압축이 해제되면서 webapp 아래에 s1이라는 폴더가 생기게 될 텐데,
		// 이 때 루트가 s1 폴더가 된다. 그리고 resources는 결국 s1아래에 있게 된다.
		// 파일 저장은 tomcat에서 하는 것이 아니라, OS에서 저장하게 된다.
		// 우리는 파일을 /resources/upload/member에 저장하고 싶어서 OS에게 그렇게 요청하고 싶다.
		// 근데, 운영체제(OS) 입장에서는 루트가 C 아래이다.
		// 그래서 C밑에 resources/upload/member를 찾게된다. 근데 있는가? 없다.
		// 그래서 tomcat이 있는 폴더를 찾으러 가게 해야한다.
		// 윈도우 입장에서는 톰캣이 깔린 C:\Backend\apache-tomcat-9.0.58\webapps\s1 이 경로를 알려주어야 한다.
		// 근데, 톰캣을 꼭 이 경로에 깔까? 톰캣이 있는 경로가 꼭 여기일까? 바뀔 수도 있다.
		// 그래서 실제 톰캣이 어디에 깔려있는지는 모르겠지만, 톰캣이 깔려있는 경로를 알려달라고 하는 것이 realPath 메서드이다.
		System.out.println(realPath);
		
		// 2. 폴더 정보를 담는 File 객체 생성
		File file = new File(realPath);
		// 파일 객체에다가 폴더 정보(realPath)를 담아라
//		System.out.println(file2.exists()); // 이 폴더 존재합니까?
//		System.out.println(file2.isDirectory()); // 이거 폴더맞습니까?
		if(!file.exists()) { // 만약 폴더가 없다면,
			//file2.mkdir(); // 우리가 준 경로로 폴더를 만들어주세요 (member폴더 하나만 만듬, 즉 upload폴더 부터 상위폴더가 없다면 에러)
			file.mkdirs(); // 중간 폴더가 없으면 중간 폴더도 다 만들어줌
		}
		
		// 3. 중복되지 않는 파일명을 생성
		// 1) 시간을 이용하는 방법
		Calendar calendar = Calendar.getInstance();
		long l = calendar.getTimeInMillis(); // ms값은 중복될 가능성이 없음
		System.out.println("time : " + l);
		// 이 ms 값에다가 파일의 확장자까지 더해주면 최종적인 파일명이 완성됨
		// 그래서 매개변수로 받은 MultipartFile 에서 확장자를 가져와서 합치면 됨
		String oriName = multipartFile.getOriginalFilename();
		String fileName = l + "_" + oriName; // 이러면 어차피 맨 마지막에 확장자가 들어가게 됨
		// 확장자를 따로 잘라서 넣어도 되긴 하지만 이 방법이 편하긴 함
		System.out.println("fileName : "+fileName);
		
		// 2) UUID 를 이용하는 방법
		String uuID = UUID.randomUUID().toString(); // 추상클래스임, 리턴타입 UUID라서 toString으로 바꿔야 함
		fileName = uuID + "_" + oriName;
		System.out.println("UUID : "+ fileName);
		
		// 4. HDD에 저장
		// 1) transferTo 메서드 사용
		// file = new File(realPath, fileName); // parent : 폴더명 / child : 파일명
		// realPath폴더에 fileName파일명으로 최종 경로/파일명을 가진 file객체를 만듬
		// realPath 대신에 아까 위에서 만든 file객체를 넣어도 됨(realPath를 넣어놨기 때문)
		// multipartFile.transferTo(file); 
		// 해당 file객체에 저장된 경로로 multipartFile을 저장해주세요
		
		// 2) FileCopyUtils 클래스의 copy 메서드 사용
		file = new File(realPath, fileName);
		FileCopyUtils.copy(multipartFile.getBytes(), file);
		// 비트의 조합을 가지고 와서 그 비트를 그대로 복사해서 목적지에 복사해주세요
		
		return fileName; // 실제 HDD에 저장된 파일 이름을 리턴해줄테니, DB에 저장해라
	}
}
