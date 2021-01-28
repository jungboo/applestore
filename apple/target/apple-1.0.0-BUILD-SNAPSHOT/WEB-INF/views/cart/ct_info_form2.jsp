<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">

div#body {
	background-color: rgb(240,240,240);
}

#cart_title {
	color: blue;
}

div#cart_main {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	background-color: white;
}


div#pr_panel {
	width: 800px;
	height: 120px;
	margin-left: auto;
	margin-right: auto;
}

div#cb {
	display: inline;
	height: 120px;
	width: 50px;
	float: left;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
}

#cb1 {
	display: block;
	margin: auto;
	margin-top: 53px;
}

div#pr_image {
	display: inline;
	width: 120px;
	height: 120px;
	/*            background-color: aqua;*/
	float: left;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
}

div#pr_box {
	display: inline;
	height: 120px;
	width: 530px;
	/*            background-color: coral;*/
	float: left;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
}

div#pr_name {
	display: inline-block;
	height: 35px;
	width: 520px;
	margin-top: 10px;
	margin-left: 5px;
	border-bottom: 1px solid black;
	text-align: center;
}

span#arrive_date {
	display: inline-block;
	margin-top: 20px;
	margin-left: 10px;
}

div#tp {
	display: inline;
	height: 120px;
	width: 92px;
	float: left;
	text-align: center;
	border: 1px solid gray;
}

.about_price {
	margin-top: 20px;
	float: right;
	margin-right: 11px;
}

span#total_price {
	display: inline-block;
	margin-top: 50px;
	padding-left: auto;
	padding-right: auto;
}

input#keep_shopping {
	background-color: rgb(080, 080, 255);
	height: 50px;
	width: 150px;
	text-align: center;
	color: white;
	float: left;
	margin-left: 300px;
	margin-top: 50px;
}

input#purchase {
	background-color: rgb(080, 080, 255);
	height: 50px;
	width: 150px;
	text-align: center;
	color: white;
	float: right;
	margin-right: 300px;
	margin-top: 50px;
}

div#conclude {
	display: block;
	width: 900px;
	height: 100px;
	margin-left: 50px;
	margin-right: 50px;
	margin-top: 40px;
	text-align: center;
	border: 4px solid gray;

}

div#menubar {
	display: inline-block;
	width: 800px;
	height: 30px;
	margin-left: 100px;
	margin-right: 100px;
	margin-top: 50px;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	padding-left: 15px;
	padding-top: 7px;
}

input#select_all {
	margin-left: 20px;
	margin-top: 7px;
	
}

div#deletebar {
	display: inline-block;
	width: 800px;
	height: 35px;
	padding-top:10px;
	margin-left: 100px;
	margin-right: 100px;
	margin-top: 50px;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	padding-left: 15px;
}

</style>

<script type="text/javascript">

// function deleteOneProduct(prId, prName, price, imagePath, count) {
	
// 	var arr = new Array();
// 	var i = 0;
	
// 	for(var name in ob) {
		
// 		arr[i] = new Array();
// 		arr[i]['name'] = name;
// 		eval!("arr["+i+"]['value'] = obj."+name);
// 		i++;
// 	}
	
// 	var len = arr.length;
// 	var str = '';
	
// 	for(var i = 0; i < len; i++) {
// 		str+=arr[i]['name'] +"="+arr[i]['value']+"&"; 
// 	}
	
// 	var path = "cart_delete_one.ap?" + str;
	
	var param = 'prId='prId+'&prName='+prName+'&price='+price+'&imagePath='+imagePath+'&count='+count;
	location.href = 'cart_delete_one.ap?' + param;
}
	


</script>

<title>장바구니</title>
</head>
<body>
	
	<%@ include file="../common/_header.jsp"%>

	<div id="body">


	<%
		session.setAttribute("mbId", 1);
		request.setAttribute("mbId", 1);
	%>


	<input type="hidden" name="memberId"
		value="${request.paramerer('mbId')}">
	

	<div id="cart_main">
	
		<div id="menubar">
			<input type="checkbox" class="select_all">&nbsp;
			전체선택 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			상품정보 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			상품가격
		</div>
			

		<form action="${pageContext.request.contextPath}/cart_buy_proc.ap"
			method="post">


			<c:forEach var="ct" varStatus="status" items="${prIds}" step="1"
				begin="0" end="${amount - 1}">
					
				<br>	
				<br>	
				<br>	
					
				<input type="hidden" name="prId${status.current}" value="${ct}"
					readonly>
				<input type="hidden" name="prName${status.current}"
					value="${names[status.index]}" readonly>
				<input type="hidden" name="prImagePath${status.current}"
					value="${imagePaths[status.index]}" readonly>
				<input type="hidden" name="prTotalPrice${status.current}"
					value="${prices[status.index]}" readonly>
				<input type="hidden" name="prCount${status.current}"
					value="${counts[status.index]}" min="1">


				<div id="pr_panel">

					<div id="cb">
						<input type="checkbox" id="cb1" class="deleteCb">
					</div>

					<div id="pr_image">${imagePaths[status.index]}</div>


					<div id="pr_box">

						<div id="pr_name">
							<span id="name"><a href="#">${names[status.index]}</a></span>
						</div>

						<span id="arrive_date">내일 1/11 도착</span>

						<button id="del_sel_product" type="button" class="about_price" 
						onclick="deleteOneProduct()">
						x</button>



						<select id="pCount" class="about_price">
							<option value="1" ${counts[status.index] eq 1 ? 'selected' : ''}>1</option>
							<option value="2" ${counts[status.index] eq 2 ? 'selected' : ''}>2</option>
							<option value="3" ${counts[status.index] eq 3 ? 'selected' : ''}>3</option>
							<option value="4" ${counts[status.index] eq 4 ? 'selected' : ''}>4</option>
							<option value="5" ${counts[status.index] eq 5 ? 'selected' : ''}>5</option>
							<option value="6" ${counts[status.index] eq 6 ? 'selected' : ''}>6</option>
							<option value="7" ${counts[status.index] eq 7 ? 'selected' : ''}>7</option>
							<option value="8" ${counts[status.index] eq 8 ? 'selected' : ''}>8</option>
							<option value="9" ${counts[status.index] eq 9 ? 'selected' : ''}>9</option>
							<option value="direct">10+</option>
						</select>
						
						 <span id="pPrice" class="about_price">${prices[status.index]}원</span>

					</div>

					<div id="tp">

						<span id="total_price">30000원</span>

					</div>
					
				</div>

			</c:forEach>
			
			<div id="deletebar">
				<input type="checkbox" class="select_all">&nbsp;
				전체선택
				<button id="delete_product" onclick="" style="background-color: white;">  선택삭제</button>
				
			</div>
						
			
			<div id="conclude">
				<br>
				<br>
				총 상품 가격 [ㅁㅁㅁㅁㅁㅁ] + 배송비 [000000] = 총 주문금액 [ㅁㅁㅁㅁㅁㅁㅁ]
	 		</div>

			<input id="keep_shopping" type="reset" value="계속 쇼핑하기">
			<input id="purchase" type="submit" value="결제 하기"> 		

		</form>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
		
	</div>
	

	</div>

	<%@ include file="../common/_footer.jsp"%>

</body>
</html>