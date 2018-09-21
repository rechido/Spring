package com.spring.persistence;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

// 댓글 다오
public interface ReplyDAO {

	public List<ReplyVO> list(Integer bno) throws Exception; // 댓글 리스트 조회

	public void create(ReplyVO vo) throws Exception; // 댓글 등록

	public void update(ReplyVO vo) throws Exception; // 댓글 수정

	public void delete(Integer rno) throws Exception; // 댓글 삭제
	
	public List<ReplyVO> listPage(Integer bno, Criteria criteria) throws Exception; // 댓글 페이징 처리
	
	public int countPaging(Integer bno) throws Exception; // 전체 데이터 개수 조회

}
