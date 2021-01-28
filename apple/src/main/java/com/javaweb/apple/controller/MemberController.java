
package com.javaweb.apple.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.apple.LoginCode;
import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.service.inf.IFileManageSVC;
import com.javaweb.apple.service.inf.IMemberSVC;
import com.javaweb.apple.service.inf.JavaMail;

@Controller
public class MemberController {
	@Autowired
	IMemberSVC mbSvc;

	@Autowired
	JavaMail mail;

	@Autowired
	IFileManageSVC fileSvc;

//	- 가입 할 수  있다. (패스워드 암호화 저장)
//	member_join_agreement.ap; get 
	@RequestMapping(value = "member_join_agreement.ap", method = RequestMethod.GET)
	public ModelAndView memberAgreement() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/mb_join_agreement");
		return mav;

	}

//	member_join_agreement_proc.ap; post 
	@RequestMapping(value = "member_join_agreement_proc.ap", method = RequestMethod.POST)
	public ModelAndView memberAgreementProc(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String check = request.getParameter("checkAll");
		if (check.equals("y")) {
			mav.addObject("msg", "다음단계로 진행됩니다");
			mav.setViewName("redirect:member_join_form.ap"); // redirect]\
			return mav;
		} else {
			mav.addObject("msg", "가입을 위한 회원 동의를 진행해 주세요");
			mav.setViewName("member/mb_join_agreement"); // fw
			return mav;
		}
	}

//	member_join_form.ap; get form 
	@RequestMapping(value = "member_join_form.ap", method = RequestMethod.GET)
	public ModelAndView memberJoinForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/mb_join_form");
		return mav;
	}

//	member_join_proc.ap; post proc dao 
	@RequestMapping(value = "member_join_proc.ap", method = RequestMethod.POST)
	public ModelAndView memberJoinFormProc(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		MemberVO mb = new MemberVO(name, login, password, birthday, email, phone);
		if (login != null && !login.isEmpty()) {
			boolean dup = mbSvc.isDuplicate(login);
			if (dup) {
				System.out.println("아이디가 중복!!");
				mav.addObject("msg", "아이디가 중복되었습니다. ");
				mav.addObject("member", mb);
				mav.setViewName("member/mb_join_form");
				return mav;
			}
		} else {
			System.out.println("아이디를 입력 해 주세요!");
			mav.addObject("msg", "아이디를 입력 해 주세요!");
			mav.addObject("member", mb);
			mav.setViewName("member/mb_join_form");
			return mav;
		}
		HttpSession ses = request.getSession();
		boolean b = mbSvc.insertNewMemberWithCrypto(mb);
		if (b) {
			if (fileSvc.makeMemberDirectory(ses, login)) {
				System.out.println("정상 회원 가입 되셨습니다 ^^-폴더생성성공");
				mav.addObject("msg", "정상 회원 가입 되셨습니다 ^^");
				mav.setViewName("member/mb_signin");
				return mav;
			} else {
				System.out.println("정상 회원 가입 되셨습니다 ^^-폴더생성실패");
				mav.addObject("msg", "정상 회원 가입 되셨습니다 ^^");
				mav.setViewName("member/mb_signin");
				return mav;
			}
		} else {
			System.out.println("가입에 실패 하셨습니다");
			mav.addObject("msg", "가입에 실패 하셨습니다");
			mav.addObject("member", mb);
			mav.setViewName("member/mb_join_form");
			return mav;
		}
	}

//- 로그인 중복체크 할 수 있다.
//	member_dupcheck.ap?login=? post proc dao // 비동기
	@ResponseBody
	@RequestMapping(value = "member_dupcheck.ap", method = RequestMethod.POST)
	public int memberDupCheck(@RequestParam("login") String login) {
		return (mbSvc.isDuplicate(login) == true) ? 1 : 0;

	}

//- 로그인 인증 할 수  있다. (세션, 암호화 인증)
//	member_login_form.ap; get form 비회원
	@RequestMapping(value = "member_login_form.ap", method = RequestMethod.GET)
	public String memberLoginForm() {
		return "member/mb_signin";
	}

