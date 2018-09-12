package com.spring.persistence;

import java.util.List;

import com.spring.domain.BoardVO;

public interface BoardDAO { // DAO: data access object
	
	public void create(BoardVO vo) throws Exception; // 입력
	
	public void update(BoardVO vo) throws Exception; // 수정
	
	public void delete(Integer bno) throws Exception; // 삭제
	
	public BoardVO read(Integer bno) throws Exception; // 상세조회 (단일)
	
	public List<BoardVO> listAll() throws Exception; // 전체조회(리스트)	
	
}
