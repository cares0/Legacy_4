package com.cares.s1.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cares.s1.util.Pager;

@Controller
@RequestMapping(value = "/qna/**")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(Pager pager, Model model) throws Exception {
		List<QnaDTO> ar = qnaService.list(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public void add() throws Exception {
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.add(qnaDTO);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		System.out.println(result);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void detail(QnaDTO qnaDTO, Model model) throws Exception {
		qnaDTO = qnaService.detail(qnaDTO);
		
		model.addAttribute("detail", qnaDTO);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(QnaDTO qnaDTO, Model model) throws Exception {
		qnaDTO = qnaService.detail(qnaDTO);
		model.addAttribute("detail", qnaDTO);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.update(qnaDTO);
		
		return "redirect:./detail?num="+qnaDTO.getNum();
	}
}
