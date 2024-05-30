<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp" %>

<link rel="stylesheet" href="/css/saleManage.css">
<link rel="stylesheet" href="/css/mystyle.css">
<div class="totalBox">
<%--    <div class="btn-sel-box">--%>
<%--        <a href="/sale/manage" class="sel-manage sel-manage-active">상품관리</a>--%>
<%--        <a href="/myPage/saleInfo" class="sel-manage" target="_blank">구매/판매 내역</a>--%>
<%--    </div>--%>
    <div id="buyerlist-modal-box"></div>
    <div class="selectOptiondiv font14">
        <input class="searchbar" name="searchTitle" type="text" placeholder="상품명을 입력해주세요." value="">
        <div class="selectOptionTx">
            <div class="optionTx selected" data-selected="true">전체</div>
            <div class="optionTx" data-selected="false">판매중</div>
            <div class="optionTx" data-selected="false">예약중</div>
            <div class="optionTx" data-selected="false">판매완료</div>
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

<script>
    $(document).ready(function () {

        let imgInfo = cssImage.getImgInfo();
        $(".searchbar").css("background-image", "url('/img/display?fileName=" + imgInfo['search'] + "')");
        window.saleList = function(title = null, sal_s_cd = null, page = 1, pageSize = 10, option = "R") {
            $.ajax({
                type: 'GET',       // 요청 메서드
                url: "/sale/managePage?page=" + page + "&pageSize=" + pageSize + "&title=" + title + "&sal_s_cd=" + sal_s_cd + "&option=" + option,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                dataType: 'json',
                success: function (data) {
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


        let title = $('input[name="searchTitle"]').val(); // 판매글 제목 검색
        let sal_s_cd = null;
        let pre_sal_s_cd = null;

        saleList();

        let optionText;
        // optionTx 클릭했을 경우 value 지정
        $('.optionTx').click(function() {
            // 클릭된 optionTx 요소의 텍스트 값을 가져와서 상태에 따라 sal_s_cd 값을 할당
            let title = $('input[name="searchTitle"]').val();
            optionText = $(this).text();
            sal_s_cd = optionTextSwitch(optionText);
            pre_sal_s_cd = sal_s_cd;

            $('.optionTx').removeClass("selected");
            $(this).addClass("selected");

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
                    let sal_s_cd = optionTextSwitch(optionText);
                    let price = '';
                    let tx_name = '';
                    if(sale.price > 0){
                        price = comma(sale.price);
                        price += "원";
                    } else {
                        price = "-";
                    }
                    let title = textlengover(sale.title, 25);

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

                    let statusSelect = createSaleStatusSelect(sale.sal_s_cd);
                    row.append($("<td>").append(statusSelect));

                    // 상품명
                    row.append($("<td>").addClass("title").html("<a href='/sale/read?no=" + sale.no + "'>" + title + "</a>"));

                    // 가격
                    row.append($("<td>").text(price));

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
                let scrollPosition = window.innerHeight;
                if (ph.prevPage) {
                    pageContainer.append('<button onclick="saleList(\'' + title + '\', ' + sal_s_cd + ', ' + (ph.beginPage - 1) + ', ' + ph.pageSize + '); window.scrollTo(0, 0);">&lt;</button>');
                }
                for (let i = ph.beginPage; i <= ph.endPage; i++) {
                    // 페이지 번호 사이에 공백 추가
                    pageContainer.append('<span class="page-space"></span>');
                    pageContainer.append('<button class="page ' + (i == ph.page ? "paging-active" : "") + '" onclick="saleList(\'' + title + '\', ' + sal_s_cd + ', ' + i + ', ' + ph.pageSize + '); window.scrollTo(0, 0);">' + i + '</button>');
                }
                if (ph.nextPage) {
                    pageContainer.append('<span class="page-space"></span>');
                    pageContainer.append('<button onclick="saleList(\'' + title + '\', ' + sal_s_cd + ', ' + (ph.endPage + 1) + ', ' + ph.pageSize + '); window.scrollTo(0, 0);">&gt;</button>');
                }
                $("#pageContainer").html(pageContainer); // 새로 생성한 페이지 컨테이너를 추가
            }
        }

        let max_hoist_cnt = 3;

        $(document).on("click", ".hoistingBtn", function() {

            // sal_no 값을 추출
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");

            // hoistCnt 값을 추출
            let hoistCntClass = $(this).closest("tr").find("td[class*='hoist_cnt_']").attr("class").split(" ").find(c => c.startsWith("hoist_cnt_"));
            let hoist_cnt = hoistCntClass.replace("hoist_cnt_", "");

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
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");
            if (confirm("수정 하시겠습니까?")) {
                // form 엘리먼트 생성
                let form = $('<form>').attr({
                    method: 'POST',
                    action: '/sale/modify'
                });
                // hidden input 필드 생성하여 sal_no 전달
                $('<input>').attr({
                    type: 'hidden',
                    name: 'no',
                    value: sal_no
                }).appendTo(form);
                // form을 body에 추가하고 submit
                form.appendTo('body').submit();
            }
        });

        $(document).on("click", ".removeBtn", function() {
            // sal_no 값을 추출
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");

            // 끌어올리기 확인 창을 표시하고 사용자의 응답을 처리
            if (confirm("삭제 하시겠습니까?")) {
                // AJAX 요청을 사용하여 폼 데이터를 서버로 전송
                $.ajax({
                    type: 'POST',
                    url: '/sale/remove?no=' + sal_no,
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

        $(document).on("click", ".buylist-close-btn", function(event) {
            event.preventDefault();
            $("#buyerlist-modal-box").children().remove();
        });

        $(document).on("click", ".choicebtn", function(event) {
            event.preventDefault();

            let selectedBuyers = [];
            $("input[name='selectedBuyers']:checked").each(function() {
                let buyerInfo = {
                    buyer_id: $(this).val(),
                    no: $(this).siblings(".buyer-id").val(),
                    buyer_nick: $(this).siblings(".buyer-nick").val(),
                    sal_s_cd: "C"
                };
                selectedBuyers.push(buyerInfo);
            });

            if (selectedBuyers.length > 0) {
                $.ajax({
                    type: "POST",
                    url: "/sale/updateSalebuyer", // your endpoint to handle the selected buyers
                    data: JSON.stringify({ selectedBuyers: selectedBuyers }),
                    contentType: "application/json",
                    success: function(response) {
                        $("#buyerlist-modal-box").children().remove();
                    },
                    error: function(xhr, status, error) {
                        alert("전송 실패: " + error);
                    }
                });
            } else {
                alert("한 명 이상의 구매자를 선택해주세요.");
            }
        });


        $(document).on("change", ".sal_s_cd", function() {
            let sal_s_cd = $(this).val();
            let sal_s_name = $(this).find("option:checked").text(); // 선택된 옵션의 텍스트 가져오기
            let sal_no = $(this).closest("tr").attr("class").split(" ").find(c => c.startsWith("sal_no_")).replace("sal_no_", "");

            $.ajax({
                type: "POST",
                url: "/sale/updateSaleSCd",
                data: {no: sal_no, sal_s_cd: sal_s_cd, seller_id: "${sessionScope.userId}"},
                dataType: 'json',
                success: function (data) {
                    alert("판매글 상태가 " + sal_s_name + "(으)로 변경되었습니다.");

                    if(sal_s_name === "판매완료" && data.length !== 0){
                        let str = "";
                        str += '<div id="buyerlist-modal">';
                        str += '<button class="buylist-close-btn">X</button>';
                        str += '<form id="buyerForm" class="buyerlist">';
                        data.forEach(user => {
                            str += `<div class="buyerlist-item">`
                            str += '<img src="/img/display?fileName=' + user.img_full_rt + '" class="buyer-profile-img"/>';
                            str += '<input type="hidden" name="no" class="buyer-id" value="' + sal_no + '">';
                            str += '<input type="hidden" name="buyer_id" class="buyer-id" value="' + user.ur_id + '">';
                            str += '<input type="hidden" name="buyer_nick" class="buyer-nick" value="' + user.nick + '">';
                            str += '<p class="p-buyer-nick">' + user.nick + '</p>'
                            str += '<input type="checkbox" name="selectedBuyers" class="buyer-checkbox" value="'+user.ur_id+'">'
                            str += `</div>`
                        });

                        str += `<input type="button" class="choicebtn" value="Submit Selected Buyers"/>`;
                        str += '</form>';
                        str += '</div>';

                        $("#buyerlist-modal-box").html(str);
                    }
                    saleList(title, pre_sal_s_cd);
                },
                error: function (xhr, status, error) {
                    alert("판매글 상태 변경이 실패하였습니다.");
                }
            });
        });

        // if(saleStatusText !== "판매중"){
        //     str += "<div class='saleStatus-box'>";
        //     str += "<span class='saleStatusText'>" + saleStatusText + "</span>";
        //     str += "</div>";
        // }



        $("#modifyBtn").on("click", function () {
            if (confirm("수정 하시겠습니까?")) {
                let saleNo = "${sale.no}";
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
                case '판매완':
                    sal_s_cd = 'C';
                    break;
                default:
                    sal_s_cd = null;
            }
            return sal_s_cd;
        };

        function createSaleStatusSelect(selectedValue) {
            const options = {
                'S': '판매중',
                'R': '예약중',
                'C': '판매완료'
            };

            let select = $('<select>').addClass('sal_s_cd');

            $.each(options, function(value, text) {
                let option = $('<option>').attr('value', value).text(text);
                if (value === selectedValue) {
                    option.attr('selected', 'selected');
                }
                select.append(option);
            });

            return select;
        }

    });
</script>
<script src="../js/Etc.js"></script>
<%@include file="../fixed/footer.jsp" %>