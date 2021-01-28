<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/css/mypage_css/mypage.css"/>">
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
<meta charset="UTF-8">
</head>


<script type="text/javascript">
function memberInfo_confirm(){
	confirm('회원 정보 수정 진행 하시겠습니까?');
};
//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var idJ = /^[a-zA-Z0-9]{4,12}$/;
// 비밀번호 정규식
var pwJ = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/; //영문숫가 특문 포함 8자 이상~
// 이름 정규식
var nameJ = /^[가-힣]{2,6}$/;

// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
var mailJ =/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

function validate_mp_update(){
	var namevalidate = document.getElementById('mpname').value;
	var emailvalidate= document.getElementById('mpemail').value;
	var phonevalidate = document.getElementById('mpphone').value;
	if(namevalidate == ""){
		alert('이름을 입력해 주세요');
		return false;
	}
	 if (!nameJ.test(namevalidate)){
		 alert('올바른 입력해 주세요 ex)홀길동');
		 return false;
	 }
	
	if(phonevalidate == ""){
		alert('휴대폰 번호를 입력해 주세요');
		return false;
	}
	if (!phoneJ.test(phonevalidate)){
		alert('기호없이 번호만 입력필요 ex) 01012345678');
		 return false;
	 }
	
	if(emailvalidate == ""){
		alert('이메일을 입력해주세요.');
		return false;
	}
	if (!mailJ.test(emailvalidate)){
		alert('이메일을 확인해 주세요.');
		 return false;
	 }
		document.mpinfoform.submit();
	
}

function mpnamecheck() {
	 var name = document.getElementById('mpname').value;
	 if (nameJ.test(name)){
		 document.getElementById('mp_name_check').innerHTML='';
	 }else{
		 document.getElementById('mp_name_check').innerHTML='<span style="color : red"> 이름을 확인해 주세요 ex)홍길동 </span>';
	 }
}

function mpphonecheck(){
	var phoneval = document.getElementById("mpphone");
    if (phoneJ.test(phoneval.value)) {
    	document.getElementById("mp_phone_check").innerHTML='';
    } else {
    	document.getElementById("mp_phone_check").innerHTML='<span style="color : red"> 기호없이 입력 ex)01012345678 </span>';
      
    }
}
function mpemailcheck(){
	var mail = document.getElementById("mpemail");
    if (mailJ.test(mail.value)) {
    	document.getElementById("mp_email_check").innerHTML='';
    } else {
    	document.getElementById("mp_email_check").innerHTML='<span style="color : red"> 이메일 입력 ex)asd123@naver.com</span>';
      
    }
}
// var birthA = false;
// function mpbirthcheck() {
// 	var birthdayval = document.getElementById("mpbirthday").value;
//     var dateStr = birthdayval;
//     var year = Number(dateStr.substr(0, 4)); // 입력한 값의 0~4자리까지 (연)
//     var month = Number(dateStr.substr(4, 2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
//     var day = Number(dateStr.substr(6, 2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
//     var today = new Date(); // 날짜 변수 선언
//     var yearNow = today.getFullYear(); // 올해 연도 가져옴

//     if (dateStr.length <= 8) {
//         // 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
//         if (1900 > year || year > yearNow) {

//         	$('#mp_birthday_check').text('생년월일을 확인해주세요 ex) 19900415');
//             $('#mp_birthday_check').css('color', 'red');

//         } else if (month < 1 || month > 12) {

//         	$('#mp_birthday_check').text('생년월일을 확인해주세요 ex) 19900415');
//             $('#mp_birthday_check').css('color', 'red');

//         } else if (day < 1 || day > 31) {

//         	$('#mp_birthday_check').text('생년월일을 확인해주세요 ex) 19900415');
//             $('#mp_birthday_check').css('color', 'red');

//         } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {

//         	$('#mp_birthday_check').text('생년월일을 확인해주세요 ex) 19900415');
//             $('#mp_birthday_check').css('color', 'red');

//         } else if (month == 2) {

//             var isLeap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

//             if (day > 29 || (day == 29 && !isLeap)) {

//                 $('#mp_birthday_check').text('생년월일을 확인해주세요 ex) 19900415');
//                 $('#mp_birthday_check').css('color', 'red');

