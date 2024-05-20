<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <style>
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

        .sale-addr-tr:hover {
            background-color: rgba(245, 157, 28, 0.5);
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

        #sale_search_addr {
            margin: 0;
        }

        .SaleHidden {
            display: none;
        }

        input:invalid {
            border: 2px solid red;
        }

        hr {
          width: 40%;
        }

        form {
            border: 3px solid #959595;
            padding: 20px;
            width: 50%;
            margin: 0 auto;
            display: block;
        }

        label {
            display: inline;
            text-align: left; /* 텍스트를 왼쪽으로 정렬 */
            margin: 10px 0; /* 레이블과 다른 요소 사이의 여백을 줌 */
        }

        .inputBox {
            padding-top: 3px;
            padding-bottom: 3px;

            margin-top: 3px;
            margin-bottom: 3px;
        }

        .center {
            text-align: center;
        }

    </style>

    <title>ResisterForm</title>
</head>
<body>



<form action="/createAccount" method="post" class="center" onsubmit="return submitCheck();">
    <h1>회원가입</h1>
    <hr>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


    <input placeholder="아이디" class="inputBox" type="text" id="id" name="id" pattern="^[A-Za-z\d]{6,25}$" minlength="5" maxlength="25" title="아이디는 영어와 숫자만 가능하며 길이는 6글자이상 20글자까지 가능합니다." onchange="checkIdDuplication()">
    <p id="idCheckResult"></p>

    <input placeholder="비밀번호" class="inputBox" type="password" id="pw" name="pw"pattern="^[A-Za-z\d@$!%*?&]*$" minlength="5" maxlength="16" title="비밀번호는 영어,숫자 및 특수문자로 가능하며 최소 6자리부터 최대 16자리까지 가능합니다.">
    <br>

    <input placeholder="비밀번호 확인" class="inputBox" type="password" id="inputPwCheck" name="inputPwCheck" pattern="^[A-Za-z\d@$!%*?&]{5,}$" title="동일한 비밀번호를 입력해주세요.">
    <br>

    <input placeholder="이메일" class="inputBox" type="email" id="email" name="email" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" minlength="6" title="이메일 주소를 정확하게 입력해주세요.">    <br>
    <br>

    <button id="openModalBtn">주소 검색</button>
    <div id="openModal" class="SaleModal SaleHidden">
        <div class="sale_modal_overlay"> <!--모달창의 배경색--></div>
        <div class="sale_modal_content">
            <button id="closeModalBtn">x</button>
            <h1 id="sale_search_addr">주소 검색</h1>
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
    <br>
    <input disabled placeholder="주소" class="inputBox" type="text" id="addr_det" name="addr_det" minlength="6" title="거주하고 있는 주소를 정확하게 입력해주세요.">
    <input type="text" id="addr_cd" hidden>
    <br>
    <br>

    <input placeholder="이름" class="inputBox" type="text" id="name" name="name" pattern="^[A-Za-z가-힣]+$" title="이름을 입력 해주세요">
    <br>

    <input placeholder="별명" class="inputBox" type="text" id="nick" name="nick" minlength="2" pattern="[A-Za-z가-힣]{2,}" title="별명은 2자리 이상입니다.">
    <br>

    <input placeholder="생년월일" class="inputBox" type="date" id="birth" name="birth" title="생년월일을 입력해주세요">
    <br>

    <input placeholder="휴대전화번호" class="inputBox" type="text" id="phone_num" name="phone_num" minlength="11" maxlength="11" title="하이픈(-)을 뺀 전화번호 11자리를 입력해주세요">
    <br>

    <label>성별   </label>
    <input required type="radio" name="gender" value="M" title="성별 하나를 선택 해주세요">남자
    <input required type="radio" name="gender" value="F" title="성별 하나를 선택 해주세요">여자<br>

    <label>내외국인   </label>
    <input type="radio" name="foreigner" value="Y" checked>내국인
    <input type="radio" name="foreigner" value="N">외국인<br>
    <br>

    <button type="submit">제출</button>
