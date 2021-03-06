package com.cares.s1.board.notice;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cares.s1.board.BoardDTO;
import com.cares.s1.util.Pager;

@Controller
@RequestMapping(value = "/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String board() {
		return "notice";
	}
	
	@RequestMapping(value = "fileDown")
	public ModelAndView fileDown(NoticeFileDTO noticeFileDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		noticeFileDTO = noticeService.detailFile(noticeFileDTO);
		
		mv.addObject("file", noticeFileDTO);
		mv.setViewName("fileDown");
		
		return mv;
	}
	
	@RequestMapping (value = "update", method = RequestMethod.POST)
	public String update(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.update(noticeDTO);
		
		return "redirect:./detail?num="+noticeDTO.getNum();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(NoticeDTO noticeDTO, Model model) throws Exception {
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		model.addAttribute("detail", boardDTO);
		return "board/update";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) throws Exception {
		List<BoardDTO> ar = noticeService.list(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(NoticeDTO noticeDTO, Model model) throws Exception { // ????????? ?????? ????????????
		BoardDTO boardDTO = noticeService.detail(noticeDTO);
		model.addAttribute("detail", boardDTO); // ???????????? ?????? ????????????
		return "board/detail";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "board/add";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(NoticeDTO noticeDTO, MultipartFile[] files) throws Exception {
		
		int result = noticeService.add(noticeDTO, files);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.delete(noticeDTO);
		return "redirect:./list";
	}
	
	@PostMapping("fileDelete")
	public ModelAndView fileDelete(NoticeFileDTO noticeFileDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println(noticeFileDTO.getFileNum());
		
		int result = noticeService.fileDelete(noticeFileDTO);
		
		mv.setViewName("common/ajaxResult");
		mv.addObject("result", result);
		return mv;
	}
	
	
	
	
	
	
	
}
