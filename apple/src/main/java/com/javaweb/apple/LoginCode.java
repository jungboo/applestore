package com.javaweb.apple;

public class LoginCode {
	public static final int MBLOGIN_ERROR = 0; 
	public static final int MBLOGIN_AUTH = 1; // authenticate
	public static final int MBLOGIN_NOT_FOUND = 2;
	public static final int MBLOGIN_NULLEMTPY = 3;
	public static final int MBLOGIN_PW_NULLEMPTY = 4;
	public static final int MBLOGIN_PW_MISMATCH = 5;
	
	public static final String[] LOGIN_MSGS = {
		"로그인 에러", "로그인 성공!", "존재 하지 않는 회원 입니다.", 
		"아이디를 입력 해 주세요!", "암호를 입력해주세요", "암호가 일치 하지 않습니다."
	};
}
