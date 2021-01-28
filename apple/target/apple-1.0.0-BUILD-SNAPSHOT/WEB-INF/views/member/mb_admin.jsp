<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<script type="text/javascript">
	var CTX = '${pageContext.request.contextPath}';
	function mbSearchResult() {
		var url = CTX + "/member_search.ap";
		var keyword = $('input[name=keyword]').val();
		var target = $('select[name=target]').val();
		var params = "?keyword="+ keyword + "&target=" + target;
		console.log(keyword);
		console.log(target);
		url = url + params;
		var tg = '#result'; 
		$(tg).load(url, function(res) {
			console.log(res);
		});
	}
</script>
<div class="wrapper">
	<form action="member_search.ap" method="post">
        <h1>고객관리</h1>
        <div class="wrap">
            <table class="piece">
                <tr>
                    <th>검색어</th>
                    <td>
                        <select name="target">
                        	<option value="all" ${param.target eq 'all' ? ' selected': ''}>전체</option>
                            <option value="name" ${param.target eq 'name' ? ' selected': ''}>이름</option>
                            <option value="login" ${param.target eq 'login' ? ' selected': ''}>아이디</option>
                            <option value="email" ${param.target eq 'email' ? ' selected': ''}>이메일</option>
                            <option value="phone" ${param.target eq 'phone' ? ' selected': ''}>전화번호</option>
                        </select>
                        <input type="search" name="keyword" size="50" placeholder="키워드 입력" required>
                    </td>
                </tr>
            </table>
            <input type="button" onclick="mbSearchResult()" class="mit" value="검색">
        </div>
	</form>
	<div id="result"></div>
</div>