package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class QnaBoardVO {

	/*
	q_id(관리번호) int <<PK>> NN AI
	title(게시판명) varchar(20) default=QnA NN
	post_title(게시글 제목) varchar(20) NN
	post_id(작성자) varchar <<FK>> NN
	read_count (조회수) int default 0
	post_contents(게시글 내용) varchar(4096)  NN
	post_answer (게시글 답변내용) varchar(4096)
	posted_day(게시일) timestamp default CURRENT_TIMESTAMP
	updated_day(갱신일) timestamp default CURRENT_TIMESTAMP
	answer (답변여부) int default 0 
	*/
	
	private String title; //(게시판명)
	private int qId; //(관리번호)
	private String postTitle; //(게시글 제목) 
	private int postId; //(작성자)
	private int readCount; //(조회수)
	private String postContents; //(게시글 내용)
	private String postAnswer; //(게시글 답변내용)
	private Timestamp postedDay; //(게시일) 
	private Timestamp updatedDay; //(갱신일) 
	private int answer; //답변여부 
	
	
	public QnaBoardVO() {
		// TODO Auto-generated constructor stub
	}
	
	// 고객용
		public QnaBoardVO(String postTitle, int postId, String postContents) {
			super();
			this.postTitle = postTitle;
			this.postId = postId;
			this.postContents = postContents;
		}
	
	public QnaBoardVO(String title, int qId, String postTitle, int postId, int readCount, String postContents,
			String postAnswer, Timestamp postedDay, Timestamp updatedDay, int answer) {
		super();
		this.title = title;
		this.qId = qId;
		this.postTitle = postTitle;
		this.postId = postId;
		this.readCount = readCount;
		this.postContents = postContents;
		this.postAnswer = postAnswer;
		this.postedDay = postedDay;
		this.updatedDay = updatedDay;
		this.answer = answer;
	}



	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public int getqId() {
		return qId;
	}




	public void setqId(int qId) {
		this.qId = qId;
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




	public int getAnswer() {
		return answer;
	}




	public void setAnswer(int answer) {
		this.answer = answer;
	}




	@Override
	public String toString() {
		return "QnaBoardVO [title=" + title + ", qId=" + qId + ", postTitle=" + postTitle + ", postId=" + postId
				+ ", readCount=" + readCount + ", postContents=" + postContents + ", postAnswer=" + postAnswer
				+ ", postedDay=" + postedDay + ", updatedDay=" + updatedDay + ", answer=" + answer + "]";
	}
		
}
