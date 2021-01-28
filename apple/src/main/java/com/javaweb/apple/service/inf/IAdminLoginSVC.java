package com.javaweb.apple.service.inf;

import com.javaweb.apple.model.vo.AdminLoginVO;

public interface IAdminLoginSVC {
//	1. 관리자
//	- 가입 할 수  있다. 
	//(패스워드 암호화 저장)
	boolean insertNewAdminWithCrypto(AdminLoginVO al);
	
//	- 로그인 중복체크 할 수 있다.
	boolean isDuplicatedAdmin(String login);
	
//	- 관리자 로그인 인증 처리 할 수 있다.
	int loginProcess(String login, String pw);
	
// - 관리자 정보를 상세보기 할 수 있다.
	AdminLoginVO selectOneAdmin(String login); //<<UQ>>

}
