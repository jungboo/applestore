<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/css/mypage_css/mypage.css"/>">
<head>
<meta charset="UTF-8">
</head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
	function showReview(rId) {
		location.href = 'review_show.ap?rId=' + rId;
	}
</script>


<style type="text/css">

</style>
<body>
	<%@ include file="../common/_header.jsp"%>
	<div id="grid_mypage">
		<%@ include file="../common/mypage_nav.jsp"%>

		<div id="mypage_contents_layout">
			<div>
				<h1 style="margin-bottom: 20px;">리뷰 게시판</h1>
				<p>작성하신 리뷰 리스트를 확인하고 편집/갱신이 가능합니다.</p>
			</div>
			<hr class="hr">
			<div id="div_review_area">
				<c:if test="${not empty rbSize}">
					<div class="board_list_wrap">
						<table class="board_list">
							<colgroup>
								<col width="10%">
								<col width="20%">
								<col width="20%">
								<col width="10%">
								<col width="20%">
								<col width="10%">
							</colgroup>
							<thead>
								<tr class="board_list">
									<th>번호</th>
									<th>제목</th>
									<th>작성일</th>
									<th>조회 수</th>
									<th>상품사진 / 상품평점</th>
									<th>답변 여부</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${rbSize == 0}">
									<tr>
										<tb colspan="7">
										<h2>등록된 게시글이 없습니다.</h2>
										</tb>
									</tr>
								</c:if>
								<c:if test="${rbSize != 0}">
									<c:forEach var="rb" items="${rbList}" varStatus="vs">
										<tr class="reviews" onclick="showReview('${rb.rId}')">
											<td><c:out value="${rb.rId}" default="0" /></td>
											<td><c:out value="${rb.postTitle}" default="없음" /></td>
											<td><fmt:formatDate value="${rb.postedDay}"
													pattern="yyyy년 MM월 dd일" /></td>
											<td><c:out value="${rb.readCount}" default="0" />번</td>
											<td class="score"><c:out
													value="${empty rb.productImage ? 'N' : 'Y'}" default="없음" />
												/ <c:choose>
													<c:when test="${rb.productScore == 5.0}">★★★★★</c:when>
													<c:when test="${rb.productScore == 4.0}">★★★★☆</c:when>
													<c:when test="${rb.productScore == 3.0}">★★★☆☆</c:when>
													<c:when test="${rb.productScore == 2.0}">★★☆☆☆</c:when>
													<c:when test="${rb.productScore == 1.0}">★☆☆☆☆</c:when>
													<c:when test="${rb.productScore == 0.0}">☆☆☆☆☆</c:when>
												</c:choose></td>
											<td class="answer"><c:out
													value="${rb.answer eq 0 ? 'N' : 'Y'}" default="없음" /></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
						<div id="paginate" class="paging">
							<c:if test="${pn > 1}">
								<a class="bt"
									href="${pageContext.request.contextPath}/mypage_review.ap?pg=${pn-1}">이전
									prev &lt;&lt;</a>
							</c:if>
							&nbsp;&nbsp;
							<c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
								<c:if test="${vs.current eq pn}">
									<b><a class="num on"
										href="${pageContext.request
					.contextPath}/mypage_review.ap?pg=${vs.current}">${vs.current}</a>
									</b>
									<!-- 현재 페이지 -->
								</c:if>
								<c:if test="${vs.current ne pn}">
									<a class="num"
										href="${pageContext.request
				.contextPath}/mypage_review.ap?pg=${vs.current}">${vs.current}</a>
								</c:if>
							</c:forEach>
							&nbsp;&nbsp;
							<c:if test="${pn < maxPg}">
								<a class="bt"
									href="${pageContext.request
				.contextPath}/mypage_review.ap?pg=${pn+1}">다음
									next &gt;&gt;</a>
							</c:if>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>
