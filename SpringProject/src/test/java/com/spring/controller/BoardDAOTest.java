package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.BoardVO;
import com.spring.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class); // 로깅
	
	@Inject
	private BoardDAO dao;
	
	@Test
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

}
