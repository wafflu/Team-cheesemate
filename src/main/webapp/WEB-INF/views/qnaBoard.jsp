<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="fixed/header.jsp" %>

<title>문의 수정하기</title>
<link rel="stylesheet" href="/css/qnaBoard.css"> <!-- qnaBoard.css 파일 포함 -->

<div class="qnaBoard_container">
    <aside class="qnaBoard_aside">
        <h3>고객센터</h3>
        <ul class="qnaBoard_QnaSide">
            <li><a href="<c:url value='/faq/list'/>">FAQ</a></li>
            <li><a href="<c:url value='/qna/new'/>">1:1 문의하기</a></li>
            <li><a href="<c:url value='/qna/list'/>">나의 문의내역</a></li>
        </ul>
    </aside>
    <main class="qnaBoard_main">
        <form id="qnaBoard_modifyForm" action="/qna/modify" method="post" onsubmit="return qnaBoard_validateForm();">
            <input type="hidden" name="no" value="<c:out value='${qna.no}' />">
            <input type="hidden" id="qnaBoard_hiddenTitle" name="hiddenTitle" value="<c:out value='${qna.title}' />">
            <div class="qnaBoard_div">
                <label for="qnaBoard_title">제목:</label>
                <input type="text" name="title" id="qnaBoard_title" value="<c:out value='${qna.title}' />" readonly>
                <p><strong>작성일:</strong> <c:out value="${qna.r_date}"/></p>
            </div>
            <div class="qnaBoard_div">
                <label for="qnaBoard_content">내용:</label>
                <textarea name="contents" id="qnaBoard_content" rows="10" readonly><c:out value="${qna.contents}"/></textarea>
            </div>
            <div class="qnaBoard_buttonContainer">
                <button type="button" onclick="qnaBoard_modify()" id="qnaBoard_editBtn" class="qnaBoard_submitButton">수정</button>
                <button type="submit" id="qnaBoard_saveBtn" class="qnaBoard_submitButton" style="display:none;">저장</button>
            </div>
        </form>
        <c:if test="${qna.q_s_cd != 'Q001Y'}">
            <form action="/qna/delete" method="post" onsubmit="return qnaBoard_confirmDelete();" class="qnaBoard_deleteForm">
                <input type="hidden" name="no" value="<c:out value='${qna.no}' />" />
                <button type="submit" class="qnaBoard_deleteButton">삭제</button>
            </form>
        </c:if>
    </main>
</div>

<input type="hidden" id="flashMsg" value="${msg}">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/qnaBoard.js"></script>

<%@ include file="fixed/footer.jsp" %>
