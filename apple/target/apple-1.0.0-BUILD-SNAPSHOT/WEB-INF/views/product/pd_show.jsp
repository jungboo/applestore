
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<c:url value="/resources/css/product_css/dongho_style.css"/>">
<head>
<meta charset="UTF-8">
<script type="text/javascript">

	function addProductToCart(productId) {
		
		var count = $('#productCount').val();
		location.href = "cart_single_add.ap?prId="+productId+"&count="+ count;
	}

</script>

</head>
<body>
	<!-- 헤더(카테고리) -->
	<div>
	<%@ include file="../common/_header.jsp"%>
	</div>
	
	<!-- 제품 상품 상세 페이지 -->
	<div class="pd_show">
		<div class="small-container single-product">
			<div class="product-row">
				<div class="product-col-2">
					<img class="product-img" src="${pageContext.request.contextPath}/resources/images/product/${product.image_path}" width="100%">
				</div>
				<div class="product-col-2">
					<h4 class="product-h4">
						<i><c:out value="${product.category}" default="이름없음" /></i>
					</h4>
					<h1 class="product-h1">
						<c:out value="${product.name}" default="이름없음" />
					</h1>
					<h4 class="product-h4">
						가격: <c:out value="${product.price}원" default="0원" />
					</h4>
					<h5 class="product-h5">
						<c:out value="${product.spec}" default="" />
					</h5>
					
						<select class="product-color-select">
						<option>Select Color</option>
						<option><c:out value="${product.color}" default="이름없음" /></option>
						</select> 
						
						<input id="productCount" class="product-cart" type="number" value="1">개 <br> 
						<a onclick="addProductToCart(${product.id})" class="cart-btn2"> Add to Cart</a>
					<hr>
					<h3 class="Product_detail">Product Detail</h3>
					<p class="Product_detail2"><c:out value="${product.description}" default="설명없음" /></p>



				</div>
			</div>
		</div>
		</div>
	<div>
	<%@ include file="../common/_footer.jsp"%>
	</div>
</body>
</html>