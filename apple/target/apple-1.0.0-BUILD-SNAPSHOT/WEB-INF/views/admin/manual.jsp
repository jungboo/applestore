<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<div class="wrapper">
	<form>
		<h4></h4>
	    <img src="${pageContext.request.contextPath}/resources/images/logo.png" height="100px">
	     <h4></h4>
	     <div class="wrap">
	         <h1>상품관리 메뉴얼</h1>
	         <h3>&nbsp;</h3>
	         <img src="${pageContext.request.contextPath}/resources/images/1.PNG" width="100%">
	         <b class="main_b">상품명</b>
	         <span class="main_s">- 등록할 상품의 상품명을 입력할 수 있습니다.</span>
	         <b class="main_b">판매가</b>
	         <span class="main_s">- 판매가를 설정할 수 있습니다.</span>
	         <b class="main_b">상품코드</b>
	         <span class="main_s">- 상품코드를 만들어 설정할 수 있습니다.</span>
	         <b class="main_b">상품컬러</b>
	         <span class="main_s">- 상품의 컬러를 결정할 수 있습니다.</span>
	         <b class="main_b">카테고리</b>
	         <span class="main_s">- 등록할 상품의 종류를 선택할 수 있습니다.</span>
	         <b class="main_b">이미지</b>
	         <span class="main_s">- 이미지를 넣을 수 있습니다.</span>
	         <b class="main_b">상세설명</b>
	         <span class="main_s">- 상품 상세설명을 입력하면 쇼핑몰의 상품 상세페이지 화면에 표시됩니다.</span>
	         <h3>&nbsp;</h3>
	         <img src="${pageContext.request.contextPath}/resources/images/2.PNG" width="100%">
	         <b class="main_b">검색분류</b>
	         <span class="main_s">- 상품명, 상품코드, 상품관리번호로 조회할 수 있습니다.</span>
	         <b class="main_b">삼품분류</b>
	         <span class="main_s">- Desktop, MacBook, iPhone, Watch, Acc 카테고리별로 조회할 수 있습니다.</span>
	         <b class="main_b">하단 검색결과</b>
	         <span class="main_s">- 하단부에 검색 결과리스트가 출력됩니다.</span>
	     </div>
	     <div class="wrap">
	         <h1>주문관리 메뉴얼</h1>
	         <h3>&nbsp;</h3>
	         <img src="${pageContext.request.contextPath}/resources/images/3.PNG" width="100%">
	         <b class="main_b">검색분류</b>
	         <span class="main_s">- 주문번호, 주문자명, 주문자아이디, 주문자이메일, 주문자전화번호로 조회할 수 있습니다.</span>
	         <b class="main_b">상품분류</b>
	         <span class="main_s">- 상품명, 상품코드, 상품관리번호로 조회 할 수 있습니다.</span>
	         <b class="main_b">입금/결제상태</b>
	         <span class="main_s">- 입금전, 입금후, 결제완료로 나누어 조회할 수 있습니다.</span>
	         <b class="main_b">하단 검색결과</b>
	         <span class="main_s">- 하단부에 검색결과 리스트가 출력됩니다.</span>
	         <span class="main_s">- 체크박스를 활용하여 삭제 및 수정할 수 있습니다.</span>
	         <h3>&nbsp;</h3>
	         <img src="${pageContext.request.contextPath}/resources/images/4.PNG" width="100%">
	         <b class="main_b">분류</b>
	         <span class="main_s">- 취소, 교환, 반품, 환불별로 조회할 수 있습니다.</span>
	         <b class="main_b">삼품분류</b>
	         <span class="main_s">- 상품명, 상품코드, 상품관리번호로 조회할 수 있습니다.</span>
	         <b class="main_b">검색결과</b>
	         <span class="main_s">- 하단부에 검색 결과리스트가 출력됩니다.</span>
	         <span class="main_s">- 체크박스를 활용하여 삭제 및 수정할 수 있습니다.</span>
	     </div>
	     <div class="wrap">
	         <h1>고객관리 메뉴얼</h1>
	         <h3>&nbsp;</h3>
	         <img src="${pageContext.request.contextPath}/resources/images/5.PNG" width="100%">
	         <b class="main_b">검색어</b>
	         <span class="main_s">- 아이디, 이메일, 전화번호로 조회할 수 있습니다.</span>
	         <b class="main_b">나이</b>
	         <span class="main_s">- 나이 범위를 지정하여 조회할 수 있습니다.</span>
	         <b class="main_b">가입일</b>
	         <span class="main_s">- 가입일 범위를 지정하여 조회할 수 있습니다.</span>
	         <b class="main_b">하단 검색결과</b>
	         <span class="main_s">- 하단부에 검색결과 리스트가 출력됩니다.</span>
	     </div>
	     <div class="wrap">
	        <h1>게시판관리 메뉴얼</h1>
	        <h3>&nbsp;</h3>
	        <img src="${pageContext.request.contextPath}/resources/images/6.PNG" width="100%">
	        <b class="main_b">분류</b>
	        <span class="main_s">- 운영/상품으로 분류하여 권한 설정을할 수 있습니다.</span>
	        <b class="main_b">게시판제목</b>
	        <span class="main_s">- 게시판제목을 수정할 수 있습니다.</span>
	        <b class="main_b">관리번호</b>
	        <span class="main_s">- 해당 게시판의 관리번호를 표시합니다.</span>
	        <b class="main_b">권한</b>
	        <span class="main_s">- 분류에따라 권한이 설정됩니다.</span>
	        <b class="main_b">표시여부</b>
	        <span class="main_s">- 표시/표시안함으로 구분하여 해당 게시판의 표시여부를 설정할 수 있습니다.</span>
	        <h3>&nbsp;</h3>
	        <img src="${pageContext.request.contextPath}/resources/images/7.PNG" width="100%">
	        <b class="main_b">게시판선택</b>
	        <span class="main_s">- 전체, 공지사항, 구매/사용후기, Q&amp;A,
	               	 구매/사용후기 답글, Q&amp;A 답글별로 조회할 수 있습니다.</span>
	        <b class="main_b">게시글찾기</b>
	        <span class="main_s">- 제목, 작성자이름, 작성자아이디로 조회할 수 있습니다.</span>
	        <b class="main_b">답변상태</b>
	        <span class="main_s">- 전체, 답변전, 답변완료로 나누어 조회할 수 있습니다.</span>
	        <b class="main_b">공지사항작성</b>
	        <span class="main_s">- 공지사항작성 버튼을 활용하여 공지사항을 작성할 수 있습니다.</span>
	        <b class="main_b">검색결과</b>
	        <span class="main_s">- 하단부에 검색 결과리스트가 출력됩니다.</span>
	        <span class="main_s">- 체크박스를 활용하여 삭제 및 수정할 수 있습니다.</span>
	        <span class="main_s">- 답변버튼을 활용하여 답글을 작성할 수 있습니다..</span>
	    </div>
	</form>
</div>