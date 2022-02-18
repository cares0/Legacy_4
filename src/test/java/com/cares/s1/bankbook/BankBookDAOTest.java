package com.cares.s1.bankbook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cares.s1.InitTestCase;

public class BankBookDAOTest extends InitTestCase {

	@Autowired
	private BankBookDAO bankBookDAO;
	
	@Test
	public void check() {
		assertNotNull(bankBookDAO);
	}

	@Test
	public void listTest() throws Exception {
		List<BankBookDTO> ar = bankBookDAO.list();
		assertNotEquals(0, ar.size());
	}
	
	@Test
	public void addTest() throws Exception {
		for(int i=0;i<10;i++) {
		BankBookDTO bankBookDTO = new BankBookDTO();
		bankBookDTO.setBookName("bookName"+i);
		bankBookDTO.setBookContents("Contents"+i);
		bankBookDTO.setBookRate(0.12+i);
		bankBookDTO.setBookSale(1);
	
		int result = bankBookDAO.add(bankBookDTO);
		}
		//assertEquals(1, result);		
	}
	
	@Test
	public void detailTest() throws Exception {
		BankBookDTO bankBookDTO = new BankBookDTO();
		bankBookDTO.setBookNumber(2L);
		bankBookDTO = bankBookDAO.detail(bankBookDTO);
		assertNotNull(bankBookDTO);
	}
	
	@Test
	public void deleteTest() throws Exception {
		BankBookDTO bankBookDTO = new BankBookDTO();
		bankBookDTO.setBookNumber(2L);
		int result = bankBookDAO.delete(bankBookDTO);
		assertEquals(1, result);
	}
}
