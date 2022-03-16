package com.cares.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cares.s1.member.MemberDTO;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("로그인 인터셉터");
		// request 객체에서 session객체 가져와서 그 안에 member Attribute를 꺼냄 -> 로그인한 회원의 정보가 나옴
		MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("member");
		
		boolean check = true;
		if (memberDTO == null) { // 로그인하지 않았다면 memberDTO가 null이니까!
			check = false; // Controller로 진행 X
			// 컨트롤러까지 안가기 때문에, 응답을 내보내기 위해 처리해주어야 함 - servlet 문법 사용
			// 1. forward 형식으로 처리할 경우
			request.setAttribute("message", "로그인이 필요합니다"); // common/result 안에 사용할 Attribute들 설정
			request.setAttribute("path", "../member/login"); 
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/views/common/result.jsp");
			view.forward(request, response);
			
			// 2. redirect 형식으로 처리할 경우
			//response.sendRedirect("../member/login");
		}
		
		return check;
	}
	
}
