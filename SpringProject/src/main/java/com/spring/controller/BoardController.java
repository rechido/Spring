package com.spring.controller;

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
	//@Inject
	//private PageMaker pageMaker;

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
	public String registerPOST(BoardVO boardVO, RedirectAttributes redirectAtt) throws Exception { // redirectAtt 개선된 모델 사용
		// public String registerPOST(BoardVO boardVO, Model model) throws
		// Exception {

		logger.info("register POST~~~~~~");

		service.create(boardVO);

		// model.addAttribute("result", "Success!!"); // get방식 전달이라
		// http://localhost:8080/controller/board/listAll?result=Success%21%21 와
		// 같이 패러미터가 url 뒤에 붙어서 날아감.
		redirectAtt.addFlashAttribute("result", "write"); // 이제 url뒤에 패러미터를
																// 붙여서 날아가지 않는다.
																// // 호출할 때는
																// ${result}

		// return "/board/success"; // success.jsp 호출 - url이 변하지 않으므로 새로고침 시 의도치
		// 않게 쿼리가 또 날아감.
		return "redirect:/board/listAll"; // 강제로 url을 바꾸어 중복쿼리가 발생하지 않게 처리 함.
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	/*
	 * public void read(@RequestParam("bno")) throws Exception { int bno =
	 * Integer.parseInt(request.getParameter("bno")); }
	 */

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes redirectAtt) throws Exception {
		service.delete(bno);
		redirectAtt.addFlashAttribute("msg_remove", "글이 삭제 되었습니다."); // ${msg_remove}로 호출
																		

		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO boardVO, RedirectAttributes redirectAtt) throws Exception {

		logger.info("modifyPOST()~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		service.update(boardVO);

		redirectAtt.addFlashAttribute("msg_update", "글이 수정 되었습니다.");

		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {

		model.addAttribute(service.read(bno));
	}

	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCri(Criteria criteria, Model model) throws Exception {
		logger.info("listCri()~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("criteria.getPage(): " + criteria.getPage());
		logger.info("criteria.getPerPageNum(): " + criteria.getPerPageNum());
		logger.info("criteria.getPageStart(): " + criteria.getPageStart());
		model.addAttribute("list", service.listCriteria(criteria));
	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria criteria, Model model) throws Exception {
		logger.info("##listPage: "+criteria.toString());
		
		model.addAttribute("list", service.listCriteria(criteria));
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(service.listCountCriteria(criteria));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {
		logger.info("##readPage: "+criteria.toString());
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, RedirectAttributes redirectAtt, Criteria criteria) throws Exception {
		logger.info("##removePage: "+criteria.toString());
		service.delete(bno);
		
		redirectAtt.addAttribute("page", criteria.getPage());
		redirectAtt.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAtt.addFlashAttribute("result", "remove"); // addAttribute는 session에 저장한 효과, addFlashAttribute는 단발성(휘발성)

		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO boardVO, Criteria criteria, RedirectAttributes redirectAtt) throws Exception {
		
		logger.info("#modifyPagePOST()~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		service.update(boardVO);

		redirectAtt.addAttribute("page", criteria.getPage());
		redirectAtt.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAtt.addFlashAttribute("result", "modify");

		return "redirect:/board/listPage";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {

		logger.info("#modifyPageGET()~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		model.addAttribute(service.read(bno));
	}
	
	// 화면 띄워주는 메소드
		@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
		public void registerPageGET(BoardVO boardVO, @ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {
			logger.info("register GET~~~~~~");
		}

		// register form 제출시 응답하는 메소드
		@RequestMapping(value = "/registerPage", method = RequestMethod.POST)
		public String registerPagePOST(BoardVO boardVO, Criteria criteria, RedirectAttributes redirectAtt) throws Exception { 
																							

			logger.info("register POST~~~~~~");

			service.create(boardVO); 
			
			redirectAtt.addAttribute("page", criteria.getPage());
			redirectAtt.addAttribute("perPageNum", criteria.getPerPageNum());
			redirectAtt.addFlashAttribute("result", "write"); 
			
			return "redirect:/board/listPage"; 
		}
	
}