package com.javaweb.apple.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.LoginCode;
import com.javaweb.apple.dao.inf.IMemberDAO;
import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.service.inf.IMemberSVC;

//컨테이너 자동 빈등록: MVC service 처리
@Service
public class MemberSVCImpl implements IMemberSVC {
	// 필드에 의존관계를 자동 주입
	@Autowired
	IMemberDAO mbDao;

	@Override
	public boolean insertNewMemberWithCrypto(MemberVO mb) {
		// TODO Auto-generated method stub
		return mbDao.insertNewMemberWithCrypto(mb);
	}

	@Override
	public boolean isDuplicate(String login) {
		// TODO Auto-generated method stub
		return mbDao.isDuplicate(login);
	}

	@Override
	public int loginProcess(String login, String pw) {
		
		// 파람 체크
		if (login == null && login.isEmpty()) {
			return LoginCode.MBLOGIN_NULLEMTPY; // login입력필요
		}
		if (pw == null && pw.isEmpty()) {
			return LoginCode.MBLOGIN_PW_NULLEMPTY;// pw입력필요
		}
		// 가입 체크

		MemberVO mb = mbDao.selectOneMember(login);
		if (mb == null) { //DB에 등록된 회원이 없습니다.
			return LoginCode.MBLOGIN_NOT_FOUND;
		} else {
			String checkpw = mbDao.decryptPassword(login);
			if (checkpw.equals(pw)) {
				return LoginCode.MBLOGIN_AUTH; // 인증성공
			} else {
				return LoginCode.MBLOGIN_PW_MISMATCH; // 비밀번호가 틀립니다.
			}
		}

	}
	@Override
	public String selectLoginByNameAndPhone(String name, String phone) {
		// TODO Auto-generated method stub
		return mbDao.selectLoginByNameAndPhone(name, phone);
	}
	@Override
	public String decryptPassword(String login) {
		// TODO Auto-generated method stub
		return mbDao.decryptPassword(login);
	}

	@Override
	public MemberVO selectOneMember(int id) {
		// TODO Auto-generated method stub
		return mbDao.selectOneMember(id);
	}

	@Override
	public MemberVO selectOneMember(String login) {
		// TODO Auto-generated method stub
		return mbDao.selectOneMember(login);
	}

	@Override
	public boolean updateOneMemberById(MemberVO mb) {
		// TODO Auto-generated method stub
		return mbDao.updateOneMemberById(mb);
	}
	@Override
	public boolean updateOneMemberByLogin(MemberVO mb) {
		// TODO Auto-generated method stub
		return mbDao.updateOneMemberByLogin(mb);
	}
	@Override
	public boolean updateOneMemberByLogin2(String name, String birthday, String email, String phone, String zipcode,
			String address, String addressDetail, String login) {
		// TODO Auto-generated method stub
		return mbDao.updateOneMemberByLogin2(name, birthday, email, phone, zipcode, address, addressDetail, login);
	}
	@Override
	public List<MemberVO> selectAllMembers() {
		// TODO Auto-generated method stub
		return mbDao.selectAllMembers();
	}

	@Override
	public boolean deleteOneMember(int id) {
		// TODO Auto-generated method stub
		return mbDao.deleteOneMember(id);
	}

	@Override
	public boolean deleteOneMember(String login) {
		// TODO Auto-generated method stub
		return mbDao.deleteOneMember(login);
	}

	@Override
	public String selectPasswordByLoginAndEmail(String login, String email) {

		return mbDao.selectPasswordByLoginAndEmail(login, email);
	}

	@Override
	public List<MemberVO> selectMembersByLogin(String login, int offset, int limit) {
		
		return mbDao.selectMembersByLogin(login, offset, limit);
	}

	@Override
	public List<MemberVO> selectMembersByEmail(String email, int offset, int limit) {
		// TODO Auto-generated method stub
		return mbDao.selectMembersByLogin(email, offset, limit);
	}

	@Override
	public List<MemberVO> selectMembersByPhone(String phone, int offset, int limit) {
		// TODO Auto-generated method stub
		return mbDao.selectMembersByLogin(phone, offset, limit);
	}

	@Override
	public List<MemberVO> selectMembersByJoinDay(String joinDay, int offset, int limit) {
		// TODO Auto-generated method stub
		return mbDao.selectMembersByLogin(joinDay, offset, limit);
	}

	@Override
	public List<MemberVO> selectMembersByALL(String keyword, int offset, int limit) {
		// TODO Auto-generated method stub
		return mbDao.selectMembersByALL(keyword, offset, limit);
	}

	@Override
	public List<MemberVO> selectMembersByName(String name, int offset, int limit) {
		// TODO Auto-generated method stub
		return mbDao.selectMembersByName(name, offset, limit);
	}

	@Override
	public MemberVO selectOneMember(String login, String email) {
		
		return mbDao.selectOneMember(login, email);
	}

	@Override
	public String makeTempoPw() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid.substring(0, 12);
	}

// -------------------- admin --------------------

	@Override
	public List<MemberVO> searchMember(String keyword, String target, int pg) {
		List<MemberVO> mbList = null;
		int offset = (pg-1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "all":
			mbList = mbDao.selectMembersByALL(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "name":
			mbList = mbDao.selectMembersByName(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "login":
			mbList = mbDao.selectMembersByLogin(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "email":
			mbList = mbDao.selectMembersByEmail(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "phone":
			mbList = mbDao.selectMembersByPhone(keyword, offset, SEARCH_PAGE_SIZE);
			break;

		}
		if( mbList != null ) {
			System.out.println("검색결과 회원 수: " + mbList.size());
		}
		return mbList;
		
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String keyword, String target) {
		int totalAtCnt = 0; 
		switch (target) {
		case "all":
			totalAtCnt = mbDao.checkAllMembersCount(keyword);
			break;
		case "name":
			totalAtCnt = mbDao.checkNameMembersCount(keyword);
			break;
		case "login":
			totalAtCnt = mbDao.checkLoginMembersCount(keyword);
			break;
		case "email":
			totalAtCnt = mbDao.checkEmailMembersCount(keyword);
			break;
		case "phone":
			totalAtCnt = mbDao.checkPhoneMembersCount(keyword);
			break;
		}	
		int maxPg = totalAtCnt / SEARCH_PAGE_SIZE + 
				(totalAtCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		System.out.println("totalAtCnt = " + totalAtCnt);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		Map<String,Integer> rMap = new HashMap<>();
		rMap.put("totalAtCnt", totalAtCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	
}
