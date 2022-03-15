package com.cares.s1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.cares.s1.file.FileDTO;

@Component
public class FileDown extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 모델의 이름과 값이 key : value인 Map형식으로 담김
		FileDTO fileDTO = (FileDTO) model.get("file");
		System.out.println(fileDTO.getFileName());
		System.out.println(fileDTO.getOriName());
		
		// 이 경로에 이 파일명으로 파일이 저장되어 있다고 알려주기 위해 정보를 담고있는 File객체를 생성함
		// 이 경로도 마찬가지로 OS가 파일시스템을 관리하기에 RealPath가 필요함,
		// 즉, Application 객체가 필요함, 근데 내장객체는 다른 영역의 내장객체를 불러올 수 있음
		// 좁은 영역에서 큰 영역으로 불러오는 것은 가능 (반대는 불가능)
		// page -> request/response -> session -> application(ServeltContext)
		// 이 메서드가 request, response 객체를 매개변수로 가지고 있기 때문에 여기서 Application 객체를 부르면 됨
		ServletContext sc = request.getSession().getServletContext(); // 차례대로 꺼내면 됨
		
		String board = (String) model.get("board");
		
		String path = "/resources/upload/"+board+"/"; // Server 입장에서 파일이 저장된 경로
		System.out.println(path);
		path = sc.getRealPath(path); 
		// 톰캣이 저장된 주소(realPath) + Server 기준 파일이 저장된 경로(path) = OS 기준 파일의 경로
		
		File file = new File(path, fileDTO.getFileName()); // 경로 + 파일명 = 파일의 정보
		System.out.println(file.exists());
		System.out.println(file.isFile());
		// 실제로 존재하는지, 파일이 맞는지 확인
		
		// Encoding 처리
		response.setCharacterEncoding("UTF-8"); // 응답으로 내보낼 때 인코딩 처리
		
		// 총 파일의 크기 계산
		response.setContentLength((int) file.length());
		
		// 다운로드시의 파일의 이름 한글 인코딩 
		String fileName = URLEncoder.encode(fileDTO.getOriName(), "UTF-8");
		
		// Header 설정 (응답으로 내보낼 때 웹브라우저가 읽어서 준비를 해달라는 의미)
		// 위에서 인코딩 한 오리지널 파일이름을 다운로드 시에 이름으로 설정(파일이름과 오리지널이름이 다르니까)
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		// 콘텐츠로 전송되는 데이터는 이진데이터임을 명시
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// HDD에서 파일을 읽어서 Client로 전송 연결 준비
		FileInputStream fi = new FileInputStream(file); // file을 읽을 준비
		OutputStream os = response.getOutputStream(); // 응답으로 내보낼 준비
		
		// 최종 전송
		FileCopyUtils.copy(fi, os);
		
		// 연결 끊기 (Stream으로 연결한 것은 끊어주어야 한다)
		os.close();
		fi.close();
	}

}
