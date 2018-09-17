package com.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.PageMaker;
import com.spring.domain.SearchCriteria;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception{
		logger.info("##list: "+criteria.toString());
		
		model.addAttribute("list", service.listSearch(criteria));
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(service.listSearchCount(criteria));
		
		model.addAttribute("pageMaker", pageMaker);
	}

}
