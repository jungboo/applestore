<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/css/member_css/member.css"/>">
<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style type="text/css">

</style>






<script type="text/javascript">
//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var idJ = /^[a-zA-Z0-9]{4,12}$/;
// 비밀번호 정규식
var pwJ = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/; //영문숫가 특문 포함 8자 이상~
// 이름 정규식
var nameJ = /^[가-힣]{2,6}$/;
// 이메일 검사 정규식
var mailJ =/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

var checkdupstatus= 0; // 중복체크 해야지만 회원가입 가능허다~
function checkDup(){
// 	if($('#dupcheck'))
	
	 if (idJ.test($('#user_id').val())){
// 		 location.href = 'member_dupcheck.ap?login='+$('#user_id').val();
		 $.ajax({
		        type		: "POST",
		        url 		: "member_dupcheck.ap?login="+$('#user_id').val(),
		       
		        success 	: function(data) {
		        	console.log(data);
		        	if(data == 1){
		        		alert('중복 아이디입니다.');
		        	}else{
		        		alert('사용가능한 아이디 입니다.');
		        		checkdupstatus=1;
		        	}
		        },
		        error		: function(error) {
		        	console.log(error);
		        }
		    });

	 }else{
		 alert('영문자숫자 4~12자 입력필요');
	 }
	
}

function logincheck() {
	 if (idJ.test($('#user_id').val())) {
	        $("#id_check").text('');
	    } else {
	        $('#id_check').text('영문자숫자 4~12자 입력필요 ex)abc123');
	        $('#id_check').css('color', 'red');
	    }
}

function pwcheck() {
	 if (pwJ.test($('#user_pw').val())) {
	        $("#pw_check").text('');
	    } else {
	        $('#pw_check').text('8자이상 영문/숫자/특수문자포함 입력필요');
	        $('#pw_check').css('color', 'red');
	    }
}
function repwcheck() {
	if(document.getElementById('user_pw').value!='' && document.getElementById('user_pw2').value!='') {
        if(document.getElementById('user_pw').value==document.getElementById('user_pw2').value) {
        	 $('#pw2_check').text('');
        }
        else {
        	 $('#pw2_check').text('비밀번호 불일치');
    		 $('#pw2_check').css('color', 'red');
        }
    }else{
    	 $('#pw2_check').text('');
    }
// 	/* if(($('#user_pw').val() != ($('#user_pw2').val())))  {
// 		 $('#pw2_check').text('비밀번호 불일치');
// 		 $('#pw2_check').css('color', 'red');
// 	}else{
// 		 $('#pw2_check').text('');
// 	} */
}
// 이름에 특수문자 들어가지 않도록 설정
function namecheck() {
	 var name = document.getElementById('user_name').value;
	 if (nameJ.test(name)){
		 document.getElementById('name_check').innerHTML='';
	 }else{
		 document.getElementById('name_check').innerHTML='<span> 이름을 확인해 주세요 ex)홍길동 </span>';
	 }
}
//이메일
// function emailcheck(){
// 	if(mailJ.test($('user_email').val())){
// 		 $("#email_check").text('');
// 	}else{
// 		 $('#email_check').text('이메일주소를 확인해주세요  ex) abc123@abc.com');
// 		 $('#email_check').css('color', 'red');
// 	}
// }
// 휴대전화
function phonecheck(){
    if (phoneJ.test($('#user_phone').val())) {
        $("#phone_check").text('');
    } else {
        $('#phone_check').text('휴대폰번호를 확인해주세요 ex) 01012345678');
        $('#phone_check').css('color', 'red');
    }
}
// 생일 유효성 검사
var birthJ = false;

