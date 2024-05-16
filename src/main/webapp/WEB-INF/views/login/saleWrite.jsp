<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String toURL = request.getParameter("toURL");
%>
<!DOCTYPE html>
<html>

<head>
    <title>sale</title>
    <style>
        .sale-division-line {
            border-top: 1px solid #444444;
            margin: 30px 0px;
        }

        .sale-preview-image {
            display: inline-block;
            position: relative;
            margin-right: 10px;
        }

        #openModalBtn {
            all: unset;
            background-color: steelblue;
            color: white;
            padding: 5px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .SaleModal {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .sale_modal_overlay {
            background-color: rgba(0, 0, 0, 0.6);
            width: 100%;
            height: 100%;
            position: absolute;
        }

        .sale_modal_content {
            background-color: white;
            padding: 50px 100px;
            text-align: center;
            position: relative;
            width: 50%;
            top: 0px;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
            overflow-y: auto;
            /* 세로 스크롤이 필요한 경우 스크롤 허용 */
            max-height: 70%;
            /* 모달 창의 최대 높이 설정 */
        }

        #closeModalBtn {
            position: absolute;
            top: 10px;
            /* 원하는 위치로 조정 */
            right: 10px;
            /* 원하는 위치로 조정 */
            background-color: transparent;
            /* 배경색 설정 */
            border: none;
            /* 테두리 제거 */
            cursor: pointer;
            /* 마우스 커서를 포인터로 변경 */
        }

        .sale-table-wrapper {
            overflow-y: auto;
            max-height: 200px;
        }

        tr:hover {
            background-color: cornflowerblue;
            cursor: pointer;
        }


        #saleSearchInput {
            /* 모달 창의 너비에 맞게 조정 */
            width: 100%;
            padding: 10px;
            /* 내부 여백 설정 */
            margin: 0 auto;
            /* 가운데 정렬 */
        }


        #addrTable {
            width: 100%;
            /* addrTable의 너비를 100%로 설정하여 모달 창에 맞춤 */
        }

        h1 {
            margin: 0;
        }

        .SaleHidden {
            display: none;
        }

        /* input text 및 textarea 너비 조절 */
        input[type="text"], input[type="number"],
        textarea {
            width: calc(100% - 20px);
            /* 전체 너비에서 여백을 뺀 값으로 설정 */
            padding: 5px;
            /* 내부 여백 설정 */
            margin-top: 10px;
        }
    </style>
</head>

