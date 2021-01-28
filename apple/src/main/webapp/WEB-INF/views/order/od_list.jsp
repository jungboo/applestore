<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value="/resources/css/cart_css/wonwoo_style.css"/>">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">

	var CTX = '${pageContext.request.contextPath}';
	
	function cancleOrder(orderId) {
		var url = CTX + '/order_cancle.ap?';
		var param = 'odId=' + orderId;
		
		url = url + param;
		
		location.href = url;
	}
	

</script>

<title>장바구니</title>
</head>
<body>

	<%@ include file="../common/_header.jsp"%>



	<div id="ov_main">


		<div id="ov_list">

			<c:forEach var="od" items="${odList}" varStatus="status">

				<div id="od_list">

					<div id="ov_status">
						<b id="ov_date">${od.orderDate}</b> <b id="ov_status">${od.orderStatus}</b>
						<b id="ov_price">${od.totalPrice}원</b>
					</div>



					<c:forEach var="prct" items="${od.prctList}" varStatus="status">



						<div id="prct_box">

							<div id="prct_image">
								<img id="ov_prct_image"
									src="${pageContext.request.contextPath}/resources/images/product/${prct.imagePath}">
							</div>

							<div id="prct_name">
								<span id="ov_prct_name"><a
									href="product_show.ap?pdId=${prct.prId}">${prct.name}</a></span>
							</div>


							<span id="prct_price">${prct.price}원</span> <span id="prct_count">${prct.count}개</span>

						</div>


					</c:forEach>

					<div id="buttons">

						<button id="bt_cancle_order" onclick='cancleOrder(${od.orderId});'>구매
							취소</button>

						<button id="bt_check_delievery">배송 확인</button>

					</div>


				</div>

			</c:forEach>


		</div>



	</div>









	<%@ include file="../common/_footer.jsp"%>

</body>
</html>