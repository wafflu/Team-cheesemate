<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/css/qnaBoard.css"> <!-- qnaBoard.css 파일 포함 -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="/js/qnaBoard.js"></script>
</head>

<body data-msg="${msg}">
<div class="qnaBoard_head">
    <button class="qnaBoard_backButton" onclick="loadQnaListModal()">
        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M12.5 19.5L4.42491 11.6251L12.5 3.5" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
        </svg>
    </button>
    <h2 class="qnaBoard_headTitle">문의 내역 상세</h2>
</div>
<div class="qnaBoard_container">
    <main class="qnaBoard_main">
        <form id="qnaBoard_modifyForm" action="/qna/modify" method="post" onsubmit="return qnaBoard_validateForm();">
            <input type="hidden" name="no" value="<c:out value='${qna.no}' />">
            <input type="hidden" id="qnaBoard_hiddenTitle" name="hiddenTitle" value="<c:out value='${qna.title}' />">
            <div class="qnaBoard_div">
                <label for="qnaBoard_title">Q. 제목 :</label>
                <input type="text" name="title" id="qnaBoard_title" value="<c:out value='${qna.title}' />" readonly>
            </div>
            <div class="qnaBoard_div">
                <label for="qnaBoard_content">내용 :</label>
                <textarea name="contents" id="qnaBoard_content" rows="1" readonly oninput="autoResizeTextarea(this)"><c:out value="${qna.contents}"/></textarea>
                <p class="date_p_tag"><strong>작성일 :</strong> <fmt:formatDate value="${qna.r_date}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
            </div>

            <c:if test="${qna.q_s_cd != 'Q001Y'}">
                <div class="deleteAndModifyBtn">
                    <div class="qnaBoard_buttonContainer">
                        <button type="button" onclick="qnaBoard_modify()" id="qnaBoard_editBtn" class="qnaBoard_submitButton">문의수정</button>
                        <button type="submit" id="qnaBoard_saveBtn" class="qnaBoard_submitButton" style="display:none;">저장하기</button>
                    </div>
                    <div class="qnaBoard_buttonContainer">
                        <form action="/qna/delete" method="post" onsubmit="return qnaBoard_confirmDelete();" class="qnaBoard_deleteForm">
                            <input type="hidden" name="no" value="<c:out value='${qna.no}' />" />
                            <button type="submit" class="qnaBoard_deleteButton">문의삭제</button>
                        </form>
                    </div>
                </div>
            </c:if>

            <c:if test="${qna.q_s_cd == 'Q001Y'}">
                <hr class="ans_divider">
                <div class="qnaBoard_div">
                    <label class="qnaBoard_titleAns">A. 답변 :</label>
                    <input type="text" name="title" id="qnaBoard_contentAns" value="<c:out value='${qna.ans_contents}' />" readonly>
                    <p class="dateAnd_p_tag"><strong>답변일 :</strong> <fmt:formatDate value="${qna.ans_p_date}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                </div>
            </c:if>
        </form>
    </main>

</div>
</body>
</html>