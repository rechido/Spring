package com.spring.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;

	private String namespace = "com.spring.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(namespace + ".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria criteria) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>(); 
		
		paramMap.put("bno", bno);
		paramMap.put("criteria", criteria);
		
		return session.selectList(namespace + ".listPage", paramMap);
		
		// replyMapper.xml에 패러미터를 2개 던지는데다 criteria는 객체이므로 이걸 한번에 보내기 위해 Map을 사용
	}

	@Override
	public int countPaging(Integer bno) throws Exception {
		
		return session.selectOne(namespace + ".countPaging", bno);
	}
	
	

}
