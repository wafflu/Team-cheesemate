<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${empty qnaDto.no}">문의하기</c:when><c:otherwise>문의 수정하기</c:otherwise></c:choose></title>
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
            padding: 20px;
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
        form {
            border: 1px solid #ccc;
            padding: 20px;
            box-shadow: 0 0 10px #ccc;
            background: #fff;
        }
        div {
            margin-bottom: 10px;
        }
    </style>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<header>
    <!-- 헤더 내용 추가 -->
</header>
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
        <h1><c:choose><c:when test="${empty qnaDto.no}">문의하기</c:when><c:otherwise>문의 수정하기</c:otherwise></c:choose></h1>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <form action="<c:choose><c:when test="${empty qnaDto.no}">/qna/send</c:when><c:otherwise>/qna/modify</c:otherwise></c:choose>" method="post" onsubmit="return submitCheck();">
            <div>
                <label for="type">유형:</label>
                <select id="type" name="type" required>
                    <option value="">유형을 선택해주세요</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.que_cd}" <c:if test="${category.que_cd == qnaDto.type}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="detailType">상세 유형:</label>
                <select id="detailType" name="que_i_cd" required>
                    <option value="">상세 유형을 선택해주세요</option>
                    <c:forEach var="subCategory" items="${subCategories}">
                        <option value="${subCategory.que_cd}" <c:if test="${subCategory.que_cd == qnaDto.que_i_cd}">selected</c:if>>${subCategory.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" value="${qnaDto.title}" required>
            </div>
            <div>
                <label for="content">내용:</label>
                <textarea id="content" name="contents" rows="5" required minlength="20">${qnaDto.contents}</textarea>
            </div>
            <input type="hidden" name="ur_id" value="${sessionScope.userId}" />
            <input type="hidden" name="q_s_cd" value="Q001U" />
            <input type="hidden" name="first_id" value="${sessionScope.userId}" />
            <input type="hidden" name="no" value="${qnaDto.no}" />
            <button type="submit"><c:choose><c:when test="${empty qnaDto.no}">문의 제출</c:when><c:otherwise>문의 수정</c:otherwise></c:choose></button>
        </form>
    </main>
</div>
<footer>
    <!-- 푸터 내용 추가 -->
</footer>
<script>
    $(document).ready(function() {
        // 기존 데이터를 기반으로 대분류 및 상세 유형을 설정
        var selectedType = "${qnaDto.type}";
        var selectedDetailType = "${qnaDto.que_i_cd}";

        // 페이지가 로드되면 대분류 데이터를 가져옴
        $.ajax({
            url: '/qna/major',
            type: 'GET',
            dataType: 'json',
            success: function(categories) {
                var typeSelect = $('#type');
                categories.forEach(function(category) {
                    var option = $('<option>', {
                        value: category.que_cd,
                        text: category.name
                    });
                    if (category.que_cd == selectedType) {
                        option.attr('selected', 'selected');
                    }
                    typeSelect.append(option);
                });
            }
        });

        // 기존에 선택된 대분류가 있는 경우, 해당 상세 유형을 로드
        if (selectedType) {
            $.ajax({
                url: '/qna/sub/' + selectedType,
                type: 'GET',
                dataType: 'json',
                success: function(subCategories) {
                    var detailTypeSelect = $('#detailType');
                    detailTypeSelect.empty().append($('<option>', { text: '상세 유형을 선택해주세요' }));
                    subCategories.forEach(function(subCategory) {
                        var option = $('<option>', {
                            value: subCategory.que_cd,
                            text: subCategory.name
                        });
                        if (subCategory.que_cd == selectedDetailType) {
                            option.attr('selected', 'selected');
                        }
                        detailTypeSelect.append(option);
                    });
                }
            });
        }

        $('#type').change(function() {
            let majorId = $(this).val();
            $.ajax({
                url: '/qna/sub/' + majorId,
                type: 'GET',
                dataType: 'json',
                success: function(subCategories) {
                    let select = $('#detailType');
                    select.empty().append($('<option>', { text: '상세 유형을 선택해주세요' }));
                    subCategories.forEach(function(category) {
                        select.append($('<option>', { value: category.que_cd, text: category.name }));
                    });
                },
                error: function(xhr, status, error) {
                    console.error('Error fetching sub-categories:', error);
                }
            });
        });
    });

    function submitCheck() {
        var type = $('#type').val();
        var detailType = $('#detailType').val();
        var title = $('#title').val().trim();
        var content = $('#content').val().trim();

        if (!type) {
            alert('유형을 선택해주세요.');
            return false;
        }
        if (!detailType) {
            alert('상세 유형을 선택해주세요.');
            return false;
        }
        if (!title) {
            alert('제목을 입력해주세요.');
            return false;
        }
        if (!content || content.length < 20) {
            alert('내용을 20자 이상 입력해주세요.');
            return false;
        }
        return true; // 모든 검사가 통과하면 폼 제출을 허용
    }
</script>
</body>
</html>
