<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의하기</title>
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
        <h1>문의하기</h1>
        <form action="/qna/send" method="post"  onsubmit="return submitCheck();">
            <div>
                <label for="type">유형:</label>
                <select id="type" name="type" required>
                    <option value="">유형을 선택해주세요</option>
                </select>
            </div>
            <div>
                <label for="detailType">상세 유형:</label>
                <select id="detailType" name="que_i_cd" required>
                    <option value="">상세 유형을 선택해주세요</option>
                </select>
            </div>
            <div>
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div>
                <label for="content">내용:</label>
                <textarea id="content" name="contents" rows="5" ></textarea>
<%--                required minlength="20"--%>
            </div>
            <button type="submit">문의 제출</button>
        </form>
    </main>
</div>
<footer>
    <!-- 푸터 내용 추가 -->
</footer>
<script>
    $(document).ready(function() {
        // 패이지가 로드되면 대분류 데이터를 가져온다.
        $.ajax({
            url: '/qna/major',
            type: 'GET',
            dataType : "json",
            success: function(categories) {
                console.log(categories)
                var typeSelect = $('#type');
                categories.forEach(function(category) {
                    typeSelect.append($('<option>', {
                        value: category.que_cd,
                        text: category.name
                    }));
                });
            }
        });

        $('#type').change(function() {
            let majorId = $(this).val();
            $.ajax({
                url: '/qna/sub/' + majorId,
                type: 'GET',
                dataType: 'json',
                success: function(subCategories) {
                    let select = $('#detailType');
                    select.empty().append($('<option>', { text: '상세 유형을 선택해주세요' }));

                    if (Array.isArray(subCategories)) { // 배열인지 확인
                        subCategories.forEach(function(category) {
                            select.append($('<option>', { value: category.que_cd, text: category.name }));
                        });
                    } else {
                        console.error('Expected an array but got:', subCategories);
                    }
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
        // if (!content || content.length < 20) {
        //     alert('내용을 20자 이상 입력해주세요.');
        //     return false;
        // }
        return true; // 모든 검사가 통과하면 폼 제출을 허용
    }
</script>
</body>
</html>
