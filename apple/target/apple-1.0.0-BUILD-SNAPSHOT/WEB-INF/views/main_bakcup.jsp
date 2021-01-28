<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apple Site</title>
</head>
<body>
	<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<title>Apple Site</title>
	<meta name="author" content="Your Name">
	<meta name="description" content="Example description">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/x-icon" href=""/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/mainpage.css"/>">
</head>

<body>
<%@ include file = "common/_header.jsp" %>
	<div id="main">
		<%@ include file = "mainpage.jsp" %>
	</div>
<%@ include file = "common/_footer.jsp" %>
</body>

</html>