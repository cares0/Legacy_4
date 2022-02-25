package com.cares.s1.util;

public class Pager {

	// page당 보여줄 row의 갯수
	private Long perPage;
	
	// page 번호
	private Long page;
	
	// 시작 번호
	private Long startRow;
	
	// 끝 번호
	private Long lastRow;
	
	
	// makeRow 호출 시에 startRow, lastRow가 자동 계산됨, page,perPage를 따로 설정하지 않아도 null이 들어가지 않음(getter를 검증해놨기 때문임)
	public void makeRow() {
		this.startRow = (this.getPage()-1) * this.getPerPage() + 1;
		this.lastRow = this.getPage() * this.getPerPage();
	}
	
	// 검색 사용 변수, 메서드
	private String search;
	private String kind;
	
	public String getSearch() {
		// 검색어가 없으면 search = null, 기본값을 넣어야 함
		if(this.search == null) {
			this.search = "";
		}
		//this.search = "%" + this.search + "%";
		return search;
	}
	public String getKind() {
		if (this.kind == null) {
			this.kind = "BOOKNAME";
		}
		return kind;
	}
	
	// -- jsp 사용 변수, 메서드 --
	private Long startNum;
	private Long lastNum;	
	private boolean pre; // 이전 블럭이 있냐 없냐를 판단
	private boolean next; // 다음 블럭이 있냐 없냐를 판단 (6번 다음임)
	
	public void makeNum(Long totalCount) { //1. 전체 row의 갯수 구하기 - DB에서 totalCount 가져오기		
		
		//2. 전체 page의 갯수 구하기
		Long totalPage = totalCount / this.getPerPage();
		if(totalCount%this.getPerPage() != 0) { // 만약 perPage로 나누고 나머지가 있을 경우는 page 하나가 더 있어야 하니까
			totalPage++;
		}
		// 3. 블럭당 보일 페이지 개수
		Long perBlock = 10L;
		// 10 페이지당 1블록으로 묶겠다는 소리임
		
		// 4. 전체 Block의 갯수 구하기
		Long totalBlock = totalPage/perBlock;
		// 남는 block이 있다면 총 블럭이 하나는 더 있어야 하니까 1++
		if(totalPage%perBlock != 0) { 
			totalBlock++;
		} 
		
		// 5. Page 번호로 현재 몇번째 블럭에 속하는지 계산
		// 1 block -> 1 - 10 page
		// 2 block -> 11 - 20 page
		
		// page		Block
		// 1	..	1
		// 2	..	1
		// 10	..	1
		// 11	..	2
		// 21	..	3
		
		Long curBlock = this.getPage()/perBlock;
		if (this.getPage()%perBlock != 0) { // 나머지가 0이 아닌애들 (11, 12 등등)은 1을 더해야 하잖아
			curBlock++;
		}

		// 6. curBlock를 이용해서 startNum, lastNum 구하기
		// curBlock이 몇번이냐에 따라 startNum, lastNum이 바껴야 함 그래야 jsp에 표시되는 숫자를 제한할 수 있음
		// curBlock		startNum	lastNum
		// 1			1			10
		// 2			11			20
		
		// 결국 아까 startRow, lastRow 계산법이랑 똑같음
		this.startNum = (curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		// 최종적으로 page에 따라 블럭이 달라지고, 블럭에 따라 JSP에 표시할 페이지번호가 달라짐
		
		//7. 이전, 다음 블럭 유무 표시
		this.pre=false;
		if(curBlock > 1) {
			this.pre = true;
		}
		
		this.next = false;
		if(totalBlock > curBlock) {
			this.next = true;
		} 
		
		// 8. 현재 블럭이 마지막 블럭번호와 같다면
		if(curBlock == totalBlock) {
			this.lastNum = totalPage;
		} // 마지막 블럭에는 결국 총 페이지 만큼 표시되어야 하기 때문임
	}
		
	// null 이면 get메서드 호출할 때 기본값 10 넣어놓자
	public Long getPerPage() {
		if(this.perPage==null || this.perPage < 1) {
			this.perPage = 10L;
		}
		return perPage;
	}
	public Long getPage() {
		if(this.page == null || this.page < 1) {
			this.page = 1L;
		}
		return page;
	}

	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}


	public void setPage(Long page) {
		this.page = page;
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


	public void setSearch(String search) {
		this.search = search;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	
}
