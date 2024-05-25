<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .selectOptiondiv {
            display: flex;
            -webkit-box-align: center;
            align-items: center;
            justify-content: space-between;
            margin-top: 30px;
            margin-bottom: 20px;
            gap:10px;
        }

        input[type="text"] {
            width: 350px;
            margin-right: 20px; /* 간격 조정 */
            padding: 5px;
        }

        .selectOptionTx{
            display: flex;
            column-gap: 24px;
        }

        .optionTx {
            font-weight: 500;
            line-height: normal;
            cursor: pointer;
            color: rgb(216, 12, 24);
        }

        .totalBox {
            width: 1200px;
            margin: auto;
        }

        .totalBox table thead > tr {
            height: 2.5rem;
        }

        .totalBox table tr > :nth-child(3) {
            width: 218px;
        }

        .totalBox table thead > tr > th {
            vertical-align: middle;
        }
        .totalBox table tr > * {
            flex-shrink: 0;
        }

        .saleList {
            border-bottom: 2px solid #eeeeee;
            text-align: center;
        }

        .totalTable {
            width: 100%;
            border-top: 1px solid #787e89;
            font-size: 14px;
            border-collapse: collapse;
        }

        th, td {
            border-bottom: 1px solid #787e89;
            padding: 10px;
        }

        .tableHead {
            display: table-header-group;
            vertical-align: middle;
            unicode-bidi: isolate;
            border-color: inherit;
        }

        .img {
            width: 152px;
            height: 152px;
            background-color: yellow;
            margin: 0 auto;
        }

        /* 원하는 색상으로 선택된 옵션 변경 */
        .optionTx.selected {
            color: red; /* 빨간색 */
            font-weight: 600;
        }

        /* 선택되지 않은 옵션 색상 변경 */
        .optionTx:not(.selected) {
            color: #7F7F7F; /* 회색 */
            font-weight: 600;
        }

        .btnColorRed {
            color: rgb(216, 12, 24);
        }

        .btnColorBlue {
            color: rgb(2, 122, 255);
        }

        .Btn {
            width: 4.875rem;
            height: 2rem;
            text-align: center;
            border-radius: 2px;
            border: 1px solid rgb(195, 194, 204);
            background-color: white;
            font-weight: 500;
            line-height: normal;
            margin: 2px;
        }

        .fontMarginPadding {
            margin: auto;
            padding: 10px;
        }

        .font16 {
            font-size: 16px;
        }



    </style>
</head>
<body>
<div class="totalBox">
    <div class="selectOptiondiv font14">
        <input class="" name="searchTitle" type="text" placeholder="상품명을 입력해주세요." value="">
        <div class="selectOptionTx">
            <div class="optionTx selected" data-selected="true">전체</div>
            <div class="optionTx" data-selected="false">판매중</div>
            <div class="optionTx" data-selected="false">예약중</div>
            <div class="optionTx" data-selected="false">거래완료</div>
        </div>
    </div>
    <table class="totalTable">
        <thead class="tableHead">
        <tr>
            <th>사진</th>
            <th>판매상태</th>
            <th>상품명</th>
            <th>가격</th>
            <th>판매/나눔</th>
            <th>찜</th>
            <th>등록일</th>
            <th>기능</th>
        </tr>
        </thead>
        <tbody class="saleList" id="saleList">
        </tbody>
    </table>
    <br>
    <div id="pageContainer" style="text-align: center">
    </div>
    <br>
</div>

