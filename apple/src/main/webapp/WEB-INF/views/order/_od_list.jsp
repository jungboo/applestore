<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<!--   target 	<div id="ov_list"> -->
		
			<c:forEach var="od" items="${odList}" varStatus="status">	
				
				<div id="od_list">
				
					<div id="ov_status">
						<b id="ov_date">${od.orderDate}</b> 
						<b id="ov_status">${od.orderStatus}</b>
						<b id="ov_price">${od.totalPrice}원</b> 
					</div>  
					
					
					
					<c:forEach var="prct" items="${od.prctList}" varStatus="status">
								
	
	
					<div id="prct_box">

						<div id="prct_image">
							<img id="ov_prct_image"
								src="${pageContext.request.contextPath}/resources/images/product/${prct.imagePath}">
						</div>

						<div id="prct_name">
							<span id="ov_prct_name"><a href="product_show.ap?pdId=${prct.prId}">${prct.name}</a></span>
						</div>

						
						<span id="prct_price">${prct.price}원</span>
						
						<span id="prct_count">${prct.count}개</span>

					</div>
						
					
					</c:forEach>
					
					<div id="buttons">
					
						<button id="bt_cancle_order" onclick="cancleOrder(${od.orderId})">구매 취소</button>						
					
						<button id="bt_check_delievery">배송 확인</button>
							
					</div>
						
				
				</div>
				
			</c:forEach>
		
		
		
		
		
		
		
		
		
		
		
		