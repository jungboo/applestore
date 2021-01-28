<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<style type="text/css">
    .left_sidemenu {
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 20px;
        padding: 0px;
        margin: 0px;
        width: 300px;
        background-color: whitesmoke;
    }

     .left_sidemenu {
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 20px;
        padding: 0px;
        margin: 0px;
        width: 300px;
        background-color: whitesmoke;
    }

    .menubox1 ul {
        text-align: left;
        margin: 0px;
        padding: 0px;
        list-style: none;
    }

    .menubox1 ul li a {
        text-decoration: none;
        color: inherit;

    }

    .menubox1 ul li ul {
        display: none;
    }

    .menubox1 ul li:hover>ul {
        display: block;

    }

    .menubox1 ul li:hover>a {
        background-color: black;
        color: white;
        font-weight: bolder;


    }

    .menubox1 ul ul {
/*        background-color: #afafaf;*/
        background-color: white;
    }

    .menubox1 a {
         text-decoration: none;
    }

    .menubox1 ul li {
        padding: 20px;
    }

    /*#mypage_contents_layout {
        position: relative;
        left: 305px;
        top: -290px;
    }*/
    #grid_mypage {
        width: 1600px;
        display: grid;
        grid-template-columns: 305px 1090px;
        grid-gap: 100px;
    }

    #show_mypageList ul {
        list-style: none;
    }
</style>

<body>
    <div id="grid_mypage">
        <div class="left_sidemenu">
            <nav class="menubox1">
                <ul>
                    <li><a href="">내 계정</a>
                        <ul>
                            <li><a href="">나의 정보</a></li>
                            <li><a href="">로그인 정보</a></li>
                        </ul>
                    </li>
                    <li><a href="">배송/주문</a>
                        <ul>
                            <li><a href="">나의 주문내역</a></li>
                            <li><a href="">a</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                        </ul>
                    </li>
                    <li><a href="">내 게시판</a>
                        <ul>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                            <li><a href="">2차메뉴아이템</a></li>
                        </ul>
                    </li>
                    <li><a href="">장바구니</a></li>
                </ul>
            </nav>
        </div>

        <div id="mypage_contents_layout">

            <h2 style="text-align: center">마이페이지</h2>
            <hr style="border-color: black">
            <b>회원님의 쇼핑 정보를 확인 하세요 !</b>
            <div id="show_mypageList">
                <ul>
                    <li>
                        <h3>내계정</h3>
                        <p>회원의 개인정보 확인 및 수정이 가능합니다.</p>
                    </li>
                    <li>

                        <h3>배송/주문</h3>
                        <p>고객님의 온라인 주문 상태와 관련 사항에 대한 정보를 알아보세요. </p>
                    </li>
                    <li>
                        <h3>내 게시판</h3>
                        <p>구매하신 상품에 관한 상품평 등록및 관리가 가능합니다.</p>
                    </li>
                    <li>
                        <h3>장바구니</h3>
                        <p>회원님이 추가하신 상품 및 결제가 가능합니다.</p>
                    </li>
                </ul>
            </div>


        </div>
    </div>
</body></html>