package com.spring.persistence;

import java.util.List;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.SearchCriteria;

public interface BoardDAO { // DAO: data access object
	
	public void create(BoardVO vo) throws Exception; // 입력
	
	public void update(BoardVO vo) throws Exception; // 수정
	
	public void delete(Integer bno) throws Exception; // 삭제
	
	public BoardVO read(Integer bno) throws Exception; // 상세조회 (단일)
	
	public List<BoardVO> listAll() throws Exception; // 전체조회(리스트)
	
	public List<BoardVO> listPage(int page) throws Exception; // 일부조회 (한 페이지)
	
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception; // 일부조회 (한 페이지)
	
	public int countPaging(Criteria criteria) throws Exception; // 전체 데이터 개수 조회
	
	public List<BoardVO> listSearch(SearchCriteria criteria) throws Exception; // 검색 조회 (리스트, 한 페이지)
	
	public int listSearchCount(SearchCriteria criteria) throws Exception; // 검색 조회 게시글 수
	
}
