<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/cart_css/wonwoo_style.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">

	function goToMain() {
		
		location.href = "main.ap";		
	}



</script>

<title>결제 완료</title>
</head>
<body>

	<%@ include file="../common/_header.jsp" %>
	
	<div id="final_main">
		
		<span id="order_finish">결제가 완료 되었습니다.</span>
		
		
		
		<button id="go_main" type="button" onclick="goToMain()">메인으로...</button>
		
		
	</div>
		
	<%@ include file="../common/_footer.jsp" %>
	
	
	
</body>
</html>