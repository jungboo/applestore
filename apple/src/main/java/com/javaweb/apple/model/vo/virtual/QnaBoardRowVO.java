package com.javaweb.apple.model.vo.virtual;

import java.sql.Timestamp;

public class QnaBoardRowVO {

	private int qId; // (관리번호)
	private String postTitle; // (게시글 제목)
	private String mbId; // (작성자)
	private int readCount; // (조회수)
	private String postContents; // (게시글 내용)
	private Timestamp postedDay; // (게시일)
	private int answer; // 답변여부

	public QnaBoardRowVO() {
		// TODO Auto-generated constructor stub
	}

	public QnaBoardRowVO(int qId, String postTitle, String mbId, int readCount, String postContents,
			Timestamp postedDay, int answer) {
		super();
		this.qId = qId;
		this.postTitle = postTitle;
		this.mbId = mbId;
		this.readCount = readCount;
		this.postContents = postContents;
		this.postedDay = postedDay;
		this.answer = answer;
	}

	public int getqId() {
		return qId;
	}

	public void setrId(int qId) {
		this.qId = qId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
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

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "QnaBoardRowVO [rId=" + qId + ", postTitle=" + postTitle + ", mbId=" + mbId + ", readCount=" + readCount
				+ ", postContents=" + postContents + ", postedDay=" + postedDay + ", answer=" + answer + "]";
	}

}
