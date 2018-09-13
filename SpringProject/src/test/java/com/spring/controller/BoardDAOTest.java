package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class); // 로깅
	
	@Inject
	private BoardDAO dao;
	
	//@Test
	public void testCreate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setTitle("제목7");
		vo.setContent("내용2");
		vo.setWriter("저자2");
		
		dao.create(vo);
	}
	
	//@Test
	public void testDelete() throws Exception{
		dao.delete(3);
	}
	
	//@Test
	public void testUpdate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setBno(2);
		vo.setTitle("제목234");
		vo.setContent("내용111");
		
		dao.update(vo);
	}

	//@Test
	public void testRead() throws Exception{
		logger.info("##read: " + dao.read(1).toString());		
	}
	
	//@Test
	public void testListAll() throws Exception{
		List<BoardVO> list = dao.listAll();
		for(BoardVO vo : list)
			logger.info("##listAll: " + vo.toString());
	}
	
	//@Test
	public void testListPage() throws Exception{
		int page = 0; // 1페이지(0,10) 2페이지(10,10) 3페이지(20,10)
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO vo : list)
			logger.info("##listAll: " + vo.toString());
	}
	
	//@Test
	public void testListCriteria() throws Exception{
		Criteria criteria = new Criteria();
		criteria.setPage(3);
		criteria.setPerPageNum(20);
		
		List<BoardVO> list = dao.listCriteria(criteria);
		/*
		select 
			bno, title, content, writer, regdate, viewcnt 
		from 
			tbl_board 
		where 
			bno > 0 
		order by		
			bno desc, regdate desc 
		limit 
			40, 20 
		*/
		for(BoardVO vo : list)
			logger.info("##listAll: " + vo.toString());
	}
	
	//@Test
	public void testURI() throws Exception{
		// 스프링에서 제공하는 추상클래스 (웹페이지에서 사용)
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("testURI()"+ uriComponents.toString());
		// /board/read?bno=12&perPageNum=20
	}
	
	@Test
	public void testURI2() throws Exception{
		// 스프링에서 제공하는 추상클래스 (웹페이지에서 사용)
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board", "read")
				.encode();
		
		logger.info("testURI2()"+uriComponents.toString());
		// /board/read?bno=12&perPageNum=20
	}

}
