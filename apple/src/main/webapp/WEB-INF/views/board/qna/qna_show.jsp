<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_show.css"/>">
<head>
<meta charset="UTF-8">
<title>Apple Q&A</title>
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
	<div class=board_banner>
	<h1 class="board_name">Apple Q&A</h1>
	<!-- 게시글 편집, 삭제,.. -->
	 <div class="buttons">
		<a class="btns" href="${pageContext.request.contextPath}/qna_list.ap">목록으로 가기</a>
		<c:if test="${not empty mbLogin}">
			<!-- 로그인 멤버가 글 작성자와 일치 시에만... 편집, 삭제 메뉴가 표시 -->
			<c:if test="${mbId eq qna_board.postId}">	
			<a class="btns" href="${pageContext.request.contextPath}/qna_edit_form.ap?qId=${qna_board.qId}">수정</a>
			<a class="btns" href="${pageContext.request.contextPath}/qna_delete.ap?qId=${qna_board.qId}">삭제</a>
			</c:if>
	</c:if>
	</div>
	</div>
	<div id="rb_show" class="board_show">
		<table class="board_view" border="1">
        <colgroup>
            <col width="15%">
            <col width="35%">
            <col width="15%">
            <col width="35%">
        </colgroup>
        <tbody>
        	<tr> 
				<th>글번호</th>
                <td><c:out value="${qna_board.qId}" default="제목 없음"/></td>
                <th>조회수</th>
                <td><c:out value="${qna_board.readCount}" default="0"/>번</td>
			</tr>
			<tr> 
				<th>작성자</th>
                <td><c:out value="${mblogin}" default="없음"/></td>
                <th>작성시간</th>
                <td><fmt:formatDate value="${qna_board.postedDay}" pattern="yyyy년 MM월 dd일 - HH시mm분ss초" /></td>
				
			</tr>
			<tr> 
				<th>제목</th>
                <td colspan="3"><c:out value="${qna_board.postTitle}" default="제목 없음"/></td>
			</tr>
			<tr> 
				 <th>내용</th>
                <td colspan="4" class="view_text">
                <textarea rows="10" cols="170" readonly><c:out 
				value="${qna_board.postContents}"
				 default="내용 없음"/></textarea> 
				 </td>
                </td>
			</tr>
						
			
			<tr> 
				<th>관리자 답글:</th> 
				<td colspan="4" class="view_text">
				<textarea rows="10" cols="170" readonly><c:out 
				value="${qna_board.postAnswer}" default="내용 없음"/></textarea> 
				 </td>
			</tr>
			 </tbody>
		</table>
	
	
	</div>
	<%@ include file="../../common/_footer.jsp" %>
</body>
</html>







