<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<body>
<div class="wrapper">	
	<h3>QnA:: 게시글 답변하기</h3>
	<div class="wrap">
	<form action="${pageContext.request
		.contextPath}/rv_answer_proc.ap" method="post">
		<input type="hidden" name="rId" value="${review.rId}">
		<input type="hidden" name="title" value="${review.title}">
		<input type="hidden" name="postTitle" value="${review.postTitle}">
		
		<table class="piece">
			<tr> 
				<th>번호:</th> 
				<td>${review.rId}</td> 
			</tr>		
			<tr> 
				<th>게시판명:</th> 
				<td><c:out value="${review.title}"
				 default="QnA"/> </td>
			</tr>
			
			<tr> 
				<th>제목:</th> 
				<td>
					<c:out value="${review.postTitle}"/>
				</td>
			</tr>
			
			<tr> 
				<th>내용:</th> 
				<td><textarea name="postContents" rows="10" 
				cols="64" readonly ><c:out value="${review.postContents}"
				 default="내용 없음"/></textarea></td>
			</tr>
			<tr> 
				<th>답변:</th> 
				<td><textarea name="postAnswer" rows="10" 
				cols="64"><c:out value="${review.postAnswer}"/></textarea></td>
			</tr>
		</table>
		<input type="reset" value="리셋">
		<input type="submit" value="답변하기">
	</form>
	<!-- 게시글 편집, 삭제, 좋아요 메뉴.. -->
	
	</div>
	</div>
</body>
</html>







