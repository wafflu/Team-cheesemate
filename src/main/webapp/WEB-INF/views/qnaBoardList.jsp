<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        header, footer {
            background-color: #f8f9fa;
            padding: 1rem;
        }
        .container {
            display: flex;
            flex: 1;
            max-width: 1200px;
            margin: 0 auto;
        }
        aside {
            width: 200px;
            background-color: #eee;
            padding: 1rem;
        }
        main {
            flex: 1;
            padding: 1rem;
        }
        .table {
            margin-top: 20px;
        }
    </style>

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
</div>
    </main>
<footer>
    <!-- 푸터 내용 추가 -->
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<%@include file="fixed/footer.jsp"%>