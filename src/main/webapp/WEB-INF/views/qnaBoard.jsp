<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>QnA 게시판</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
    <form id="modifyForm" action="/qna/modify" method="post">
        <input type="hidden" name="no" value="${qna.no}">
        <div class="header">
            <h1 contenteditable="false" id="title">${qna.title}</h1>
            <input type="hidden" name="title" id="hiddenTitle" value="${qna.title}">
            <p><strong>작성일:</strong> <c:out value="${qna.r_date}"/></p>
        </div>
        <div class="contents">
            <textarea name="contents" rows="10" readonly><c:out value="${qna.contents}"/></textarea>
        </div>
        <div class="buttons">
            <button type="button" onclick="modify()" id="editBtn">수정</button>
            <button type="submit" id="saveBtn" style="display:none;">저장</button>
            <a href="/qna/list">목록</a>
        </div>
    </form>
    <c:if test="${qna.q_s_cd != 'Q001Y'}">
        <form action="/qna/delete" method="post" onsubmit="return confirmDelete();">
            <input type="hidden" name="no" value="${qna.no}" />
            <button type="submit">삭제</button>
        </form>
    </c:if>
</div>

<script>
    function modify() {
        $('#title').attr('contenteditable', 'true');
        $('#title').on('input', function() {
            $('#hiddenTitle').val($(this).text());
        });
        $('textarea').removeAttr('readonly');
        $('#editBtn').hide();
        $('#saveBtn').show();
    }
    function confirmDelete() {
        return confirm('정말 삭제하시겠습니까?');
    }
</script>
</body>
</html>
