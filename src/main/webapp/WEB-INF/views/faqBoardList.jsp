<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<link rel="stylesheet" href="/css/faq.css">
<h2 class="cs-center">고객센터</h2>

<div class="faq-container">
    <div class="btn-container">
        <button class="questionWriteBtn">1:1 문의</button>
        <button class="questionListBtn">문의 내역</button>
    </div>
    <hr class="divider">
    <h2 class="faq">자주 묻는 질문 FAQ</h2>
    <main class="faq-main">
        <div>
            <!-- FAQ 검색 -->
            <div class="faq-search-container">
                <input type="text" id="faq-searchInput" placeholder="무엇을 찾고 계신가요?">
            </div>
            <!-- FAQ 카테고리 버튼 -->
            <div class="faq-category-container">
                <button class="faq-category-button active" value="6">전체</button>
                <button class="faq-category-button" value="1">거래문의</button>
                <button class="faq-category-button" value="2">이용/운영정책 문의</button>
                <button class="faq-category-button" value="3">신고접수</button>
                <button class="faq-category-button" value="4">회원계정/이용문의</button>
                <button class="faq-category-button" value="5">기타</button>
            </div>
            <!-- FAQ 테이블 -->
            <table class="faq-table">
                <tbody id="faq-table-body">
                <!-- AJAX 동적 삽입 -->
                </tbody>
            </table>
        </div>
    </main>
</div>

<!-- 1:1 문의하기 모달 -->
<div id="questionWriteModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <div id="modal-body"></div> <!-- 동적 콘텐츠가 삽입될 부분 -->
    </div>
</div>

<script src="/js/faq.js"></script>
<%@include file="fixed/footer.jsp"%>
