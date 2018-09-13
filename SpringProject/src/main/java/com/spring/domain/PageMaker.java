package com.spring.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.controller.BoardController;

public class PageMaker {
	
	private static final Logger logger = LoggerFactory.getLogger(PageMaker.class); // 로깅

	private int totalCount; // 전체 데이터 개수 - SQL로 추출
	private int startPage; // 보여줄 페이지 중 시작페이지
	private int endPage; // 보여줄 페이지 중 끝페이지
	private boolean prev; // 이전 페이지 번호 리스트로 넘기기
	private boolean next; // 다음 페이지 번호 리스트로 넘기기

	private int displayPageNum = 10; // 페이지 리스트에 한번에 보여주는 페이지 수가 10개

	private Criteria criteria;
	
	////

	public PageMaker() {
		//logger.info("##PageMaker");
	}
	
	
	public String makeParameter(int page){
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				//.path("/{module}/{page}")
				.queryParam("page", page)
				.queryParam("perPageNum", criteria.getPerPageNum())
				.build(); // ex) ?page=1&perPageNum=10
		//logger.info("makePerPageNum(int page): "+uriComponents.toString());
		
		
		return uriComponents.toString();
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		logger.info("#this.totalCount: " + this.totalCount);
		calData();
	}

	public void calData() {
		logger.info("#criteria.getPage(): " + criteria.getPage());
		logger.info("#displayPageNum: " + displayPageNum);
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = endPage - displayPageNum + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;
		next = (endPage * criteria.getPerPageNum() >= totalCount) ? false : true;

		logger.info("#endPage: " + endPage);
		logger.info("#startPage: " + startPage);
		logger.info("#prev: " + prev);
		logger.info("#next: " + next);
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return criteria;
	}

	public void setCri(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	

}
