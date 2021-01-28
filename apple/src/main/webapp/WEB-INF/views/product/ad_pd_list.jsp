<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp"%>

<link href="${pageContext.request.contextPath}/resources/css/style.css"
   rel="stylesheet" type="text/css">
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.page-btn {
   margin-top: 50px;
   margin: 20px auto 20px;
   text-align: center;
   font-size: 12px;
}

.page-btn-link {
   display: inline-block;
}

.piece {
   width: 100%;
}

.page-btn .page-span {
   display: inline-block;
   border: 1px solid black;
   margin-left: 10px;
   width: 40px;
   height: 40px;
   text-align: center;
   line-height: 40px;
   cursor: pointer;
   color: black;
}

.page-btn .page-span:hover {
   background: black;
   color: #fff;
}

.admin_pd:hover {
   background: black;
   color: #fff;
}

.view_btn {
   padding: 5px;
   font-size: 15px;
}

.pd_table{
width: 60%;

}
.wrap_card img {
margin-right: 20px;
}

.wrap_card {
width: 39%;
float: right;
text-align: center;
}
</style>

<!-- 동기식 상세보기 페이지 이동 -->
<script type="text/javascript">
   function AdminshowProductPage(pdId) {
      if(confirm("상세보기로 이동하십니까?")==true) {
         location.href = 'admin_product_show.ap?pdId=' + pdId;
         // 현재 페이지 변경 (바로 이동 - 동기방식 http get)   
      } else {
         return false;
      }
      
   }
</script>
<!-- 상품 미리보기 기능 -->
<script type="text/javascript">
   $(document).ready(function() {
      console.log("ready");
   });
   
   var CTX = '${pageContext.request.contextPath}';
   $(document).ready( function() {
      
      
      console.log("ctx:" + CTX); //콘솔체크
      
      $('tr').on('mouseenter', 'td>b', function(e) { // 테이블 자체에 진입시 표시
         console.log("x: " + e.pageX + "y: " + e.pageY);
         var name = $(this).text();
         
         console.log("name: " + name); //콘솔체크
         
         $.ajax({
            type: 'get',
            url: CTX+"/admin_prodcut_card@"+name+".ap",
            success: function(res, sc, xhr) {
               console.log("res:"+ res);
               console.log("sc:"+ sc);
               console.log("xhr:"+ xhr.status);
               var pdPcard = res;
               var tempRes = "<h2>" + pdPcard.name + " 상품 미리보기</h2>" + 
               "<ul style='list-style: none'>" +
               '<li><img src="${pageContext.request.contextPath}/resources/images/product/'+pdPcard.image_path+'"></li>' + 
               "<li>" + "상품명: " + pdPcard.name + "</li>" +
               "<li>" + "가격: " + pdPcard.price + "원</li>" +
               "</ul>";
               
               if(xhr.status == 200) {
                  $('#temp_cardview').html(tempRes);
               }
            },
            error: function(xhr, status) {
               console.log("ERROR: " + status);
               console.log("ERROR: " + xhr.status);
            },
            complete: function() {
               console.log("completed.."); // 끝에 실행 - 모래시계 끄자...
            }
         }); // ajax
      }); // mouseenter
   }); // ready

</script>
<div class="wrapper">
   <h1>
      Apple Store 상품목록(
      <c:out value="${pdSize}" />
      개)
   </h1>
   <h4>상품명의 마우스를 가져가시면 상품 미리보기가 가능합니다.</h4>
   <h4>상품리스트를 클릭하시면 상세보기로 이동합니다.</h4>
   <div class="wrap">
   <!-- 카드 뷰 (미리보기) -->
   <div class="wrap_card">
      <div id="temp_cardview"></div>
   </div>
      <!-- 상품 테이블 -->
      <div class="pd_table">
         <table class="piece" style="text-align: center" border="1">
            <tr>
               <th style="text-align: center">상품번호</th>
               <th style="text-align: center">상품명</th>
               <th style="text-align: center">판매가</th>
               <th style="text-align: center">카테고리</th>
               <th style="text-align: center">상품등록일</th>
            </tr>
            <c:forEach var="pd" items="${pdList}">
               <tr class="admin_pd" onclick="AdminshowProductPage(${pd.id})">
                  <td><c:out value="${pd.id}번" default="없음" /></td>
                  <td><b><c:out value="${pd.name}" default="없음" /></b></td>
                  <td><c:out value="${pd.price}원" default="0원" /></td>
                  <td><c:out value="${pd.category}" default="0원" /></td>
                  <td><fmt:formatDate value="${pd.reg_day}"
                        pattern="yyyy년 MM월 dd일" /></td>
               </tr>
            </c:forEach>
         </table>
         <!-- 페이지네이션 -->
         <div class="page-btn">
            <c:if test="${pn > 1}">
               <a
                  href="${pageContext.request.contextPath}/admin_product_list.ap?pg=${pn-1}"
                  class="page-btn-link"><span class="page-span">이전</span></a>
            </c:if>
            <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
               <c:if test="${vs.current eq pn}">
                  <!-- 현재 페이지 -->
                  <span class="page-span"><b>${vs.current}</b></span>
               </c:if>
               <c:if test="${vs.current ne pn}">
                  <a
                     href="${pageContext.request.contextPath}/admin_product_list.ap?pg=${vs.current}"
                     class="page-btn-link"><span class="page-span">${vs.current}</span></a>
               </c:if>
            </c:forEach>
            <c:if test="${pn < maxPg}">
               <a
                  href="${pageContext.request.contextPath}/admin_product_list.ap?pg=${pn+1}"
                  class="page-btn-link"><span class="page-span">다음</span></a>
            </c:if>
         </div>
      </div>
   </div>
</div>



