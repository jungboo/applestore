<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/css/mypage_css/mypage.css"/>">
<head>
<meta charset="UTF-8">
</head>
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

<script type="text/javascript">
function memberInfo_confirm(){
   confirm('주소를 업데이트 하시겠습니까?');
};

function checkaddress(){
   if($("#sample6_postcode").val = ""){
      alert('우편번호를 조회해 주세요');
      return false;
   }
   if($("#sample6_address").val = ""){
      alert('주소를 입력해 주세요.');
      return false;
   }
   if($("#sample6_detailAddress").val = ""){
      alert('상세 주소를 입력해 주세요.');
      return false;
   }
   
}
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
                    //                         document.getElementById("sample6_extraAddress").value = extraAddr;

                } else {
                    //                         document.getElementById("sample6_extraAddress").value = '';
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
<body>
<%@ include file="../common/_header.jsp"%>
<div id="grid_mypage">
   <%@ include file="../common/mypage_nav.jsp"%>

   <div id="mypage_contents_layout">
      <div>
         <h1 style="margin-bottom : 20px;">주소 설정</h1>
         <p>결제시 빠른 결제를 위한 메인 주소지 설정 및 수정이 가능 합니다.</p>
      </div>
      <hr>
      <div>
         <div id="div_form_area">
            <form action="mypage_member_address_info_proc.ap" method="post" onsubmit="return checkaddress()">

               <div class="input_layout hidden">
                  <div class="div_label">
                     <label for="personal_name">회원(실명)</label>
                  </div>
                  <input type="text" name="name" id="mpname"
                     value="${member.name}" required onchange="mpnamecheck()">
                  <div id="mp_name_check" class="mp_check_area"></div>
               </div>

               <div class="input_layout hidden">
                  <div class="div_label">
                     <label for="login">아이디</label>
                  </div>
                  <input type="text" name="login" id="mplogin" readonly
                     value="${member.login}">
               </div>
               <div class="input_layout hidden">
                  <div class="div_label">
                     <label for="birthday">생년월일</label>
                  </div>
                  <input type="text" name="birthday" id="mpbirthday"
                     placeholder="8자리입력" onchange="" value="${member.birthday}"
                     required onchange="mpbirthcheck()">
                  <div id="mp_birthday_check"></div>
               </div>
               <div class="input_layout hidden">
                  <div class="div_label">
                     <label for="phone">휴대폰번호</label>
                  </div>
                  <input type="text" name="phone" id="mpphone"
                     placeholder=" ' - ' 없이 입력" onchange="" value="${member.phone}"
                     required onchange="mpphonecheck()">
                  <div id="mp_phone_check"></div>
               </div>
               <div class="input_layout hidden">
                  <div class="div_label">
                     <label for="email">이메일</label>
                  </div>
                  <input type="email" name="email" id="mpemail" placeholder="이메일 입력"
                     onchange="" value="${member.email}" required>
                  <div id="mp_email_check"></div>
               </div>
               <div class="input_layout">
                  <div class="div_label">
                     <label for="sample6_postcode">우편번호</label>
                  </div>
                  <input type="text" id="sample6_postcode" placeholder="우편번호"
                     name="zipcode" value="${member.zipcode}"> <input
                     type="button" onclick="sample6_execDaumPostcode()" value="우편번호" required>
               </div>
               <div class="input_layout">
                  <div class="div_label">
                     <label for="sample6_address">주소</label>
                  </div>
                  <input type="text" id="sample6_address" placeholder="주소"
                     name="address" value="${member.address}" required>
               </div>
               <div class="input_layout">
                  <div class="div_label">
                     <label for="sample6_detailAddress">상세주소</label>
                  </div>
                  <input type="text" id="sample6_detailAddress" placeholder="상세주소"
                     name="addressDetail" value="${member.addressDetail}" required>
               </div>
               <div class="input_layout">
                  <input id="pw_update_button" type="submit" value="주소 업데이트" onclick="memberInfo_confirm()">
               </div>
            </form>
         </div>
      </div>



   </div>
</div>
<%@ include file="../common/_footer.jsp"%>
</body>
</html>