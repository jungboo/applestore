<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	function showArticle(ntId) {
		//window.location.href = URL;
		location.href = 'nt_show.ap?ntId='+ntId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
	
	function updateNotice(ntId) {
		location.href = 'nt_edit_form.ap?ntId='+ntId;
	}
	function deleteNotice(ntId) {
		location.href = 'nt_delete.ap?ntId='+ntId;
	}
	
	var CTX = '${pageContext.request.contextPath}';
	function searchResultPg(pg, keyword, target) {
		var url = CTX + "/nt_search_proc.ap";
		var params = "?pg=" + pg + "&keyword="+ keyword + "&target=" + target;
		url = url + params;
		var tg = '#result'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
	
</script>

<body>
	<div class="wrapper">
	<h1>공지사항조회 결과</h1>
	<div id="result" class="wrap">
	<h4>
	<c:out value="${ntSize}" default="0" /> 
				/ <c:out value="${ntMaxSize}" default="0" /> 개</h4>
		<c:if test="${empty ntSize}">
		<h4> 검색 결과 게시글이 하나도 없습니다!! </h4>
		</c:if>
	
		<c:if test="${not empty ntSize}">
            <table class="piece">
                <tr>
                	<th>관리번호</th>
                    <th>게시판명</th>
                    <th>제목</th>
                    <th>게시일</th>
                    <th>수정/삭제</th>
                </tr>
                <c:forEach var="nt" items="${ntList}">
				<tr class="search">
					<td onclick="showArticle('${nt.nId}')"><c:out value="${nt.nId}" default="0"/> </td>
					<td onclick="showArticle('${nt.nId}')"><c:out value="${nt.title}" default="없음"/> </td>
					<td onclick="showArticle('${nt.nId}')"><c:out value="${nt.postTitle}" default="없음"/> </td>
					<td onclick="showArticle('${nt.nId}')"> <fmt:formatDate value="${nt.postedDay}" pattern="yyyy년 MM월 dd일 HH:mm:ss" /> </td>
					<td>
						<button class="delete" onclick="deleteNotice('${nt.nId}')"><i class="fas fa-trash-alt"></i>&nbsp;삭제</button>
		    			<button class="update" onclick="updateNotice('${nt.nId}')"><i class="fas fa-edit"></i>&nbsp;수정</button>
					</td>
				</tr>
			</c:forEach>
            </table>
            </c:if>
        </div>
        <div class="page-btn">
	      <c:if test="${pn > 1}">
	         <a onclick="searchResultPg('${pn-1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">이전</span></a>
	      </c:if>
	      <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	         <c:if test="${vs.current eq pn}">
	         <!-- 현재 페이지 -->
	            <span class="page-span"><b>${vs.current}</b></span>
	         </c:if>
	         <c:if test="${vs.current ne pn}">
	            <a onclick="searchResultPg('${vs.current}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">${vs.current}</span></a>
	         </c:if>
	      </c:forEach>
	      <c:if test="${pn < maxPg}">
	         <a onclick="searchResultPg('${pn+1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">다음</span></a>
	      </c:if>
	   </div>
	</div>
</body>
</html>