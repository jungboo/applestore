<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp"%>

<style>
.piece th {
	text-align: left;
	font-size: 18px;
	border: 1px solid lightgray;
	background-color: #f3f3f3;
	margin: 0px;
}

.piece {
	width: 90%;
}

.undo {
	padding: 5px;
	margin-bottom: 10px;
	font-size: 15px;
	width: 100px;
}

.undo:hover, .update:hover, .delete:hover {
	background-color: black;
	color: white;
}
</style>


<script type="text/javascript">
	function backToProductPage() {
		//window.location.href = URL;
		location.href = 'admin_product_list.ap?pg=1';
		// 현재 페이지 변경 (바로 이동 - 동기방식 http get)
		
	}
	
	function moveToEditPage(pdId) {
		
		location.href = 'admin_product_edit.ap?pdId='+pdId
	}
	
	function deleteProduct(pdId) {
		
		location.href = 'admin_product_delete.ap?pdId='+pdId
	}
	
	
</script>

<body>
	<div class="wrapper">
		<h3>
			Apple Store 상품 '
			<c:out value="${pd.name }" />
			' 상세조회
		</h3>
		<div id="wrap">
			<table class="piece">
				<tr>
					<th>상품번호:</th>
					<td>${pd.id}번</td>
				</tr>
				<tr>
					<th>상품이름:</th>
					<td>${pd.name}</td>
				</tr>
				<tr>
					<th>상품가격:</th>
					<td>${pd.price}원</td>
				</tr>
				<tr>
					<th>카테고리:</th>
					<td>${pd.category}</td>
				</tr>
				<tr>
					<th>컬러:</th>
					<td>${pd.color}</td>
				</tr>
				<tr>
					<th>스펙:</th>
					<td><c:out value="${pd.spec}" /></td>
				</tr>

				<tr>
					<th>이미지경로</th>
					<td><img
						src="${pageContext.request.contextPath}/resources/images/product/${pd.image_path}"><br>
						&nbsp;<c:out value="${pd.image_path }" /></td>
				</tr>
				<tr>
					<th>등록일:</th>
					<td><c:out value="${pd.reg_day }" /></td>
				</tr>
			</table>
			<br>
			<button onclick="backToProductPage()" class="undo">
				<i class="fas fa-undo">돌아가기</i>
			</button>
			<button onclick="moveToEditPage(${pd.id})" class="update">
				<i class="fas fa-edit">수정</i>
			</button>
			<button class="delete" onclick="deleteProduct(${pd.id})">
				<i class="fas fa-trash-alt">삭제</i>
			</button>

		</div>
	</div>
</body>
</html>