<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <style>

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

    <title>치즈메이트 - 회원가입</title>
</head>
<body>



<form action="/createAccount" method="post" class="center" onsubmit="return submitCheck();">
    <h1>회원가입</h1>
    <hr>

    <input type="hidden" name="${_csrf.parameterName}" value="<c:out value='${_csrf.token}' />"/>

    <input placeholder="아이디" class="inputBox" type="text" id="id" name="id" value="<c:out value='${userDto.id}' />" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,25}$" minlength="5" maxlength="25" title="아이디는 영어와 숫자가 포함되어야하며, 길이는 6글자이상 20글자까지 가능합니다." onchange="checkIdDuplication()">
    <p id="idCheckResult"></p>

    <input placeholder="비밀번호" class="inputBox" type="password" id="pw" name="pw" value="<c:out value='${userDto.pw}' />" pattern="^[A-Za-z\d@$!%*?&]*$" minlength="5" maxlength="16" title="비밀번호는 영어,숫자 및 특수문자로 가능하며 최소 6자리부터 최대 16자리까지 가능합니다.">
    <br>

    <input placeholder="비밀번호 확인" class="inputBox" type="password" id="inputPwCheck" name="inputPwCheck" value="<c:out value='${userDto.pw}' />" pattern="^[A-Za-z\d@$!%*?&]*$" minlength="5" maxlength="16"  title="동일한 비밀번호를 입력해주세요.">
    <br>

    <input placeholder="이메일" class="inputBox" type="email" id="email" value="<c:out value='${userDto.email}' />" name="email" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" minlength="5" maxlength="50" title="이메일 주소를 정확하게 입력해주세요.">
    <br>

    주소 :
    <select name="tradingPlace_A_large" id="tradingPlace_A_large" onchange="fetchMediumCategories()">
        <c:forEach var="category" items="${largeCategory}">
            <option value="${category.addr_cd}">${category.addr_name}</option>
        </c:forEach>
    </select>
    <select name="tradingPlace_A_medium" id="tradingPlace_A_medium" onchange="fetchSmallCategories()">
        <!-- 중분류 옵션들이 여기에 동적으로 추가됩니다 -->
    </select>
    <select name="tradingPlace_A_small" id="tradingPlace_A_small">
        <!-- 소분류 옵션들이 여기에 동적으로 추가됩니다 -->
    </select>
    <br>

    <input placeholder="상세주소" class="inputBox" type="text" id="addr_det" name="addr_det" value="<c:out value='${userDto.addr_det}' />" pattern="^[가-힣a-zA-Z0-9\s]+$" minlength="6" maxlength="50" title="거주하고 있는 주소를 정확하게 입력해주세요.">
    <br>
    <br>

    <input placeholder="이름" class="inputBox" type="text" id="name" name="name" value="<c:out value='${userDto.name}' />" minlength="2" maxlength="30" pattern="^[A-Za-z가-힣]+$" title="이름은 2글자 이상 30자 이하입니다.">
    <br>

    <input placeholder="별명" class="inputBox" type="text" id="nick" name="nick" value="<c:out value='${userDto.nick}' />" minlength="2" maxlength="20" pattern="^[A-Za-z가-힣]+$" title="별명은 2자리 이상 20자 이하입니다.">
    <br>

    <input placeholder="생년월일" class="inputBox" type="date" id="birth" name="birth" value="<c:out value='${userDto.birth}' />" title="생년월일을 입력해주세요">
    <br>

    <input placeholder="휴대전화번호" class="inputBox" type="text" id="phone_num" name="phone_num" value="<c:out value='${userDto.phone_num}' />" minlength="11" maxlength="11" pattern="^[0-9]+$" title="하이픈(-)을 뺀 전화번호 11자리를 입력해주세요">
    <br>

    <label id="gender">성별</label>
    <input type="radio" id="genderM" name="gender" value="M" title="성별 하나를 선택 해주세요">남자
    <input type="radio" id="genderF" name="gender" value="F" title="성별 하나를 선택 해주세요">여자<br>

    <label>내외국인</label>
    <input type="radio" name="foreigner" value="Y" checked>내국인
    <input type="radio" name="foreigner" value="N">외국인<br>

    <button type="submit">제출</button>
