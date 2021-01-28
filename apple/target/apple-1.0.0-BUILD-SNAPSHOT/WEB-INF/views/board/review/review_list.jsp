<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<meta charset="UTF-8">
<title>Apple Review List</title>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_list.css"/>">

<script type="text/javascript">
	function showReview(rId) {
		//window.location.href = URL;
		location.href = 'review_show.ap?rId='+rId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
	function login(){
		alert('로그인이 필요 합니다.'); 
	}	
</script>

</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
	
	<h1 class="board_name">Apple 상품 후기</h1>
	<c:if test="${empty rbSize}">
		<h2> 등록된 게시글이 없습니다. </h2>
	</c:if>
	<div class="search-bar">
	<form action="${pageContext.request
		.contextPath}/review_search.ap" method="post">
		<label for=""> 총  ${totalRbSize} 개 &nbsp;
			<select name="target">
				<option value="title" 
				 ${param['target'] eq 'posTitle' ? ' selected': '' }>제목</option>
				<option value="content"
				 ${param['target'] eq 'poostContent' ? ' selected': '' }>내용</option>
				<option value="tags"
				${param['target'] eq 'postId' ? ' selected': '' }>작성자 아이디</option>
				<option value="all"
				${param['target'] eq 'all' ? ' selected': '' }>모두</option>
			</select>
		</label>
		
		<label for="">검색 키워드:  &nbsp;</label>
			<input type="search" name="keyword" 
			placeholder="키워드 입력" size="40" value="${param.keyword}">
		<input type="submit" value="검색">	
	</form>
	</div>
	<c:if test="${not empty rbSize}">
	<div class="board_list_wrap">
        <table class="board_list">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성일</th>
                    <th>조회 수</th>
                    <th>상품사진  / 상품평점</th>
                    <th>답변 여부</th>       
                </tr>
            </thead>
            <tbody>
            <c:if test="${rbSize == 0}">
			<tr> <h2>등록된 게시글이 없습니다. </h2> <tr>
			</c:if>
			 <c:if test="${rbSize != 0}">
            <c:forEach var="rb" items="${rbList}" varStatus="vs">
				<tr class="reviews" onclick="showReview('${rb.rId}')">
					<td><c:out value="${rb.rId}" default="0"/> </td>
					<td><c:out value="${rb.postTitle}" default="없음"/> </td>
					<td><c:out value="${mbNameList[vs.index]}" default="없음"/> </td>					
					<td> <fmt:formatDate value="${rb.postedDay}" 
						pattern="yyyy년 MM월 dd일" /> </td>
					<td><c:out value="${rb.readCount}" default="0"/>번 </td>
					<td class="score"><c:out value="${empty rb.productImage ? 'N' : 'Y'}" default="없음"/> / 
						<c:choose>
						 <c:when test="${rb.productScore == 5.0}">★★★★★</c:when>
						 <c:when test="${rb.productScore == 4.0}">★★★★☆</c:when>
						 <c:when test="${rb.productScore == 3.0}">★★★☆☆</c:when>
						 <c:when test="${rb.productScore == 2.0}">★★☆☆☆</c:when>
						 <c:when test="${rb.productScore == 1.0}">★☆☆☆☆</c:when>
						 <c:when test="${rb.productScore == 0.0}">☆☆☆☆☆</c:when>
						</c:choose>
					 </td>		
					 <td class="answer"><c:out value="${rb.answer eq 0 ? 'N' : 'Y'}" default="없음"/> </td>	
				</tr>
			</c:forEach>
			</c:if>
            </tbody>
        </table>
        <div id="paginate" class="paging">
			<a class="bt" href="${pageContext.request.contextPath}/review_list.ap?pg=1">첫 페이지</a>
			<c:if test="${pn > 1}">
				<a class="bt" href="${pageContext.request.contextPath}/review_list.ap?pg=${pn-1}"
				>이전 prev &lt;&lt;</a>
			</c:if>
				&nbsp;&nbsp;
			<c:forEach begin="1" end="${maxPg}" step="1" 
				varStatus="vs">
				<c:if test="${vs.current eq pn}">
					<a class="num on" href="${pageContext.request
					.contextPath}/review_list.ap?pg=${vs.current}"
					>${vs.current}</a>
					</b> <!-- 현재 페이지 -->
				</c:if>	
				<c:if test="${vs.current ne pn}">
				<a class="num" href="${pageContext.request
				.contextPath}/review_list.ap?pg=${vs.current}"
					>${vs.current}</a>
				</c:if> 	
			</c:forEach>
				&nbsp;&nbsp;
			<c:if test="${pn < maxPg}">
				<a class="bt" href="${pageContext.request
				.contextPath}/review_list.ap?pg=${pn+1}"
				>다음 next &gt;&gt;</a>
			</c:if>	
			<a class="bt" href="${pageContext.request.contextPath}/review_list.ap?pg=${maxPg}">마지막 페이지</a>
		</div>
    </div>
	</c:if>
	<c:if test="${empty mbId}">
	<div class="write_riview"><a onclick="javascript:login()" href="${pageContext.request.contextPath}/member_login_form.ap">게시글 작성</a></div>
	</c:if>
	<c:if test="${not empty mbId}">
	<div class="write_riview"><a href="${pageContext.request.contextPath}/review_new_form.ap">게시글 작성</a></div>
	</c:if>
	<br><br><br><br>
		
<%@ include file="../../common/_footer.jsp" %>
</body>
</html>