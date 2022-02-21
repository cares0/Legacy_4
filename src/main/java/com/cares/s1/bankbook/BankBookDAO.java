package com.cares.s1.bankbook;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankBookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.cares.s1.bankbook.BankBookDAO.";
	
	public List<BankBookDTO> list() throws Exception {
		return sqlSession.selectList(NAMESPACE+"list");
	}
	
	public int add(BankBookDTO bankBookDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"add", bankBookDTO);
	}
	
	public BankBookDTO detail(BankBookDTO bankBookDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"detail", bankBookDTO);
	}
	
	public int delete(BankBookDTO bankBookDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"delete", bankBookDTO);
	}
	
}