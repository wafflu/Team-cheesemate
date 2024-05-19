<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>치즈마켓</title>
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
        .faq-prefix {
            color: green;
            font-weight: bold;
            margin-right: 10px;
        }
        .title {
            display: flex;
            align-items: center;
            cursor: pointer;
            padding: 10px;
            background-color: #fff; /* 질문 배경을 흰색으로 설정 */
            border-bottom: 1px solid #ddd;
        }
        .title:hover {
            background-color: #e7e7e7;
        }
        .content-row {
            display: none;
            background-color: #f9f9f9; /* 답변 배경 색상 유지 */
            border-bottom: 1px solid #ddd;
        }
        .content {
            padding: 10px;
        }
        .category-button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 10px;
        }
        .category-container {
            margin-bottom: 20px;
        }
    </style>
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
        <div>
            <div class="search-container">
                <input type="text" id="searchInput" placeholder="검색어 입력">
                <button id="searchButton">검색</button>
            </div>
            <div class="category-container">
                <button class="category-button" value="6">전체</button>
                <button class="category-button" value="1">거래문의</button>
                <button class="category-button" value="2">이용/운영정책 문의</button>
                <button class="category-button" value="3">신고접수</button>
                <button class="category-button" value="4">회원계정/이용문의</button>
                <button class="category-button" value="5">기타</button>
            </div>
            <table class="table">
                <thead>
                <tr><th>제목</th></tr>
                </thead>
                <tbody id="faq-table-body">
                <!-- AJAX 동적 삽입 -->
                </tbody>
            </table>
        </div>
    </main>
</div>
<footer>
    <!-- 푸터 내용 추가 -->
</footer>
<script>
    $(document).ready(function() {
        function categoryFaq(queId) {
            $.ajax({
                url: "/faq/major",
                type: "GET",
                data: { queId: queId },
                dataType: "json",
                success: function(data) {
                    var tbody = $("#faq-table-body");
                    tbody.empty();
                    data.forEach(function(faq) {
                        var tr = $("<tr></tr>");
                        var td = $("<td></td>").addClass('title');
                        var span = $("<span></span>").addClass('faq-prefix').text('Q');
                        td.append(span);
                        td.append(document.createTextNode(faq.title));
                        tr.append(td);
                        tbody.append(tr);

                        var contentTr = $("<tr class='content-row'></tr>");
                        var contentTd = $("<td></td>").attr("colspan", "1").addClass("content");
                        contentTr.append(contentTd);
                        tbody.append(contentTr);
                        tr.data('no', faq.no);
                    });
                },
                error: function(error) {
                    console.error("Error", error);
                }
            });
        }

        $(".category-button").click(function() {
            var queId = $(this).val();
            categoryFaq(queId);
        });

        $(document).on('click', '.title', function() {
            var tr = $(this).closest('tr');
            var contentTr = tr.next(".content-row");
            var no = tr.data('no');

            if (!contentTr.is(':visible')) {
                $(".content-row").hide();
                if (contentTr.find('.content').html().trim() === "") {
                    $.ajax({
                        url: '/faq/getContents',
                        method: 'GET',
                        data: { no: no },
                        success: function(content) {
                            contentTr.find('.content').html(content);
                            contentTr.show();
                        },
                        error: function(xhr) {
                            console.error("Error fetching content:", xhr.responseText);
                        }
                    });
                } else {
                    contentTr.show();
                }
            } else {
                contentTr.hide();
            }
        });

        $("#searchButton").click(function() {
            var keyword = $("#searchInput").val().trim();
            if (keyword) {
                $.ajax({
                    url: '/faq/search',
                    type: 'GET',
                    data: { keyword: keyword },
                    dataType: 'json',
                    success: function(data) {
                        var tbody = $("#faq-table-body");
                        tbody.empty();
                        if (data.length === 0) {
                            tbody.append('<tr><td colspan="1">해당 검색어가 없습니다.</td></tr>');
                        } else {
                            data.forEach(function(faq) {
                                var tr = $("<tr></tr>");
                                var td = $("<td></td>").addClass('title');
                                var span = $("<span></span>").addClass('faq-prefix').text('Q');
                                td.append(span);
                                td.append(document.createTextNode(faq.title));
                                tr.append(td);
                                tbody.append(tr);

                                var contentTr = $("<tr class='content-row'></tr>");
                                var contentTd = $("<td></td>").attr("colspan", "1").addClass("content");
                                contentTr.append(contentTd);
                                tbody.append(contentTr);
                                tr.data('no', faq.no);
                            });
                        }
                    },
                    error: function(xhr) {
                        console.error("검색 Error:", xhr.responseText);
                    }
                });
            }
        });

        categoryFaq(6);
    });
</script>
</body>
</html>