//	member_login_proc.ap; post proc dao 암호화, 세션, 회원
	@RequestMapping(value = "member_login_proc.ap", method = RequestMethod.POST)
	public String memberLoginProc(HttpServletRequest request, HttpSession ses, RedirectAttributes ra, Model model) {
//		ModelAndView mav = new ModelAndView();

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		int loginResult = mbSvc.loginProcess(login, password);

		if (loginResult == LoginCode.MBLOGIN_AUTH) { // 성공시 아이디 로그인 회원이름 세션 등록

			MemberVO mb = mbSvc.selectOneMember(login);
			ses.setAttribute("mbId", mb.getId());
			ses.setAttribute("mbLogin", mb.getLogin());
			ses.setAttribute("mbName", mb.getName());
			System.out.println("로그인성공");
//			mav.addObject("msg", "로그인 성공");
			ra.addFlashAttribute("msg", "로그인 성공");
//			mav.setViewName("redirect:main.ap"); // 로그인 성공시 메인페이지 이동
			return "redirect:main.ap";
		} else {// 실패시 실패요인 alert 출력
			String errMag = LoginCode.LOGIN_MSGS[loginResult];
			System.out.println(errMag);
			model.addAttribute("msg", errMag);
//			mav.setViewName("member/mb_signin");
			return "member/mb_signin";
		}

	}

//- 로그아웃 할 수  있다.
//	member_logout.ap; get proc dao/세션, 회원
	@RequestMapping(value = "member_logout.ap", method = RequestMethod.GET)
	public String memberLogout(HttpSession ses, RedirectAttributes ra) {
		ra.addFlashAttribute("msg", "로그아웃 되셨습니다");
		ses.invalidate(); // 세션 종료
		return "redirect:main.ap"; // 메인 페이지로 리다이렉트
	}

//- 회원 아이디 를 찾을 수 있다.
//	member_find_idpw.ap; get 
	@RequestMapping(value = "member_find_idpw.ap", method = RequestMethod.GET)
	public String memberFindIdPw() {
		return "member/mb_find_idpw";
	}

//	member_find_id_proc.ap; post dao

	@RequestMapping(value = "member_find_id_proc.ap", method = RequestMethod.POST)
	public ModelAndView memberFindIdProc(String name, String phone) {
		ModelAndView mav = new ModelAndView();
		String login = mbSvc.selectLoginByNameAndPhone(name, phone);
		if (login == null) {
			mav.addObject("msg", "존재하지 않는 회원입니다.");
			mav.setViewName("member/mb_find_idpw");
			return mav;
		} else {
			mav.addObject("mbLogin", login);
			mav.setViewName("member/mb_result_id");
			return mav;
		}

	}

//- 회원 비밀번호를 찾을 수 있다.
//	member_find_pw_proc.ap; post  dao 암호화 이메일 전송
	@RequestMapping(value = "member_find_pw_proc.ap", method = RequestMethod.POST)
	public ModelAndView memberFindPwProc(String login, String email) {
		ModelAndView mav = new ModelAndView();
//		mav.addObject("mbemail", email);				
//		mav.setViewName("member/mb_result_pw");
		MemberVO mb = mbSvc.selectOneMember(login, email);
		if (mb == null) {
			System.out.println("존재하지 않는 회원 이거나 올바른 정보를 기입해 주세요.");
			mav.addObject("msg", "존재하지 않는 회원 이거나 올바른 정보를 기입해 주세요.");
			mav.setViewName("member/mb_find_idpw");
		} else { // 회원 존재
			String newPassword = mbSvc.makeTempoPw(); // 12자리 비밀번호 생성
			mb.setPassword(newPassword);
			boolean b = mbSvc.updateOneMemberByLogin(mb); // 임시발급 번호로 업데이트
			if (b) {
				String title = "애플 샵 임시 비밀번호 발급 안내";
				StringBuffer sb = new StringBuffer();
				sb.append("요청하신 비밀번호 결과 ");
				sb.append("임시 비밀번호 :" + newPassword + "입니다");
				sb.append("반드시 로그인 후 마이페이지 내에서 새로운 비밀번호로 변경 하시기 바람니다.");
				String content = "임시 비밀번호 :" + newPassword + "입니다";
				boolean mailCheck = mail.gmailSend(title, content, email); // 이메일 보내기
				if (mailCheck) {
					System.out.println("이메일 전송 성공");
					mav.addObject("msg", "입력하신 이메일로 임시 비밀번호를 발송 했습니다 .");
					mav.addObject("mbemail", email);
					mav.setViewName("member/mb_result_pw");
					return mav;
				} else {
					System.out.println("구글 보안 걸어놈 이메일 전송 실패");
					mav.addObject("msg", "이메일 발송 실패 , 고객센터 문의바람 ");
					mav.setViewName("member/mb_find_idpw");
					return mav;
				}

			} else {

			}

		}
		return mav;

	}

