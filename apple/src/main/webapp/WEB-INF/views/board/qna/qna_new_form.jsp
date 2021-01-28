<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<meta charset="UTF-8">
<title>Apple Q&A Write</title>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_write.css"/>">
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
	
	<form action="${pageContext.request.contextPath}/qna_add.ap" method="post">
	<div class=board_banner>
		<h1 class="board_name">Apple Q&A 작성</h1>
		 <a class="buttons" href="${pageContext.request.contextPath}/qna_list.ap">목록으로</a>
		
		<button class="buttons btn" type="submit">등록</button>
	    <button class="buttons btn" type="reset">리셋</button>
	    	   
	 </div>   
		<table id="boardWrite">
			<tbody>

				<input type="hidden" name="postId" value="${mbId}">
				<tr>

					<th scope="row"><label for="postTitle">제목:</label></th>

					<td><input type="text" name="postTitle" id="postTitle" size="64"
						placeholder="제목 입력" required="required"> </td>
				</tr>
                      
				<tr>
					<th scope="row"><label for="postContents">내용</label></th>
					<td><textarea rows="20" cols="140" 
						name="postContents" id="postContents"
						placeholder="게시글 내용 입력"></textarea></td>
				</tr>
			</tbody>
		</table>

	</form>
	<%@ include file="../../common/_footer.jsp" %>
</body>
</html>