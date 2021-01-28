package com.javaweb.apple.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.javaweb.apple.MyCode;
import com.javaweb.apple.dao.inf.IAdminLoginDAO;
import com.javaweb.apple.model.vo.AdminLoginVO;
import com.javaweb.apple.service.inf.IAdminLoginSVC;

@Service // 컨테이너 자동 빈등록: MVC service 처리
public class AdminLoginSVCImpl implements IAdminLoginSVC
{

	@Autowired // 필드에 의존관계를 자동 주입
	IAdminLoginDAO alDao;


	@Override
	public boolean insertNewAdminWithCrypto(AdminLoginVO al)
	{
		return this.alDao.insertNewAdminWithCrypto(al);
	}

	@Override
	public boolean isDuplicatedAdmin(String login)
	{
		return this.alDao.isDuplicatedAdmin(login);
	}

	@Override
	public int loginProcess(String login, String pw)
	{
		System.out.println("SVC: loginProcess()");

		// 파람 체크
		if (login == null || login.isEmpty())
			return MyCode.AL_LOGIN_NULLEMTPY;
		if (pw == null || pw.isEmpty())
			return MyCode.AL_LOGIN_PW_NULLEMPTY;

		// 가입 체크
		AdminLoginVO al = alDao.selectOneAdmin(login);
		if (al == null)
		{
			System.out.println(login + " 회원이 없습니다");
			return MyCode.AL_LOGIN_NOT_FOUND;
		}
		int alId = al.getId();

		// 패스 인증
		// String dbPW = this.mbDao.decryptPassword(mbId, login);
		String dbPW = this.alDao.decryptPassword(login);
		if (dbPW.equals(pw))
		{
			// 인증 성공!
			return MyCode.AL_LOGIN_AUTH;
		} else
		{
			return MyCode.AL_LOGIN_PW_MISMATCH;
		}
	}

	@Override
	public AdminLoginVO selectOneAdmin(String login)
	{
		return this.alDao.selectOneAdmin(login);
	}

}
