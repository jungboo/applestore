<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp"%>

<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
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
 .page-btn .page-span {
 	display: inline-block;
 	border: 1px solid black; 
 	margin-left: 10px; 
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
 
 
 .admin_od:hover {
	background: black;
	color: #fff;
}
 
 
</style>

<div class="wrapper">
	<h1>
		Apple Store 주문목록(<c:out value="${odSize}" />개)
	</h1>
	<h4>주문번호의 마우스를 가져가시면 고객에 정보의 미리보기가 가능합니다.</h4>
	<h4>주문리스트를 클릭하시면 상세보기로 이동합니다.</h4>
	<div class="wrap">
		<!-- 주문내역 테이블 -->
		<div class="od_table">
			<table class="piece" style="text-align: center" border="1">
				<tr>
					<th style="text-align: center">주문번호</th>
					<th style="text-align: center">주문자</th>
					<th style="text-align: center">주문상품</th>
					<th style="text-align: center">주문수량</th>
					<th style="text-align: center">총 가격</th>
					<th style="text-align: center">주문일시</th>
				</tr>
				<c:forEach var="od" items="${odList}">
					<tr class="admin_od">
						<td><c:out value="${od.orderId}"/>번</td>
						<td><c:out value="${od.memberId}"/>번</td>
						<td><c:out value="${od.prIds}"/></td>
						<td><c:out value="${od.counts}"/>개</td>
						<td><c:out value="${od.totalPrice}"/>원</td>
						<td><fmt:formatDate value="${od.orderDate}"
								pattern="yyyy년 MM월 dd일" /></td>
					</tr>
				</c:forEach>
			</table>
			<!-- 페이지네이션 -->
		<div class="page-btn">
		<c:if test="${pn > 1}">
			<a href="${pageContext.request.contextPath}/admin_order_list.ap?pg=${pn-1}" class="page-btn-link"><span class="page-span">이전</span></a>
		</c:if>
		<c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
			<c:if test="${vs.current eq pn}">
			<!-- 현재 페이지 -->
				<span class="page-span"><b>${vs.current}</b></span>
			</c:if>
			<c:if test="${vs.current ne pn}">
				<a href="${pageContext.request.contextPath}/admin_order_list.ap?pg=${vs.current}" class="page-btn-link"><span class="page-span">${vs.current}</span></a>
			</c:if>
		</c:forEach>
		<c:if test="${pn < maxPg}">
			<a href="${pageContext.request.contextPath}/admin_order_list.ap?pg=${pn+1}" class="page-btn-link"><span class="page-span">다음</span></a>
		</c:if>
	</div>
		</div>
	</div>
</div>