<body>
<div>
    <form id="writeForm" name="writeForm" method="POST" enctype="multipart/form-data">
        <p>상품 정보</p>
        <div class="sale-division-line"></div>
        <div id="register_img">
            <p>
                상품이미지(<span id="img_count">0</span>/10) <input type="file" id="imageBtn" name="imagename"
                                                               multiple='multiple'>
            </p>
        </div>
        <p>
            제목 <input name="title" type="text" placeholder="판매/나눔글 제목을 입력하세요" value="${Sale.title}"/>
        </p>
        <p>카테고리</p>
        <select id="category1" onchange="loadCategory2()">
            <option value="" disabled selected>대분류</option>
            <c:forEach var="category" items="${saleCategory1}">
                <option value="${category.sal_cd}">${category.name}</option>
            </c:forEach>
        </select>

        <select id="category2" onchange="loadCategory3()">
            <option value="" disabled selected>중분류</option>
        </select>

        <select id="category3">
            <option value="" disabled selected>소분류</option>
        </select>

        <p style="color: orangered;" id="salecategoryMsg"></p>
        <span style="color: red;">선택한 카테고리 : <b><p style="display: inline; color: red;" id="sal_name"></p></b></span>

        <p>상품상태</p>
        <input type="radio" name="pro_s_cd" value="S"/>새상품(미사용) <br/>
        <input type="radio" name="pro_s_cd" value="A"/>사용감 없음 <br/>
        <input type="radio" name="pro_s_cd" value="B"/>사용감 적음 <br/>
        <input type="radio" name="pro_s_cd" value="C"/>사용감 많음 <br/>
        <input type="radio" name="pro_s_cd" value="D"/>고장/파손 상품 <br/>
        <p>
            거래방법(2개 이하)
            <input type="checkbox" class="trade_s_cd" value="O"/> 온라인
            <input type="checkbox" class="trade_s_cd" value="F"/> 직거래
            <input type="checkbox" class="trade_s_cd" value="D"/> 택배거래
        </p>
        <p>설명</p>
        <p style="white-space: pre-wrap"><textarea name="contents" id="contents" cols="30" rows="10" style="white-space: pre-line;">${Sale.contents}</textarea></p>
        <p>해시태그(선택) <input type="text" id="hashtagInput" name="tag" value="${Tag}"/></p>
        <div id="hashtagContainer"></div>
        <input type="radio" class="tx_s_cd" name="tx_s_cd" value="S"/>판매
        <input type="radio" class="tx_s_cd" name="tx_s_cd" value="F"/>나눔
        <p style="color: red;" id="txMsg">판매, 나눔 중 한 가지를 선택해 주세요.</p>
        <p>
            상품가격
            <input name="price" type="number" placeholder="판매할 가격을 입력해주세요." min="0" value="${Sale.price}"/>
        </p>
        <p hidden><input type="radio" name="bid_cd" value="N" checked/> 미사용 </p>
        <p class="proposal" hidden><input class="proposal" type="radio" name="bid_cd" value="P"/> 가격제안받기</p>
        <p class="trade" hidden><input class="trade" type="radio" name="bid_cd" value="T"/> 나눔신청받기</p>
        <p>
            상품정가(선택)
            <input type="number" name="reg_price" placeholder="상품의 정가를 입력해주세요(선택)." min="0" value="${Sale.reg_price}"/>
        </p>
        <button id="openModalBtn">거래희망 주소 검색</button>
        <div id="openModal" class="SaleModal SaleHidden">
            <div class="sale_modal_overlay"> <!--모달창의 배경색--></div>
            <div class="sale_modal_content">
                <button id="closeModalBtn">x</button>
                <h1>주소 검색</h1>
                <input id="saleSearchInput" type="text" placeholder="동(읍/면/리)을 입력해주세요.">
                <div class="sale-table-wrapper">
                    <table id="addrTable" class="table text-center">
                        <thead>
                        <tr>
                            <th style="width:150px;">행정동코드</th>
                            <th style="width:600px;">주소명</th>
                        </tr>
                        </thead>
                        <tbody id="addrList"></tbody>
                    </table>
                </div>
            </div>
        </div>
