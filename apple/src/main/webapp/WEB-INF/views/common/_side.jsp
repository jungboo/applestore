<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<%-- ${pageContext.request.contextPath} --%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>관리자 페이지</title>
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

	<div class="btn">
            <span class="fas fa-bars"></span>
    </div>
    <nav class="sidebar">
        <div class="text">관리자 메뉴</div>
        <ul>
            <li><a href="admin_stat.ap" class="aa act">통계</a></li>
            <li>
                <a href="#" class="produt_btn">상품관리<span class="fas fa-caret-down first"></span></a>
                <ul class="produt_show">
                    <li><a href="admin_add_product.ap" class="aa">상품 등록</a></li>
                    <li><a href="admin_product_list.ap" class="aa">상품 목록</a></li>
                    <li><a href="admin_product_search_form.ap" class="aa">상품 검색</a></li>
                </ul>
            </li>
            <li>
                <a href="#" class="order_btn">주문관리<span class="fas fa-caret-down second"></span></a>
                <ul class="order_show">
                    <li><a href="admin_order_list.ap" class="aa">전체주문 목록</a></li>
                    <li><a href="cancel_form.ap" class="aa">반품/환불</a></li>
                </ul>
            </li>
            <li><a href="mb_admin.ap" class="aa">고객관리</a></li>
            <li>
                <a href="#" class="board_btn">게시판관리<span class="fas fa-caret-down third"></span></a>
                <ul class="board_show">
                    <li><a href="nt_admin.ap" class="aa">공지사항 관리</a></li>
                    <li><a href="rv_admin.ap" class="aa">리뷰 관리</a></li>
                    <li><a href="qa_admin.ap" class="aa">QnA 관리</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    
	<script>
        $('.btn').click(function() {
            $(this).toggleClass("click");
            $('.sidebar').toggleClass("show");
            $('.wrapper').toggleClass("show0");
        });
        $('.produt_btn').click(function() {
            $('nav ul .produt_show').toggleClass("show");
            $('nav ul .first').toggleClass("rotate");
        });
        $('.order_btn').click(function() {
            $('nav ul .order_show').toggleClass("show1");
            $('nav ul .second').toggleClass("rotate");
        });
        $('.board_btn').click(function() {
            $('nav ul .board_show').toggleClass("show2");
            $('nav ul .third').toggleClass("rotate");
        });
        $('nav ul li').click(function() {
            $(this).addClass("active").siblings().removeClass("active");
        });
    </script>
</body>
</html>