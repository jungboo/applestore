<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<div class="wrapper">
	<form>
        <h1>전체주문목록</h1>
        <div class="wrap">
            <table class="piece">
                <tr>
                    <th>검색분류</th>
                    <td>
                        <select>
                            <option></option>
                            <option>주문번호</option>
                            <option>주문자명</option>
                            <option>주문자아이디</option>
                            <option>주문자이메일</option>
                            <option>주문자전화번호</option>
                        </select>
                        <input type="search" size="80">
                    </td>
                </tr>
                <tr>
                    <th>상품분류</th>
                    <td>
                        <select>
                            <option></option>
                            <option>상품명</option>
                            <option>상품코드</option>
                            <option>상품관리번호&nbsp;&nbsp;&nbsp;</option>
                        </select>
                        <input type="search" size="80">
                    </td>
                </tr>
                <tr>
                    <th>입금/결제상태</th>
                    <td>
                        <input type="radio" id="all" name="pay" checked>
                        <label for="all">전체</label>
                        <input type="radio" id="b_deposit" name="pay">
                        <label for="b_deposit">입금전</label>
                        <input type="radio" id="a_deposit" name="pay">
                        <label for="a_deposit">입금완료</label>
                        <input type="radio" id="pay" name="pay">
                        <label for="pay">결제완료</label>
                    </td>
                </tr>
            </table>
            <input type="submit" class="mit" value="검색">
        </div>
        <div class="wrap">
            <table class="piece">
                <tr>
                    <th>주문번호</th>
                    <th>주문자</th>
                    <th>상품명</th>
                    <th>결제금액</th>
                    <th>결제상태</th>
                </tr>
                <tr>
                    <td>xxxxxxxxx</td>
                    <td>구슬기</td>
                    <td>Mac 3.0</td>
                    <td>1290000원</td>
                    <td>입금전</td>
                </tr>
            </table>
        </div>
	</form>
</div>