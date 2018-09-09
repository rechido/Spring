package com.spring.domain;

import java.util.Date;

public class BoardVO {

	private Integer bno;
	private String title;	
	private String content;	
	private String writer;	
	private Date regdate;	
	private Integer viewcnt;	
	
	public BoardVO() {
		super();
	}

	public BoardVO(Integer bno, String title, String content, String writer, Date regdate, Integer viewcnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.viewcnt = viewcnt;
	}



	public Integer getBno() {
		return bno;
	}
	
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Integer getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	@Override
	public String toString() {
		return "[bno]: " + bno + " [title]: " + title + " [content]: " + content;
	}
	
	
	
}