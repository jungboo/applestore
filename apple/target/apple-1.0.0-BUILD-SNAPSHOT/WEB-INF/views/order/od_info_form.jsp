<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/cart_css/wonwoo_style.css"/>">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<script type="text/javascript">
   
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
   





<title>결제 페이지</title>
</head>
<body>

   <%@ include file="../common/_header.jsp" %>
   
   <form action="${pageContext.request.contextPath}/order_final_proc.ap" method="post">
         
   
   
   <div id="order_main">
         
      
      <div id="buyer_info">
         <b style="font-size: 30px">구매자 정보</b>
         <table id="buyer_table">
            <tr>
               <td class="buyer_header">이름</td>
               <td class="buyer_data">${mb.name}</td>
            </tr>
            
            <tr>
               <td class="buyer_header">이메일</td>
               <td class="buyer_data">${mb.email}</td>
            </tr>
            
            <tr>
               <td class="buyer_header">휴대폰 번호</td>
               <td class="buyer_data">${mb.phone}</td>
            </tr>
         
         </table>
         

      </div>
      
      
      <div id="receiver_info">
         <b style="font-size: 30px">수령자 정보</b>
         <table id="receiver_table">
            <tr>
               <td class="receiver_header">이름</td>
               <td class="receiver_data">${mb.name}</td>
            </tr>

            <tr>
               <td class="receiver_header">배송주소</td>
               <td class="receiver_data">
                  
                  <div class="input_layout">
                  <div class="div_label">
                     <label for="sample6_postcode">우편번호</label>
                  </div>
                  <input type="text" id="sample6_postcode" placeholder="우편번호"
                     name="zipcode" value="${member.zipcode}"> <input
                     type="button" onclick="sample6_execDaumPostcode()" value="우편번호">
               </div>
               <div class="input_layout">
                  <div class="div_label">
                     <label for="sample6_address">주소</label>
                  </div>
                  <input type="text" id="sample6_address" name="address1"  placeholder="주소"
                     name="address" value="${member.address eq null ? '' : member.address}" >
               </div>
               <div class="input_layout">
                  <div class="div_label">
                     <label for="sample6_detailAddress">상세주소</label>
                  </div>
                  <input type="text" id="address2" name="address2" placeholder="상세주소"
                     name="addressDetail" value="${member.addressDetail eq null ? '' : member.addressDetail}">
               </div>
               
               </td>
            </tr>
            
            <tr>
               <td class="receiver_header">휴대폰 번호</td>
               <td class="receiver_data">${mb.phone}</td>
            </tr>

            <tr>
               <td class="receiver_header">배송 요청사항</td>
               <td class="receiver_data">
                  <select id="demand">
                     <option value="경비실" selected> 경비실 </option>
                     <option value="문 앞" > 문 앞 </option>
                     <option value="택배함" >택배함</option>
                     <option value="직접 수령" >직접 수령</option>
                  </select>
               </td>
            </tr>
         
         </table>
      </div>
      
      <div id="purchase_info">
         <b style="font-size: 30px">결제 정보</b>
         <table id="purchase_table">
            
            <tr>
               <td class="purchase_header">총상품가격</td>
               <td class="purchase_data">${totalPrice}원</td>
            </tr>
            
            <tr>
               <td class="purchase_header">할인쿠폰</td>
               <td class="purchase_data" style="color:red">0 원 </td>
            </tr>
            
            <tr>
               <td class="purchase_header">배송비</td>
               <td class="purchase_data">2500원</td>
            </tr>

            <tr>
               <td class="purchase_header">총결제금액</td>
               <td class="purchase_data">${totalPrice+2500}원</td>
            </tr>

            <tr>
               <td class="purchase_method_header">결제 방법</td>
               <td class="purchase_method_data">
                  <div>
                     <input type="radio" name="purchase_method" value="계좌이체"> 계좌이체
                     <input type="radio" name="purchase_method" 
                     value="신용 /체크카드" checked="checked"> 신용/체크카드 
                     <input type="radio" name="purchase_method" value="휴대폰"> 휴대폰
                     <input type="radio" name="purchase_method" value="무통장입금"> 무통장입금
                  </div>
                  <br> <br>
                  <input name="credit_card1" type="number" max="9999"> -
                  <input name="credit_card2" type="number" max="9999"> -
                  <input name="credit_card3" type="number" max="9999"> -
                  <input name="credit_card4" type="number" max="9999">
               </td>
            </tr>
            
         
         </table>

         <div id="buttonGroup">
            
         <input id="return_cart" type="reset" value="장바구니로 돌아가기">
   
         <input id="purchase_final" type="submit" value="결제하기">
         
         </div>

      </div>
      
      
            
      
      <input type="hidden" name="totalPrice" value="${totalPrice}">
      <input type="hidden" name="totalPrice" value="${mb.address1}">
      <input type="hidden" name="totalPrice" value="${mb.address2}">
   
      <c:forEach var="ct" items="${cart}" varStatus="status">   
         
      <input type="hidden" name="prIds" value="${ct.prId}">
      <input type="hidden" name="counts" value="${ct.count}">
      
      
      </c:forEach>
   
   </div>

      
   </form>   
   <%@ include file="../common/_footer.jsp" %>
   
   
   
</body>
</html>