<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apple 관리자 로그인 </title>
<link rel="stylesheet" href="<c:url value="/resources/css/admin_login_css/admin_login.css"/>">
</head>
<body>
	<form action="admin_login_proc.ap" method="post">
		<div class="login-box">
			<h1>Apple 관리자 로그인</h1>
				<div class="textbox">
					<i class="fa fa-user" aria-hidden="true"></i>
		            <input type="text" name="login" size="16" placeholder="관리자 ID" required>
		        </div>
				  <div class="textbox">
				  	<i class="fa fa-lock" aria-hidden="true"></i>
		            <input type="password" name="pw" size="16" placeholder="Password" 
		                        required>
		        </div>
		        <c:if test="${not empty msg}" >
				<script type="text/javascript">
   					alert('<c:out value="${msg}"></c:out>')
				</script>
				</c:if>
		        <div>
		         <input class="btn" type="submit" value="로그인">
		         <a class="btn btn-join" href="admin_new_form.ap">관리자 등록</a>
		    	</div>
	    </div>	
	</form>
</body>
</html>