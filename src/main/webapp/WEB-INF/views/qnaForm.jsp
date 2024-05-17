<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의하기</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
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
<div>
    <h1>문의하기</h1>
    <form action="/qna/send" method="post">
        <div>
            <label for="type">유형:</label>
            <select id="type" name="type" required>
                <option value="">유형을 선택해주세요</option>
            </select>
        </div>
        <div>
            <label for="detailType">상세 유형:</label>
            <select id="detailType" name="que_i_cd" required> <!-- name을 'que_i_cd'로 변경 -->
                <option value="">상세 유형을 선택해주세요</option>
            </select>
        </div>
        <div>
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required> <!-- name을 확인 -->
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea id="content" name="contents" rows="5" required></textarea> <!-- name을 'contents'로 변경 -->
        </div>
        <button type="submit">문의 제출</button>
    </form>
</div>
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
</script>
</body>
</html>
