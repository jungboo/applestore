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
	function pdSearchResultPg(pg, keyword, target) {
		var url = CTX + "/product_search.ap";
		var params = "?pg=" + pg + "&keyword="+ keyword + "&target=" + target;
		url = url + params;
		var tg = '#resultPd'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
</script>
<div class="wrapper">
    <div id="resultPd" class="wrap">
    <h1>상품조회 결과</h1>
	
	<div id="search_result">
		<h4>
	<c:out value="${pdSize}" default="0" /> 
				/ <c:out value="${pdMaxSize}" default="0" /> 개</h4>
			<c:if test="${empty pdSize}">
				<h4> 검색 결과 상품이 하나도 없네요!! </h4>
			</c:if>
			<c:if test="${not empty pdSize}">
				<table class="piece">
					<tr>
						<th>관리번호</th> <th>상품 명</th> <th>가격</th> 
						<th>카테고리</th> <th>상품 등록일</th>
					</tr>
					<c:forEach var="pd" items="${pdList}">
						<tr>
							<td><c:out value="${pd.id}번" default="없음" /></td>
					         <td><c:out value="${pd.name}" default="없음" /></td>
					         <td><c:out value="${pd.price}원" default="0원" /></td>
					         <td><c:out value="${pd.category}" default="없음" /></td>
					         <td><c:out value="${pd.reg_day}" default="없음" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
		
		<div class="page-btn">
	      <c:if test="${pn > 1}">
	         <a onclick="pdSearchResultPg('${pn-1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">이전</span></a>
	      </c:if>
	      <c:forEach begin="1" end="${maxPg}" step="1" varStatus="vs">
	         <c:if test="${vs.current eq pn}">
	         <!-- 현재 페이지 -->
	            <span class="page-span"><b>${vs.current}</b></span>
	         </c:if>
	         <c:if test="${vs.current ne pn}">
	            <a onclick="pdSearchResultPg('${vs.current}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">${vs.current}</span></a>
	         </c:if>
	      </c:forEach>
	      <c:if test="${pn < maxPg}">
	         <a onclick="pdSearchResultPg('${pn+1}','${param.keyword}','${param.target}')" class="page-btn-link"><span class="page-span">다음</span></a>
	      </c:if>
	   </div>
	</div>