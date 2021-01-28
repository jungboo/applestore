<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<body>
<div class="wrapper">	
	<h3>리뷰:: 게시글 수정</h3>
	<div class="wrap">
	<form action="${pageContext.request
		.contextPath}/rv_update.ap" method="post">
		<input type="hidden" name="rId" value="${review.rId}">
		<input type="hidden" name="title" value="${review.title}">
		
		<table class="piece">
			<tr> 
				<th>번호:</th> 
				<td>${review.rId}</td> 
			</tr>		
			<tr> 
				<th>게시판명:</th> 
				<td><c:out value="${review.title}"
				 default="후기"/> </td>
			</tr>
			
			<tr> 
				<th>제목:</th> 
				<td>
					<input type="text" name="postTitle" value="${review.postTitle}">
				</td>
			</tr>
			
			<tr> 
				<th>내용:</th> 
				<td><textarea name="postContents" rows="10" 
				cols="64"><c:out value="${review.postContents}"
				 default="내용 없음"/></textarea></td>
			</tr>
		</table>
		<input type="reset" value="리셋">
		<input type="submit" value="게시글 갱신">
	</form>
	<!-- 게시글 편집, 삭제, 좋아요 메뉴.. -->
	
	</div>
	</div>
</body>
</html>







