<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_edit.css"/>">
<head>
<meta charset="UTF-8">
<title>Apple QnA Edit</title>
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
		<form action="${pageContext.request.contextPath}/qna_update.ap" method="post">
	<div class="board_banner">
		<h1 class="board_name">Apple 상품 QnA 편집</h1>
		
			<a class="buttons btns" href="${pageContext.request.contextPath}/qna_show.ap?qId=${qnaBoard.qId}">뒤로 가기</a>
			<input class="buttons btns" type="reset" value="리셋">
			<input class="buttons btns" type="submit" value="게시글 수정">
		<br>
	</div>

	
		<input type="hidden" name="qId" value="${qnaBoard.qId}">
		<input type="hidden" name="postId" value="${qnaBoard.postId}">
		<input type="hidden" name="readCount" value="${qnaBoard.readCount}">
		<input type="hidden" name="answer" value="${qnaBoard.answer}">
		
			
		<table border="1">
			<tr><td>제목:</td> 
			<td><input type="text" name="postTitle" size="64"
				placeholder="제목 입력" required="required"
				value="${qnaBoard.postTitle}"> 
			</td>
			</tr>				
			<tr><td>내용:</td> 
			<td>
				<textarea rows="20" cols="140"
					name="postContents" 
					placeholder="게시글 내용 입력"
				>${qnaBoard.postContents}
				</textarea>			 
			</td>
			</tr>		
		</table>	
	</form>
	<%@ include file="../../common/_footer.jsp" %>
</body>
</html>