//- 마이페이지 회원 정보를 상세보기 및 수정 할 수 있다.
//  mypage_main.ap;
	@RequestMapping(value = "mypage_main.ap", method = RequestMethod.GET)
	public String mypageMain(HttpSession ses, Model model) {
		String checkSes = "";
		checkSes = (String) ses.getAttribute("mbName");
		if (checkSes == null || checkSes.isEmpty()) {
			System.out.println("로그인 필요");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "member/mb_signin";
		} else {
			System.out.println("세션확인 완료 마이페이지로 이동");
			return "mypage/mp_main";
		}

	}

//	mypage_member_info.ap get dao  인증,
	@RequestMapping(value = "mypage_member_info.ap", method = RequestMethod.GET)
	public ModelAndView mypageMemberInfo(HttpSession ses) {
		ModelAndView mav = new ModelAndView();
		int id = (Integer) ses.getAttribute("mbId");
		String checkSes = "";
		checkSes = (String) ses.getAttribute("mbName");
		if (checkSes == null || checkSes.isEmpty()) {
			System.out.println("로그인 세션 문제 해결필요");
			mav.addObject("msg", "로그인이 필요한 서비스입니다.");
			mav.setViewName("member/mb_signin");
			return mav;
		} else { // 세션 존재 멤버정보 속성값주고 나오게하기
			MemberVO mb = mbSvc.selectOneMember(id);
			if (mb != null) {
				mav.addObject("member", mb);
				mav.setViewName("mypage/mp_mb_info");
				return mav;
			} else {
				System.out.println("존재하는 회원이 없습니다.");
				mav.addObject("msg", "존재하는 회원이 없습니다.");
				return mav;
			}
		}

	}

//	member_info_update.ap ; post proc dao 세션, 인증, 회원
	@RequestMapping(value = "member_info_update.ap", method = RequestMethod.POST)
	public String mypageMemberInfoProc(HttpServletRequest request, Model model) {

		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		String login = request.getParameter("login");

//		int id = (Integer) ses.getAttribute("mbId");
		boolean b = mbSvc.updateOneMemberByLogin2(name, birthday, email, phone, zipcode, address, addressDetail, login);
		if (b) {
			MemberVO mb = mbSvc.selectOneMember(login);
			model.addAttribute("member", mb);
			model.addAttribute("msg", "회원수정 성공");
			return "mypage/mp_mb_info";
		} else {

			model.addAttribute("msg", "회원 수정 실패");
			return "mypage/mp_mb_info";
		}

	}

// 마이페이지 회원 내주소를 설정 할 수 있다.
	@RequestMapping(value = "mypage_member_address_info.ap", method = RequestMethod.GET)
	public String mypageMemberAddressInfo(HttpSession ses, Model model) {
		System.out.println("어드레스 추가 폼");
		int id = (Integer) ses.getAttribute("mbId");
		if (id <= 0) {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다");
			return "member/mb_signin";
		} else {
			MemberVO mb = mbSvc.selectOneMember(id);
			model.addAttribute("member", mb);
			return "mypage/mp_mb_address";
		}

	}

	@RequestMapping(value = "mypage_member_address_info_proc.ap", method = RequestMethod.POST)
	public String mypageMemberAddressInfoProc(MemberVO mb, @RequestParam("zipcode") String zipcode,
			@RequestParam("address") String address, @RequestParam("addressDetail") String addressDetail, Model model) {

		boolean b = mbSvc.updateOneMemberByLogin2(mb.getName(), mb.getBirthday(), mb.getEmail(), mb.getPhone(), zipcode,
				address, addressDetail, mb.getLogin());
		if (b) {
			model.addAttribute("member", mb);
			model.addAttribute("msg", "주소 업데이트 성공");
			return "mypage/mp_mb_address";
		} else {
			model.addAttribute("msg", "주소 업데이트 실패");
			return "mypage/mp_mb_address";
		}

	}

