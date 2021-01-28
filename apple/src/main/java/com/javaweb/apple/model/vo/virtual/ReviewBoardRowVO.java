package com.javaweb.apple.model.vo.virtual;

import java.sql.Timestamp;

public class ReviewBoardRowVO {
	/*
	 * title(게시판명) varchar(20) NN default=후기 r_id(관리번호) int <<PK>> NN post_title(게시글
	 * 제목) varchar(20) post_id(작성자) varchar <<FK>> NN read_count (조회수) int default 0
	 * post_contents(게시글 내용) varchar(4096) post_answer (게시글 답변내용) varchar(4096)
	 * posted_day(게시일) timestamp default CURRENT_TIMESTAMP updated_day(갱신일)
	 * timestamp default CURRENT_TIMESTAMP product_score 상품점수 int product_image
	 * 상품이미지 vachar answer 답변여부 int default 0 NN
	 */

	private int rId; // (관리번호)
	private String postTitle; // (게시글 제목)
	private String mbId; // (작성자)
	private int readCount; // (조회수)
	private String postContents; // (게시글 내용)
	private Timestamp postedDay; // (게시일)
	private float productScore; // (상품점수)
	private String productImage; // 상품이미지
	private int answer; // 답변여부

	public ReviewBoardRowVO() {
	}

	public ReviewBoardRowVO(int rId, String postTitle, String mbId, int readCount, String postContents,
			Timestamp postedDay, float productScore, String productImage, int answer) {
		super();
		this.rId = rId;
		this.postTitle = postTitle;
		this.mbId = mbId;
		this.readCount = readCount;
		this.postContents = postContents;
		this.postedDay = postedDay;
		this.productScore = productScore;
		this.productImage = productImage;
		this.answer = answer;
	}

	public int getrId() {
		return rId;
	}

	public void setqId(int rId) {
		this.rId = rId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getmbId() {
		return mbId;
	}

	public void setmbId(String mbId) {
		this.mbId = mbId;
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

	public Timestamp getPostedDay() {
		return postedDay;
	}

	public void setPostedDay(Timestamp postedDay) {
		this.postedDay = postedDay;
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
		return "ReviewBoardRowVO [rId=" + rId + ", postTitle=" + postTitle + ", mbId=" + mbId + ", readCount="
				+ readCount + ", postContents=" + postContents + ", postedDay=" + postedDay + ", productScore="
				+ productScore + ", productImage=" + productImage + ", answer=" + answer + "]";
	}

}
