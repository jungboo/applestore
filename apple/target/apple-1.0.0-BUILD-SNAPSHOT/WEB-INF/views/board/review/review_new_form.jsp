<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<meta charset="UTF-8">
<title>Apple Review Write</title>
<link rel="stylesheet" href="<c:url value="/resources/css/board_css/board_write.css"/>">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(function(){
        var msg = "<c:out value="${msg}"/>";
        if (msg != ""){
            alert(msg);
        }
    });
</script>
</head>
<body>
	<%@ include file="../../common/_header.jsp" %>
	
	<form action="${pageContext.request.contextPath}/review_add.ap" method="post" enctype="multipart/form-data">
	<div class=board_banner>
		<h1 class="board_name">Apple 리뷰 작성</h1>
		 <a class="buttons" href="${pageContext.request.contextPath}/review_list.ap">목록으로</a>
		
		<button class="buttons btn" type="submit">등록</button>
	    <button class="buttons btn" type="reset">리셋</button>
	    	   
	 </div>   
		<table id="boardWrite">
			<tbody>

				<input type="hidden" name="postId" value="${mbId}">
				<tr>

					<th scope="row"><label for="postTitle">제목:</label></th>

					<td><input type="text" name="postTitle" id="postTitle" size="64"
		placeholder="제목 입력" required="required"> </td>

				</tr>

				<tr>

					<th scope="row"><label for="productScore">상품 평점:</label></th>

					<td><select name="productScore" id="productScore">
                                  <option value="5.0" >5.0</option>
                                  <option value="4.0" >4.0</option>
                                  <option value="3.0" >3.0</option>
                                  <option value="2.0" >2.0</option>
                                  <option value="1.0" >1.0</option>
                                  <option value="0.0" >0.0</option>
                              </select></td>

				</tr>
                      
                      <tr>

					<th scope="row"><label for="upfiles">상품 사진 :</label></th>

					<td><input type="file" name="upfiles" id="upfiles" size="64"
		                placeholder="첨부 파일명" multiple="multiple"> 	
		<!-- multiple 다중 멀티 파일 업로드 가능 -->	</td>

				</tr>
                      
				<tr>

					<th scope="row"><label for="postContents">내용</label></th>

					<td><textarea rows="20" cols="140" 
			name="postContents" id="postContents"
			placeholder="게시글 내용 입력"></textarea></td>

				</tr>
                      
			</tbody>

		</table>

	</form>
	<%@ include file="../../common/_footer.jsp" %>
</body>
</html>