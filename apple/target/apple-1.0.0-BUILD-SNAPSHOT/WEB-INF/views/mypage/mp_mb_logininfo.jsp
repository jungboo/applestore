<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/css/mypage_css/mypage.css"/>">
<head>
<meta charset="UTF-8">
</head>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<script type="text/javascript">
		function pwchange_confirm(){
			confirm("회원 비밀번호 변경 하시겠습니까?");
		}
	</script>
<script type="text/javascript">
	var pwa = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
	function mppwcheck() {
		if (pwa.test($('#changepw').val())) {
			$("#checkOnepw").text('');
		} else {
			$('#checkOnepw').text('8자이상 영문/숫자/특수문자포함 입력필요');
			$('#checkOnepw').css('color', 'red');
		}
	};
	function mprepwcheck() {
		if (document.getElementById('changepw').value != ''
				&& document.getElementById('password').value != '') {
			if (document.getElementById('changepw').value == document
					.getElementById('password').value) {
				$('#checkTwopw').text('');
			} else {
				$('#checkTwopw').text('비밀번호 불일치');
				$('#checkTwopw').css('color', 'red');
			}
		} else {
			$('#pw2_check').text('');
		}
	};
	function mppwvalidate() {
		if (document.getElementById('currentpw').value == ''
				|| document.getElementById('changepw').value == ''
				|| document.getElementById('password').value == '') {
			alert('올바른 정보를 기입해 주세요');
			return false;
		}
		// 비밀번호가 같은 경우 && 비밀번호 정규식
		if (($('#changepw').val() == ($('#password').val()))
				&& pwa.test($('#password').val())) {
			return true;
		} else {
			alert('올바른 정보를 기입해 주세요');
			return false;
		}

	}
</script>

</style>

<body>
	<%@ include file="../common/_header.jsp"%>
	<div id="grid_mypage">
		<%@ include file="../common/mypage_nav.jsp"%>

		<div id="mypage_contents_layout">
			<div>
				<h1 style="margin-bottom : 20px;">비밀번호 변경</h1>
				<p>회원 로그인시 필요한 비밀번호의 변경이 가능합니다.</p>
			</div>
			<hr>

			<div id="div_pw_form_area">
				<form action="mypage_member_pw_info_proc.ap" method="post"
					onsubmit="return mppwvalidate()">
					<div class="pwbox_layout">
						<div class="pw_label_layout">
							<label for="">현재 비밀번호</label>
						</div>
						<div>
							<input type="password" name="currentpw" id="currentpw">
						</div>
						<div class="checkPw" class="mypage_pw_check"></div>
					</div>
					<div class="pwbox_layout">
						<div class="pw_label_layout">
							<label for="">변경 비밀번호</label>
						</div>
						<div>
							<input type="password" name="changepw" id="changepw"
								onchange="mppwcheck()" placeholder="8자이상 영문/숫자/특수문자포함 입력필요">
						</div>
						<div id="checkOnepw" class="mypage_pw_check"></div>
					</div>
					<div class="pwbox_layout">
						<div class="pw_label_layout">
							<div>
								<label for="">변경 비밀번호 확인</label>
							</div>
							<div>
								<input type="password" name="password" id="password"
									onchange="mprepwcheck()" placeholder="변경 비밀번호 재입력">
							</div>
							<div id="checkTwopw" class="mypage_pw_check"></div>
						</div>
					</div>

					<div class="pwbox_layout">
						<input type="submit" value="비밀번호 변경" id="pwchange_submit"
							onclick="pwchange_confirm()">
					</div>
				</form>
			</div>



		</div>
	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>