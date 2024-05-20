<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- 구글 폰트 영역 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- 기본 리셋 영역 -->
    <link rel="stylesheet" href="/css/reset.css">
    <!-- 사용자 영역 -->
    <link rel="stylesheet" href="/css/mystyle.css">
    <link rel="stylesheet" href="/css/qnaBoardList.css"> <!-- qnaBoardList.css 파일 포함 -->
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">--%>
</head>
<body>
<%--<header id="header_box">--%>
<%--    <div id="herder_top">--%>
<%--        <div id="logobox">--%>
<%--            <a href="#" id="cheezmate"><img src="icon/logo.png" alt="우리들의 팀메이트 치즈마켓" id="logoimg"></a>--%>
<%--        </div>--%>
<%--        <div id="searchbox">--%>
<%--            <input type="text" name="search" id="search" autocomplete="off">--%>
<%--        </div>--%>
<%--        <div id="subnavbox">--%>
<%--            <div id="subnavinnerbox">--%>
<%--                <span class="subnavspan">--%>
<%--                    <a href="#" id="chatlink" class="subnavlink">--%>
<%--                        <img src="icon/chat.png" alt="chatting" id="chaticon" class="subnavicon">--%>
<%--                        <span class="subnavtext">치즈톡</span>--%>
<%--                    </a>--%>
<%--                </span>--%>
<%--                <span class="subnavspan">--%>
<%--                    <a href="#" id="storelink" class="subnavlink">--%>
<%--                        <img src="icon/store.png" alt="sell" id="storeicon" class="subnavicon">--%>
<%--                        <span class="subnavtext">판매하기</span>--%>
<%--                    </a>--%>
<%--                </span>--%>
<%--                <span class="subnavspan">--%>
<%--                    <a href="#" id="userlink" class="subnavlink">--%>
<%--                        <img src="icon/person.png" alt="user" id="usericon" class="subnavicon">--%>
<%--                        <span class="subnavtext">마이</span>--%>
<%--                    </a>--%>
<%--                </span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="nav">--%>
<%--        <ul id="navlist">--%>
<%--            <li class="navli">--%>
<%--                <a href="#"><span class="navtext">판매/나눔</span></a>--%>
<%--            </li>--%>
<%--            <li class="navli">--%>
<%--                <a href="#"><span class="navtext">이벤트</span></a>--%>
<%--            </li>--%>
<%--            <li class="navli">--%>
<%--                <a href="#"><span class="navtext">커뮤니티</span></a>--%>
<%--            </li>--%>
<%--            <li class="navli">--%>
<%--                <a href="#"><span class="navtext">고객센터</span></a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</header>--%>
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
        <h1>문의 목록</h1>
        <table class="table">
            <thead>
            <tr>
                <th>제목</th>
                <th>문의 상태</th>
                <th>문의 유형</th>
                <th>등록 일시</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="qna" items="${qnaList}">
                <tr>
                    <td><a href="/qna/read?no=${qna.no}">${qna.title}</a></td>
                    <td>
                        <c:choose>
                            <c:when test="${qna.q_s_cd eq 'Q001U'}">확인중</c:when>
                            <c:when test="${qna.q_s_cd eq 'Q001C'}">미확인</c:when>
                            <c:when test="${qna.q_s_cd eq 'Q001Y'}">답변완료</c:when>
                            <c:when test="${qna.q_s_cd eq 'Q001N'}">답변거절</c:when>
                            <c:otherwise>error</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${qna.categoryName}</td>
                    <fmt:formatDate value="${qna.r_date}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
                    <td>${formattedDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <c:if test="${ph.prevPage}">
                <a href="?page=${ph.beginPage - 1}&pageSize=${ph.pageSize}" class="btn btn-primary">이전</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="?page=${i}&pageSize=${ph.pageSize}" class="btn btn-default">${i}</a>
            </c:forEach>
            <c:if test="${ph.nextPage}">
                <a href="?page=${ph.endPage + 1}&pageSize=${ph.pageSize}" class="btn btn-primary">다음</a>
            </c:if>
        </div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="/js/qnaBoardList.js"></script> <!-- qnaBoardList.js 파일 포함 -->
</body>
</html>
