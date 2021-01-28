<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/admin_login_css/admin_join.css"/>">
<title>Apple 관리자 등록</title>
</head>
<body>
	<form action="admin_join.ap" method="post">
	<div class="join-box">
	<h1>Apple 관리자 등록</h1>
			<table class="textbox">
				<tr>
				<td>등록아이디:</td> 
					<td><input class="text" type="text" name="login" size="20"
					placeholder="등록 계정명" required="required"  
						value="${empty param.login ? '': param.login}">
					</td>
				</tr>
				<tr>
					<td>암호:</td> 
					<td>
					<input class="text" type="password" name="pw" 
					placeholder="암호입력" size="20" required="required">
					</td>
				</tr>
				<tr>
				<c:if test="${not empty msg}" >
				<script type="text/javascript">
   					alert('<c:out value="${msg}"></c:out>')
				</script>
				</c:if>
					<td><input class="btn" type="reset" value="리셋"></td>
					<td><input class="btn" type="submit" value="가입"></td>
				</tr>
			</table>
		</div>	
	</form>
</body>
</html>