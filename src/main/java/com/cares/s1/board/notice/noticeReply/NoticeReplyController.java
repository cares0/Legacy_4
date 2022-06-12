package com.cares.s1.board.notice.noticeReply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/noticeReply/**") // value 생략 가능, method를 따로 명시하지 않기 때문
public class NoticeReplyController {

	@Autowired
	private NoticeReplyService noticeReplyService;
	
	// @RequestMapping(value = "add", method = RequestMethod.POST)
	// @GetMapping("add")
	@PostMapping("add") // value 생략 가능
	public ModelAndView add(NoticeReplyDTO noticeReplyDTO) throws Exception {
		int result = noticeReplyService.add(noticeReplyDTO);

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView list(NoticeReplyDTO noticeReplyDTO) throws Exception {

		List<NoticeReplyDTO> ar = noticeReplyService.list(noticeReplyDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("noticeReply", ar);
		mv.setViewName("common/noticeReply");
		
		return mv;
	}
	
	@PostMapping("delete")
	public ModelAndView delete(NoticeReplyDTO noticeReplyDTO) throws Exception {
		
		int result = noticeReplyService.delete(noticeReplyDTO);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView update(NoticeReplyDTO noticeReplyDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = noticeReplyService.update(noticeReplyDTO);
		
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		
		return mv;
	}
}
