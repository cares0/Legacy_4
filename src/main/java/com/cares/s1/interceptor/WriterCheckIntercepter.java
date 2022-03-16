package com.cares.s1.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cares.s1.board.BoardDTO;
import com.cares.s1.member.MemberDTO;

public class WriterCheckIntercepter extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			return; // 모든 메서드는 return을 만나는 즉시 종료
		}
		
		// login 정보
		MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("member");
		
		// writer 정보 - 컨트롤러에서 Model에 Attribute에 담아서 내보냄
		Map<String, Object> map = modelAndView.getModel();
		BoardDTO boardDTO = (BoardDTO) map.get("detail");
		
		if(!memberDTO.getId().equals(boardDTO.getWriter())) {
			// Spring 문법
			// 1. Forward 방식
			modelAndView.addObject("message", "권한이 없습니다.");
			modelAndView.addObject("path", "./list");
			modelAndView.setViewName("common/result");	
		}
		
	}
}