// 생년월일	birthJ 유효성 검사
function birthcheck() {
    var dateStr = $('#user_birth').val();
    var year = Number(dateStr.substr(0, 4)); // 입력한 값의 0~4자리까지 (연)
    var month = Number(dateStr.substr(4, 2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
    var day = Number(dateStr.substr(6, 2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
    var today = new Date(); // 날짜 변수 선언
    var yearNow = today.getFullYear(); // 올해 연도 가져옴

    if (dateStr.length <= 8) {
        // 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
        if (1900 > year || year > yearNow) {

        	$('#birth_check').text('생년월일을 확인해주세요 ex) 19900415');
            $('#birth_check').css('color', 'red');

        } else if (month < 1 || month > 12) {

        	$('#birth_check').text('생년월일을 확인해주세요 ex) 19900415');
            $('#birth_check').css('color', 'red');

        } else if (day < 1 || day > 31) {

        	$('#birth_check').text('생년월일을 확인해주세요 ex) 19900415');
            $('#birth_check').css('color', 'red');

        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {

        	$('#birth_check').text('생년월일을 확인해주세요 ex) 19900415');
            $('#birth_check').css('color', 'red');

        } else if (month == 2) {

            var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

            if (day > 29 || (day == 29 && !isleap)) {

                $('#birth_check').text('생년월일을 확인해주세요 ex) 19900415');
                $('#birth_check').css('color', 'red');

            } else {
                $('#birth_check').text('');
                birthJ = true;
            } //end of if (day>29 || (day==29 && !isleap))

        } else {

            $('#birth_check').text('');
            birthJ = true;
        } //end of if

    } else {
        //1.입력된 생년월일이 8자 초과할때 :  auth:false
        $('#birth_check').text('생년월일을 확인해주세요 ex) 19900415');
        $('#birth_check').css('color', 'red');
    }
}
	// 가입하기 실행 버튼 유효성 검사!
	var inval_Arr = new Array(5).fill(false);
	function join_validation(){ 
	    // 비밀번호가 같은 경우 && 비밀번호 정규식
	    if (($('#user_pw').val() == ($('#user_pw2').val())) &&
	        pwJ.test($('#user_pw').val())) {
	        inval_Arr[0] = true;
	    } else {
	        inval_Arr[0] = false;
	    }
	    // 이름 정규식
	    if (nameJ.test($('#user_name').val())) {
	        inval_Arr[1] = true;
	    } else {
	        inval_Arr[1] = false;
	    }
	    // 이메일 정규식
	    if (mailJ.test($('#user_email').val())) {
	        console.log(phoneJ.test($('#user_email').val()));
	        inval_Arr[2] = true;
	    } else {
	        inval_Arr[2] = false;
	    }
	    // 휴대폰번호 정규식
	    if (phoneJ.test($('#user_phone').val())) {
	        console.log(phoneJ.test($('#user_phone').val()));
	        inval_Arr[3] = true;
	    } else {
	        inval_Arr[3] = false;
	    }
	    // 생년월일 정규식
	    if (birthJ) {
	        console.log(birthJ);
	        inval_Arr[4] = true;
	    } else {
	        inval_Arr[4] = false;
	    }
	
	    var validAll = true;
	    for (var i = 0; i < inval_Arr.length; i++) {
	
	        if (inval_Arr[i] == false) {
	            validAll = false;
	        }
	    }
	    if(checkdupstatus ==0){
	    	alert('중복체크를 진행해 주세요');
	    	return false;
	    }
	
	    if (validAll) { // 유효성 모두 통과
		return true;
	
	    } else {
	        alert('입력한 정보들을 다시 한번 확인해주세요 :)')
	        return false;
	
	    }
	}
	function join_confirm(){
		confirm('회원 가입을 진행하시겠습니까?');
	}

 </script>





<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%@ include file="../common/_header.jsp"%>
	<div id="join_form_wrapper">
		<div id="step_status">
			<div class="smallbox">
				<strong>STEP1 약관동의</strong>

			</div>
			<div class="smallbox" style="background-color: black; color: white;">
				<strong>STEP2 정보입력</strong>
			</div>
		</div>
		<div id="div_form">
			<h2 style="text-align: center">회원정보 입력</h2>
			<form action="member_join_proc.ap" method="post" id="join_form_form"
				onsubmit="return join_validation()">
				<div id="form_margin_wapper">
					<div class="form-group">
						<div>
							<label for="user_id">아이디</label>
						</div>
						<div id="div_login">
							<input type="text" class="form-control" id="user_id" name="login"
								placeholder="영문숫자포함 4~12자 입력" required onchange="logincheck()" value="${member.login}">
						<input id="dupcheck" type="button" value="중복체크" onclick="checkDup()">
						</div>
						<div class="check_font" id="id_check"></div>
					</div>

					<div class="form-group">
						<div>
							<label for="user_pw">비밀번호</label>
						</div>
						<input type="password" class="form-control" id="user_pw" name="pw"
							placeholder="8자이상 영문/숫자/특수문자포함" required onchange="pwcheck()" value="${member.password}">
						<div class="check_font" id="pw_check"></div>
					</div>
					<!-- 비밀번호 재확인 -->
					<div class="form-group">
						<div>
							<label for="user_pw2">비밀번호 확인</label>
						</div>
						<input type="password" class="form-control" id="user_pw2"
							name="password" placeholder="비밀번호 재입력" required
							onchange="repwcheck()" value="${member.password}">
						<div class="check_font" id="pw2_check"></div>
					</div>
					<!-- 이름 -->
					<div class="form-group">
						<div>
							<label for="user_name">이름</label>
						</div>
						<input type="text" class="form-control" id="user_name" name="name"
							placeholder="Name" required onchange="namecheck()" value="${member.name}">
						<div class="check_font" id="name_check"></div>
					</div>
					<!-- 생년월일 -->
					<div class="form-group required">
						<div>
							<label for="user_birth">생년월일</label>
						</div>
						<input type="text" class="form-control" id="user_birth"
							name="birthday" placeholder="ex) 19900415" 
							onchange="birthcheck()" value="${member.birthday}" required="required">
						<div class="check_font" id="birth_check"></div>
					</div>
					<div class="form-group">
						<div>
							<label for="user_phone">휴대폰</label>
						</div>
						<input type="text" class="form-control" id="user_phone"
							name="phone" placeholder=" ex) 01012345678" required
							onchange="phonecheck()" value="${member.phone}">
						<div class="check_font" id="phone_check"></div>
					</div>
					<div class="form-group required">
						<div>
							<label for="user_email">이메일</label>
						</div>
						<input type="email" class="form-control" id="user_email"
							name="email" placeholder="abc123@abc.com" required
							value="${member.email}">
						<div class="check_font" id="email_check"></div>
					</div>
					<div id="div_resetAndSubmit">
						<input type="reset" value="다시입력" > <input
							type="submit" value="회원가입" id="reg_submit" onclick="join_confirm()">
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>
