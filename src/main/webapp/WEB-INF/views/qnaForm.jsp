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
    <link rel="stylesheet" href="/css/qnaForm.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

<div class="qnaForm_container">
    <main class="qnaForm_main">
        <h1><c:choose><c:when test="${empty qnaDto.no}">문의하기</c:when><c:otherwise>문의 수정하기</c:otherwise></c:choose></h1>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <form action="<c:choose><c:when test="${empty qnaDto.no}">/qna/send</c:when><c:otherwise>/qna/modify</c:otherwise></c:choose>" id="qnaForm" method="post" onsubmit="return qnaForm_submitCheck();">
            <div class="qnaForm_div">
                <label for="qnaForm_type">유형:</label>
                <select id="qnaForm_type" name="type" required>
                    <option value="">유형을 선택해주세요</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.que_cd}" <c:if test="${category.que_cd == qnaDto.type}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="qnaForm_div">
                <label for="qnaForm_detailType">상세 유형:</label>
                <select id="qnaForm_detailType" name="que_i_cd" required>
                    <option value="">상세 유형을 선택해주세요</option>
                    <c:forEach var="subCategory" items="${subCategories}">
                        <option value="${subCategory.que_cd}" <c:if test="${subCategory.que_cd == qnaDto.que_i_cd}">selected</c:if>>${subCategory.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="qnaForm_div">
                <label for="qnaForm_title">제목:</label>
                <input type="text" id="qnaForm_title" name="title" value="<c:out value='${qnaDto.title}' />" required>
            </div>
            <div class="qnaForm_div">
                <label for="qnaForm_content">내용:</label>
                <textarea id="qnaForm_content" name="contents" rows="5" required minlength="20">${qnaDto.contents}</textarea>
            </div>
            <input type="hidden" name="ur_id" value="<c:out value='${sessionScope.userId}' />" />
            <input type="hidden" name="q_s_cd" value="Q001U" />
            <input type="hidden" name="first_id" value="<c:out value='${sessionScope.userId}' />" />
            <input type="hidden" name="no" value="<c:out value='${qnaDto.no}' />" />
            <div class="qnaForm_buttonContainer">
                <button type="submit" id="qnaForm_submit" class="qnaForm_submitButton"><c:choose><c:when test="${empty qnaDto.no}">문의 제출</c:when><c:otherwise>문의 수정</c:otherwise></c:choose></button>
            </div>
        </form>

        <div class="qnaForm_notice">
            <h3>유의사항</h3>
            <ul>
                <li>상담에 필요한 정보 외 개인정보를 포함하지 않도록 주의해주세요.</li>
                <li>산업안전 보건법에 따라 고객응대 근로자 보호조치를 시행하고 있어요. 욕설 또는 폭언을 하지 말아주세요.</li>
                <li>자주묻는질문을 확인하면 답변을 빨리 받을 수 있어요.</li>
                <li>접수는 24시간 가능하지만, 답변은 9시 - 18시 사이에 순차적으로 받을 수 있어요.</li>
                <li>문의취소는 접수상태에서만 가능해요.</li>
            </ul>
        </div>
    </main>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/js/qnaForm.js"></script>

</body>
</html>
