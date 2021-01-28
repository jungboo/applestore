<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/cart_css/wonwoo_style.css"/>">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<title>장바구니</title>


<script type="text/javascript">

	var CTX = '${pageContext.request.contextPath}';
	
	function deleteOneProduct(prId) {
		
		var url = 'cart_delete_one.ap?';
		var param = 'prId=' + prId;
		
		url = url + param;
		location.href = url;
		
	}
	
	function cancleOrder(orderId) {
		var url = CTX + '/order_cancle.ap?';
		var param = 'odId=' + orderId;
		
		url = url + param;
		var target = '#ov_list';
		
		$(target).load(url, function(res) {
			console.log(res);
		})
	}
	

	$(document).ready(function() {
	
		var total = ${amount};
		var index = 0;
		
		while(index < total) {
			
			var pPriceId = '#pPrice_' + index;
			var pCountId = '#pCount_' + index;			
			var totalPriceId = '#total_price_' + index;
			
			var price = ($(pPriceId).text()).replace('원', '');
			var count = $(pCountId).val();
			
			$(totalPriceId).text(price*count + '원');
			index++;
		}
	})
	
	
	function countIncrease(index) {
		
		// 수량 조절
		var oldCount = $('#pCount_' + index).val();
		var newCount = oldCount*1 + 1
		$('#pCount_' + index).val(newCount);
		
		// 상품 하나 가격 조절
		var price = $('#pPrice_' + index).text().replace('원', '') * 1;
		var oldPrice = ($('#total_price_' + index).text()).replace('원', '') * 1;
		$('#total_price_' + index).text(oldPrice + price + '원');
		
		// 총 가격 조절
		var oldTotalPrice = $('#sum_price').text().replace('원', '') * 1;
		var fixTotalPrice = oldTotalPrice + price;
		console.log(oldTotalPrice);
		$('#sum_price').text(fixTotalPrice);
		$('#total_pay').text(fixTotalPrice + 2500);
		
	}
	
	function countDecrease(index) {
		var oldCount = $('#pCount_' + index).val();
		
		if (oldCount > 1) {
			var newCount = oldCount*1 - 1
			$('#pCount_' + index).val(newCount);
			
			// 상품 하나 가격 조절
			var price = $('#pPrice_' + index).text().replace('원', '') * 1;
			var oldPrice = ($('#total_price_' + index).text()).replace('원', '') * 1;
			$('#total_price_' + index).text(oldPrice - price + '원');
			
			// 총 가격 조절
			var oldTotalPrice = $('#sum_price').text().replace('원', '') * 1;
			var fixTotalPrice = oldTotalPrice - price;
			console.log(oldTotalPrice);
			$('#sum_price').text(fixTotalPrice);
			$('#total_pay').text(fixTotalPrice + 2500);
		}
	}
	
	$('#select_all').click(function() {
		
		console.log("되긴함");
		if ($("input:checkbox[id='select_all']").prop("ckecked")) {
			console.log('true');
			$("input[type=checkbox]").prop("checked", true);			
		}
		else {
			console.log('false');
			$("input[type=checkbox]").prop("checked", false);				
		}
	})
	
	
		
	
	
	
	
	
	
</script>

<title>장바구니</title>
</head>
<body>

	<%@ include file="../common/_header.jsp"%>

	<div id="grid_mypage">
	<%@ include file="../common/mypage_nav.jsp" %>
	

	<div id="cart_main">

		<div id="menubar">
			<input type="checkbox" id="select_all"> 
			<span id="sel_all">전체 선택</span>
			<span id="product_info"> 상품정보 </span>
			<span id="product_price">상품가격 </span>
		</div>


		<form action="${pageContext.request.contextPath}/order_info_form.ap"
			method="post">

			<div id="div_for">

				<c:forEach var="ct" items="${ctList}" varStatus="status">

					<br>
					<br>
					<br>
					<!--  넘어갈 것들   -->
					<input type="hidden" name="prIds" value="${ct.prId}">
					<input type="hidden" name="amount" value="${amount}">
					<input type="hidden" name="mdId" value="${mbId}">



					<!--  상품 패널   -->
					<div id="pr_panel">

						<div id="cb">
							<input type="checkbox" id="cb${status.index}" class="deleteCb">
						</div>
	
						<div id="pr_image">
							<img name="ct_image" class="img_class"
								src="${pageContext.request.contextPath}/resources/images/product/${ct.imagePath}">
						</div>


						<div id="pr_box">

							<div id="pr_name">
								<span id="name"><a href="product_show.ap?pdId=${ct.prId}">${ct.prName}</a></span>
							</div>
							
<!-- 							배송쪽 미구현으로 막음	 -->
<!-- 							<span id="arrive_date"></span> -->

							<button id="del_product_${status.index}" type="button" class="about_price"
								onclick="deleteOneProduct(${ct.prId})" style="width: 25px;">Χ</button>


							
							
							<div id="div_count">
								<button id="cDown" type="button" onclick="countDecrease(${status.index})">▼</button>
								<input id="pCount_${status.index}" name="pCounts"  type="number" 
								readonly="readonly" value="${ct.count}" style="width: 40px; height: 24px; text-align: right;">
								<button id="cUp" type="button" onclick="countIncrease(${status.index})">▲</button>
							</div>
							
							<span id="pPrice_${status.index}" class="about_price">${ct.price}원</span>

						</div>

						<div id="tp">

							<span id="total_price_${status.index}" class="total_price"></span>

						</div>

					</div>

				</c:forEach>

				<div id="deletebar">
					<input type="checkbox">
					<button id="delete_product" type="button" onclick=""
						style="height: 25px; width: 40px; margin-top: -30px; margin-left: 10px;">삭제</button>

				</div>


				<div id="conclude">
					<span id="price_conclude">
						 총 상품 가격 [&nbsp;<span id="sum_price">${totalPrice}</span> 원&nbsp;] + 배송비 [&nbsp;2500 원&nbsp;] = 총 주문금액
						[&nbsp;<span id="total_pay">${totalPrice + 2500}</span> 원&nbsp;] 
					</span>
				</div>

				<input id="keep_shopping" type="reset" value="계속 쇼핑하기"> 
				
				<input id="purchase" type="submit" value="결제 하기">


			</div>

		</form>






		<br> <br> <br> <br> <br> <br> <br>


	</div>
	
	</div>


	<%@ include file="../common/_footer.jsp"%>

</body>
</html>


