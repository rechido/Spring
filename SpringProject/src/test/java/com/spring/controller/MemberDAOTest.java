package com.spring.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.MemberVO;
import com.spring.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class); // 로깅

	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime() throws Exception{
		
		logger.info("##testTime: " + dao.getTime());
	}
	
	//@Test
	public void testInsertMember() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUserid("user01");
		vo.setUserpw("12345");
		vo.setUsername("이다은");
		vo.setEmail("rechido@naver.com");
		
		dao.insertMember(vo);
		
	}
}
