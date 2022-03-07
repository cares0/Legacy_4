package com.cares.s1.board;

import java.util.List;

import com.cares.s1.util.Pager;

public interface BoardDAO {

	//detail
	public BoardDTO detail(BoardDTO boardDTO) throws Exception;
	// NoticeDTO는 BoardDTO이다
	// QnaDTO는 BoardDTO이다 -> 둘다 BoardDTO로 받아줄 수 있음
		
	//list
	public List<BoardDTO> list(Pager pager) throws Exception;
	
	//add
	public int add(BoardDTO boardDTO) throws Exception;

	// update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//delete
	public int delete(BoardDTO boardDTO) throws Exception;
	
	//total
	public long total(Pager pager) throws Exception;
	
}
