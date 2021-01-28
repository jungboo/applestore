package com.javaweb.apple.dao.inf;

import com.javaweb.apple.model.vo.AdminLoginVO;

public interface IAdminLoginDAO {
//	1. 관리자
//	- 가입 할 수  있다. 
//	(패스워드 암호화 저장)
	boolean insertNewAdminWithCrypto(AdminLoginVO al);
	
// - 관리자 정보를 상세보기 할 수 있다.
	AdminLoginVO selectOneAdmin(String login); //<<UQ>>
	
//	- 로그인 중복체크 할 수 있다.
	boolean isDuplicatedAdmin(String login);
	
// - 로그인 할수 있다.. 암호화를 풀어서 패스워드 문구 가져오기..	
	String decryptPassword(String login);
	

}
