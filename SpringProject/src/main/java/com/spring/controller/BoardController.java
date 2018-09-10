package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.BoardVO;
import com.spring.persistence.BoardDAO;
import com.spring.service.BoardService;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;
	@Inject
	private BoardDAO dao;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		model.addAttribute("list", service.listAll());
	}
	
	// 화면 띄워주는 메소드
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO boardVO, Model model) throws Exception {
		logger.info("register GET~~~~~~");
	}
	
	// register form 제출시 응답하는 메소드
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO boardVO, Model model) throws Exception {
		logger.info("register POST~~~~~~");
		
		//service.create(boardVO);
		dao.create(boardVO);
		
		model.addAttribute("result", "Success!!");
		
		return "/board/success"; // success.jsp 호출
	}
	
//	@RequestMapping(value = "/read")
//	public void read(Model model) throws Exception {
//		model.addAllAttributes("vo", )
//	}
	
}