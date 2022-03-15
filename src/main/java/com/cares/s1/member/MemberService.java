package com.cares.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.cares.s1.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileManager fileManager;
		
	
	public MemberFileDTO detailFile(MemberFileDTO memberFileDTO) throws Exception {
		return memberDAO.detailFile(memberFileDTO);
	}
	
	public int join(MemberDTO memberDTO, MultipartFile photo) throws Exception {
		int result = memberDAO.join(memberDTO); // MEMBER 테이블에 먼저 ID가 인서트 되어야 참조무결성이 위배되지 않음
		
		// 1. 파일을 하드디스크에 저장
		String fileName = fileManager.save(photo, "resources/upload/member/");
		
		// 2. 저장된 정보를 DB에 저장
		MemberFileDTO memberFileDTO = new MemberFileDTO();
		
		memberFileDTO.setId(memberDTO.getId());
		memberFileDTO.setFileName(fileName);
		memberFileDTO.setOriName(photo.getOriginalFilename());
		memberDAO.addFile(memberFileDTO);
		
		return result;
	}
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		return memberDAO.login(memberDTO);
	}
	
	public MemberDTO mypage(MemberDTO memberDTO) throws Exception {
		return memberDAO.mypage(memberDTO);
	}
	
	public int update(MemberDTO memberDTO) throws Exception {
		return memberDAO.update(memberDTO);
	}
}
