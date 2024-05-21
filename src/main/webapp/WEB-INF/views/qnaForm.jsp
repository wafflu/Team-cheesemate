<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<title><c:choose><c:when test="${empty qnaDto.no}">문의하기</c:when><c:otherwise>문의 수정하기</c:otherwise></c:choose></title>
<link rel="stylesheet" href="/css/qnaForm.css">

<div class="qnaForm_container">
    <aside class="qnaForm_aside">
        <h3>고객센터</h3>
        <ul class="qnaForm_QnaSide">
            <li><a href="<c:url value='/faq/list'/>">FAQ</a></li>
            <li><a href="<c:url value='/qna/new'/>">1:1 문의하기</a></li>
            <li><a href="<c:url value='/qna/list'/>">나의 문의내역</a></li>
        </ul>
    </aside>
    <main class="qnaForm_main">
        <h1><c:choose><c:when test="${empty qnaDto.no}">문의하기</c:when><c:otherwise>문의 수정하기</c:otherwise></c:choose></h1>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <form action="<c:choose><c:when test="${empty qnaDto.no}">/qna/send</c:when><c:otherwise>/qna/modify</c:otherwise></c:choose>" method="post" onsubmit="return qnaForm_submitCheck();">
            <div>
                <label for="qnaForm_type">유형:</label>
                <select id="qnaForm_type" name="type" required>
                    <option value="">유형을 선택해주세요</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.que_cd}" <c:if test="${category.que_cd == qnaDto.que_i_cd}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="qnaForm_detailType">상세 유형:</label>
                <select id="qnaForm_detailType" name="que_i_cd" required>
                    <option value="">상세 유형을 선택해주세요</option>
                    <c:forEach var="subCategory" items="${subCategories}">
                        <option value="${subCategory.que_cd}" <c:if test="${subCategory.que_cd == qnaDto.que_i_cd}">selected</c:if>>${subCategory.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="qnaForm_title">제목:</label>
                <input type="text" id="qnaForm_title" name="title" value="<c:out value='${qnaDto.title}' />" required>
            </div>
            <div>
                <label for="qnaForm_content">내용:</label>
                <textarea id="qnaForm_content" name="contents" rows="5" required minlength="20">${qnaDto.contents}</textarea>
            </div>
            <input type="hidden" name="ur_id" value="<c:out value='${sessionScope.userId}' />" />
            <input type="hidden" name="q_s_cd" value="Q001U" />
            <input type="hidden" name="first_id" value="<c:out value='${sessionScope.userId}' />" />
            <input type="hidden" name="no" value="<c:out value='${qnaDto.no}' />"  />
            <button type="submit" id = "qnaForm_submit"><c:choose><c:when test="${empty qnaDto.no}">문의 제출</c:when><c:otherwise>문의 수정</c:otherwise></c:choose></button>
        </form>
    </main>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/js/qnaForm.js"></script>

<%@include file="fixed/footer.jsp"%>

