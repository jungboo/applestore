<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<script type="text/javascript">
	function showArticle(ntId) {
		//window.location.href = URL;
		location.href = 'nt_show.ap?ntId='+ntId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
	function updateNotice(ntId) {
		location.href = 'nt_edit_form.ap?ntId='+ntId;
	}
	function deleteNotice2(ntId) {
		location.href = 'nt_delete2.ap?ntId='+ntId;
	}
</script>
<body>
<div class="wrapper">
<h3>Apple - 공지사항 리스트 </h3>
	<c:if test="${empty ntSize}">
	<h4> 게시글이 하나도 없네요!! </h4>
	</c:if>
	
	<c:if test="${not empty ntSize}">
	<div id="wrap">
		<table class="piece">
			<tr>
				<th>#</th> <th>게시판명</th> <th>제목</th> <th>게시일</th><th>수정/삭제</th>
			</tr>
			<c:forEach var="nt" items="${ntList}">
				<tr class="search">
					<td onclick="showArticle('${nt.nId}')"><c:out value="${nt.nId}" default="0"/> </td>
					<td onclick="showArticle('${nt.nId}')"><c:out value="${nt.title}" default="공지사항"/> </td>
					<td onclick="showArticle('${nt.nId}')"><c:out value="${nt.postTitle}" default="없음"/> </td>
					<td onclick="showArticle('${nt.nId}')"> <fmt:formatDate value="${nt.postedDay}"
						pattern="yyyy년 MM월 dd일" /> </td>
					<td>
						<button class="delete" onclick="deleteNotice2('${nt.nId}')"><i class="fas fa-trash-alt"></i>&nbsp;삭제</button>
		    			<button class="update" onclick="updateNotice('${nt.nId}')"><i class="fas fa-edit"></i>&nbsp;수정</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</c:if>
</div>
</body>
</html>