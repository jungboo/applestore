<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<style type="text/css">
.body {
	width: 100%;
	position: relative;
}
</style>
<meta charset="UTF-8">
<title>Apple Store 상품 추가</title>
</head>
<body>

	<div class="body">
		<h3>Apple Store 상품 추가</h3>
		<form class="tab_content tab_1" action="product_add.ap" method="post">
			<div>
				<h1>상품등록</h1>
				<div class="wrap">
					<table class="piece">
						<tr>
							<th>상품명<sub> (필수)</sub></th>
							<td><input type="text" name="name" size="100" placeholder="예시) 맥북pro3.0" required><sub> (100자)</sub></td>
						</tr>
						<tr>
							<th>판매가<sub> (필수)</sub></th>
							<td><input type="number" name="price" placeholder="예시) 399000" required step="5000" min="10000">원</td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td><select name="category">
									<option>Mac</option>
									<option>MacBook</option>
									<option>iPad</option>
									<option>iPhone</option>
									<option>Watch</option>
									<option>Accessories</option>
							</select></td>
						</tr>
						<tr>
							<th>상품컬러</th>
							<td><input type="text" placeholder="예시) 영어로입력: black" name="color"> <input type="color"></td>
						</tr>
						<tr>
							<th>상품스펙</th>
							<td><input type="text" size="50" placeholder="상품스펙 입력" name="spec"></td>
						</tr>
						<tr>
							<th>이미지</th>
							<td><input type="file" value="파일선택" name="image_path"></td>
						</tr>
						<tr>
							<th>상세설명</th>
							<td><textarea rows="30" cols="100" name="description"></textarea></td>
						</tr>
					</table>
					<input type="submit" class="mit" value="상품등록">
				</div>
			</div>
		</form>
	</div>
	<
	<%@ include file="../common/_footer.jsp"%>
</body>
</html>