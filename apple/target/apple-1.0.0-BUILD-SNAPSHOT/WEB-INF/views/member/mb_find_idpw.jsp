<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/member_css/member.css"/>">

<script type="text/javascript">
	//이름 정규식
	var namea = /^[가-힣]{2,6}$/;
	//이메일 검사 정규식
	var maila = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	//휴대폰 번호 정규식
	var phonea = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

// 	function clickid(){
// 		document.getElementById('tabid').style.backgroundColor="black";
// 		document.getElementById('tabid').style.color="white";
// 		document.getElementById('tabpw').style.backgroundColor="white";
// 		document.getElementById('tabpw').style.color="black";
// 	}
// 	function clickpw(){
// 		document.getElementById('tabpw').style.backgroundColor="black";
// 		document.getElementById('tabpw').style.color="white";
// 		document.getElementById('tabid').style.backgroundColor="white";
// 		document.getElementById('tabid').style.color="black";
// 	}
	function checkfindid() {
		var objName1 = document.getElementById("name");
		var objPhone1 = document.getElementById("phone");

		if (objName1.value == "") {
			alert("회원 이름을 입력해주세요");
			return false;
		}
		if (!namea.test(objName1.value)) {
			alert("올바른 이름을 입력해주세요");
			return false;
		}
		if (objPhone1.value == "") {
			alert("휴대폰번호를 '-'없이 입력");
			return false;
		}
		if (!phonea.test(objPhone1.value)) {
			alert("휴대폰번호를 '-'없이 입력 ex)01012345678");
			return false;
		}

		document.findidform.submit();

	};
	function checkfindpw() {
		var objLogin1 = document.getElementById("login");
		var objEmail1 = document.getElementById("email");

		if (objLogin1.value == "") {
			alert("회원 아이디를 입력해주세요");
			return false;
		}
		if (objEmail1.value == "") {
			alert("회원 이메일을 입력해주세요");
			return false;
		}
		if (!maila.test(objEmail1.value)) {
			alert("올바른 이메일을 입력 해 주세요");
			return false;
		}

		document.findpwform.submit();

	};
	$(document).ready(function() {

		$('.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');

			// 			$('.tabs li').removeClass('current');
			$('.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#" + tab_id).addClass('current');
		});
		$('#tabpw').click(function() {
			$('#tabid').css('color','black');
			$('#tabid').css('background-color','white');
		});
	});
</script>

<style type="text/css">

</style>

<%@ include file="../common/_header.jsp"%>

<div id="find_wrapper">
	<div id="find_nav">
		<ul class="tabs">
			<li class="tab-link" data-tab="tab1" id="tabid">아이디 찾기 <i
				class="fas fa-search"></i></li>
			<li class="tab-link" data-tab="tab2">비밀번호 찾기 <i
				class="fas fa-key" id="tabpw" ></i></li>

		</ul>
	</div>
	<div id="content_wrapper">
		<div id="tab1" class="tab-content current"
			style="background-color: white;">
			<!-- #ededed -->

			<h2>ID 찾기</h2>
			<br> <label>가입시 등록한 정보를 입력 해주세요.</label>
			<hr>

			<form action="member_find_id_proc.ap" method="post"
				onsubmit="return checkfindid()" name="findidform">

				<table>
					<tr>
						<th>이름 (실명)</th>
						<td><input type="text" size="50" name="name" id="name"
							onchange=""></td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td><input type="text" placeholder="휴대폰번호 '-'없이 입력" size="50"
							name="phone" id="phone"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="아이디찾기" onchange=""></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="tab2" class="tab-content">
			<h2>패스워드 찾기</h2>
			<br> 가입시 등록한 정보를 입력 해주세요.
			<hr>

			<form action="member_find_pw_proc.ap" method="post"
				onsubmit=" return checkfindpw()" name="findpwform">
				<table>
					<tr>
						<th>회원 아이디</th>
						<td><input type="text" placeholder="" size="50" name="login"
							id="login" required="required"></td>
					</tr>
					<tr>
						<th>회원 이메일</th>
						<td><input type="email" placeholder="" size="50" name="email"
							id="email" required="required"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value='비밀번호찾기'></td>
					</tr>
				</table>
			</form>
		</div>

	</div>
</div>
<%@ include file="../common/_footer.jsp"%>
