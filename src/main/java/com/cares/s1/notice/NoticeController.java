package com.cares.s1.notice;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cares.s1.util.NoticePager;

@Controller
@RequestMapping(value = "/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping (value = "update", method = RequestMethod.POST)
	public String update(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.update(noticeDTO);
		
		return "redirect:./detail?num="+noticeDTO.getNum();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(NoticeDTO noticeDTO, Model model) throws Exception {
		noticeDTO = noticeService.detail(noticeDTO);
		model.addAttribute("detail", noticeDTO);
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(Model model, NoticePager noticePager) throws Exception {
		List<NoticeDTO> ar = noticeService.list(noticePager);
		model.addAttribute("pager", noticePager);
		model.addAttribute("list", ar);
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void detail(NoticeDTO noticeDTO, Model model) throws Exception {
		noticeDTO = noticeService.detail(noticeDTO);
		model.addAttribute("detail", noticeDTO);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public void add() throws Exception {
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.add(noticeDTO);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.delete(noticeDTO);
		
		return "redirect:./list";
	}
	
	
	
	
	
	
	
}
