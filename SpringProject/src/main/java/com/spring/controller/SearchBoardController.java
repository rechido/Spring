package com.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageMaker;
import com.spring.domain.SearchCriteria;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	// 게시글 리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception{
		logger.info("##list: "+criteria.toString());
		
		model.addAttribute("list", service.listSearch(criteria));
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(service.listSearchCount(criteria));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	// 상세조회
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {
		logger.info("##readPage: "+criteria.toString());
		model.addAttribute(service.read(bno));
	}
	
	// 등록 화면 띄워주는 메소드
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO boardVO, SearchCriteria criteria, Model model) throws Exception {
		logger.info("register GET~~~~~~~~~~~~");
	}

	// 등록 폼 제출시 응답하는 메소드
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO boardVO, RedirectAttributes redirectAtt) throws Exception { 
																						

		logger.info("register POST~~~~~~~~~~~~");

		service.create(boardVO); 
		redirectAtt.addFlashAttribute("result", "write"); 
		
		return "redirect:/sboard/list"; 
	}
	
	// 삭제
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, RedirectAttributes redirectAtt, SearchCriteria criteria) throws Exception {
		logger.info("##removePage: "+criteria.toString());
		service.delete(bno);
		
		redirectAtt.addAttribute("page", criteria.getPage());
		redirectAtt.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAtt.addAttribute("searchType", criteria.getSearchType());
		redirectAtt.addAttribute("keyword", criteria.getKeyword());		
		redirectAtt.addFlashAttribute("result", "remove"); // addAttribute는 session에 저장한 효과, addFlashAttribute는 단발성(휘발성)

		return "redirect:/sboard/list";
	}
	
	// 수정 화면 띄우기
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(int bno, SearchCriteria criteria, Model model) throws Exception {

		logger.info("#modifyPageGET()~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		model.addAttribute(service.read(bno));
	}
	
	// 수정 폼 제출
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO boardVO, SearchCriteria criteria, RedirectAttributes redirectAtt) throws Exception {
		
		logger.info("#modifyPagePOST()~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		service.update(boardVO);

		redirectAtt.addAttribute("page", criteria.getPage());
		redirectAtt.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAtt.addAttribute("searchType", criteria.getSearchType());
		redirectAtt.addAttribute("keyword", criteria.getKeyword());	
		redirectAtt.addFlashAttribute("result", "modify");
		
		logger.info("##redirectAtt.toString(): "+redirectAtt.toString());

		return "redirect:/sboard/list";
	}

	

}
