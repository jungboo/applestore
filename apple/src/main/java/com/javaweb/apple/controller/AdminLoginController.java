package com.javaweb.apple.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.MyCode;
import com.javaweb.apple.model.vo.AdminLoginVO;
import com.javaweb.apple.service.inf.IAdminLoginSVC;

@Controller
public class AdminLoginController {
	
	@Autowired
	IAdminLoginSVC alSvc;
	
// - 로그인 인증 할 수 있다.
//	default.ap => 로그인창(admin_login_form.ap)
	@RequestMapping(value = "admin_login_form.ap", method = RequestMethod.GET)
	public String adminLogin() {
		return "admin/al_login_form";
	}

	@RequestMapping(value = "admin_login_proc.ap", method = RequestMethod.POST)
	public ModelAndView adminloginProc(HttpSession ses, String login, String pw) {
//			String login  = req.getParameter("login");
		// 파람 name과 동일한 인자들..
		System.out.println("AdminLoginProc : login = " + login + ", pw = " + pw);

		ModelAndView mav = new ModelAndView();

		int loginResult = alSvc.loginProcess(login, pw);

		if (loginResult == MyCode.AL_LOGIN_AUTH) { // OK
			System.out.println(">> 로그인 성공 " + login);
			// 세션 처리
			ses.setAttribute("adLoginName", login); // 로그인 중... UQ
			AdminLoginVO ad = alSvc.selectOneAdmin(login);
			// 출석부... 알람...
			ses.setAttribute("adPKId", ad.getId()); // 로그인 중... PK

			ses.setAttribute("adLoginTime", System.currentTimeMillis());// 로그인 시작 시간..

			mav.addObject("msg", "로그인 성공");
			mav.setViewName("redirect:admin_stat.ap");// mypage.my
		} else {
			String errMsg = MyCode.LOGIN_MSGS[loginResult];
			mav.addObject("msg", "로그인 실패 : " + errMsg);
			mav.setViewName("admin/al_login_form");
		}

		return mav;

	}

//	- 가입 할 수  있다. (패스워드 암호화 저장)
	@RequestMapping(value = "admin_new_form.ap", method = RequestMethod.GET)
	public ModelAndView adminNewForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/al_new_form");
		return mav;
	}

	// 가입
	@RequestMapping(value = "admin_join.ap", method = RequestMethod.POST)
	public ModelAndView adminJoinProc(HttpServletRequest request) {
		// 요청파람, 세션 처리
		String login = request.getParameter("login");
		String pw = request.getParameter("pw");

		ModelAndView mav = new ModelAndView();
		// 부가기능 DAO에서 체크 - 로그인 중복체크
		if (login != null && !login.isEmpty()) {
			boolean dup = alSvc.isDuplicatedAdmin(login);
			if (dup) { // 사용중
				mav.addObject("msg", "관리자 등록 실패  login이 중복 : " + login);
				mav.setViewName("admin/al_new_form"); // fw
				return mav;
			}
		} else {
			mav.addObject("msg", "관리자 등록 실패 login명 파람 error");
			mav.setViewName("admin/al_new_form"); // fw
			return mav;
		}
		AdminLoginVO al = new AdminLoginVO(login, pw);
		boolean b = this.alSvc.insertNewAdminWithCrypto(al); // 암호화
		if (b) {
			mav.addObject("msg", "관리자 등록 완료");
			System.out.println("msg");
			mav.setViewName("redirect:admin_login_form.ap");
			// 리다이렉션 url 재지정
		} else {
			mav.addObject("msg", "관리자 등록 실패 DB");
			mav.setViewName("admin/al_new_form"); // fw
		}
		return mav;
	}
}
