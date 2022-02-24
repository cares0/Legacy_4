package com.cares.s1.util;

public class NoticePager {
	
	private Long startRow;
	private Long lastRow;
	private Long page;
	private Long perPage;
	
	// DB에서 ROWNUM 활용 데이터 꺼내오기
	
	public void makeRow() {
		this.startRow = (this.getPage() - 1) * this.getPerPage() + 1;
		this.lastRow = this.getPage() * this.getPerPage();
	}
	// Getters
	public Long getPage() {
		if (this.page == null || this.page < 1) {
			this.page = 1L;
		}
		return page;
	}
	public Long getPerPage() {
		if (this.perPage == null || this.perPage < 1) {
			this.perPage = 10L;
		}
		return perPage;
	}
	
	// JSP 로 보내는 용
	
	private Long startNum;
	private Long lastNum;
	private boolean pre;
	private boolean next;
	
	public void makeNum(Long totalCount) { // 1. 총 Row의 갯수 구하기 - DB와 연결
//		2. 총 페이지 갯수 구하기
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}
//		3. Block 당 갯수 정하기 - Block : 보여줄 page 숫자의 갯수
		Long perBlock = 10L;
//		4. 전체 Block 갯수 구하기
		Long totalBlock = totalPage/perBlock;
		if (totalPage%perBlock != 0) {
			totalBlock++;
		}
//		5. 현재 Page 번호가 몇 번째 Block에 속하는지 계산
		Long curBlock = this.getPage()/perBlock;
		if (this.getPage()%perBlock != 0) {
			curBlock++;
		}
//		6. 현재 Block 번호를 통해 JSP에 표시할 startNum, lastNum 구하기
		this.startNum = (curBlock-1) * perBlock + 1;
		this.lastNum = curBlock * perBlock;
//		7. 이전블럭, 다음블럭 표시 유무를 설정하기
		this.pre = false;
		if (curBlock > 1) {
			this.pre = true;
		}
		this.next = false;
		if (curBlock < totalBlock) {
			this.next = true;
		}
//		8. 맨 마지막 블럭일 경우, 최종 페이지 번호만을 표시하기
		if (curBlock == totalBlock) {
			this.lastNum = totalPage;
		}
	}
	
	
	
	public Long getStartRow() {
		return startRow;
	}
	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}
	public Long getLastRow() {
		return lastRow;
	}
	public void setLastRow(Long lastRow) {
		this.lastRow = lastRow;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}
	public Long getStartNum() {
		return startNum;
	}
	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}
	public Long getLastNum() {
		return lastNum;
	}
	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}
	public boolean isPre() {
		return pre;
	}
	public void setPre(boolean pre) {
		this.pre = pre;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	
}
