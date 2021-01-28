<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>

<script type="text/javascript">
	var CTX = '${pageContext.request.contextPath}';
	function pdSearchResult() {
		var url = CTX + "/product_search.ap";
		var keyword = $('input[name=keyword]').val();
		var target = $('select[name=target]').val();
		var params = "?keyword="+ keyword + "&target=" + target;
		console.log(keyword);
		console.log(target);
		url = url + params;
		var tg = '#resultPd'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
</script>

<div class="wrapper">
	<form action="${pageContext.request.contextPath}/product_search.ap" method="post">
		<h1>상품 검색</h1>
		<div class="wrap">
			<table class="piece"> 
				<tr>
					<th>상품 검색</th>
					<td>
						<select name="target">
							<option value="all" ${param['target'] eq 'all'? 'selected' : ''}>전체</option>
							<option value="name" ${param['target'] eq 'name'? 'selected' : ''}>상품 명</option>
							<option value="price" ${param['target'] eq 'price'? 'selected' : ''}>상품 가격</option>
							<option value="category" ${param['target'] eq 'category'? 'selected' : ''}>카테고리</option>
<%-- 							<option value="color" ${param['target'] eq 'color'? 'selected' : ''}>상품 색상</option> --%>
		<%-- 					<option value="reg_day" ${param['target'] eq 'reg_day'? 'selected' : ''}>출시일</option> --%>		
						</select>
						<input type="search" name="keyword" placeholder="키워드 입력" size="50" required>
					</td>
				</tr>
			</table>
			<input type="button" class="mit" onclick="pdSearchResult()" value="검색">
		</div>
	</form>
	<div id="resultPd"></div>
</div>