//             } else {
//                 $('#mp_birthday_check').text('');
//                 birthA = true;
//             } //end of if (day>29 || (day==29 && !isleap))

//         } else {

//             $('#mp_birthday_check').text('');
//             birthA = true;
//         } //end of if

//     } else {
//         //1.입력된 생년월일이 8자 초과할때 :  auth:false
//         $('#mp_birthday_check').text('생년월일을 확인해주세요 ex) 19900415');
//         $('#mp_birthday_check').css('color', 'red');
//     }
// }
</script>

<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' &&
                        /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' &&
                        data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' +
                            data.buildingName :
                            data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    // 								document.getElementById("sample6_extraAddress").value = extraAddr;

                } else {
                    // 								document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress")
                    .focus();
            }
        }).open();
    };
</script>

<style type="text/css">

</style>
<bod>
<%@ include file="../common/_header.jsp"%>
<div id="grid_mypage">
	<%@ include file="../common/mypage_nav.jsp"%>

	<div id="mypage_contents_layout">
		<div>
			<h1 style="margin-bottom : 20px;">나의 정보</h1>
			<p>추후 용이한 구매 및 연락처 정보 변경 내용을 당사에</p>
			<p>통지하기 위한 귀하의 개인정보(이름, 등록 주소, 전화번호 등)에 접근, 수정이 가능합니다.</p>
			<p>' <b>*</b> ' 의 표시가 있는 정보만 수정 가능합니다.</p>

		</div>
		<hr>
		<div>
		
			<div id="div_form_area">
				<form action="member_info_update.ap" method="post" onsubmit="return validate_mp_update()" name="mpinfoform">

					<div class="input_layout">
						<div class="div_label">
							<label for="personal_name">회원(실명)*</label>
						</div>
						<input type="text" name="name" id="mpname"
							value="${member.name}" required onchange="mpnamecheck()">
						<div id="mp_name_check" class="mp_check_area"></div>
					</div>

					<div class="input_layout">
						<div class="div_label">
							<label for="login">아이디</label>
						</div>
						<input type="text" name="login" id="mplogin" readonly
							value="${member.login}">
					</div>
					
					<div class="input_layout">
						<div class="div_label">
							<label for="phone">휴대폰번호*</label>
						</div>
						<input type="text" name="phone" id="mpphone"
							placeholder=" ' - ' 없이 입력"  value="${member.phone}"
							required onchange="mpphonecheck()">
						<div id="mp_phone_check" class="mp_check_area"></div>
					</div>
					<div class="input_layout">
						<div class="div_label">
							<label for="birthday">생년월일</label>
						</div>
						<input type="text" name="birthday" id="mpbirthday"
							placeholder="8자리입력"  value="${member.birthday}"
							required onchange="mpbirthcheck()" readonly>
						<div id="mp_birthday_check" class="mp_check_area"></div>
					</div>
					<div class="input_layout">
						<div class="div_label">
							<label for="email">이메일*</label>
						</div>
						<input type="email" name="email" id="mpemail" placeholder="이메일 입력"
							onchange="" value="${member.email}" required  onclick="mpemailcheck()">
						<div id="mp_email_check" class="mp_check_area"></div>
					</div>
					<div class="input_layout address">
						<div class="div_label">
<!-- 							<label for="sample6_postcode">우편번호</label> -->
						</div>
						<input type="hidden" id="sample6_postcode" placeholder="우편번호"
							name="zipcode" value="${member.zipcode}"> <input
							type="hidden" onclick="sample6_execDaumPostcode()" value="우편번호">
					</div>
					<div class="input_layout address">
						<div class="div_label">
<!-- 							<label for="sample6_address">주소</label> -->
						</div>
						<input type="hidden" id="sample6_address" placeholder="주소"
							name="address" value="${member.address}">
					</div>
					<div class="input_layout address">
						<div class="div_label">
<!-- 							<label for="sample6_detailAddress">상세주소</label> -->
						</div>
						<input type="hidden" id="sample6_detailAddress" placeholder="상세주소"
							name="addressDetail" value="${member.addressDetail}">
					</div>
					<div class="input_layout">
						<input type="submit" value="회원정보수정" onclick="memberInfo_confirm()">
					</div>
				</form>
			</div>
		</div>



	</div>
</div>
<%@ include file="../common/_footer.jsp"%>
</body>
</html>
