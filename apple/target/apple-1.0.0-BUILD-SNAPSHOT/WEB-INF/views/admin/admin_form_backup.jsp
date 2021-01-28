<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<%-- ${pageContext.request.contextPath} --%>
	<meta charset="utf-8">
	<title>관리자 페이지</title>
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>
	<%@ include file="../common/_side.jsp" %>
    <div class="wrapper">
<%-- 	    <%@ include file="../common/_manual.jsp" %> --%>
<%-- 	    <%@ include file="../common/_add_product.jsp" %> --%>
<%-- 		<%@ include file="../common/_product_list.jsp" %> --%>
<%-- 		<%@ include file="../common/_order_list.jsp" %> --%>
<%-- 		<%@ include file="../common/_cancel.jsp" %> --%>
<%-- 	    <%@ include file="../common/_member_admin.jsp" %> --%>
<%-- 	  	<%@ include file="../common/_board_setting.jsp" %> --%>
<%-- 	  	<%@ include file="../common/_post_admin.jsp" %>         --%>
    </div>
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
    <script>
// 		$(document).ready(function(){
// 			$(".wrapper .tab_content").hide();
// 			$(".wrapper .tab_content:first-child").show();

// 			$("ul li .aa").click(function(){
			  
// 			  $("ul li .aa").removeClass("act");
// 			  $(this).addClass("act");
			  
// 			  var current_tab = $(this).attr("data-list");
// 			  $(".wrapper .tab_content").hide();
// 			  $("."+current_tab).show();
// 			})
// 		});
	</script>
</body>
</html>