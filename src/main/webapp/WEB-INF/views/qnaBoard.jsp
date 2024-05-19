<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 구글 폰트 영역 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- 기본 리셋 영역 -->
    <link rel="stylesheet" href="reset.css">
    <!-- 사용자 영역 -->
    <link rel="stylesheet" href="mystyle.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/qnaBoard.css"> <!-- qnaBoard.css 파일 포함 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<header id="header_box">
    <div id="herder_top">
        <div id="logobox">
            <a href="#" id="cheezmate"><img src="icon/logo.png" alt="우리들의 팀메이트 치즈마켓" id="logoimg"></a>
        </div>
        <div id="searchbox">
            <input type="text" name="search" id="search" autocomplete="off">
        </div>
        <div id="subnavbox">
            <div id="subnavinnerbox">
                <span class="subnavspan">
                    <a href="#" id="chatlink" class="subnavlink">
                        <img src="icon/chat.png" alt="chatting" id="chaticon" class="subnavicon">
                        <span class="subnavtext">치즈톡</span>
                    </a>
                </span>
                <span class="subnavspan">
                    <a href="#" id="storelink" class="subnavlink">
                        <img src="icon/store.png" alt="sell" id="storeicon" class="subnavicon">
                        <span class="subnavtext">판매하기</span>
                    </a>
                </span>
                <span class="subnavspan">
                    <a href="#" id="userlink" class="subnavlink">
                        <img src="icon/person.png" alt="user" id="usericon" class="subnavicon">
                        <span class="subnavtext">마이</span>
                    </a>
                </span>
            </div>
        </div>
    </div>
    <div id="nav">
        <ul id="navlist">
            <li class="navli">
                <a href="#"><span class="navtext">판매/나눔</span></a>
            </li>
            <li class="navli">
                <a href="#"><span class="navtext">이벤트</span></a>
            </li>
            <li class="navli">
                <a href="#"><span class="navtext">커뮤니티</span></a>
            </li>
            <li class="navli">
                <a href="#"><span class="navtext">고객센터</span></a>
            </li>
        </ul>
    </div>
</header>
<div class="container">
    <aside>
        <h3>고객센터</h3>
        <ul class="QnaSide">
            <li><a href="<c:url value='/faq/list'/>">FAQ</a></li>
            <li><a href="<c:url value='/qna/new'/>">1:1 문의하기</a></li>
            <li><a href="<c:url value='/qna/list'/>">나의 문의내역</a></li>
        </ul>
    </aside>
    <main>
        <form id="modifyForm" action="/qna/modify" method="post" onsubmit="return validateForm();">
            <input type="hidden" name="no" value="${qna.no}">
            <div class="header">
                <h1 contenteditable="false" id="title">${qna.title}</h1>
                <input type="hidden" name="title" id="hiddenTitle" value="${qna.title}">
                <p><strong>작성일:</strong> <c:out value="${qna.r_date}"/></p>
            </div>
            <div class="contents">
                <textarea name="contents" id="content" rows="10" readonly><c:out value="${qna.contents}"/></textarea>
            </div>
            <div class="buttons">
                <button type="button" onclick="modify()" id="editBtn">수정</button>
                <button type="submit" id="saveBtn" style="display:none;">저장</button>
            </div>
        </form>
        <c:if test="${qna.q_s_cd != 'Q001Y'}">
            <form action="/qna/delete" method="post" onsubmit="return confirmDelete();">
                <input type="hidden" name="no" value="${qna.no}" />
                <button type="submit">삭제</button>
            </form>
        </c:if>
    </main>
</div>
<footer id="footer">
    <div id="footersub">
        <ul>
            <li class="cheesecompany"><b>(주)치즈마켓 사업자 정보</b></li>
            <li>
                <ul class="footerulflex">
                    <li>(주)치즈마켓</li>
                    <li>대표자 : 치즈메이트</li>
                </ul>
            </li>
            <li>사업자 등록번호 : 123-45-56789</li>
            <li>통신판매신고번호 : 제2024-서울강남-0001호</li>
            <li>주소 : 서울특별시 강남구 강남대로 364 미왕빌딩 10층</li>
            <li>대표번호 : 1234-1234</li>
            <li>메일 : cheezmate@cheezmate.co.kr</li>
            <li>호스팅제공자 : 개인PC</li>
            <li>
                <ul class="footerulflex">
                    <li><b style="color:#5a616b;">Contact</b></li>
                    <li>치즈마켓 VIP 회원 신청</li>
                    <li>치즈마켓 광고 문의</li>
                </ul>
            </li>
        </ul>

        <div id="footergflexgroup1">
            <ul class="footerulflex">
                <li>이용약관</li>
                <li><b>개인정보처리방침</b></li>
                <li>분쟁처리절차</li>
                <li>청소년보호정책</li>
                <li>사업자정보확인</li>
            </ul>
            <ul class="footerulflex">
                <li>게시글 수집 및 이용 안내</li>
                <li>치즈마켓 고객센터</li>
            </ul>
        </div>

        <p class="footermsg">"치즈마켓" 상점의 판매상품을 제외한 모든 상품들에 대하여, (주)치즈마켓은 통신판매중개자로서 거래 당사자가 아니며 판매 회원과 구매 회원 간의 상품거래 정보 및 거래에 관여하지 않고, 어떠한 의무와 책임도 부담하지 않습니다.</p>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/js/qnaBoard.js"></script> <!-- qnaBoard.js 파일 포함 -->
</body>
</html>
