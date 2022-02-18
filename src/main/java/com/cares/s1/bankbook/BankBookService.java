package com.cares.s1.bankbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankBookService {
	// Controller -> Service -> DAO
	// DAO, Controller로 데이터를 보내기 전에 전/후처리 작업을 수행하는 클래스 (데이터 가공)
	// Controller에서 받은 파라미터(데이터)를 가공함 -> DAO로 보냄
	// DAO에서 리턴받은 데이터를 Controller로 보내기 전에 후처리 작업을 진행함
	
	@Autowired
	private BankBookDAO bankBookDAO;
	
	// 메서드는 DAO의 메서드와 거의 동일
	
	//list
	public List<BankBookDTO> list() throws Exception {
		// DAO 메서드 호출 전에 전처리 작업
		// 호출 후 후처리 작업
		// 무조건은 아니고 필요로 하다면 하는 것
		
		List<BankBookDTO> ar = bankBookDAO.list();
		
		return ar;
	}
	
	public BankBookDTO detail(BankBookDTO bankBookDTO) throws Exception {
		return bankBookDAO.detail(bankBookDTO);
	}
	
	public int add(BankBookDTO bankBookDTO) throws Exception {
		return bankBookDAO.add(bankBookDTO);
	}
	
}
