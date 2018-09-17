package com.spring.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchCriteria extends Criteria{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchCriteria.class);
	
	private String searchType;
	private String keyword;
	
	
	
	public SearchCriteria() {
		super(true);
		logger.info("SearchCriteria() 기본 생성자 호출");
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		
		return super.toString() + "\n" + 
				"SearchCriteria [현재 searchType = " + searchType + ", 키워드 keyword= " + keyword + "]";
	}
	
	
	
	
	
}
