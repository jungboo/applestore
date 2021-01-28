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

    #div_pw_form_area {
        padding: 80px 80px;
    }

    .pwbox_layout {
        width: 700px;
        height: 100px;
    }

    .pwbox_layout input[type=password] {
        width: 500px;
        height: 50px;
        border: 1px solid;
        border-color: white;
        border-bottom-color: beige;
        border-bottom-style: groove;
    }

    #pwchange_submit {
        background-color: black;
        color: white;
        font-size: 18px;
        width: 200px;
        height: 40px;
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
                            <li><a href="">비밀번호 변경</a></li>
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
            <div>
                <h1>비밀번호 변경</h1>
                <p> 회원 로그인시 필요한 비밀번호의 변경이 가능합니다.</p>
            </div>
            <hr>

            <div id="div_pw_form_area">
              
                <div class="pwbox_layout">
                    <div class="pw_label_layout">
                        <label for="">새 비밀번호</label>
                    </div>
                    <input type="password" name=password>

                </div>
                <div class="pwbox_layout">
                    <div class="pw_label_layout">
                        <label for="">새 비밀번호 재입력</label>
                    </div>
                    <input type="password" name=password>

                </div>

                <div class="pwbox_layout"> <input type="submit" value="비밀번호 변경" id="pwchange_submit"></div>

            </div>



        </div>
    </div>
</body></html>