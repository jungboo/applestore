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
<script type="text/javascript"></script>
<script type="text/javascript">
	function showArticle(qaId) {
		//window.location.href = URL;
		location.href = 'qa_show.ap?qaId='+qaId;
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
	}
	function updateQnA(qaId) {
		location.href = 'qa_edit_form.ap?qaId='+qaId;
	}
	function answerQnA(qaId) {
		location.href = 'qa_answer_form.ap?qaId='+qaId;
	}
	function deleteQnA(qaId) {
		location.href = 'qa_delete.ap?qaId='+qaId;
	}
	
	var CTX = '${pageContext.request.contextPath}';
	function qaSearchResultPg(pg, keyword,target) {
		var url = CTX + "/qa_search_proc.ap";
		var params = "?pg=" + pg + "&keyword="+ keyword + "&target=" + target;
		url = url + params;
		var tg = '#resultQa'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
</script>
<body>
<div class="wrapper">
	<h1>QnA조회 결과</h1>
	<div id="resultQa" class="wrap">
	<h4>
	<c:out value="${qaSize}" default="0" /> 
				/ <c:out value="${qaMaxSize}" default="0" /> 개</h4>
		<c:if test="${empty qaSize}">
		<h4> 검색 결과 게시글이 하나도 없습니다!! </h4>
		</c:if>
	
		<c:if test="${not empty qaSize}">
            <table class="piece">
                <tr>
                	<th>관리번호</th>
                    <th>게시판명</th>
                    <th>제목</th>
                    <th>답변여부</th>
                    <th>게시일</th>
                    <th>수정/삭제</th>
                </tr>
                <c:forEach var="qa" items="${qaList}">
				<tr class="search">
					<td onclick="showArticle('${qa.qId}')"><c:out value="${qa.qId}" default="0"/> </td>
					<td onclick="showArticle('${qa.qId}')"><c:out value="${qa.title}" default="없음"/> </td>
					<td onclick="showArticle('${qa.qId}')"><c:out value="${qa.postTitle}" default="없음"/> </td>
					<c:if test="${qa.answer eq 0}">
						<td>답변안함
							<button class="answer" onclick="answerQnA(${qa.qId})"><i class="fab fa-replyd"></i>&nbsp;답변하기</button>
						</td>
					</c:if>
					<c:if test="${qa.answer eq 1}">
						<td>답변완료</td>
					</c:if>
					<td> <fmt:formatDate value="${qa.postedDay}" pattern="yyyy년 MM월 dd일 HH:mm:ss" /> </td>
					<td>
						<button class="delete" onclick="deleteQnA('${qa.qId}')"><i class="fas fa-trash-alt"></i>&nbsp;삭제</button>
		    			<button class="update" onclick="updateQnA('${qa.qId}')"><i class="fas fa-edit"></i>&nbsp;수정</button>
					</td>
				</tr>
			</c:forEach>
            </table>
            </c:if>
        </div>
        <div class="page-btn">
	      <c:if test="${pn > 1}">
	         <a onclick="qaSearchResultPg('${pn-1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">이전</span></a>
	      </c:if>
	      <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	         <c:if test="${vs.current eq pn}">
	         <!-- 현재 페이지 -->
	            <span class="page-span"><b>${vs.current}</b></span>
	         </c:if>
	         <c:if test="${vs.current ne pn}">
	            <a onclick="qaSearchResultPg('${vs.current}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">${vs.current}</span></a>
	         </c:if>
	      </c:forEach>
	      <c:if test="${pn < maxPg}">
	         <a onclick="qaSearchResultPg('${pn+1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">다음</span></a>
	      </c:if>
	   </div>
</div>
</body>
</html>