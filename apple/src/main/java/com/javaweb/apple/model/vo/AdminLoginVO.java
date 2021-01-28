package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class AdminLoginVO {
	private int id; // 관리자 순서번호
	private String login; // 관리자 id
	private String pw; //관리자 pw
	
	public AdminLoginVO() {}
	
	public AdminLoginVO(String login, String pw) {
		super();
		this.login = login;
		this.pw = pw;
	}

	public AdminLoginVO(int id, String login, String pw) {
		super();
		this.id = id;
		this.login = login;
		this.pw = pw;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		return "AdminLoginVO [id=" + id + ", login=" + login + ", pw=" + pw + "]";
	}
	
	
	
}
