
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/product_css/dongho_style.css"/>">
<script type="text/javascript">
	function showProductPage(pdId) {
		//window.location.href = URL;
		location.href = 'product_show.ap?pdId=' + pdId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
</script>


</head>
<body>
	<%@ include file="../common/_header.jsp"%>
	<div class="prodList_wrap">
		<h2 class="pd-title">
			<c:out value="${category}" />
			Products (
			<c:out value="${pdSize}개" default="0" />
			)
		</h2>
		<div class="box1">
			<ul class="prod_wrap">
				<c:forEach var="pd" items="${pdList}">
					<li style="list-style: none;" class="prod_li">
						<div class="prod_st" onclick="showProductPage('${pd.id}')">
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
	<div class="page-btn">
		<c:if test="${pn > 1}">
			<a href="${pageContext.request.contextPath}/product_category_list.ap?pg=${pn-1}&category=${category}" class="page-btn-link"><span class="page-span">이전</span></a>
		</c:if>
		<c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
			<c:if test="${vs.current eq pn}">
			<!-- 현재 페이지 -->
				<span class="page-span"><b>${vs.current}</b></span>
			</c:if>
			<c:if test="${vs.current ne pn}">
				<a href="${pageContext.request.contextPath}/product_category_list.ap?pg=${vs.current}&category=${category}"class="page-btn-link"><span class="page-span">${vs.current}</span></a>
			</c:if>
		</c:forEach>
		<c:if test="${pn < maxPg}">
			<a href="${pageContext.request.contextPath}/product_category_list.ap?pg=${pn+1}&category=${category}" class="page-btn-link"><span class="page-span">다음</span></a>
		</c:if>
	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>

