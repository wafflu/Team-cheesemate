<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<body>
<link rel="stylesheet" href="/css/faq.css">

<div class="faq-container">
    <aside class="faq-aside">
        <h3>고객센터</h3>
        <ul class="QnaSide">
            <li><a href="<c:url value='/faq/list'/>">FAQ</a></li>
            <li><a href="<c:url value='/qna/new'/>">1:1 문의하기</a></li>
            <li><a href="<c:url value='/qna/list'/>">나의 문의내역</a></li>
        </ul>
    </aside>
    <main class="faq-main">
        <div>
            <div class="faq-search-container">
                <input type="text" id="searchInput" placeholder="검색어 입력">
                <button id="searchButton">검색</button>
            </div>
            <div class="faq-category-container">
                <button class="faq-category-button" value="6">전체</button>
                <button class="faq-category-button" value="1">거래문의</button>
                <button class="faq-category-button" value="2">이용/운영정책 문의</button>
                <button class="faq-category-button" value="3">신고접수</button>
                <button class="faq-category-button" value="4">회원계정/이용문의</button>
                <button class="faq-category-button" value="5">기타</button>
            </div>
            <table class="faq-table">
                <thead>
                <tr><th>제목</th></tr>
                </thead>
                <tbody id="faq-table-body">
                <!-- AJAX 동적 삽입 -->
                </tbody>
            </table>
        </div>
    </main>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/faq.js"></script> <!-- faq.js 파일 포함 -->
<%@include file="fixed/footer.jsp"%>


