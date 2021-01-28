<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>

<script type="text/javascript">
	function showMsg(pdId) {
		if(confirm("상품을 수정하시겠습니까??")==true) {
		} else {
			location.href = 'admin_product_show.ap?pdId=' + pdId;
			return false;
		}
		
	}
	
</script>

<div class="wrapper">
      <form class="tab_content tab_1" action="admin_product_edit.ap" method="post">
         <input type="hidden" name="id" value="${pd.id}">
         <div>
            <h1>Apple Store 상품수정</h1>
            <div class="wrap">
               <table class="piece">
                  <tr>
                     <th>상품명<sub> (필수)</sub></th>
                     <td><input type="text" name="name" size="100"
                        placeholder="예시) 맥북pro3.0" required value="${pd.name}"></td>
                  </tr>
                  <tr>
                     <th>판매가<sub> (필수)</sub></th>
                     <td><input type="text" name="price" placeholder="예시) 399000"
                        required value="${pd.price}원"></td>
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
                     <td><input type="text" placeholder="예시) 영어로입력: black" name="color" value="${pd.color}"> <input type="color"></td>
                  </tr>
                  <tr>
                     <th>상품스펙</th>
                     <td><input type="text" size="50" placeholder="상품스펙 입력"
                        name="spec" value="${pd.spec}"></td>
                  </tr>
                  <tr>
                     <th>이미지</th>
                     <td><input type="file" value="파일선택" name="image_path"></td>
                  </tr>
                  <tr>
                     <th>상세설명</th>
                     <td><textarea rows="30" cols="100" name="description" ></textarea></td>
                  </tr>
               </table>
               <input type="submit" class="mit" value="상품수정" onclick="showMsg(${pd.id})">
            </div>
         </div>
      </form>
</div>