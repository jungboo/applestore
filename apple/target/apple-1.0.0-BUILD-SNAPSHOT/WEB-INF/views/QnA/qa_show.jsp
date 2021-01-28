<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<body>
<div class="wrapper">	
	<h3>QnA:: 게시글 상세조회</h3>
	<div class="wrap">
		<table class="piece">
			<tr> 
				<th>번호:</th> 
				<td>${qa.qId}</td> 
			</tr>		
			<tr> 
				<th>게시판명:</th> 
				<td><c:out value="${qa.title}"
				 default="QnA"/> </td>
			</tr>
			
			<tr> 
				<th>제목:</th> 
				<td><c:out 
				value="${qa.postTitle}"
				 default="제목 없음"/> </td>
			</tr>
			
			<tr> 
				<th>내용:</th> 
				<td><textarea rows="10" 
				cols="64" readonly><c:out value="${qa.postContents}"
				 default="내용 없음"/></textarea></td>
			</tr>
			<c:if test="${qa.answer eq 1}">
				<tr> 
					<th>답변:</th> 
					<td><textarea rows="10"
					cols="64" readonly><c:out value="${qa.postAnswer}"
					 default="내용 없음"/></textarea></td>
				</tr>
			</c:if>
			<tr> 
				<th>등록일:</th> 
				<td> <fmt:formatDate value="${qa.postedDay}" 
						pattern="yyyy년 MM월 dd일 " />   </td>
			</tr>

		</table>
	
	<!-- 게시글 편집, 삭제, 좋아요 메뉴.. -->
	
	</div>
</div>
</body>
</html>







