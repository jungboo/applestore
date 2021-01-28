## 요청 URL-mapping (매핑)
- / (default.my); get 비회원  => welcomefilelist 
	main

//	- 가입 할 수  있다. (패스워드 암호화 저장)
//	member_join_agreement.ap; get 
//	member_join_agreement_proc.ap; post 
	
//	member_join_form.ap; get form 
//	member_join_proc.ap; post proc dao 
//- 로그인 중복체크 할 수 있다.
//	member_dupcheck.ap; get proc dao ajax 
//- 로그인 인증 할 수  있다. (세션, 암호화 인증)
//	member_login_form.ap; get form 비회원
//	member_login_proc.ap; post proc dao 암호화, 세션, 회원
//- 로그아웃 할 수  있다.
//	member_logout.ap; get proc dao/세션, 회원
//- 회원 아이디 를 찾을 수 있다.
//	member_find_idpw.ap; get 
//	member_find_id_proc.ap; post dao
//- 회원 비밀번호를 찾을 수 있다.
//	member_find_idpw.ap; get 
//	member_find_pw_proc.ap; post  dao 암호화 이메일 전송
//- 마이페이지 회원 정보를 상세보기 및 수정 할 수 있다.
//  mypage_main.ap;
//	mypage_member_info.ap?id=x; get proc dao  인증,
//	member_info_update_proc.ap ; post proc dao 세션, 인증, 회원
//- 마이페이지  회원 패스워드 변경 할 수 있다.
//	mypage_member_pw_info.ap?id=x;
//	mypage_member_pw_info_proc.ap; post proc 암호화