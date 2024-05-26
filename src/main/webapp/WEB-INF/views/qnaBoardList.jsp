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
    <link rel="stylesheet" href="/css/qnaBoardList.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<main id="qnaBoardList_main">
    <h1>나의 문의 목록</h1>
    <div id="qnaBoardList_content">
        <div id="qnaBoardList_list">
            <c:forEach var="qna" items="${qnaList}">
                <div class="qnaBoardList_item">
                    <a href="/qna/read?no=${qna.no}" class="qnaBoardList_title">
                        (<c:out value="${qna.categoryName}" />) ${qna.title}
                    </a>
                    <div class="qnaBoardList_meta">
                            <span class="qnaBoardList_date">
                                <fmt:formatDate value="${qna.r_date}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate"/>
                                ${formattedDate}
                            </span>
                        <span class="qnaBoardList_status">
                                <c:choose>
                                    <c:when test="${qna.q_s_cd eq 'Q001U'}">확인중</c:when>
                                    <c:when test="${qna.q_s_cd eq 'Q001C'}">미확인</c:when>
                                    <c:when test="${qna.q_s_cd eq 'Q001Y'}">답변완료</c:when>
                                    <c:when test="${qna.q_s_cd eq 'Q001N'}">답변거절</c:when>
                                    <c:otherwise>error</c:otherwise>
                                </c:choose>
                            </span>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id="qnaBoardList_pagination">
            <c:if test="${ph.prevPage}">
                <a href="/qna/list?page=${ph.beginPage - 1}&pageSize=${ph.pageSize}" class="btn btn-primary pagination-link">이전</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="/qna/list?page=${i}&pageSize=${ph.pageSize}" class="btn btn-default pagination-link">${i}</a>
            </c:forEach>
            <c:if test="${ph.nextPage}">
                <a href="/qna/list?page=${ph.endPage + 1}&pageSize=${ph.pageSize}" class="btn btn-primary pagination-link">다음</a>
            </c:if>
        </div>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/qnaBoardList.js"></script>

</body>
</html>
