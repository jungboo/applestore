<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="left_sidemenu">
	<nav class="menubox1">
		<ul>
			<li><a href="">내 계정</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/mypage_member_info.ap">나의 정보</a></li>
					<li><a href="${pageContext.request.contextPath}/mypage_member_pw_info.ap">비밀번호 변경</a></li>
					<li><a href="${pageContext.request.contextPath}/mypage_member_address_info.ap">주소 설정</a></li>
				</ul></li>
			<li><a href="">주문/배송</a>
				<ul>
					<li><a href="order_list.ap">나의 주문내역</a></li>
					<li><a href="">배송 확인</a></li>
				</ul></li>
			<li><a href="">내 게시판</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/mypage_qna.ap">Q&A</a></li>
					<li><a href="${pageContext.request.contextPath}/mypage_review.ap">Review</a></li>
				</ul></li>
			<li><a href="${pageContext.request.contextPath}/cart_info_form.ap">장바구니</a></li>
		</ul>


	</nav>
</div>