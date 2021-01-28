<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
    #div_form_area{
        padding-left: 80px;
        padding-top: 80px;
    }
    .div_label{
        font-size: 15px;
    }
    .input_layout{
        width: 490px;
        height: 100px;
        display: inline-block;
    }
     .input_layout input[type=text] {
       border: 1px solid white;
        width: 350px;
         height: 25px;
        
    border-bottom-style: groove;
         border-bottom-color: beige;
    }
    .input_layout input[type=button]{
        width: 100px;
        height: 35px;
        background-color: black;
        color: white;
        font-weight: bolder;
    }
    .input_layout input[type=submit]{
        width: 200px;
        height: 40px;
        background-color: black;
        color: white;
    }
    #content_top{
        width: 900px;
        min-height: 800px;
    }
   
     .left_sidemenu {
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 20px;
        padding: 0px;
        margin: 0px;
        width: 300px;
        background-color: whitesmoke;
    }

     .left_sidemenu {
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 20px;
        padding: 0px;
        margin: 0px;
        width: 300px;
        background-color: whitesmoke;
    }

    .menubox1 ul {
        text-align: left;
        margin: 0px;
        padding: 0px;
        list-style: none;
    }

    .menubox1 ul li a {
        text-decoration: none;
        color: inherit;

    }

    .menubox1 ul li ul {
        display: none;
    }

    .menubox1 ul li:hover>ul {
        display: block;

    }

    .menubox1 ul li:hover>a {
        background-color: black;
        color: white;
        font-weight: bolder;


    }

    .menubox1 ul ul {
/*        background-color: #afafaf;*/
        background-color: white;
    }

    .menubox1 a {
         text-decoration: none;
    }

    .menubox1 ul li {
        padding: 20px;
    }

    /*#mypage_contents_layout {
        position: relative;
        left: 305px;
        top: -290px;
    }*/
    #grid_mypage {
        width: 1600px;
        display: grid;
        grid-template-columns: 305px 1090px;
        grid-gap: 100px;
    }

    #show_mypageList ul {
        list-style: none;
    }
    </style>
<body>
   <div id="grid_mypage">
        <div class="left_sidemenu">
            <nav class="menubox1">
                <ul>
                    <li><a href="">내 계정</a>
                        <ul>
                            <li><a href="personalInfo.html">나의 정보</a></li>
                            <li><a href="personallogininfor.html">로그인 정보변경</a></li>
                        </ul>
                    </li>
                    <li><a href="">배송/주문</a>
                        <ul>
                            <li><a href="">나의 주문내역</a></li>
                            <li><a href="">a</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                        </ul>
                    </li>
                    <li><a href="">내 게시판</a>
                        <ul>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                        </ul>
                    </li>
                    <li><a href="">장바구니</a></li>
                </ul>
            </nav>
        </div>

        <div id="mypage_contents_layout">
<div>
        <h1>나의 정보</h1>
        <p>추후 용이한 구매 및 연락처 정보 변경 내용을 당사에 </p>
        <p>통지하기 위한 귀하의 개인정보(이름, 등록 주소, 전화번호 등)에 접근, 수정이 가능합니다.
        </p>

    </div>
    <hr>
    <div>
       <div id="div_form_area">
        <form action="" method="post">
          
            <div class="input_layout">
              <div class="div_label">
                <label for="personal_name">회원(실명)</label></div>
                <input type="text" name="name" id="personal_name">
            </div>
               
                <div class="input_layout">
                 <div class="div_label"><label for="login">아이디</label></div>
                <input type="text" name="name" id="login" readonly>
            </div>
            <div class="input_layout">
               <div class="div_label">
                <label for="birthday">생년월일</label></div>
                <input type="text" name="birthday" id="birthday" placeholder="8자리입력" onchange="">
            </div>
            <div class="input_layout">
               <div class="div_label">
                <label for="phone">휴대폰번호</label></div>
                <input type="text" name="phone" id="phone" placeholder=" ' - ' 없이 입력" onchange="">
            </div>
            <div class="input_layout">
                <div class="div_label">
                <label for="email">이메일</label>
                </div>
                <input type="text" name="email" id="email" placeholder="이메일 입력" onchange="">
            </div>
            <div class="input_layout">
                <div class="div_label"> <label for="sample6_postcode">우편번호</label></div>
                <input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode">
                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호" size="">
            </div>
            <div class="input_layout">
                <div class="div_label"> <label for="sample6_address">주소</label></div>
                <input type="text" id="sample6_address" placeholder="주소" name="address">
            </div>
            <div class="input_layout">
                 <div class="div_label"><label for="sample6_detailAddress">상세주소</label></div>
                <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address2">
            </div>
            <div class="input_layout"> <input type="submit" value="회원정보수정"></div>
        </form>
        </div>
    </div>
         


        </div>
    </div>
    
</body></html>