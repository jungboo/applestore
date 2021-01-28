package com.javaweb.apple.dao.inf;

import java.util.List;

import com.javaweb.apple.model.vo.MemberVO;

public interface IMemberDAO {

//	
//	1. 회원
//	- 가입 할 수  있다. (패스워드 암호화 저장)
	boolean insertNewMemberWithCrypto(MemberVO mb);

//	- 로그인 중복체크 할 수 있다.
	boolean isDuplicate(String login);

//	- 로그인 인증 할 수  있다. (세션, 암호화 인증)
	int loginProcess(String login, String pw);

	public String decryptPassword(String login);

//	- 회원 정보를 상세보기 할 수 있다.
	MemberVO selectOneMember(int id); // <<PK>>

	MemberVO selectOneMember(String login); // <<UQ>>
	
	 MemberVO selectOneMember(String login, String email);
//	- 회원 정보를 편집/갱신 할 수 있다.

	boolean updateOneMemberById(MemberVO mb);

	boolean updateOneMemberByLogin(MemberVO mb);

	boolean updateOneMemberByLogin2(String name, String birthday, String email, String phone, String zipcode,
			String address, String addressDetail, String login);

//	- 전체 회원 조회  할 수  있다. (어드민)
	List<MemberVO> selectAllMembers();
//	- 로그아웃 할 수  있다.

	// - 회원 아이디를 이름과 휴대폰 번호로 조회 할 수 잇다.
	String selectLoginByNameAndPhone(String name, String phone);

	// 비밀번호 가져오기
	String selectPasswordByLoginAndEmail(String login, String email );
	// all으로 조회 (login , email, name, phone , birthday)
		List<MemberVO> selectMembersByALL(String keyword, int offset , int limit);
		int checkAllMembersCount(String keyword);
	// name으로 조회
		List<MemberVO> selectMembersByName(String name, int offset , int limit);
		int checkNameMembersCount(String keyword);
//		login으로 조회
		List<MemberVO> selectMembersByLogin(String login, int offset , int limit);
		int checkLoginMembersCount(String keyword);
//		email로 조회
		List<MemberVO> selectMembersByEmail(String email, int offset , int limit);
		int checkEmailMembersCount(String keyword);
//		phone로 조회
		List<MemberVO> selectMembersByPhone(String phone, int offset , int limit);
		int checkPhoneMembersCount(String keyword);
//		join_day로 조회
		List<MemberVO> selectMembersByJoinDay(String joinDay, int offset , int limit);
		int checkJoinDayMembersCount(String keyword);

//	- 회원 탈퇴 할 수 있다. (미정)
	boolean deleteOneMember(int id);

	boolean deleteOneMember(String login);
	
}
