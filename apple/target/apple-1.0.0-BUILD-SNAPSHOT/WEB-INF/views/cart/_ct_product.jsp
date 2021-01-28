<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp"%>




				<div id="pr_panel">

						<div id="cb">
							<input type="checkbox" id="cb1" class="deleteCb">
						</div>

						<div id="pr_image">
							<img class="img_class"
								src="${pageContext.request.contextPath}/resources/images/product/${ct.imagePath}">
						</div>


						<div id="pr_box">

							<div id="pr_name">
								<span id="name"><a href="#">${ct.prName}</a></span>
							</div>

							<span id="arrive_date">내일 1/11 도착</span>

							<button id="del_sel_product" type="button" class="about_price"
								onclick="deleteOneProduct(${ct.prId})">x</button>



							<select id="pCount" class="about_price">
								<option value="1" ${ct.count eq 1 ? 'selected' : ''}>1</option>
								<option value="2" ${ct.count eq 2 ? 'selected' : ''}>2</option>
								<option value="3" ${ct.count eq 3 ? 'selected' : ''}>3</option>
								<option value="4" ${ct.count eq 4 ? 'selected' : ''}>4</option>
								<option value="5" ${ct.count eq 5 ? 'selected' : ''}>5</option>
								<option value="6" ${ct.count eq 6 ? 'selected' : ''}>6</option>
								<option value="7" ${ct.count eq 7 ? 'selected' : ''}>7</option>
								<option value="8" ${ct.count eq 8 ? 'selected' : ''}>8</option>
								<option value="9" ${ct.count eq 9 ? 'selected' : ''}>9</option>
								<option value="direct"></option>
							</select> <span id="pPrice" class="about_price">${ct.price}원</span>

						</div>

						<div id="tp">

							<span id="total_price">30000원</span>

						</div>

					</div>