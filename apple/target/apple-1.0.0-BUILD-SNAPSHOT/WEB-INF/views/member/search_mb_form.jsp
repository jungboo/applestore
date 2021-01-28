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
	var CTX = '${pageContext.request.contextPath}';
	function mbSearchResultPg(pg, keyword, target) {
		var url = CTX + "/member_search.ap";
		var params = "?pg=" + pg + "&keyword="+ keyword + "&target=" + target;
		url = url + params;
		var tg = '#resultMb'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
</script>
	<div class="wrapper">
	<h1>회원조회 결과</h1>
	<div id="resultMb" class="wrap">
	<h4>
	<c:out value="${mbSize}" default="0" /> 
				/ <c:out value="${mbMaxSize}" default="0" /> 개</h4>
		<c:if test="${empty mbSize}">
		<h4> 검색 결과 게시글이 하나도 없습니다!! </h4>
		</c:if>
	
		<c:if test="${not empty mbSize}">
            <table class="piece">
                <tr>
                	<th>관리번호</th>
                    <th>이름</th>
                    <th>아이디</th>
                    <th>생년월일</th>
                    <th>이메일</th>
                    <th>전화번호</th>
                    <th>주소</th>
                    <th>가입일</th>
                </tr>
                <c:forEach var="mb" items="${mbList}">
				<tr>
					<td><c:out value="${mb.id}" default="0"/> </td>
					<td><c:out value="${mb.name}" default="없음"/> </td>
					<td><c:out value="${mb.login}" default="없음"/> </td>
					<td><c:out value="${mb.birthday}" default="없음"/> </td>
					<td><c:out value="${mb.email}" default="없음"/> </td>
					<td><c:out value="${mb.phone}" default="없음"/> </td>
					<td><c:out value="${mb.address}${mb.addressDetail}" default="없음"/> </td>
					<td> <fmt:formatDate value="${mb.joinDay}" pattern="yyyy년 MM월 dd일" /> </td>
				</tr>
			</c:forEach>
            </table>
            </c:if>
        </div>
        <div class="page-btn">
	      <c:if test="${pn > 1}">
	         <a onclick="mbSearchResultPg('${pn-1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">이전</span></a>
	      </c:if>
	      <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	         <c:if test="${vs.current eq pn}">
	         <!-- 현재 페이지 -->
	            <span class="page-span"><b>${vs.current}</b></span>
	         </c:if>
	         <c:if test="${vs.current ne pn}">
	            <a onclick="mbSearchResultPg('${vs.current}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">${vs.current}</span></a>
	         </c:if>
	      </c:forEach>
	      <c:if test="${pn < maxPg}">
	         <a onclick="mbSearchResultPg('${pn+1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">다음</span></a>
	      </c:if>
	   </div>
	</div>