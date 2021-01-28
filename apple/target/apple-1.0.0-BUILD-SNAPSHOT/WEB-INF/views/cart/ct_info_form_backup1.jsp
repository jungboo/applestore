<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">


#cart_main {
	width: 60%;
	height: 1000px;
	margin-left: auto; 
	margin-right: auto; 
	border: 1px dotted gray;
}
</style>


<title>장바구니</title>
</head>
<body>

	<%@ include file="../common/_header.jsp" %>
	
	<% request.setAttribute("mbId", 1); %>

	
	<input type="hidden" name="memberId" value="${request.paramerer('mbId')}">	
		

	<div id="cart_main">
		
		<form action="${pageContext.request.contextPath}/cart_buy_proc.ap" method="post">
		
			<table border="1">
				<tr>
					<th><input type="checkbox"></th>
					<th>#</th>
					<th>상품 이름</th>
					<th>이미지</th>
					<th>총가격</th>
					<th>수량</th>
				</tr>
				
				<c:forEach var="ct" varStatus="status" items="${prIds}"  step="1" begin="0" end="${amount - 1}">
				
					<tr>	
						<td><input type="checkbox"></td>
						<td>
							<input type="text" name="prId${status.current}" value="${ct}" readonly>
						</td>
						<td>
							<input type="text" name="prName${status.current}" value="${names[status.index]}" readonly>
						</td>
						<td>
							<input type="text" name="prImagePath${status.current}" value="${imagePaths[status.index]}" readonly>
						</td>
						<td>
							<input type="text" name="prTotalPrice${status.current}" value="${prices[status.index]}" readonly> 원
						</td>
						<td>
							<input type="text" name="prCount${status.current}" value="${counts[status.index]}" min="1">
						</td>
					</tr>
					
					<br>
					<br>
					
					
					
					
					
					
				</c:forEach>				
			
			</table>
		
		</form>
	
	</div>

	
	
	<%@ include file="../common/_footer.jsp" %>
	
</body>
</html>