</body>
</html>
<script>
    $(document).ready(function () {
        window.saleList = function(title = null, sal_s_cd = null, page = 1, pageSize = 10) {
            $.ajax({
                type: 'GET',       // 요청 메서드
                url: "/sale/managePage?page=" + page + "&pageSize=" + pageSize + "&title=" + title + "&sal_s_cd=" + sal_s_cd,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    let ph = data.ph;
                    let saleList = data.saleList;
                    let startOfToday = data.startOfToday;
                    $("#saleList").html(updateSaleList(saleList, startOfToday, ph, title, sal_s_cd));
                },
                error: function (result) {
                    alert("화면 로딩 중 오류 발생");
                }
            });
        }


        <%--let seller_id = "${sessionScope.userId}";--%>
        let title = $('input[name="searchTitle"]').val(); // 판매글 제목 검색
        let sal_s_cd = null;

        saleList(title, sal_s_cd);

        // optionTx 클릭했을 경우 value 지정
        $('.optionTx').click(function() {
            // 클릭된 optionTx 요소의 텍스트 값을 가져와서 상태에 따라 sal_s_cd 값을 할당
            let title = $('input[name="searchTitle"]').val();
            let optionText = $(this).text();
            sal_s_cd = optionTextSwitch(optionText);

            // 모든 optionTx에 #7F7F7F 색상 적용
            $('.optionTx').css('color', '#7F7F7F');
            // 클릭된 요소만 빨간색으로 변경
            $(this).css('color', 'red');

            saleList(title, sal_s_cd);
        });

        // 검색 버튼 클릭 이벤트 핸들러
        $('#searchBtn').click(function() {
            performSearch();
        });

        // input 창에서 엔터 키를 눌렀을 때의 이벤트 핸들러
        $('input[name="searchTitle"]').keypress(function(event) {
            if (event.which === 13) { // 엔터 키의 keyCode는 13입니다.
                performSearch();
            }
        });

        // 검색을 수행하는 함수
        function performSearch() {
            let title = $('input[name="searchTitle"]').val();
            let optionText = $('.optionTx.selected').text();
            let sal_s_cd = optionTextSwitch(optionText);
            saleList(title, sal_s_cd);
        }


        let saleFunc = '<button class="Btn btnColorRed hoistingBtn">UP</button><br><button class="Btn btnColorBlue modifyBtn">수정</button><br><button class="Btn btnColorBlue removeBtn">삭제</button>';
        // 업데이트된 saleList를 화면에 출력하는 함수
        function updateSaleList(saleList, startOfToday, ph, title, sal_s_cd) {
            // 기존 saleList 테이블의 tbody를 선택하여 내용을 비웁니다.
            $("#saleList").empty();

            let bid_name = '';
            let tx_name = '';

            if (saleList.length > 0) {
                // 판매 상태에 따라 텍스트 설정
                saleList.forEach(function (sale) {
                    let optionText = $('.optionTx.selected').text();
                    let sal_s_cd = optionTextSwitch(optionText);;

                    switch (sale.tx_s_cd) {
                        case 'S':
                            tx_name = '판매';
                            break;
                        case 'F':
                            tx_name = '나눔';
                            break;
                        default:
                            tx_name = '';
                    }

                    switch (sale.bid_cd) {
                        case 'P':
                            bid_name = '가격제시';
                            break;
                        case 'T':
                            bid_name = '나눔신청';
                            break;
                        default:
                            bid_name = '';
                    }


                    let row = $("<tr>").addClass("sal_no_" + sale.no);
                    // 썸네일
                    row.append($("<td>").addClass("Thumbnail_ima").html("<a href='/sale/read?no=" + sale.no + "'>" + "<img class='imgClass' src='/img/display?fileName=" + sale.img_full_rt + "'/>" + "</a>")); // 이미지

                    // 판매상태
                    row.append($("<td>").text(sal_s_cd)); // select option

                    // 상품명
                    row.append($("<td>").addClass("title").html("<a href='/sale/read?no=" + sale.no + "'>" + sale.title + "</a>"));

                    // 가격
                    row.append($("<td>").text(sale.price));

                    // 판매/나눔 + 가격제시/나눔신청 여부
                    // 판매/나눔 + 가격제시/나눔신청 여부
                    if(sale.bid_cd != 'N') {
                        row.append($("<td>").text(tx_name + "(" + bid_name + ")" ));

                    } else {
                        row.append($("<td>").text(tx_name));
                    }

                    // 찜 횟수
                    row.append($("<td>").text(sale.like_cnt));

                    // 등록일
                    row.append($("<td>").addClass("hoist_cnt_" + sale.hoist_cnt).html(dateToString(sale.h_date, startOfToday)));

                    // 기능
                    row.append($("<td>").html(saleFunc));

                    $("#saleList").append(row);
                });
            } else {
                $("#saleList").append("<tr><td colspan='8'>데이터가 없습니다.</td></tr>");
            }

            $("#pageContainer").empty(); // 기존에 있는 페이지 내용 비우기

            if (ph.totalCnt != null && ph.totalCnt != 0) {
                let pageContainer = $('<div>').attr('id', 'pageContainer').css('text-align', 'center'); // 새로운 div 엘리먼트 생성
                if (ph.prevPage) {
                    pageContainer.append('<a onclick="saleList(\'' + title + '\', ' + sal_s_cd + ', ' + (ph.beginPage - 1) + ', ' + ph.pageSize + ')">&lt;</a>');
                }
                for (let i = ph.beginPage; i <= ph.endPage; i++) {
                    // 페이지 번호 사이에 공백 추가
                    pageContainer.append('<span class="page-space"></span>');
                    pageContainer.append('<a class="page ' + (i == ph.page ? "paging-active" : "") + '" href="#" onclick="saleList(\'' + title + '\', ' + sal_s_cd + ', ' + i + ', ' + ph.pageSize + ')">' + i + '</a>');
                }
                if (ph.nextPage) {
                    pageContainer.append('<span class="page-space"></span>');
                    pageContainer.append('<a onclick="saleList(\'' + title + '\', ' + sal_s_cd + ', ' + (ph.endPage + 1) + ', ' + ph.pageSize + ')">&gt;</a>');
                }
                $("#pageContainer").html(pageContainer); // 새로 생성한 페이지 컨테이너를 추가
            }
        }

        <%--let hoist_cnt = ${sale.hoist_cnt};--%>
        let max_hoist_cnt = 3;

        $(document).on("click", ".hoistingBtn", function() {

            // sal_no 값을 추출
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");

            // hoistCnt 값을 추출
            let hoistCntClass = $(this).closest("tr").find("td[class*='hoist_cnt_']").attr("class").split(" ").find(c => c.startsWith("hoist_cnt_"));
            let hoist_cnt = hoistCntClass.replace("hoist_cnt_", "");

            // 추출된 값을 알림창으로 출력
            alert(sal_no + " " + hoist_cnt);

            // 끌어올리기 확인 창을 표시하고 사용자의 응답을 처리
            if (confirm("끌어올리겠습니까? 끌어올리기 한도가 " + (max_hoist_cnt - hoist_cnt) + "번 남았습니다.")) {
                // AJAX 요청을 사용하여 폼 데이터를 서버로 전송
                $.ajax({
                    type: 'POST',
                    url: '/hoisting',
                    data: {
                        no: sal_no
                    },
                    success: function(data) {
                        // 요청이 성공한 경우, saleList 함수를 호출하여 리스트를 업데이트
                        let title = $('input[name="searchTitle"]').val(); // 판매글 제목 검색
                        let optionText = $('.optionTx.selected').text();
                        let sal_s_cd = optionTextSwitch(optionText);

                        saleList(title, sal_s_cd);
                    },
                    error: function(xhr, status, error) {
                        // 요청이 실패한 경우, 오류 메시지를 알림창으로 표시
                        alert("끌어올리기에 실패했습니다. 다시 시도해주세요.");
                    }
                });
            }
        });

        $(document).on("click", ".modifyBtn", function() {
            // sal_no 값을 추출
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");
            alert(sal_no);
            // 끌어올리기 확인 창을 표시하고 사용자의 응답을 처리
            if (confirm("수정 하시겠습니까?")) {
                // AJAX 요청을 사용하여 sal_no 값을 서버로 전송
                $.ajax({
                    type: 'POST',
                    url: '/sale/modify',
                    data: {
                        no: sal_no
                    },
                    success: function(response) {
                        // 서버 응답에 따라 페이지를 이동
                        window.location.replace('/sale/modify?no=' + sal_no);
                    },
                    error: function(xhr, status, error) {
                        // 요청이 실패한 경우, 오류 메시지를 알림창으로 표시
                        alert("수정 요청에 실패했습니다. 다시 시도해주세요.");
                    }
                });
            }
        });

        $(document).on("click", ".removeBtn", function() {
            // sal_no 값을 추출
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");
            alert(sal_no);
            // 끌어올리기 확인 창을 표시하고 사용자의 응답을 처리
            if (confirm("수정 하시겠습니까?")) {
                // AJAX 요청을 사용하여 폼 데이터를 서버로 전송
                $.ajax({
                    type: 'POST',
                    url: '/sale/modify?no=' + sal_no,
                    success: function(data) {
                        // 요청이 성공한 경우, saleList 함수를 호출하여 리스트를 업데이트
                        let title = $('input[name="searchTitle"]').val(); // 판매글 제목 검색
                        let optionText = $('.optionTx.selected').text();
                        let sal_s_cd = optionTextSwitch(optionText);

                        saleList(title, sal_s_cd);
                    },
                    error: function(xhr, status, error) {
                        // 요청이 실패한 경우, 오류 메시지를 알림창으로 표시
                        alert("에러가 발생하였습니다. 다시 시도해 주세요.");
                    }
                });
            }
        });


        // $(document).on("click", ".hoistingBtn", function() {
        //     alert($(this));
        //     let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");
        //     let hoistCntClass = $(this).closest("tr").find("td[class*='hoist_cnt_']").attr("class").split(" ").find(c => c.startsWith("hoist_cnt_"));
        //     let hoist_cnt = hoistCntClass.replace("hoist_cnt_", "");
        //
        //     alert(sal_no + " " + hoist_cnt);
        //
        //     if (confirm("끌어올리겠습니까? 끌어올리기 한도가 " + (max_hoist_cnt-hoist_cnt) + "번 남았습니다.")) {
        //         $("<input>").attr({
        //             type: "hidden",
        //             name: "no",
        //             value: sal_no
        //         }).appendTo("#form");
        //         $("#form").attr("action", "/hoisting");
        //         $("#form").submit();
        //     }
        // });

        <%--$("button[name='hoistingBtn']").on("click", function() {--%>
        <%--    // 현재 클릭된 버튼의 부모인 <tr> 태그를 찾고 해당 클래스를 가져옵니다.--%>
        <%--    var trClass = $(this).closest("tr").attr("class");--%>

        <%--    // 가져온 클래스를 가지고 원하는 작업을 수행합니다.--%>
        <%--    alert("클릭된 버튼의 부모 <tr> 태그의 클래스: " + trClass);--%>
        <%--// $("button[name='hoistingBtn']").on("click", function () {--%>
        <%--//     let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");--%>
        <%--//     alert(sal_no);--%>
        <%--    // if (confirm("끌어올리겠습니까? 끌어올리기 한도가" + (max_hoist_cnt-hoist_cnt) + "남았습니다.")) {--%>
        <%--    if (confirm("끌어올리겠습니까? 끌어올리기 한도가 남았습니다.")) {--%>
        <%--        let saleNo = "${Sale.no}";--%>
        <%--        $("<input>").attr({--%>
        <%--            type: "hidden",--%>
        <%--            name: "no",--%>
        <%--            value: saleNo--%>
        <%--        }).appendTo("#form");--%>
        <%--        $("#form").attr("action", "/hoisting");--%>
        <%--        $("#form").submit();--%>
        <%--    }--%>
        <%--});--%>


        $("#modifyBtn").on("click", function () {
            if (confirm("수정 하시겠습니까?")) {
                let saleNo = "${Sale.no}";
                $("<input>").attr({
                    type: "hidden",
                    name: "no",
                    value: saleNo
                }).appendTo("#form");
                $("#form").attr("action", "/sale/modify");
                $("#form").submit();
            }
        });


        function addZero(value = 1) {
            return value > 9 ? value : "0" + value;
        }

        function dateToString(ms = 0, startOfToday) {
            console.log(startOfToday);
            let date = new Date(ms);

            let yyyy = date.getFullYear();
            let mm = addZero(date.getMonth() + 1);
            let dd = addZero(date.getDate());

            let HH = addZero(date.getHours());
            let MM = addZero(date.getMinutes());
            let ss = addZero(date.getSeconds());

            let now = new Date();

            if (date.getTime() >= startOfToday) {
                let secondsAgo = Math.floor((now.getTime() - date.getTime()) / 1000);
                if (secondsAgo < 60) {
                    return secondsAgo + "초 전";
                } else {
                    let minutesAgo = Math.floor(secondsAgo / 60);
                    if (minutesAgo < 60) {
                        return minutesAgo + "분 전";
                    } else {
                        let hoursAgo = Math.floor(minutesAgo / 60);
                        return hoursAgo + "시간 전";
                    }
                }
            }
            let daysAgo = Math.floor((now.getTime() - date.getTime()) / (1000 * 60 * 60 * 24)) + 1;
            return daysAgo + "일 전";
        }

        function optionTextSwitch(optionText) {
            switch (optionText) {
                case '전체':
                    sal_s_cd = null;
                    break;
                case '판매중':
                    sal_s_cd = 'S';
                    break;
                case '예약중':
                    sal_s_cd = 'R';
                    break;
                case '거래완료':
                    sal_s_cd = 'C';
                    break;
                default:
                    sal_s_cd = null;
            }
            return sal_s_cd;
        };
    });
</script>



<%@include file="../fixed/footer.jsp" %>