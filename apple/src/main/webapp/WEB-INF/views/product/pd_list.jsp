<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<link rel="stylesheet"
	href="<c:url value="/resources/css/product_css/dongho_style.css"/>">
<meta charset="UTF-8">

<script type="text/javascript">
	function showProductPage(pdId) {
		//window.location.href = URL;
		location.href = 'product_show.ap?pdId=' + pdId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
</script>


</head>
<body>
	<div class="prodList_wrap">
		<h2 class="pd-title">
			All Products (
			<c:out value="${pdSize}개" default="0" />
			)
		</h2>
		<div class="box1">
			<ul class="prod_wrap" id="prod_wrap">
				<c:forEach var="pd" items="${pdList}">
					<li style="list-style: none;" class="prod_li">
						<div id="prod_st" class="prod_st"
							onclick="showProductPage('${pd.id}')">
							<img
								src="${pageContext.request.contextPath}/resources/images/product/${pd.image_path}">
							<h4>
								<c:out value="${pd.name}" default="없음" />
							</h4>
							<p class="pd-price">
								<c:out value="${pd.price}" default="0" />
								원
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>

		</div>
	</div>

</body>
</html>