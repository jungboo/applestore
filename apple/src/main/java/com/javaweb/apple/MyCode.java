package com.javaweb.apple;

public class MyCode {
	// 로그인 상수
	public static final int AL_LOGIN_ERROR = 0; 
	public static final int AL_LOGIN_AUTH = 1; // authenticate
	public static final int AL_LOGIN_NOT_FOUND = 2;
	public static final int AL_LOGIN_NULLEMTPY = 3;
	public static final int AL_LOGIN_PW_NULLEMPTY = 4;
	public static final int AL_LOGIN_PW_MISMATCH = 5;
	
	public static final String[] LOGIN_MSGS = {
		"로그인 에러", "로그인 성공!", "관리자 아이디 없음", 
		"관리자 아이디 에러", "관리자 암호 에러", "암호 불일치"
	};
}
