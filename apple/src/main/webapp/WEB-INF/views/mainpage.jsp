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
	<main>
        <section class="change-pictures">
            <div class="section">
                <input type="radio" name="slide" id="slide1" checked>
                <input type="radio" name="slide" id="slide2">
                <input type="radio" name="slide" id="slide3">
                <input type="radio" name="slide" id="slide4">
                <input type="radio" name="slide" id="slide5">
                <div class="slidewrap">
                    <ul class="slidelist">
                        <!-- 슬라이드 영역 -->
                        <li class="slideitem">
                            <a class="slides">
                                <div class="textbox">
                                   <h3>Desktop</h3>
                                    <p>모든 것을 바꿀 수 있는 능력</p>
                                </div>
                                <img src="<c:url value="/resources/images/apple-change1.jpg"/>">
                            </a>
                        </li>
                        <li class="slideitem">
                            <a class="slides">
                                <div class="textbox">
                                    <h3>MacBook</h3>
                                    <p>이미 도래한 Mac의 미래.</p>
                                </div>
                                <img src="<c:url value="/resources/images/apple-change2.jpg"/>">
                            </a>
                        </li>
                        <li class="slideitem">
                            <a class="slides">
                                <div class="textbox">
                                    <h3>iPhone</h3>
                                    <p>도약의 시간.</p>
                                </div>
                                <img src="<c:url value="/resources/images/apple-change3.jpg"/>">
                            </a>
                        </li>
                        <li class="slideitem">
                            <a class="slides">
                                <div class="textbox">
                                    <h3>Apple Watch Series 6</h3>
                                    <p>건강의 미래,<br> 이미 손목 위에</p>
                                </div>
                                <img src="<c:url value="/resources/images/apple-change4.jpg"/>">
                            </a>
                        </li>
                        <li class="slideitem">
                            <a class="slides">
s                                <div class="textbox">
                                    <h3>AirPods Pro</h3>
                                    <p>온전히 빠져들게 하는<br>액티브 노이즈 캔슬링.</p>
                                </div>
                                <img src="<c:url value="/resources/images/apple-change5.jpg"/>">
                            </a>
                        </li>

                        <!-- 좌,우 슬라이드 버튼 -->
                        <div class="slide-control">
                            <div>
                                <label for="slide5" class="left"></label>
                                <label for="slide2" class="right"></label>
                            </div>
                            <div>
                                <label for="slide1" class="left"></label>
                                <label for="slide3" class="right"></label>
                            </div>
                            <div>
                                <label for="slide2" class="left"></label>
                                <label for="slide4" class="right"></label>
                            </div>
                            <div>
                                <label for="slide3" class="left"></label>
                                <label for="slide5" class="right"></label>
                            </div>
                            <div>
                                <label for="slide4" class="left"></label>
                                <label for="slide1" class="right"></label>
                            </div>
                        </div>

                    </ul>
                    <!-- 페이징 -->
                    <ul class="slide-pagelist">
                        <li><label for="slide1"></label></li>
                        <li><label for="slide2"></label></li>
                        <li><label for="slide3"></label></li>
                        <li><label for="slide4"></label></li>
                        <li><label for="slide5"></label></li>
                    </ul>
                </div>
            </div>
            <script type="text/javascript">
                var counter = 1;
                setInterval(function(){
                    document.getElementById('slide' + counter).checked = true;
                    counter++;
                    if(counter > 5){
                        counter = 1;
                    }
                },5000);
            </script>
        </section>
        <!-- 상품 리스트 -->
        <%@ include file="product/pd_list.jsp" %>
        
        <!-- 추천상품 -->
         <section class="hero recommendation-product1"> 
            <div class="container">
                <div class="title title-dark">
                    <h2 class="title-heading">iPhone 12 Pro</h2>
                    <h3 class="title-sub-heading">스피드 그 이상의 스피드.</h3>
                    <p class="title-price">1,350,000원 ~</p>
                </div>
            </div>
        </section>
        <!-- 추천상품 -->
         <section class="hero recommendation-product2"> 
            <div class="container">
                <div class="title title-dark">
                    <h2 class="title-heading">Apple Watch Series 6</h2>
                    <h3 class="title-sub-heading"> 손색없다.<br>부담 없다.</h3>
                    <p class="title-price">539,000원 ~</p>
                </div>
            </div>
        </section>
    </main>
</body>

</html>