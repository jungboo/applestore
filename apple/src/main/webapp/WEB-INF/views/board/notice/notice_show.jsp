<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_show.css"/>">
<head>
<meta charset="UTF-8">
<title>Apple Notice</title>
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
	
	<div class="notice_banner">
		<h1 class="notice_name">Apple 공지사항</h1>
		<div class="buttons">s
			<a class="btns" href="${pageContext.request.contextPath}/notice_list.ap">목록으로 가기</a>
		</div>
	</div>
	<div id="nt_show" class="board_show">
	<table class="board_view" border="1">	
		 <colgroup>
            <col width="15%">
            <col width="35%">
            <col width="15%">
            <col width="35%">
        </colgroup>
			<tr> 
				<th>번호:</th> 
				<td colspan="3">${notice_board.nId}</td> 
			</tr>		
			<tr> 
				<th>제목:</th> 
				<td colspan="3"><c:out value="${notice_board.postTitle}"
				 default="제목 없음"/> </td>
			</tr>
			<tr> 
				<th>내용:</th> 
				<td colspan="4" class="view_text">
				<textarea rows="10" cols="170" readonly><c:out 
				value="${notice_board.postContents}"
				 default="내용 없음"/></textarea> </td>
			</tr>
			
			
			<tr> 
				<th>등록일:</th> 
				<td> <fmt:formatDate value="${notice_board.postedDay}" 
						pattern="yyyy년 MM월 dd일 - HH시mm분ss초" />   </td>
			</tr>
			
		</table>
	</div>
	<%@ include file="../../common/_footer.jsp" %>
</body>
</html>







