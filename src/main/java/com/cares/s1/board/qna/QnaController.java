package com.cares.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cares.s1.board.BoardDTO;
import com.cares.s1.util.Pager;

@Controller
@RequestMapping(value = "/qna/**")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	// model에 board라는 속성으로 qna를 담겠다. 모든 요청이 발생할 때마다 한번씩 실행됨
	@ModelAttribute("board")
	public String board() {
		return "qna";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);

		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		return "board/list";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "board/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.add(qnaDTO);

		return "redirect:./list";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		return "redirect:./list";
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);

		model.addAttribute("detail", boardDTO);
		return "board/detail";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(QnaDTO qnaDTO, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.detail(qnaDTO);
		model.addAttribute("detail", boardDTO);
		return "board/update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.update(qnaDTO);

		return "redirect:./detail?num="+qnaDTO.getNum();
	}
}
