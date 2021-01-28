package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class NoticeVO {

	private String title; //(게시판명)
	private int nId; //(관리번호)
	private String postTitle; //(게시글 제목) 
	private String postContents; //(게시글 내용)
	private Timestamp postedDay; //(게시일) 

	public NoticeVO() {	}
	
	//고객용
	public NoticeVO(String postTitle, String postContents) {
		super();
		this.postTitle = postTitle;
		this.postContents = postContents;
	}
	
	public NoticeVO(String title, int nId, String postTitle, String postContents, Timestamp postedDay) {
		super();
		this.title = title;
		this.nId = nId;
		this.postTitle = postTitle;
		this.postContents = postContents;
		this.postedDay = postedDay;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContents() {
		return postContents;
	}

	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}

	public Timestamp getPostedDay() {
		return postedDay;
	}

	public void setPostedDay(Timestamp postedDay) {
		this.postedDay = postedDay;
	}

	@Override
	public String toString() {
		return "NoticeVO [title=" + title + ", nId=" + nId + ", postTitle=" + postTitle + ", postContents="
				+ postContents + ", postedDay=" + postedDay + "]";
	}
	
	
	
}
