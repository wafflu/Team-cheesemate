<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>문의 목록</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <style>
        .container {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
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
</div>
<script src="//cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
