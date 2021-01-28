<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<meta charset="UTF-8">
<title>Apple Notice List</title>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_list.css"/>">

<script type="text/javascript">
	function showNotice(nId) {
		//window.location.href = URL;
		location.href = 'notice_show.ap?nId='+nId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
</script>
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
		
	<h1 class="board_name">Apple 공지사항</h1>
	<c:if test="${empty ntSize}">
		<h2> 등록된 게시글이 없습니다. </h2>
	</c:if>
	<div class="search-bar">
	<form action="${pageContext.request.contextPath}/notice_search.ap" method="post">
		<label for=""> 총  ${totalNtSize} 개 &nbsp;
			<select name="target">
				<option value="title" 
				 ${param['target'] eq 'posTitle' ? ' selected': '' }>제목</option>
				<option value="content"
				 ${param['target'] eq 'poostContent' ? ' selected': '' }>내용</option>
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
	<c:if test="${not empty ntSize}">
	<div class="board_list_wrap">
        <table class="board_list">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>등록일</th>
                </tr>
            </thead>
            <tbody>
            <c:if test="${ntSize == 0}">
			<tr> <h2>등록된 게시글이 없습니다. </h2> <tr>
			</c:if>
			 <c:if test="${ntSize != 0}">
            <c:forEach var="nt" items="${ntList}">
				<tr class="reviews" onclick="showNotice('${nt.nId}')">
					<td><c:out value="${nt.nId}" default="0"/> </td>
					<td><c:out value="${nt.postTitle}" default="없음"/> </td>
					<td> <fmt:formatDate value="${nt.postedDay}" 
						pattern="yyyy년 MM월 dd일" /> </td>
				</tr>
			</c:forEach>
			</c:if>
            </tbody>
        </table>
        <div id="paginate" class="paging">
			<a class="bt" href="${pageContext.request.contextPath}/notice_search.ap?pg=1
				&keyword=${param.keyword}&target=${param.target}">첫 페이지</a>
			<c:if test="${pn > 1}">
				<a class="bt" href="${pageContext.request.contextPath}/notice_search.ap?pg=
				${pn-1}&keyword=${param.keyword}&target=${param.target}"
				>이전 prev &lt;&lt;</a>
			</c:if>
				&nbsp;&nbsp;
			<c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
				<c:if test="${vs.current eq pn}">
					<b><a class="num on" href="${pageContext.request.contextPath}/rnotice_search.ap?pg=
				${vs.current}&keyword=${param.keyword}&target=${param.target}"
					>${vs.current}</a>
					</b><!-- 현재 페이지 -->
				</c:if>	
				<c:if test="${vs.current ne pn}">
				<a class="num" href="${pageContext.request.contextPath}/notice_search.ap?pg=
				${vs.current}&keyword=${param.keyword}&target=${param.target}"
					>${vs.current}</a>
				</c:if> 	
			</c:forEach>
				&nbsp;&nbsp;
			<c:if test="${pn < maxPg}">
				<a class="bt" href="${pageContext.request.contextPath}/notice_search.ap?pg=
				${pn+1}&keyword=${param.keyword}&target=${param.target}">다음 next &gt;&gt;</a>
			</c:if>	
			<a class="bt" href="${pageContext.request.contextPath}/notice_search.ap?pg=${maxPg}
				&keyword=${param.keyword}&target=${param.target}">마지막 페이지</a>
		</div>
    </div>
	</c:if>
	
		
<%@ include file="../../common/_footer.jsp" %>
</body>
</html>