package com.spring.domain;

public class Criteria {
	
	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0){
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
//		if(perPageNum <= 0 || perPageNum > 100){
//			this.perPageNum = 10;
//			return;
//		}
		this.perPageNum = perPageNum;
	}
	
	// mapper.xml 파일에서 pageStart 변수로 인식함. 실제 멤버필드가 존재하느냐는 중요하지 않고 get메서드 이름을 인식.
	// 멤버필드변수 이름은 중요하지 않지만 get메서드의 이름과 mapper상의 변수명은 일치해야함.
	public int getPageStart(){
		return(this.page-1)*perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [현재 page = " + page + ", 출력데이터갯수 perPageNum= " + perPageNum + "]";
	}
	
	/*
	 *  ex) 10개씩 데이터를 출력하는 경우
	 *  1page limit 0, 10
	 *  2page limit 10, 10
	 *  3page limit 20, 10
	 *  
	 *  시작데이터번호 = (페이지번호 - 1) * 페이지당 보여질 갯수
	 */
	

}
