package com.cares.s1.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(MemberDTO memberDTO) throws Exception {
		int result = memberService.update(memberDTO);
		
		return "redirect:./mypage?id="+memberDTO.getId();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(MemberDTO memberDTO, Model model) throws Exception {
		memberDTO = memberService.mypage(memberDTO);
		model.addAttribute("mypage", memberDTO);
	}
		
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public ModelAndView mypage(HttpSession session) throws Exception {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		memberDTO = memberService.mypage(memberDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("mypage", memberDTO);
		mv.setViewName("member/mypage");
		return mv;
		
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:../";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(@CookieValue(value="remember", defaultValue = "", required = false) String rememberId, Model model) throws Exception {
		//model.addAttribute("remember", rememberId);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpSession session, MemberDTO memberDTO, String remember, HttpServletResponse response, Model model) throws Exception {

		if(remember != null && remember.equals("1")) {
			// 쿠키 생성
			Cookie cookie = new Cookie("remember", memberDTO.getId());
			// 쿠키 응답으로 보내기			
			response.addCookie(cookie);
		} else { // check 해제시 쿠키를 삭제하도록
			Cookie cookie = new Cookie("remember", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		// 쿠키를 생성하기 전에 DB에 호출해버리면, 로그인이 실패했을 경우 Null인 DTO의 ID를 쿠키에 저장하기 때문에 Exception 발생, 코드 순서 고려
		memberDTO = memberService.login(memberDTO);
//		String path = "redirect:./login";
//		if (memberDTO != null) {
//			// 로그인 성공 시 session 객체에 성공한 DTO 객체를 넣어놓음
//			session.setAttribute("member", memberDTO);
//			path = "redirect:../";
//		}
		String message = "Login Fail";
		String p = "./login"; // 로그인 실패시에는 다시 로그인 페이지로
		if(memberDTO != null) {
			session.setAttribute("member", memberDTO);
			message = "Login Success";
			p = "../"; // 로그인 성공시에는 index페이지로 
		} // 미리 Controller에서 경로를 계산해서 JSP로 보내는 것임
		model.addAttribute("message", message);
		model.addAttribute("path", p);
		String path = "common/result";

		return path;
	}
	
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void join() throws Exception {
	}
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(MemberDTO memberDTO) throws Exception {
		int result = memberService.join(memberDTO);
		return "redirect:../";
	}
	
	@RequestMapping(value = "joinCheck", method = RequestMethod.GET)
	public void joinCheck() throws Exception {
		
	}
}
