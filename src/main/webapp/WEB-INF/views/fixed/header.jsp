<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>치즈메이트</title>

    <!-- 구글 폰트 영역 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- 슬라이드 영역 -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <!-- 기본 리셋 영역 -->
    <link rel="stylesheet" href="/css/reset.css">
    <!-- 사용자 영역 -->
    <link rel="stylesheet" href="/css/mystyle.css">
    <link rel="stylesheet" href="/css/mainslider.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script>
        const Image = (function() {
            let imginfo = [];

            return {
                getImgInfo: function() {
                    return imginfo;
                }
            };
        })();

        const cssImage = (function() {
            let imginfo = {};

            <c:forEach items="${imglist}" var="img">
                imginfo['${img.o_name}'] = "${img.img_full_rt}";
            </c:forEach>

            return {
                getImgInfo: function() {
                    return imginfo;
                }
            };
        })();

    </script>

</head>
<body>
<header id="header_box">
    <div id="herder_top">
        <div id="logobox">
            <a href="/" id="cheezmate"><img src="" alt="우리들의 팀메이트 치즈마켓" id="logoimg"></a>
        </div>
        <div id="searchbox">
            <input type="text" name="search" id="search" autocomplete="off">
        </div>
        <div id="subnavbox">
            <div id="subnavinnerbox">
                    <span class="subnavspan">
                        <a href="/chat2" id="chatlink" class="subnavlink">
                            <img src="" alt="chatting" id="chaticon" class="subnavicon">
                            <span class="subnavtext">치즈톡</span>
                        </a>
                    </span>
                <span class="subnavspan">
                    <form id="postForm" action="/sale/write" method="POST" style="display: none;">
                        <input type="hidden" name="addr_cd" value="${sessionScope.userAddrCdDtoList.get(0).addr_cd}">
                        <input type="hidden" name="addr_name" value="${sessionScope.userAddrCdDtoList.get(0).addr_name}">
                    </form>

                    <a href="#" id="storelink" class="subnavlink" onclick="document.getElementById('postForm').submit(); return false;">
                        <img src="" alt="sell" id="storeicon" class="subnavicon">
                        <span class="subnavtext">판매하기</span>
                    </a>
                </span>
                <span class="subnavspan">
                        <a href="/myPage/main" id="userlink" class="subnavlink">
                            <img src="" alt="user" id="usericon" class="subnavicon">
                            <span class="subnavtext">마이</span>
                        </a>
                    </span>
            </div>
        </div>
    </div>
    <div id="nav">
        <ul id="navlist">
            <li class="navli">
                <a href="/sale/list"><span class="navtext">판매/나눔</span></a>
            </li>
            <li class="navli">
                <a href="/event"><span class="navtext">이벤트</span></a>
            </li>
            <li class="navli">
                <a href="/community/list"><span class="navtext">커뮤니티</span></a>
            </li>
            <li class="navli">
                <a href="/qna/list"><span class="navtext">고객센터</span></a>
            </li>
        </ul>
    </div>
</header>

<div id="headerspace"></div>

<script>
    $(document).ready(function (){
        let imgInfo = cssImage.getImgInfo();
        $("#logoimg").attr("src", "/img/display?fileName=" + imgInfo['logo']);
        $("#chaticon").attr("src", "/img/display?fileName=" + imgInfo['chat']);
        $("#storeicon").attr("src", "/img/display?fileName=" + imgInfo['store']);
        $("#usericon").attr("src", "/img/display?fileName=" + imgInfo['person']);
        $("#search").css("background-image", "url('/img/display?fileName=" + imgInfo['search'] + "')");
    });
    // document.addEventListener("DOMContentLoaded", function() {
    //
    // });
</script>

