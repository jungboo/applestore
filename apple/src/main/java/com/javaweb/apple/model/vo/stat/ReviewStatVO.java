package com.javaweb.apple.model.vo.stat;

public class ReviewStatVO {
	private int rvId; // x
	private int readCount; // y
	private String postTitle;
	
	public ReviewStatVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReviewStatVO(int rvId, int readCount, String postTitle) {
		super();
		this.rvId = rvId;
		this.readCount = readCount;
		this.postTitle = postTitle;
	}


	public int getRvId() {
		return rvId;
	}


	public void setRvId(int rvId) {
		this.rvId = rvId;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public String getPostTitle() {
		return postTitle;
	}


	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}


	@Override
	public String toString() {
		return "ReviewStatVO [rvId=" + rvId + ", readCount=" + readCount + ", postTitle=" + postTitle + "]";
	}
	
	
}
