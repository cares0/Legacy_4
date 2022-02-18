package com.cares.s1.bankbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/bankbook/**")
public class BankBookController {

	@Autowired
	private BankBookService bankBookService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		List<BankBookDTO> ar = bankBookService.list();
		
		model.addAttribute("list", ar);
		
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView mv, BankBookDTO bankBookDTO) throws Exception {
		
		
		bankBookDTO = bankBookService.detail(bankBookDTO);
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
	
}
