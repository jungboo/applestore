<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/member_css/member.css"/>">
<meta charset="UTF-8">
<style type="text/css">

</style>
<script type="text/javascript">
	function redirect_login() {
		location.href = "member_login_form.ap";

	}

	function redirect_findpw() {
		location.href = "member_find_idpw.ap";
	}
</script>
</head>

<body>
	<%@ include file="../common/_header.jsp"%>
	<div id="div_result_id">
		<h2>아이디 조회 결과</h2>
		<hr>
		<div>
			<div style="border: 1px">
				<label class="result_label">저희 쇼핑몰을 이용해 주셔서 감사합니다.</label><br>
				<label class="result_label">다음 정보로 가입된 아이디가 총 1개 있습니다.</label>
			</div>
			<div id="result_id_area">
				<div id="div_text_center">
					<label for="">회원님의 아이디는 <b>${mbLogin}</b> 입니다.
					</label>
				</div>
			</div>
		</div>
		<div id="div_result_button">
			<input type="button" value="로그인" onclick="redirect_login()"><input
				type="button" value="비밀번호 찾기" onclick="redirect_findpw()">
		</div>
	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>