<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<link rel="stylesheet" href="/css/qnaBoard.css"> <!-- qnaBoard.css 파일 포함 -->
<%--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>
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
            <div class="qnaBoard_header">
                <input type="text" name="title" id="qnaBoard_title" value="<c:out value='${qna.title}' />" readonly>
                <p><strong>작성일:</strong> <c:out value="${qna.r_date}"/></p>
            </div>
            <div class="qnaBoard_contents">
                <textarea name="contents" id="qnaBoard_content" rows="10" readonly><c:out value="${qna.contents}"/></textarea>
            </div>
            <div class="qnaBoard_buttons">
                <button type="button" onclick="qnaBoard_modify()" id="qnaBoard_editBtn">수정</button>
                <button type="submit" id="qnaBoard_saveBtn" style="display:none;">저장</button>
            </div>
        </form>
        <c:if test="${qna.q_s_cd != 'Q001Y'}">
            <form action="/qna/delete" method="post" onsubmit="return qnaBoard_confirmDelete();">
                <input type="hidden" name="no" value="<c:out value='${qna.no}' />" />
                <button type="submit">삭제</button>
            </form>
        </c:if>
    </main>
</div>

<input type="hidden" id="flashMsg" value="${msg}">
<script src="/js/qnaBoard.js"></script>

<%@include file="fixed/footer.jsp"%>

