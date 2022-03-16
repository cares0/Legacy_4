package com.cares.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cares.s1.board.BoardDTO;
import com.cares.s1.board.qna.QnaDAO;
import com.cares.s1.board.qna.QnaDTO;
import com.cares.s1.member.MemberDTO;

@Component
public class WriterInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("라이터 인터셉터");
		boolean check = true;
		
		// Spring에서는 이건 자동으로 해주지만, 여긴 Servlet 문법을 따르기에 다 형변환 알아서 해야함
		Long num = Long.parseLong(request.getParameter("num"));
		
		// 파라미터로 넘겨준 num을 가지고 글의 상세내용을 가져옴
		BoardDTO boardDTO = new QnaDTO();
		boardDTO.setNum(num);
		boardDTO = qnaDAO.detail(boardDTO);
		
		// 로그인한 회원의 ID정보를 가져오기 위해 DTO를 빼옴
		MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("member");
		
		// 현재 게시글의 작성자와, 로그인한 사람의 ID가 같지 않으면 check = false로 바꾸고 처리
		if(!boardDTO.getWriter().equals(memberDTO.getId())) {
			check = false;
			// 1. forward
			request.setAttribute("message", "작성한 사람만 접근 가능합니다.");
			request.setAttribute("path", "./list");
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/common/result.jsp");
			view.forward(request, response);
			// 2. redirect 
			
			//response.sendRedirect("./list")
		}
		
		return check;
	}
}
