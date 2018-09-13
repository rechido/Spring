package com.spring.service;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

public interface BoardService {	
	
	public void create(BoardVO boardVO) throws Exception; // 등록	
	
	public void update(BoardVO boardVO) throws Exception; // 수정
	
	public void delete(Integer bno) throws Exception; // 삭제
	
	public BoardVO read(Integer bno) throws Exception; // 상세조회
	
	public List<BoardVO> listAll() throws Exception; // 전체 리스트
	
	public List<BoardVO> listPage(int page) throws Exception; // 일부조회 (한 페이지)
	
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception; // 일부조회 (한 페이지)
}