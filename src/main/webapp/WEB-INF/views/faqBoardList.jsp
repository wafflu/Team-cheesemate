<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자주묻는질문</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 8px;
            margin-bottom: 2px;
            background-color: #f0f0f0;
            border: 1px solid #ddd;
        }
        .faq-prefix {
            color: green;   /* 초록색으로 설정 */
            font-weight: bold; /* 글씨를 굵게 */
            margin-right: 10px; /* 오른쪽 여백 추가 */
        }
        .title {
            display: flex; /* Flexbox를 사용하여 내용을 한 줄에 나열 */
            align-items: center; /* 세로 중앙 정렬 */
        }
        .category-button {
            background-color: #4CAF50; /* 버튼 배경색 */
            border: none; /* 테두리 없앰 */
            color: white; /* 글자색 */
            padding: 10px 20px; /* 패딩 */
            text-align: center; /* 텍스트 중앙 정렬 */
            text-decoration: none; /* 밑줄 없앰 */
            display: inline-block; /* 인라인 블록으로 표시 */
            font-size: 16px; /* 글자 크기 */
            margin: 4px 2px; /* 마진 */
            cursor: pointer; /* 커서 포인터 */
            border-radius: 10px; /* 모서리 둥글게 */
        }
        .category-container {
            margin-bottom: 20px; /* 카테고리와 테이블 사이 여백 */
        }
    </style>

</head>

<body>
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

<table>
    <thead>
    <tr><th>제목</th></tr>
    </thead>
    <tbody id="faq-table-body">
    <!-- AJAX 동적 삽입 -->
    <tbody id="">
    </tbody>
</table>

<script>
    $(document).ready(function() {
        // queId == 6 이면 전체 목록, 1~5이면 각각의 카테고리로 이동한다.
        function categoryFaq(queId) {
            $.ajax({
                url: "/faq/major",
                type: "GET",
                data: { queId: queId },
                dataType: "json",
                success: function(data) {
                    var tbody = $("#faq-table-body"); // FAQ 목록을 표시할 테이블
                    // 기존에 표시된 내용을 비운다
                    tbody.empty();
                    data.forEach(function(faq) {
                        // 새 tr 생성 후 td생성하고 title클래스 추가한다.
                        var tr = $("<tr></tr>");
                        var td = $("<td></td>").addClass('title');
                        var span = $("<span></span>").addClass('faq-prefix').text('Q');
                        td.append(span);
                        td.append(document.createTextNode(faq.title));
                        tr.append(td);
                        tbody.append(tr);

                        // 내용을 위한 숨겨진 행 추가
                        var contentTr = $("<tr style='display:none;'></tr>").addClass("content-row");
                        var contentTd = $("<td></td>").attr("colspan", "1").addClass("content");
                        contentTr.append(contentTd);
                        tbody.append(contentTr);
                        tr.data('no', faq.no); // 행에 FAQ의 고유 번호 저장한다.
                    });
                },
                error: function(error) { // 요청이 실패했을 때 실행할 함수
                    console.error("Error", error);
                }
            });
        }

        // 카테고리 버튼을 클릭하면 이벤트 처리한다.
        $(".category-button").click(function() {
            var queId = $(this).val(); // 클릭된 버튼의 value 속성(카테고리 ID) 읽기
            categoryFaq(queId); // 해당 카테고리 ID로 FAQ 데이터 요청
        });

        // 제목 클릭 시 이벤트 처리
        $(document).on('click', '.title', function() {
            var tr = $(this).closest('tr'); // 클릭된 제목이 속한 행 선택
            var contentTr = tr.next(".content-row"); // 해당 제목의 내용이 담긴 행 선택
            var no = tr.data('no'); // 행에 저장된 FAQ 번호 가져오기

            if (!contentTr.is(':visible')) { // 내용 행이 현재 숨겨져 있으면
                $(".content-row").hide(); // 모든 내용 행을 숨김
                if (contentTr.find('.content').html().trim() === "") { // 내용이 아직 로드되지 않았으면
                    // 서버에서 내용 가져오기
                    $.ajax({
                        url: '/faq/getContents',
                        method: 'GET',
                        data: { no: no },
                        success: function(content) {
                            contentTr.find('.content').html(content); // 내용 행에 데이터 삽입
                            contentTr.show(); // 내용 행 표시
                        },
                        error: function(xhr) {
                            console.error("Error fetching content:", xhr.responseText);
                        }
                    });
                } else {
                    contentTr.show(); // 이미 로드된 내용이 있으면 그냥 표시
                }
            } else {
                contentTr.hide(); // 내용 행이 이미 보이는 상태면 숨김
            }
        });

        // 검색 버튼 클릭 시 이벤트 처리한다.
        $("#searchButton").click(function() {
            // 입력에서 검색어 읽기는다.
            var keyword = $("#searchInput").val().trim();
            if (keyword) { // 검색어가 비어있지 않으면
                $.ajax({
                    url: '/faq/search',
                    type: 'GET',
                    data: { keyword: keyword },
                    dataType: 'json',
                    success: function(data) {
                        var tbody = $("#faq-table-body");
                        tbody.empty(); // 기존 목록 제거
                        if (data.length === 0) { // 검색 결과가 없으면
                            tbody.append('<tr><td colspan="1">해당 검색어가 없습니다.</td></tr>');
                        } else { // 검색 결과가 있으면
                            data.forEach(function(faq) {
                                var tr = $("<tr></tr>");
                                var td = $("<td></td>").addClass('title');
                                var span = $("<span></span>").addClass('faq-prefix').text('Q');
                                td.append(span);
                                td.append(document.createTextNode(faq.title));
                                tr.append(td);
                                tbody.append(tr);

                                // 내용을 위한 숨겨진 행 추가
                                var contentTr = $("<tr style='display:none;'></tr>").addClass("content-row");
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
        // 초기 페이지 로드 시 전체 FAQ 목록 로드
        categoryFaq(6);
    });
</script>
</body>
</html>