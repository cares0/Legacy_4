package com.cares.s1.bankbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cares.s1.util.Pager;

@Controller
@RequestMapping(value = "/bankbook/**")
public class BankBookController {

	@Autowired
	private BankBookService bankBookService;
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(BankBookDTO bankBookDTO) throws Exception {
		int result = bankBookService.update(bankBookDTO);
		
		return "redirect:./detail?bookNumber="+bankBookDTO.getBookNumber();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(BankBookDTO bankBookDTO, Model model) throws Exception {
		bankBookDTO = bankBookService.detail(bankBookDTO);
		model.addAttribute("detail", bankBookDTO);
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(Model model, Pager pager) throws Exception {
		List<BankBookDTO> ar = bankBookService.list(pager);
		model.addAttribute("pager", pager);
		model.addAttribute("list", ar);		
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView mv, BankBookDTO bankBookDTO) throws Exception {

		bankBookDTO = bankBookService.detail(bankBookDTO);
		// 조회가 성공하면 출력
		// 조회가 실패하면 alert으로 없는 번호입니다 출력
		// 다시 list로 이동
		// common/result.jsp 활용
		
		if(bankBookDTO == null) {
			String p = "./list";
			String message = "없는 번호입니다.";
			mv.addObject("path", p);
			mv.addObject("message", message);
			mv.setViewName("common/result");
			return mv;
		}
		
		mv.addObject("detail", bankBookDTO);
		return mv;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public void add() throws Exception {
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Model model, BankBookDTO bankBookDTO) throws Exception {
		int result = bankBookService.add(bankBookDTO);
		
		return "redirect:./list";
		
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(BankBookDTO bankBookDTO) throws Exception {
		int result = bankBookService.delete(bankBookDTO);
		return "redirect:./list";
	}
	
}
