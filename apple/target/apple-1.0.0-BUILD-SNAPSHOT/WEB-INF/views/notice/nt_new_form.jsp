<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<body>
	<div class="wrapper">
	<h3>공지사항 작성</h3>
	<form action="${pageContext.request
		.contextPath}/nt_add_proc.ap" method="post">
		<div class="wrap">
			<table class="piece">
				<th>제목</th>
				<tr>
					<td>
						<input type="text" name="postTitle" placeholder="재목">
					</td>
				</tr>
				<th>내용</th>
				<tr>
					<td>
						<textarea name="postContents" rows="8" cols="100" placeholder="내용입력"></textarea>
					</td>
				</tr>
			</table>
			<input type="submit" class="mit" value="게시글 등록">
		</div>
	</form>
	</div>
</body>
</html>