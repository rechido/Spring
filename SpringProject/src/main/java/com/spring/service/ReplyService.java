package com.spring.service;

import java.util.List;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> listReplies(Integer bno) throws Exception;

	public void addReply(ReplyVO vo) throws Exception;

	public void modifyReply(ReplyVO vo) throws Exception;

	public void removeReply(Integer rno) throws Exception;
	
	public List<ReplyVO> listReplyPage(Integer bno, Criteria criteria) throws Exception; // 댓글 페이징 처리
	
	public int countPaging(Integer bno) throws Exception; // 전체 데이터 개수 조회
}
