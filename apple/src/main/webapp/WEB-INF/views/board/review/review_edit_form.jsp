<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_edit.css"/>">
<head>
<meta charset="UTF-8">
<title>Apple Review Edit</title>
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
		<form action="${pageContext.request.contextPath}/review_update.ap" method="post">
	<div class="board_banner">
		<h1 class="board_name">Apple 상품 후기 편집</h1>
		
			<a class="buttons btns" href="${pageContext.request.contextPath}/review_show.ap?rId=${reviewBoard.rId}">뒤로 가기</a>
			<input class="buttons btns" type="reset" value="리셋">
			<input class="buttons btns" type="submit" value="게시글 수정">
		<br>
	</div>

	
		<input type="hidden" name="rId" value="${reviewBoard.rId}">
		<input type="hidden" name="title" value="${reviewBoard.title}">
		<input type="hidden" name="postId" value="${reviewBoard.postId}">
		<input type="hidden" name="readCount" value="${reviewBoard.readCount}">
		<input type="hidden" name="postAnswer" value="${reviewBoard.postAnswer}">
		<input type="hidden" name="productImage" value="${reviewBoard.productImage}">	
		<input type="hidden" name="answer" value="${reviewBoard.answer}">
		
			
		<table border="1">
			<tr><td>제목:</td> 
			<td><input type="text" name="postTitle" size="64"
				placeholder="제목 입력" required="required"
				value="${reviewBoard.postTitle}"> 
			</td>
			</tr>				
			<tr><td>상품 평점:</td> 
			<td>
			<script>
			$(document).ready(function(){
		    $("select option[value='${reviewBoard.productScore}']").attr("selected", true);});
		    </script>
			<select name="productScore" >
				<option value="5.0" >5.0</option>
				<option value="4.0" >4.0</option>
				<option value="3.0" >3.0</option>
				<option value="2.0" >2.0</option>
				<option value="1.0" >1.0</option>
				<option value="0.0" >0.0</option>
			</select>
			</td>
			</tr>
			<tr><td>내용:</td> 
			<td>
				<textarea rows="20" cols="140"
					name="postContents" 
					placeholder="게시글 내용 입력"
				>${reviewBoard.postContents}
				</textarea>			 
			</td>
			</tr>		
		</table>	
	</form>
		
	<%@ include file="../../common/_footer.jsp" %>
</body>
</html>