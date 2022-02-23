package com.cares.s1.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cares.s1.InitTestCase;

public class MemberDAOTest extends InitTestCase {

	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void logintTest() throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("id1");
		memberDTO.setPw("pw2");
		memberDTO = memberDAO.login(memberDTO);
		
		assertNotNull(memberDTO);
	}
	
	
	//@Test
	public void joinTest() throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("id2");
		memberDTO.setPw("pw2");
		memberDTO.setName("name2");
		memberDTO.setPhone("01021211141");
		memberDTO.setEmail("id2@gmail.com");
		
		int result = memberDAO.join(memberDTO);
		
		assertEquals(1, result);
		
	}
}
