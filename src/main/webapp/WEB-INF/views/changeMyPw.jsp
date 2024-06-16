<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.userId}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js"></script>

<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>saleInfo</title>
    <link rel="stylesheet" href="/css/saleInfo.css">

    <style>
        .mainContainer {
            width: 630px;
            height: 510px;
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

<form class="mainContainer" action="/myPage/changeMyPw" method="post">

    <br><br>
    <h1>비밀번호 수정</h1>
    <br><br>

    <hr class="hr">

    <div class="form-group">
        <div class="form-title"><span>현재 비밀번호</span></div>
        <div>
            <input type="text" class="inputBox" id="currentPw" name="currentPw" value="<c:out value='' />" pattern="^[A-Za-z\d@$!%*?&]{6,16}$" minlength="6" maxlength="16" title="기존의 비밀번호를 입력 해주세요." oninput="validateCurrentPw()"><br>
            <p class="error-message" id="currentPwErrorMSG"></p>
        </div>
    </div>
    <div class="form-group">
        <div class="form-title"><span>새로운 비밀번호</span></div>
        <div>
            <input type="text" class="inputBox" id="newPw" name="newPw" value="<c:out value='' />" pattern="^[A-Za-z\d@$!%*?&]{6,16}$" minlength="6" maxlength="16" title="비밀번호는 영어, 숫자 및 특수문자로 가능하며 최소 6자리부터 최대 16자리까지 가능합니다." oninput="validateNewPw()">
            <div class="error-message" id="newPwErrorMSG"></div>
        </div>
    </div>
    <div class="form-group">
        <div class="form-title"><span>새로운 비밀번호 확인</span></div>
        <div>
            <input type="text" class="inputBox" id="newPwCheck" name="newPwCheck" value="<c:out value='' />" minlength="7" maxlength="15" pattern="^[A-Za-z\d@$!%*?&]{6,16}$" minlength="6" maxlength="16" title="새로 입력한 비밀번호를 입력해 주세요." oninput="validateNewPwCheck()">
            <div class="error-message" id="newPwCheckErrorMSG"></div>
        </div>
    </div>

    <div>
        <button class="reg_btn" onclick="return invalidateFinalCheck()">비밀번호 변경</button>
        <button class="cancle_bnt">취소</button>
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

    const userPw = "<c:out value='${userDto.pw}' />";

    function hashPassword(password) {
        return CryptoJS.SHA256(password).toString(CryptoJS.enc.Hex);
    }

    function invalidateFinalCheck() {
        var check = true;

        // 현재 비밀번호 체크
        var currentPw = document.getElementById("currentPw").value;
        var currentPwErrorMSG = document.getElementById("currentPwErrorMSG");

        if(hashPassword(currentPw) === userPw) {
            currentPwErrorMSG.textContent = "";
            document.getElementById("currentPw").style.borderColor = "";
        }
        else {
            check = false;
            document.getElementById("currentPw").style.borderColor = "red";
            currentPwErrorMSG.textContent = "기존 비밀번호와 일치하지 않습니다.";
        }

        // 새로운 비밀번호 체크
        var newPw = document.getElementById('newPw').value;
        var newPwErrorMSG = document.getElementById('newPwErrorMSG');
        var patternLength = /^.{6,16}$/;
        var patternChar = /^(?=.*[A-Za-z])(?=.*\d).*$/;
        var patternSpace = /\s/;


        if(!patternLength.test(newPw)) {
            check = false;
            newPwErrorMSG.textContent = '비밀번호는 최소 6자리부터 가능합니다.';
            document.getElementById("newPw").style.borderColor = "red";
        }
        else if(!patternChar.test(newPw)) {
            check = false;
            newPwErrorMSG.textContent = '비밀번호는 반드시 영어와 숫자가 섞여있어야 합니다.';
            document.getElementById("newPw").style.borderColor = "red";
        }
        else if(patternSpace.test(newPw)) {
            check = false;
            newPwErrorMSG.textContent = '비밀번호에 공백은 넣을 수 없습니다.';
            document.getElementById("newPw").style.borderColor = "red";
        }
        else if(currentPw === newPw) {
            check = false;
            newPwErrorMSG.textContent = '이전과 같은 비밀번호는 사용할 수 없습니다.';
            document.getElementById("newPw").style.borderColor = "red";
        }
        else {
            document.getElementById("newPw").style.borderColor = "";
            newPwErrorMSG.textContent = '';
        }

        // 새로운 비밀번호 2차 확인
        var newPwCheck = document.getElementById("newPwCheck").value;
        var errorMSG = document.getElementById('newPwCheckErrorMSG');

        if(newPw === newPwCheck) {
            errorMSG.textContent = "";
            document.getElementById("newPwCheck").style.borderColor = "";
        }
        else {
            check = false;
            errorMSG.textContent = "새로운 비밀번호와 일치하지 않습니다.";
            document.getElementById("newPwCheck").style.borderColor = "red";
        }

        if(currentPw === "" || newPw === "" || newPwCheck === "") {
            check = false;
        }

        if(check === true) {
            return true;
        }
        else {
            window.alert("입력하신 정보를 다시 한번 확인해 주세요.")
            return false;
        }
    }

</script>