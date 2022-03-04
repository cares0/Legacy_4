package com.cares.s1.qna;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cares.s1.InitTestCase;

public class QnaDAOTest extends InitTestCase{

	@Autowired
	private QnaDAO qnaDAO;
	
	@Test
	public void check() {
		assertNotNull(qnaDAO);
	}

}