</form>

</body>
</html>

<script>
    // *** 아이디 중복 확인(Ajax) ***
    function checkIdDuplication() {

        let inputId = document.getElementById("id").value;
        let resultElement = document.getElementById("idCheckResult");

        if (inputId.length > 5) {
            fetch('/checkIdDuplication?id=' + encodeURIComponent(inputId))
                .then(response => response.text())
                .then(data => {
                    resultElement.innerText = data;
                    if (data === "아이디에 띄어쓰기를 넣을 수 없습니다.") {
                        resultElement.style.color = 'red';
                    } else if (data === "이미 존재하는 아이디입니다.") {
                        resultElement.style.color = 'red';
                    } else if (data === "사용 가능한 아이디입니다.") {
                        resultElement.style.color = 'green';
                    }
                });
        } else {
            document.getElementById("idCheckResult").innerText = "";
        }
    }

    // *** 생년월일 입력시 미래 날짜 사용 불가능 ***
    document.addEventListener('DOMContentLoaded', function() {
        let today = new Date();
        let dd = today.getDate();
        let mm = today.getMonth() + 1;
        let yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        today = yyyy + '-' + mm + '-' + dd;

        let minDate = '1950-01-01';

        let birthInput = document.getElementById("birth");
        birthInput.setAttribute("max", today);
        birthInput.setAttribute("min", minDate);
    });

    // 중분류를 동적으로 가져오는 함수
    function fetchMediumCategories() {
        let tradingPlace_A = document.getElementById("tradingPlace_A_large").value;
        fetch('/mediumCategory?tradingPlace_A_large=' + encodeURIComponent(tradingPlace_A))
            .then(response => response.json())
            .then(data => {
                let smallCategorySelect = document.getElementById("tradingPlace_A_small");
                smallCategorySelect.innerHTML = ""; // 기존 옵션들을 제거합니다.

                let mediumCategorySelect = document.getElementById("tradingPlace_A_medium");
                mediumCategorySelect.innerHTML = ""; // 기존 옵션들을 제거합니다.
                data.forEach(function(category) {
                    let option = document.createElement("option");
                    option.value = category.addr_cd;
                    option.text = category.addr_name;
                    mediumCategorySelect.add(option);
                });
            });
    }

    // 소분류를 동적으로 가져오는 함수
    function fetchSmallCategories() {
        let tradingPlace_A = document.getElementById("tradingPlace_A_medium").value;
        fetch('/smallCategory?tradingPlace_A_medium=' + encodeURIComponent(tradingPlace_A))
            .then(response => response.json())
            .then(data => {
                let smallCategorySelect = document.getElementById("tradingPlace_A_small");
                smallCategorySelect.innerHTML = ""; // 기존 옵션들을 제거합니다.
                data.forEach(function(category) {
                    let option = document.createElement("option");
                    option.value = category.addr_cd;
                    option.text = category.addr_name;
                    smallCategorySelect.add(option);
                });
            });
    }


    // *** 확인 버튼 누를 시 부적합한 입력을 화면에 표시 ***
    function submitCheck() {
        let check = true;

        let inputId = document.getElementById("id").value;
        let inputPw = document.getElementById("pw").value;
        let inputPwCheck = document.getElementById("inputPwCheck").value;
        let inputEmail = document.getElementById("email").value;
        let inputAddress = document.getElementById("addr_det").value.trim();
        let inputName = document.getElementById("name").value;
        let inputNick = document.getElementById("nick").value;
        let inputBirth = document.getElementById("birth").value;
        let inputPhoneNum = document.getElementById("phone_num").value;

        if(inputId === "" && inputId.trim().length !== inputId.length) {
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

        if(inputAddress.trim() === "" || inputAddress.replace(/\s/g, "").length < 6) {
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

        if(inputPhoneNum === "" && inputPhoneNum.length !== 11) {
            document.getElementById("phone_num").style.borderColor = 'red';
            check = false;
        }
        else {
            document.getElementById("phone_num").style.borderColor = '';
        }

        if(!check) {
        }
        else {
            alert("회원가입이 완료되었습니다.")
        }

        return check;
    }

</script>