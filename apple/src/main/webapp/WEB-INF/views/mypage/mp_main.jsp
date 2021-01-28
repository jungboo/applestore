<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/css/mypage_css/mypage.css"/>">
<head>
<meta charset="UTF-8">
</head>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<style type="text/css">

</style>

<body>
<%@ include file="../common/_header.jsp"%>
	<div id="grid_mypage">
		<%@ include file="../common/mypage_nav.jsp"%>

		<div id="mypage_contents_layout">

			<h2 style="text-align: center" id="mypage_header">MYPAGE</h2>
			<hr style="border-color: black">
			<p id="mypage_header_detail">회원님의 쇼핑 정보를 확인 하세요 !</p>
			<div id="show_mypageList">
				<ul>
					<li>
						<h3>내계정</h3>
						<p>회원의 개인정보 확인 및 수정이 가능합니다.</p>
					</li>
					<li>

						<h3>배송/주문</h3>
						<p>고객님의 온라인 주문 상태와 관련 사항에 대한 정보를 알아보세요.</p>
					</li>
					<li>
						<h3>내 게시판</h3>
						<p>구매하신 상품에 관한 상품평 등록및 관리가 가능합니다.</p>
					</li>
					<li>
						<h3>장바구니</h3>
						<p>회원님이 추가하신 상품 및 결제가 가능합니다.</p>
					</li>
				</ul>
			</div>


		</div>
	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>