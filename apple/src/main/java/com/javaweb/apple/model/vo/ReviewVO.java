package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class ReviewVO {
	private int	rId; //(관리번호) int <<PK>> NN AI
	private String title; //(게시판명) varchar(20) default=후기 NN
	private String postTitle; //(게시글 제목) varchar(20) NN
	private int postId; //(작성자) varchar <<FK>> NN
	private int readCount; // (조회수) int default 0
	private String postContents; //(게시글 내용) varchar(4096) NN
	private String postAnswer; // (게시글 답변내용) varchar(4096)
	private Timestamp postedDay; //(게시일) timestamp default CURRENT_TIMESTAMP
	private Timestamp updatedDay; //(갱신일) timestamp default CURRENT_TIMESTAMP
	private float productScore; // 상품점수 float NN
	private String productImage; // 상품이미지 varchar
	private int answer; // 답변여부 int default 0 
	
	public ReviewVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReviewVO(String postTitle, String postContents) {
		super();
		this.postTitle = postTitle;
		this.postContents = postContents;
	}

	public ReviewVO(int rId, String title, String postTitle, int postId, int readCount, String postContents,
			String postAnswer, Timestamp postedDay, Timestamp updatedDay, float productScore, String productImage,
			int answer) {
		super();
		this.rId = rId;
		this.title = title;
		this.postTitle = postTitle;
		this.postId = postId;
		this.readCount = readCount;
		this.postContents = postContents;
		this.postAnswer = postAnswer;
		this.postedDay = postedDay;
		this.updatedDay = updatedDay;
		this.productScore = productScore;
		this.productImage = productImage;
		this.answer = answer;
	}

	public int getrId() {
		return rId;
	}


	public void setrId(int rId) {
		this.rId = rId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPostTitle() {
		return postTitle;
	}


	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public String getPostContents() {
		return postContents;
	}


	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}


	public String getPostAnswer() {
		return postAnswer;
	}


	public void setPostAnswer(String postAnswer) {
		this.postAnswer = postAnswer;
	}


	public Timestamp getPostedDay() {
		return postedDay;
	}


	public void setPostedDay(Timestamp postedDay) {
		this.postedDay = postedDay;
	}


	public Timestamp getUpdatedDay() {
		return updatedDay;
	}


	public void setUpdatedDay(Timestamp updatedDay) {
		this.updatedDay = updatedDay;
	}


	public float getProductScore() {
		return productScore;
	}


	public void setProductScore(float productScore) {
		this.productScore = productScore;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public int getAnswer() {
		return answer;
	}


	public void setAnswer(int answer) {
		this.answer = answer;
	}


	@Override
	public String toString() {
		return "ReviewVO [rId=" + rId + ", title=" + title + ", postTitle=" + postTitle + ", postId=" + postId
				+ ", readCount=" + readCount + ", postContents=" + postContents + ", postAnswer=" + postAnswer
				+ ", postedDay=" + postedDay + ", updatedDay=" + updatedDay + ", productScore=" + productScore
			+ ", productImage=" + productImage + ", answer=" + answer + "]";
}


}