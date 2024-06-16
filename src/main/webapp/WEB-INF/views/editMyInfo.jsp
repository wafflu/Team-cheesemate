<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.userId}"/>
<%--<c:set var="loginId" value="${ur_id}"/>--%>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>saleInfo</title>
    <link rel="stylesheet" href="/css/saleInfo.css">

    <style>
        .mainContainer {
            width: 630px;
            height: 600px;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            margin-top: 10px;
            border-radius: 12px;
            text-align: center;
        }

        .form-group {
            width: 630px;
            height: 80px;
            display: flex;
            justify-content: left; /* 가로 정렬을 중앙으로 설정 */
            align-items: center; /* 수직 정렬을 중앙으로 설정 */
        }

        .form-title {
            width: 150px;
            font-size: 14px;
            text-align: left;
            margin-left: 50px;
        }

        .inputBox {
            border: 1px solid rgb(218 222 229);
            width: 300px;
            height: 45px;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 10px;
            margin-bottom: 3px;
            border-radius: 5px;
        }

        .reg_btn {
            display: inline-block;
            width: 30%;
            height: 50px;
            font-weight: 250;
            font-size: 25px;
            border: 1px solid #ee8703;
            border-radius: 5px;
            color: white;
            background-color: #ee8703;
            padding: 3px;
            margin-top: 10px;
            margin-bottom: 10px;
            margin-right: 60px;
        }

        .cancle_bnt {
            display: inline-block;
            width: 30%;
            height: 50px;
            font-weight: 250;
            font-size: 25px;
            border: 1px solid #ee8703;
            border-radius: 5px;
            color: #ee8703;
            background-color: #ffffff;
            padding: 3px;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .error-message {
            color: red;
            width: 200px;
            height: 10px;
            font-size: 0.9em;
            font-weight: bold;
            margin-top: 5px;
            white-space: nowrap;
            text-align: left;
        }

        .hr {
            width: 100%; /* 너비를 100%로 설정하여 가로 전체를 차지하도록 변경 */
            height: 1px; /* 선의 높이를 설정하여 선이 보이도록 변경 */
            border: none;
            border-top: 1px solid rgb(218, 222, 229); /* 상단 테두리 추가 */
        }
    </style>

</head>
<body>
<div class="navigation">
    <ul>
        <li><a href="/myPage/editMyInfo">개인 정보 수정</a></li>
        <li><a href="/myPage/changeMyPw">비밀번호 변경</a></li>
        <li><a href="/myPage/leaveAccountForm">회원 탈퇴</a></li>
    </ul>
</div>
<form class="mainContainer" action="/myPage/editMyInfo" method="post">

    <br><br>
    <h1>개인 정보 수정</h1>
    <br><br>

    <hr class="hr">

    <div class="form-group">
        <div class="form-title"><span>아이디</span></div>
        <div>
            <input type="text" class="inputBox" id="id" name="id" value="<c:out value='${userDto.id}' />" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$" minlength="6" maxlength="20" title="아이디는 영어와 숫자가 포함되어야하며, 길이는 6글자이상 20글자까지 가능합니다." readonly><br>
            <p class="error-message" id="idErrorMSG"></p>
        </div>
    </div>
    <div class="form-group">
        <div class="form-title"><span>닉네임</span></div>
        <div>
            <input type="text" class="inputBox" id="nick" name="nick" value="<c:out value='${userDto.nick}' />" minlength="2" maxlength="20" pattern="^[A-Za-z가-힣]+$" title="별명은 2자리 이상 20자 이하입니다." oninput="validateNick()">
            <div class="error-message" id="nickErrorMSG"></div>
        </div>
    </div>
    <div class="form-group">
        <div class="form-title"><span>휴대전화번호</span></div>
        <div>
            <input type="text" class="inputBox" id="phone_num" name="phone_num" value="<c:out value='${userDto.phone_num}' />" minlength="7" maxlength="15" pattern="^\d{7,15}$" title="하이픈(-)을 뺀 전화번호를 입력해주세요" placeholder="하이픈(-)을 뺀 전화번호를 입력해주세요" oninput="validatePhoneNum()">
            <div class="error-message" id="phoneNumberErrorMSG"></div>
        </div>
    </div>
    <div class="form-group">
        <div class="form-title"><span>이메일</span></div>
        <div>
            <input type="text" class="inputBox" id="email" name="email" value="<c:out value='${userDto.email}' />" pattern="^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$" minlength="5" maxlength="50" title="이메일 주소를 정확하게 입력해주세요." oninput="validateEmail()">
            <div class="error-message" id="emailErrorMSG"></div>
        </div>
    </div>

    <div>
        <button type="submit" class="reg_btn">수정</button>
        <button type="button" class="cancle_bnt" onclick="window.location.href='/myPage/editMyInfo'">취소</button>
    </div>
</form>
</body>
</html>

<script>
    $(document).ready(function() {
        const urlParams = new URLSearchParams(window.location.search);
        const resultMSG = urlParams.get('resultMSG');
        if (resultMSG) {
            alert(resultMSG);
        }
    });

    function validateNick() {
        var nick = document.getElementById("nick").value;
        var errorMSG = document.getElementById("nickErrorMSG");
        var pattern = /^.{2,}$/;

        if(nick === "") {
            document.getElementById("nick").style.borderColor = "red";
            errorMSG.textContent = "닉네임을 입력해 주세요";
        }
        if(!pattern.test(nick)) {
            document.getElementById("nick").style.borderColor = "red";
            errorMSG.textContent = "닉네임은 2글자 이상입니다.";
        }
        else {
            document.getElementById("nick").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    function validatePhoneNum() {
        var input = document.getElementById('phone_num');
        input.value = input.value.replace(/[^\d]/g, '');

        var phoneNum = document.getElementById("phone_num").value;
        var errorMSG = document.getElementById("phoneNumberErrorMSG");
        var pattern = /^\d{7,15}$/;

        if(phoneNum === "") {
            document.getElementById("phone_num").style.borderColor = "red";
            errorMSG.textContent = "전화번호를 입력해 주세요";
        }
        if(!pattern.test(phoneNum)) {
            document.getElementById("phone_num").style.borderColor = "red";
            errorMSG.textContent = "전화번호를 정확하게 입력해주세요.";
        }
        else {
            document.getElementById("phone_num").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }

    function validateEmail() {
        var email = document.getElementById("email").value;
        var errorMSG = document.getElementById("emailErrorMSG");
        var pattern = /^[a-zA-Z0-9._%+\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/;

        if(email === "") {
            document.getElementById("email").style.borderColor = "red";
            errorMSG.textContent = "이메일을 입력해 주세요";
        }
        if(!pattern.test(email)) {
            document.getElementById("email").style.borderColor = "red";
            errorMSG.textContent = "이메일 형식을 맞춰주세요";
        }
        else {
            document.getElementById("email").style.borderColor = "";
            errorMSG.textContent = "";
        }
    }
</script>