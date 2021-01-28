<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
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
	<footer>
        <div class="main-content">
            <div class="left box">
                <h2>About us</h2>
                <div class="content">
                    <p>4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.4조 프로젝트 테스트 HTML입니다.</p>
                </div>
            </div>
            <div class="center box">
                <h2>Address</h2>
                <div class="content">
                    <div class="place">
                    <span class="fas fa-map-marker-alt"></span>
                    <span class="text">왕십리캠퍼스 : 서울시 성동구 왕십리로 303 4층</span>
                    </div>
                    <div class="phon">
                    <span class="fas fa-phone-alt"></span>
                    <span class="text">Tel:02-441-6006 / Fax:02-428-9694</span>
                    </div>
                    <div class="email">
                    <span class="fas fa-mpa-envelope"></span>
                    <span class="text">abcd@google.com</span>
                    </div>
                </div>                            
            </div>
            <div class="right box">
                <h2>Contact us</h2>
                <form action="#">
                    <div class="email">
                        <div class="text">Email *</div>
                        <input type="email" required>
                    </div>
                    <br>
                    <div class="msg">
                        <div class="text">Message *</div>
                        <textarea rows="2" cols="25" required></textarea>
                    </div>
                    <br>
                    <div class="btn">
                        <button type="submit">Send</button>
                    </div>
                </form>
            </div>
        </div>
    </footer>
</body>
</html>