<%--        <input name="addr_cd" value="${Sale.addr_cd}">--%>
<%--        <input name="addr_name" value="${Sale.addr_name}">--%>
        <p>거래장소
            <input id="pickup_addr_cd" name="pickup_addr_cd" type="text" value="${Sale.pickup_addr_cd}" hidden>
            <input id="pickup_addr_name" name="pickup_addr_name" type="text" value="${Sale.pickup_addr_name}" disabled>
        </p>

        <p>
            거래희망장소(선택)
            <input type="text" name="detail_addr" placeholder="거래를 희망하는 상세장소를 작성하세요." value="${Sale.detail_addr}"/>
        </p>
        <p>
            브랜드(선택)
            <input type="text" name="brand" placeholder="브랜드를 작성하세요(선택)." value="${Sale.brand}"/>
        </p>
        <!-- <input type="button" id="submitBtn" value="등록하기" onclick="write()"/> -->
        <input type="button" id="submitBtn" value="등록하기"/>
    </form>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    let submitURL = '/sale/insert';
    $(document).ready(function () {
        // 현재 URL에서 addr_cd 매개변수를 제거하고 페이지를 다시로드하지 않음
        // history.replaceState({}, document.title, window.location.pathname);

        // 현재 URL에서 "modify" 문자열이 포함되어 있는지 확인
        if (window.location.href.indexOf("modify") != -1) {
            // Sale의 pro_s_cd 값 : 상품상태
            let pro_s_cd = "${Sale.pro_s_cd}";
            // pro_s_cd 값에 해당하는 radio input 선택
            $("input[name='pro_s_cd'][value='" + pro_s_cd + "']").prop("checked", true);

            // Sale의 trade_s_cd_1과 trade_s_cd_2 값 : 거래방법
            let trade_s_cd_1 = "${Sale.trade_s_cd_1}";
            let trade_s_cd_2 = "${Sale.trade_s_cd_2}";

            // trade_s_cd_1 값에 해당하는 checkbox 선택(필수값O)
            $("input[type='checkbox'][value='" + trade_s_cd_1 + "']").prop("checked", true);

            // trade_s_cd_2 값에 해당하는 checkbox 선택(필수값X)
            if (trade_s_cd_2) {
                $("input[type='checkbox'][value='" + trade_s_cd_2 + "']").prop("checked", true);
            }

            let tagValue = "${Tag}";
            createHashtagInput(); // 페이지 로드 시에도 input 생성
            // input 요소에 이벤트 리스너 추가
            document
                .getElementById("hashtagInput")
                .addEventListener("input", createHashtagInput);

            // Sale의 tx_s_cd값 : 판매/나눔
            let tx_s_cd = "${Sale.tx_s_cd}";
            if (tx_s_cd) {
                $("#txMsg").text(""); // 메시지 제거
                $("input[name='tx_s_cd'][value='" + tx_s_cd + "']").prop("checked", true);
            }

            // Sale의 bid_cd 값 : 가격제시/나눔신청
            let bid_cd = "${Sale.bid_cd}";
            // bid_cd 값에 해당하는 radio input 선택
            $(".proposal").show();
            if (bid_cd === "P") {
                $("input[name='bid_cd'][value='" + bid_cd + "']").prop("checked", true);
            } else if (bid_cd === "T") {
                $(".trade").show();
                $("input[name='bid_cd'][value='" + bid_cd + "']").prop("checked", true);
            }

            // Sale의 category 값
            // 조건에 따라 sal_i_cd 값을 설정
            let sal_i_cd = "${Sale.sal_i_cd}";
            console.log(sal_i_cd);
            if (sal_i_cd.length == 9) {
                let category1Value = sal_i_cd.substring(0, 3); // sal_i_cd의 앞 3글자
                $("#category1").val(category1Value).trigger("change");
                let category2Value = sal_i_cd.substring(0, 6); // sal_i_cd의 앞 6글자
                $.ajax({
                    type: "POST",
                    url: "/sale/saleCategory2",
                    dataType: "json",
                    data: {category1: category1Value},
                    success: function (data) {
                        $("#category2").val(category2Value).trigger("change");
                        let category3Value = sal_i_cd; // sal_i_cd의 전체 글자
                        $.ajax({
                            type: "POST",
                            url: "/sale/saleCategory3",
                            dataType: "json",
                            data: {category2: category2Value},
                            success: function (data) {
                                $("#category3").val(category3Value).trigger("change");
                            },
                            error: function (xhr, status, error) {
                                alert("Error", error);
                            }
                        });
                    },
                    error: function (xhr, status, error) {
                        alert("Error", error);
                    }
                });
                // let category1Value = sal_i_cd.substring(0, 3); // sal_i_cd의 앞 3글자
                // let category2Value = sal_i_cd.substring(0, 6); // sal_i_cd의 앞 6글자
                // let category3Value = sal_i_cd; // sal_i_cd의 전체 글자
                // $("#category1").val(category1Value).trigger("change").promise().done(function(){
                //     $("#category2").val(category2Value).trigger("change").promise().done(function(){
                //         $("#category3").val(category3Value).trigger("change");
                //     });
                // });
            } else if (sal_i_cd.length == 6) {
                let category1Value = sal_i_cd.substring(0, 3); // sal_i_cd의 앞 3글자
                $("#category1").val(category1Value).trigger("change");
                let category2Value = sal_i_cd; // sal_i_cd의 전체 글자
                console.log("6글자일때 : ", category2Value);
                $.ajax({
                    type: "POST",
                    url: "/sale/saleCategory2",
                    dataType: "json",
                    data: {category1: category1Value},
                    success: function (data) {
                        $("#category2").val(category2Value).trigger("change");
                    },
                    error: function (xhr, status, error) {
                        alert("Error", error);
                        // window.location.reload();
                    }
                });
                // let category1Value = sal_i_cd.substring(0, 3); // sal_i_cd의 앞 3글자
                // let category2Value = sal_i_cd; // sal_i_cd의 전체 글자
                // $("#category1").val(category1Value).trigger("change").promise().done(function(){
                //     $("#category2").val(category2Value).trigger("change");
                // });
            } else {
                let category1Value = sal_i_cd; // sal_i_cd의 전체 글자
                $("#category1").val(category1Value).trigger("change");
            }
            // "modify" 문자열이 포함되어 있다면 버튼의 텍스트를 "수정하기"로 변경
            $("#submitBtn").val("수정하기");
            submitURL = '/sale/update';
        }
    });

    // Enter키 누를 시 모달창 뜨는 것 방지 및 텍스트 영역에서 Enter 키 입력 허용
    $(document).ready(function () {
        // 텍스트 영역에서 Enter 키 입력 허용
        $(document).on("keydown", "#contents", function (event) {
            // 현재 포커스된 요소가 텍스트 영역인지 확인
            if ($(this).is(":focus")) {
                // Enter 키 입력이면서 Shift 키가 눌리지 않았을 때만 기본 동작 막지 않음
                if (event.keyCode === 13) {
                    return; // Enter 키 입력
                }
            }
            // event.preventDefault(); // 그 외의 경우에는 Enter 키의 기본 동작 막기
            if ($(".SaleModal").hasClass("SaleHidden")) {
                // 모달 창이 숨겨져 있을 때만 Enter 키 입력 방지
                if (event.keyCode === 13) {
                    event.preventDefault(); // Enter 키의 기본 동작 막기(input 창에서 Enter치면 모달창이 열리기 때문)
                }
            }
        });

    });

    // 이미지 등록 개수 한도 정하기
    $(document).ready(function () {
        let img_count = 0;
        let max_images = 10;
        $("#imageBtn").change(function () {
            let files = this.files;
            if (img_count + files.length > max_images) {
                alert("최대 " + max_images + "개의 이미지까지만 등록할 수 있습니다.");
                return;
            }
            for (let i = 0; i < files.length; i++) {
                let reader = new FileReader();
                reader.onload = function (event) {
                    let imageUrl = event.target.result;
                    let imagePreview =
                        "<div class='sale-preview-image'><img src='" +
                        imageUrl +
                        "' width='150'><span class='delete-icon'>x</span></div>";
                    $("#register_img").append(imagePreview);
                };
                reader.readAsDataURL(files[i]);
                img_count++;
                $("#img_count").text(img_count);
            }
        });


        // 이미지 삭제시 이미지 카운트 수 줄이기
        $(document).on("click", ".delete-icon", function () {
            $(this).closest(".sale-preview-image").remove();
            img_count--;
            $("#img_count").text(img_count);
        });
    });

    // 거래방법 선택 최대 2개까지 한도 주기
    $(".trade_s_cd").change(function () {
        let checkedCount = $(".trade_s_cd:checked").length;
        if (checkedCount > 2) {
            $(this).prop("checked", false);
            alert("최대 2개의 거래방법까지 선택할 수 있습니다.");
        }
    });

    // 판매/나눔 선택
    $("input[name='tx_s_cd']").change(function () {
        if ($(this).val() === "S") {
            $(".proposal").show(); // 판매 선택 시 가격제안받기 옵션 보이기
            $(".trade").hide(); // 판매 선택 시 나눔신청받기 옵션 숨기기
            $("input[name='price']").prop("disabled", false); // 상품 가격 입력란 활성화
            $("input[name='price']").attr("placeholder", "판매할 가격을 입력해주세요.");
            $("#txMsg").text(""); // 메시지 제거
        } else if ($(this).val() === "F") {
            $(".proposal").hide(); // 나눔 선택 시 가격제안받기 옵션 숨기기
            $(".trade").show(); // 나눔 선택 시 나눔신청받기 옵션 보이기
            $("input[name='price']").val();
            $("input[name='price']").prop("disabled", true); // 상품 가격 입력란 비활성화
            $("input[name='price']").attr("placeholder", "나눔글입니다.");
            $("#txMsg").text(""); // 메시지 제거
        }
    });

    // tag값 배열 초기화
    let t_contents = [];

    $(document).ready(function () {
        // ${Tag} 값이 존재하는지 확인
        let tagValue = "${Tag}";
        // if (tagValue) {
            createHashtagInput(); // 페이지 로드 시에도 input 생성

            // input 요소에 이벤트 리스너 추가
            document
                .getElementById("hashtagInput")
                .addEventListener("input", createHashtagInput);
        // }
    });

    // 해시태그 input 생성하는 함수
    function createHashtagInput() {
        let hashtags = document.getElementById("hashtagInput").value.split("#").filter(Boolean); // #을 기준으로 문자열을 나누고 빈 문자열 제거

        // 기존의 해시태그를 모두 삭제
        let container = document.getElementById("hashtagContainer");
        container.innerHTML = "";

        // 배열 초기화
        t_contents = [];

        // 각 해시태그를 별도의 input 요소에 추가하여 표시
        hashtags.forEach(function (tag) {
            let input = document.createElement("input");
            input.type = "text";
            input.name = "t_contents";
            input.value = "#" + tag; // 해시태그 앞에 #을 붙여서 표시
            input.disabled = true; // 입력 불가능하도록 설정
            container.appendChild(input);

            // 해시태그 사이 간격 조절
            // 해시태그의 글자 수에 따라 너비 동적 조절
            input.style.width = (tag.length + 3) * 1.5 + "vw";

            // 해시태그를 배열에 추가
            t_contents.push("#" + tag);

            container.appendChild(input);
            container.appendChild(document.createTextNode(" ")); // 각 해시태그 뒤에 공백 추가
        });
    }


    $(document).ready(function () {
        // 대분류 선택 전 메시지 설정
        $("#salecategoryMsg").text("대분류 > 중분류 > 소분류를 선택하세요.");

        // 대분류 선택 시 중분류 메시지
        $("#category1").change(function () {
            $("#salecategoryMsg").text("중분류 > 소분류를 선택하세요.");
        });

        // 중분류 선택 시 소분류 메시지
        $("#category2").change(function () {
            $("#salecategoryMsg").text("소분류를 선택하세요.");
        });

        // 소분류 선택 시 메시지 제거
        $("#category3").change(function () {
            $("#categoryMsg").text("");
        });
    });

    $(document).ready(function () {
        // 페이지 로드 시 현재 선택된 대분류 카테고리의 이름을 가져와서 sal_name에 표시
        let category1Name = $("#category1 option:checked").text();
        if (category1Name !== "대분류") {
            $("#sal_name").text($("#category1 option:checked").text());
        }

        // 대분류 선택 시 선택된 카테고리의 이름을 sal_name에 업데이트
        $("#category1").change(function () {
            $("#sal_name").text($("#category1 option:checked").text());
        });

        // 중분류 선택 시 대분류 카테고리와 함께 중분류 카테고리의 이름을 sal_name에 업데이트
        $("#category2").change(function () {
            let category1Name = $("#category1 option:checked").text();
            let category2Name = $("#category2 option:checked").text();
            if (category2Name !== "중분류") {
                $("#sal_name").text(category1Name + " > " + category2Name);
            }
        });

        // 소분류 선택 시 대분류, 중분류 카테고리와 함께 소분류 카테고리의 이름을 sal_name에 업데이트
        $("#category3").change(function () {
            let category1Name = $("#category1 option:checked").text();
            let category2Name = $("#category2 option:checked").text();
            let category3Name = $("#category3 option:checked").text();
            if (category3Name !== "소분류") {
                $("#sal_name").text(category1Name + " > " + category2Name + " > " + category3Name);
            }
        });

    });

    function loadCategory2() {
        let category1Value = $('#category1').val();
        console.log(category1Value)
        if (category1Value !== "") {
            $.ajax({
                type: "POST",
                url: "/sale/saleCategory2",
                dataType: "json", // 받을 값
                data: {category1: category1Value},
                success: function (data) {
                    let category2Select = document.getElementById("category2");
                    category2Select.innerHTML = "<option value='' disabled selected>중분류</option>";
                    let category3Select = document.getElementById("category3");
                    category3Select.innerHTML = "<option value='' disabled selected>소분류</option>";
                    console.log("data.length : ", data.length);
                    if (data.length > 0) {
                        data.forEach(function (category) {
                            // console.log(typeof category);
                            if (category.sal_cd.startsWith(category1Value)) {
                                let option = new Option(category.name, category.sal_cd);
                                category2Select.add(option);
                            }
                        });
                    } else {
                        $("#salecategoryMsg").text("");
                    }
                },
                error: function (xhr, status, error) {
                    alert("error", error);
                }
            });
        }
    }

    function loadCategory3() {
        let category2Value = $('#category2').val();
        console.log(category2Value)
        if (category2Value !== "") {
            $.ajax({
                type: "POST",
                url: "/sale/saleCategory3",
                dataType: "json",
                data: {category2: category2Value},
                success: function (data) {
                    let category3Select = document.getElementById("category3");
                    category3Select.innerHTML = "<option value='' disabled selected>소분류</option>";
                    console.log("data.length : ", data.length);
                    if (data.length > 0) {
                        data.forEach(function (category) {
                            if (category.sal_cd.startsWith(category2Value)) {
                                let option = new Option(category.name, category.sal_cd);
                                category3Select.add(option);
                            }
                        });
                    } else {
                        $("#salecategoryMsg").text("");
                    }
                },
                error: function (xhr, status, error) {
                    alert("Error", error);
                }
            });
        }
    }

    // 소분류 선택 시 메시지 제거
    $("#category3").change(function () {
        $("#salecategoryMsg").text("");
    });

    const openModalBtn = document.getElementById("openModalBtn");
    const modal = document.querySelector(".SaleModal");
    const overlay = modal.querySelector(".sale_modal_overlay");
    const closeModalBtn = document.getElementById("closeModalBtn");
    const openModal = (event) => {
        event.preventDefault(); // form의 기본 동작을 막음
        modal.classList.remove("SaleHidden");
    }
    const closeModal = () => {
        event.preventDefault(); // form의 기본 동작을 막음
        modal.classList.add("SaleHidden");
    }
    overlay.addEventListener("click", closeModal);
    closeModalBtn.addEventListener("click", closeModal);
    openModalBtn.addEventListener("click", openModal);


    $(document).ready(function () {
        $("#saleSearchInput").on("input", function () {
            let searchLetter = $(this).val();
            $.ajax({
                type: "POST",
                url: "/sale/searchLetter",
                data: {searchLetter: searchLetter},
                dataType: "json",
                success: function (data) {
                    // 검색 결과를 처리하여 addrTable에 추가
                    console.log(data);
                    $("#addrList").empty(); // 기존 내용 초기화
                    if (data.length > 0) {
                        data.forEach(function (addr) {
                            console.log("addr", addr);
                            let row = $("<tr>");
                            row.append($("<td>").text(addr.addr_cd)); // 행정구역 코드
                            row.append($("<td>").text(addr.addr_name)); // 주소명
                            $("#addrList").append(row);
                        });
                    } else {
                        $("#addrTable").append("<tr><td colspan='2'>검색 결과가 없습니다.</td></tr>");
                    }
                },
                error: function (xhr, status, error) {
                    alert("Error", error);
                }
            });
        });
    });

    $(document).ready(function () {
        // 주소를 클릭했을 때의 이벤트 핸들러
        $("#addrList").on("click", "tr", function () {
            // 클릭한 행에서 주소 코드와 주소명을 가져옴
            let addrCode = $(this).find("td:eq(0)").text(); // 첫 번째 td 열의 텍스트 (주소 코드)
            let addrName = $(this).find("td:eq(1)").text(); // 두 번째 td 열의 텍스트 (주소명)

            // pickup_addr input에 선택한 주소 정보를 추가
            $("#pickup_addr_cd").val(addrCode);
            $("#pickup_addr_name").val(addrName);

            // 모달 닫기
            closeModal();
        });
    });

    // 등록하기 버튼 누르는 경우
    $("#submitBtn").on("click", function () {
        // addr_cd와 addr_name 값을 가져오기 위해 input 태그 추가
        $("<input>").attr("type", "hidden").attr("name", "addr_cd").val("${Sale.addr_cd}").appendTo("#writeForm");
        $("<input>").attr("type", "hidden").attr("name", "addr_name").val("${Sale.addr_name}").appendTo("#writeForm");

        let no = "${Sale.no}";
        let seller_id = "${Sale.seller_id}";
        let seller_nick = "${Sale.seller_nick}";
        let title = $('input[name="title"]').val(); // 제목
        let pro_s_cd = $('input[name="pro_s_cd"]:checked').val(); // 상품상태
        let contents = $('textarea[name="contents"]').val(); // 설명
        let tx_s_cd = $('input[name="tx_s_cd"]:checked').val(); // 판매/나눔
        let price = $('input[name="price"]').val(); // 가격
        let bid_cd = $('input[name="bid_cd"]:checked').val(); // 가격제시/나눔신청
        let reg_price = $('input[name="reg_price"]').val(); // 상품정가
        let pickup_addr_cd = $('input[name="pickup_addr_cd"]').val(); // 거래장소코드
        let pickup_addr_name = $('input[name="pickup_addr_name"]').val(); // 거래장소
        let detail_addr = $('input[name="detail_addr"]').val(); // 거래희망장소
        let brand = $('input[name="brand"]').val(); // 브랜드
        let addr_cd = $('input[name="addr_cd"]').val(); // 행정구역코드
        let addr_name = $('input[name="addr_name"]').val(); // 주소명

        // category1, category2, category3의 값 추출
        let category1Value = $("#category1").val();
        let category2Value = $("#category2 option:checked").val();
        let category3Value = $("#category3 option:checked").val();
        let category1Text = $("#category1 option:checked").text();
        let category2Text = $("#category2 option:checked").text();
        let category3Text = $("#category3 option:checked").text();

        // 조건에 따라 sal_i_cd 값을 설정
        let sal_i_cd_value;
        let sal_name_value;
        if (category1Value && !category2Value && !category3Value) {
            sal_i_cd_value = category1Value;
            sal_name_value = category1Text;
        } else if (category1Value && category2Value && !category3Value) {
            sal_i_cd_value = category2Value;
            sal_name_value = category2Text;
        } else if (category1Value && category2Value && category3Value) {
            sal_i_cd_value = category3Value;
            sal_name_value = category3Text;
        }

        // trade_s_cd 값 추출
        let trade_s_cd_values = [];
        $(".trade_s_cd:checked").each(function (index, checkbox) {
            trade_s_cd_values.push($(checkbox).val());
        });

        let sale = {
            "no": no,
            "seller_id":seller_id,
            "seller_nick":seller_nick,
            "title": title,
            "pro_s_cd": pro_s_cd,
            "contents": contents,
            "tx_s_cd": tx_s_cd,
            "price": price,
            "bid_cd": bid_cd,
            "reg_price": reg_price,
            "pickup_addr_cd": pickup_addr_cd,
            "pickup_addr_name": pickup_addr_name,
            "detail_addr": detail_addr,
            "brand": brand,
            "sal_i_cd": sal_i_cd_value,
            "sal_name": sal_name_value,
            "addr_cd": addr_cd,
            "addr_name": addr_name
        }

        // trade_s_cd 파라미터 설정
        if (trade_s_cd_values.length === 1) {
            sale["trade_s_cd_1"] = trade_s_cd_values[0];
        } else if (trade_s_cd_values.length === 2) {
            sale["trade_s_cd_1"] = trade_s_cd_values[0];
            sale["trade_s_cd_2"] = trade_s_cd_values[1];
        }

        let tagSet = new Set(t_contents);
        let tagList = Array.from(tagSet);

        let tag = {
            "contents": tagList
        }

        let map =
            {
                "sale": sale,
                "tag": tag
            };


        // jsonData를 JSON 문자열로 변환
        let jsonString = JSON.stringify(map);

        alert(jsonString);

        $.ajax({
            type: 'POST',
            url: submitURL,
            // contentType: 'multipart/form-data; charset=utf-8',
            contentType: 'application/json; charset=utf-8',
            data: jsonString,
            dataType: 'text',
            success: function (data) {
                // // 글이 성공적으로 등록되었을 때
                // // data에는 등록된 글의 번호가 반환됨
                // // 반환된 글 번호를 이용하여 리다이렉션할 URL 생성
                // let redirectURL = '/sale/read?no=' + data;
                // console.log("redirectURL : ", redirectURL);
                // // 새로운 URL로 이동
                window.location.replace(data);
            },
            error: function (xhr, status, error) {
                alert("등록 중 오류가 발생했습니다.");
            }
        });
    })

</script>

</html>