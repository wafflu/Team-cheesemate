<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="loginId" value="${sessionScope.userId}"/>
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
            let cssimginfo = {};

            <c:forEach items="${headerimglist}" var="img">
                cssimginfo['${img.o_name}'] = "${img.img_full_rt}";
            </c:forEach>
            return {
                getImgInfo: function() {
                    return cssimginfo;
                }
            };
        })();

        // const uploadImage = (function() {
        //     let uploadImage = [];
        //
        //     return {
        //         getImgInfo: function() {
        //             return uploadImage;
        //         }
        //     };
        // })();
    </script>

</head>
<body>
<header id="header_box">
    <div id="header_subbox">
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
                        <!-- userAddrCdDtoList가 비어있지 않은 경우에만 파라미터를 추가 -->
                        <c:if test="${not empty sessionScope.userAddrCdDtoList}">
                            <input type="hidden" name="addr_cd"
                                   value="${not empty sessionScope.userAddrCdDtoList ? sessionScope.userAddrCdDtoList[0].addr_cd : ''}">
                            <input type="hidden" name="addr_name"
                                   value="${not empty sessionScope.userAddrCdDtoList ? sessionScope.userAddrCdDtoList[0].addr_name : ''}">
                        </c:if>
                    </form>

                    <a href="#" id="storelink" class="subnavlink" onclick="document.getElementById('postForm').submit(); return false;">
                        <img src="" alt="sell" id="storeicon" class="subnavicon">
                        <span class="subnavtext">판매하기</span>
                    </a>
                </span>
                <span class="subnavspan">
                   <c:choose>
                       <c:when test="${empty userId}">
                            <a href="/myPage/main" id="userlink" class="subnavlink">
                                <img src="" alt="user" id="usericon" class="subnavicon usericon">
                                <span class="subnavtext">마이</span>
                            </a>
                       </c:when>
                       <c:otherwise>
                           <div class="mylogin-box">
                                <img src="" alt="user" class="subnavicon usericon">
                                <span class="subnavtext">마이</span>
                           </div>
                       </c:otherwise>
                    </c:choose>
                    </span>
            </div>
        </div>
    </div>
    <div id="nav">
        <div class="hearder-category-list">
            <p class="hearder-category-title">카테고리</p>
        </div>
        <ul id="navlist">
            <li class="navli">
                <a href="/sale/list"><span class="navtext">판매/나눔</span></a>
            </li>
            <li class="navli">
                <a href="#"><span class="navtext">사기조회</span></a>
            </li>
            <li class="navli">
                <a href="/event"><span class="navtext">이벤트</span></a>
            </li>
            <li class="navli">
                <a href="#"><span class="navtext">출석체크</span></a>
            </li>
            <li class="navli">
                <a href="/community/list"><span class="navtext">커뮤니티</span></a>
            </li>
            <li class="navli">
                <a href="/faq/list"><span class="navtext">고객센터</span></a>
            </li>
        </ul>
    </div>
    </div>
</header>

<%--<div id="headerspace"></div>--%>

<script>
    $(document).ready(function (){
        let imgInfo = cssImage.getImgInfo();
        $("#logoimg").attr("src", "/img/display?fileName=" + imgInfo['logo']);
        $("#chaticon").attr("src", "/img/display?fileName=" + imgInfo['chat']);
        $("#storeicon").attr("src", "/img/display?fileName=" + imgInfo['store']);
        $(".usericon").attr("src", "/img/display?fileName=" + imgInfo['person']);
        $("#search").css("background-image", "url('/img/display?fileName=" + imgInfo['search'] + "')");
        $("#sendMessage").css("background-image", "url('/img/display?fileName=" + imgInfo['sendmsg'] + "')");
        $(".response-active").css("background-image", "url('/img/display?fileName=" + imgInfo['chatmsgicon'] + "')");
        $("#chatlist-box").css("background-image", "url('/img/display?fileName=" + imgInfo['chatmsgicon'] + "')");
        $(".kakao-login-btn").css("background-image", "url('/img/display?fileName=" + imgInfo['kakao'] + "')");
        $(".naver-login-btn").css("background-image", "url('/img/display?fileName=" + imgInfo['naver'] + "')");
        $(".google-login-btn").css("background-image", "url('/img/display?fileName=" + imgInfo['google'] + "')");
        $(".hearder-category-list").css("background-image", "url('/img/display?fileName=" + imgInfo['menu'] + "')");
        $(".chatsel-drop-down").css("background-image", "url('/img/display?fileName=" + imgInfo['drop_down'] + "')");
        $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + imgInfo['Like1'] + "')");
        $(".regimgbtn").css("background-image", "url('/img/display?fileName=" + imgInfo['addimg'] + "')");
    });
    $(document).ready(function() {
        let isMenuVisible = false;
        $(".mylogin-box").click(function(e) {
            e.stopPropagation(); // 이벤트 버블링 방지
            if (!isMenuVisible) {
                let str = '<ul id="loginmenu">';
                str += '<li><a href="/myPage/main" class="subnavlink homemylink">마이스토어</a></li>';
                str += '<li><a href="/logout" class="subnavlink homemylink">로그아웃</a></li>';
                str += '</ul>';
                $(".mylogin-box").append(str);
            } else {
                $("#loginmenu").remove();
            }
            isMenuVisible = !isMenuVisible;
        });

        $(document).click(function() {
            if (isMenuVisible) {
                $("#loginmenu").remove();
                isMenuVisible = false;
            }
        });

        $(window).on('scroll', function() {
            let scrollTop = $(window).scrollTop();
            let triggerHeight = 10; // 스크롤 트리거 높이 설정

            if (scrollTop > triggerHeight) {
                $('#header_box').addClass('header-shadow');
            } else {
                $('#header_box').removeClass('header-shadow');
            }
        });
    });
</script>
<%----%>

