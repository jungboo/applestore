<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_side.jsp" %>
<div class="wrapper">
	<form>
        <h1>취소/반품/환불</h1>
        <div class="wrap">
            <table class="piece">
                <tr>
                    <th>분류</th>
                    <td>
                        <select>
                            <option></option>
                            <option>취소</option>
                            <option>교환</option>
                            <option>반품</option>
                            <option>환불</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>상품분류</th>
                    <td>
                        <select>
                            <option></option>
                            <option>상품명</option>
                            <option>상품코드</option>
                            <option>상품관리번호</option>
                        </select>
                        <input type="search" size="80">
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
                    <th>금액</th>
                    <th>결제상태</th>
                    <th>분류</th>
                </tr>
                <tr>
                    <td>xxxxxxxxx</td>
                    <td>구슬기</td>
                    <td>Mac 3.0</td>
                    <td>1290000원</td>
                    <td>입금전</td>
                    <td>취소신청</td>
                </tr>
            </table>
        </div>
	</form>
</div>