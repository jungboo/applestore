<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Your Name">
<meta name="description" content="Example description">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon" href="" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/mainpage.css"/>">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(function(){
        var msg = "<c:out value="${msg}"/>";
        if (msg != ""){
            alert(msg);
        }
    });
    function searchitems(pg){
        var target= $("#searchkeyword").val();
        
        $.ajax({
           type: "get",
           url: "${pageContext.request.contextPath}/product_search_main.ap",
           data: {"search": encodeURI(encodeURIComponent(target)), "pg" : pg},
           dataType : "json",
           success: function(data){ //검색 결과 성공
              
              var datahtml ='';
              datahtml += '<h2 class="pd-title"> 검색 결과 :' +data.maxPg + '  개 </h2>';
              
              
              if(data.maxPg == 0){
                 datahtml += '<div style = "margin: 20px auto , text-align:center; , width= 100%;"><h2>상품이 존재 하지 않습니다. </h2></div>';
              }else{
                 datahtml += '<div class="box1"><ul class="prod_wrap">';
           
                 for (var i = 0; i < data.pdList.length; i++) {
                    datahtml +=   '<li style="list-style: none;" class="prod_li">';
                    datahtml += '<div class="prod_st" onclick="showProductPage('+data.pdList[i].id+')">';
                    datahtml += '<img src="${pageContext.request.contextPath}/resources/images/product/'+data.pdList[i].image_path+'">';
                    datahtml += '<h4>'+data.pdList[i].name+'</h4>';
                    datahtml += '<p class="pd-price">'+data.pdList[i].price+'원</p>';
                    datahtml +='</div></li>';
                       
                 }
                 datahtml +='</ul>'
                 datahtml +='<div class="page-btn">';
                 
                 if (pg > 1){
//                     datahtml +='<a href="${pageContext.request.contextPath}/product_search.ap?pg='+(pg-1)+'&search='+target+'" class="page-btn-link"><span class="page-span">이전</span></a>';
                    datahtml +='<a onclick="searchitems('+(pg-1)+')" class="page-btn-link"><span class="page-span">이전</span></a>';
                 }
                 
                 var totalCount =  data.maxPg; // 11
                 var countList = 4;
                 var totalPage = Math.ceil(totalCount / countList); //2.75
//                  if (totalCount > countList * totalPage) {
//                     ++totalPage;
//                  }   
                 console.log(totalPage);

                 for (var i = 1; i <= totalPage ; i++) {
//                     datahtml +=   '<a href="${pageContext.request.contextPath}/product_search.ap?pg='+i+'&search='+target+'" class="page-btn-link"><span class="page-span">'+i+'</span></a>';
                    datahtml +=   '<a onclick="searchitems('+i+')" class="page-btn-link"><span class="page-span">'+i+'</span></a>';
                }
                 if (pg < totalPage){
//                     datahtml +='<a href="${pageContext.request.contextPath}/product_search.ap?pg='+(pg+1)+'&search='+target+'" class="page-btn-link"><span class="page-span">다음</span></a>';
                    datahtml +='<a onclick="searchitems('+(pg+1)+')" class="page-btn-link"><span class="page-span">다음</span></a>';
                 }
                 datahtml+='</div>';
              }
              
                 console.log(datahtml);
                 $('#prodList_wrap_forsearch').html(datahtml);
             
           },
           error: function(request, status, error){
           cosole.log(error);
        }
        });
     }
 </script>
 <style type="text/css">
 #prodList_wrap_forsearch{
    text-align: center;}
 </style>
</head>
<body>
	<header>
		<a href="/apple/"> <img class="logo"
			src="<c:url value="/resources/images/icon/mainLogo.png" />"
			alt="logo">
		</a>
		<nav class="nav-category">
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_all_list.ap"
				class="nav-link nav-link-c">All</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_category_list.ap?category=Mac"
				class="nav-link nav-link-c">Mac</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_category_list.ap?category=MacBook"
				class="nav-link nav-link-c">MacBook</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_category_list.ap?category=iPhone"
				class="nav-link nav-link-c">iPhone</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_category_list.ap?category=iPad"
				class="nav-link nav-link-c">iPad</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_category_list.ap?category=Watch"
				class="nav-link nav-link-c">Watch</a></li>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/product_category_list.ap?category=Accessories"
				class="nav-link nav-link-c">Accessories</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link nav-link-c nav-cummunity">Community</a>
				<div class="sub-menu">
					<ul>
						<li><a
							href='${pageContext.request.contextPath}/notice_list.ap'>Notice</a></li>
						<li><a href="${pageContext.request.contextPath}/qna_list.ap">QnA</a></li>
						<li><a
							href="${pageContext.request.contextPath}/review_list.ap">Review</a></li>
					</ul>
				</div></li>
				
				<!-- 검색 바  -->
            <li class="nav-link nav-link-button">
            <a  id="search" class="nav-link nav-link-search"> 
            <img class="search" src="<c:url value="/resources/images/icon/search.png"/>" alt="search"></a>
            </li>
      </nav>
      <div class="search-form">
         <form action="${pageContext.request.contextPath}/main.ap">
            <input type="text" name="search" placeholder="상품명/상품디스크립션/스펙/카테고리 로 검색가능"  id="searchkeyword">
            <input type="button" onclick="searchitems(1)" value="SearchItems">
         </form>
		</div>
		<a class="close"><i class="fa fa-times"></i></a>
		<script src="https://code.jquery.com/jquery-3.5.1.js"
			integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
			crossorigin="anonymous">
			-->
		</script>
		<script type="application/javascript">
			
         $(document).ready(function(){
             $('#search').click(function(){
                 $('.nav-link-c').addClass('hide-link')
                  $('.search-form').addClass('active')
                 $('.close').addClass('active')
                 $('#search').hide()
             })
             $('.close').click(function(){
                 $('.nav-link-c').removeClass('hide-link')
                  $('.search-form').removeClass('active')
                 $('.close').removeClass('active')
                 $('#search').show()
             })
         })
     
		</script>
		<nav class="nav-login">
		<c:if test="${not empty mbId}">
		<li class="nav-item"><a href="member_logout.ap" class="nav-link">Logout</a></li>
		</c:if>
		<c:if test="${empty mbId}">
			<li class="nav-item"><a href="member_login_form.ap"
				class="nav-link">Login</a></li>
			<li class="nav-item nav-item-button"><a
				href="member_join_agreement.ap" class="nav-link">Join</a></li>
		</c:if>
			<li class="nav-item"><a href="${pageContext.request.contextPath}/mypage_main.ap" class="nav-link">Mypage</a></li>
			<li class="nav-item"><a href="${pageContext.request.contextPath}/cart_info_form.ap" class="nav-link nav-link-cart">
					<img class="search" src="<c:url value="/resources/images/icon/cart.png"/>" alt="search">
			</a></li>
		</nav>
	</header>
</body>
</html>