</form>

</body>
</html>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
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

    // Enter키 누를 시 모달창 뜨는 것 방지 및 텍스트 영역에서 Enter 키 입력 허용
    $(document).ready(function () {
        // input 영역에서 Enter 키 입력 허용
        $(document).on("keydown", function (event) {
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

    $(document).ready(function () {
        $("#saleSearchInput").on("input", function () {
            let searchLetter = $(this).val();
            $.ajax({
                type: "POST",
                url: "/searchLetter",
                data: {searchLetter: searchLetter},
                dataType: "json",
                success: function (data) {
                    // 검색 결과를 처리하여 addrTable에 추가
                    console.log(data);
                    $("#addrList").empty(); // 기존 내용 초기화
                    if (data.length > 0) {
                        data.forEach(function (addr) {
                            console.log("addr", addr);
                            let row = $("<tr class='sale-addr-tr'>");
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
            $("#addr_cd").val(addrCode);
            $("#addr_det").val(addrName);

            // 모달 닫기
            closeModal();
        });
    });

    // *** 아이디 중복 확인(Ajax) ***
    function checkIdDuplication() {

        var inputId = document.getElementById("id").value;
        var resultElement = document.getElementById("idCheckResult");

        if(inputId.length > 5) {
            fetch('/checkIdDuplication?id=' + encodeURIComponent(inputId))
                .then(response => response.text())
                .then(data => {
                    resultElement.innerText = data;
                    if (data === "이미 존재하는 아이디입니다.") {
                        resultElement.style.color = 'red';
                    } else if (data === "사용 가능한 아이디입니다.") {
                        resultElement.style.color = 'green';
                    }
                });
        }
        else {
            document.getElementById("idCheckResult").innerText = "";
        }
    }

    // *** 생년월일 입력시 미래 날짜 사용 불가능 ***
    document.addEventListener('DOMContentLoaded', function() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        today = yyyy + '-' + mm + '-' + dd;
        document.getElementById("birth").setAttribute("max", today);
    });

    // *** 확인 버튼 누를 시 부적합한 입력을 화면에 표시 ***
    function submitCheck() {
        var check = true;

        var inputId = document.getElementById("id").value;
        var inputPw = document.getElementById("pw").value;
        var inputPwCheck = document.getElementById("inputPwCheck").value;
        var inputEmail = document.getElementById("email").value;
        var inputAddress = document.getElementById("addr_det").value;
        var inputName = document.getElementById("name").value;
        var inputNick = document.getElementById("nick").value;
        var inputBirth = document.getElementById("birth").value;
        var inputPhoneNum = document.getElementById("phone_num").value;


        if(inputId === "") {
            document.getElementById("id").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("id").style.borderColor = '';
        }

        if(inputPw === "") {
            document.getElementById("pw").style.borderColor = 'red';
            check = false;
        }
        else if(inputPw !== inputPwCheck ) {
            document.getElementById("inputPwCheck").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("pw").style.borderColor = '';
            document.getElementById("inputPwCheck").style.borderColor = '';
        }

        if(inputEmail === "") {
            document.getElementById("email").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("email").style.borderColor = '';
        }

        if(inputAddress === "") {
            document.getElementById("addr_det").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("addr_det").style.borderColor = '';
        }

        if(inputName === "") {
            document.getElementById("name").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("name").style.borderColor = '';
        }

        if(inputNick === "") {
            document.getElementById("nick").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("nick").style.borderColor = '';
        }

        if(inputBirth === "") {
            document.getElementById("birth").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("birth").style.borderColor = '';
        }

        if(inputPhoneNum === "") {
            document.getElementById("phone_num").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("phone_num").style.borderColor = '';
        }

        if(!check) {
            alert("입력하신 내용을 다시 확인해 주세요");
        }
        else {
            alert("회원가입이 완료되었습니다.")
        }

        return check;
    }

</script>