//	mypage_member_address_info.ap
//- 마이페이지  회원 패스워드 변경 할 수 있다.
//	mypage_member_pw_info.ap get
	@RequestMapping(value = "mypage_member_pw_info.ap", method = RequestMethod.GET)
	public String mypageMemberLoginInfo() {
		System.out.println("비밀번호 변경 폼");
		return "mypage/mp_mb_logininfo";
	}

//	mypage_member_pw_info_proc.ap; post proc 암호화
	@RequestMapping(value = "mypage_member_pw_info_proc.ap", method = RequestMethod.POST)
	public ModelAndView mypageMemberLoginInfoProc(HttpServletRequest request, HttpSession ses) {
		ModelAndView mav = new ModelAndView();

		String currentPw = request.getParameter("currentpw");
		String changepw = request.getParameter("password");

		int id = (Integer) ses.getAttribute("mbId");
		String checkSes = "";
		checkSes = (String) ses.getAttribute("mbName");
		if (checkSes == null || checkSes.isEmpty()) {
			mav.addObject("msg", "로그인이 필요한 서비스 입니다.");
			mav.setViewName("redirect:member_join_form.ap");
			return mav;
		} else {
			MemberVO mb = mbSvc.selectOneMember(id);
			String dbpw = mbSvc.decryptPassword(mb.getLogin());
			if (!dbpw.equals(currentPw)) {
				System.out.println("입력한 현재 아이디의 비밀번호 다르므로 변경 불가");
				mav.addObject("msg", "현재 비밀번호 인증 실패");
				mav.setViewName("mypage/mp_mb_logininfo");
				return mav;
			} else { // 같은 경우 진행 한다~ 인증성공
				mb.setPassword(changepw);
				boolean b = mbSvc.updateOneMemberByLogin(mb); // 암호화포함!
				if (b) { // 성공
					System.out.println("비밀번호 변경성공");
					mav.addObject("msg", "비밀번호 변경성공");
					mav.setViewName("mypage/mp_mb_logininfo");
					return mav;
				} else {
					System.out.println("비밀번호 변경실패");
					mav.addObject("msg", "비밀번호 변경실패");
					mav.setViewName("mypage/mp_mb_logininfo");
					return mav;
				}

			}
		}
	}

	// -------------------- admin --------------------

//		회원을 검색 할 수 있다.
//		member_search.ap
	@RequestMapping(value = "member_search.ap", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView memberSearchProc(@RequestParam(value = "keyword", defaultValue = "test") String k,
			@RequestParam(value = "target", required = false, defaultValue = "title") String target,
			@RequestParam(value = "pg", defaultValue = "1", required = false) int pg
	// pg
	// order
	) {
		System.out.println(">> keyword: " + k);
		List<MemberVO> mbList = mbSvc.searchMember(k, target, pg);
		ModelAndView mav = new ModelAndView("member/mb_admin");
		if (mbList != null) {
			int mbSize = mbList.size();
			mav.addObject("msg", "회원 검색 성공: " + mbSize + "개");
			mav.addObject("mbList", mbList);
			mav.addObject("mbSize", mbSize); // 0개 이상
			mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
			Map<String, Integer> rMap = mbSvc.checkMaxPageNumber(k, target);
			mav.addObject("maxPg", rMap.get("maxPg")); // 최대 페이지 번호
			mav.addObject("mbMaxSize", rMap.get("totalAtCnt")); // 총 검색일치 레코드 개수
			mav.setViewName("member/search_mb_form");
		} else {
			mav.addObject("msg", "회원 검색 실패"); // null
		}
		return mav;
	}

}
