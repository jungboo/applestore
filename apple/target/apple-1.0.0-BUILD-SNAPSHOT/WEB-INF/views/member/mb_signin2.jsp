<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>


<body>
<body>
<%@ include file="../common/_header.jsp" %>
	<div id="login_wrapper" style="margin-top: 100px;">
		<div id="content"
			style="width: 510px; border: 1px solid white; margin: 10px auto;">
			<h1 style="text-align: center;">Login</h1>
			<form action="signin_proc.jsp" method="post">
				<div
					style="display: grid; grid-template-columns: 350px 150px; grid-template-rows: 60px 60px; gap: 4px 4px;">
					<input type="text" class="input_class" placeholder="아이디"
						name="login" id="login" required
						style="grid-column: 1/2; grid-row: 1/2;"> <input
						type="password" class="input_class" placeholder="비밀번호"
						name="password" id="password" required
						style="grid-column: 1/2; grid-row: 2/3;"> <input
						type="submit" id="input_class_submit" value="로그인"
						style="grid-column: 2/3; grid-row: 1/3; background-color: #4A5164; color: #fff; font-size: 18px;">
				</div>
				<div style="margin-top: 8px;"></div>
				<hr style="margin-top: 10px;" />
				<ul
					style="display: flex; justify-content: space-around; margin: 8px 0 0 0; padding: 0; list-style-type: none;">
					<li style="border-right-color: lightgray"><a
						href="find_idAndpw.jsp"
						style="font-size: 15px; color: inherit; text-decoration: none;">
							ID/PW 찾기 </a></li>|

					<li><a href="agreement.jsp"
						style="font-size: 15px; color: inherit; text-decoration: none;">
							회원가입 </a></li>
				</ul>
			</form>
		</div>
	</div>
</body>
<%@ include file="../common/_footer.jsp" %>

</body>
</html>