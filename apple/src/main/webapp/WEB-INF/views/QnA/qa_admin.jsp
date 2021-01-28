<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<script type="text/javascript">
	var CTX = '${pageContext.request.contextPath}';
	function qaSearchResult() {
		var url = CTX + "/qa_search_proc.ap";
		var keyword = $('input[name=keyword]').val();
		var target = $('select[name=target]').val();
		var params = "?keyword="+ keyword + "&target=" + target;
		console.log(keyword);
		console.log(target);
		url = url + params;
		var tg = '#resultQa'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
</script>
<div class="wrapper">
	<form action="qa_search_proc.ap" method="post">
	    <h1>QnA 관리</h1>
	    <div class="wrap">
	        <table class="piece">
	            <tr>
	                <th>게시글찾기</th>
	                <td>
	                    <select name="target">
	                            <option value="all" ${param.target eq 'all' ? ' selected': ''}>전체</option>
	                            <option value="title" ${param.target eq 'title' ? ' selected': ''}>제목</option>
	                            <option value="contens" ${param.target eq 'contens' ? ' selected': ''}>내용</option>
	                    </select>
	                    <input name="keyword" type="search" size="50" placeholder="키워드 입력" required>
	                </td>
	            </tr>
	        </table>
	        <input type="button" class="mit" onclick="qaSearchResult()" value="검색">
	    </div>
	</form>
	<div id="resultQa"></div>
</div>