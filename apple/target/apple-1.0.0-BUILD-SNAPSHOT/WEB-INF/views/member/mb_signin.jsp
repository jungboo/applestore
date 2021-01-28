<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/resources/css/member_css/member.css"/>">

<style type="text/css">

</style>

<script type="text/javascript">
	function checksignin() {
		var objlogin = document.getElementById("login");
		var objpw = document.getElementById("password");

		if (objlogin.value == "") {
			alert("회원 아이디를 입력 해주세요!!");
			return false;
		}
		if (objpw.value == "") {
			alert("회원 비밀번호를 입력 해주세요!!");
			return false;
		}
		document.loginform.submit();

	};
</script>

<meta charset="UTF-8">
<title>로그인 페이지</title>



</head>
<body>
	<%@ include file="../common/_header.jsp"%>
	<div id="login_wrapper">
		<div id="div_join_content">
			<!-- 		    <h1>LOGIN</h1> -->
			<i class="fab fa-apple fa-7x"></i>

			<form action="member_login_proc.ap" method="post"
				onsubmit="return checksignin()" name="loginform">
				<table>
					<tr>

						<td><input type="text" class="input_class"
							placeholder="아이디입력" name="login" id="login"></td>
					</tr>
					<tr>

						<td><input type="password" class="input_class"
							placeholder="비밀번호 입력" name="password" id="password"></td>
					</tr>

					<tr>

						<td><input type="submit" id="input_class_submit" value="로그인"></td>

					</tr>
				</table>
			</form>
		</div>
		<div id="pwjoinlist">
			<ul>
				<li><a href="member_join_agreement.ap">회원가입</a></li>
				<li><a href="member_find_idpw.ap">아이디/비밀번호 찾기</a></li>
			</ul>

		</div>


	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>