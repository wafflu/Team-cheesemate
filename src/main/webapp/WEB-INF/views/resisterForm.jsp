<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style>

        input:invalid {
            border: 2px solid red;
        }

        form {
            border: 3px solid #f1f1f1;
            padding: 20px;
            width: 50%;
            margin: 0 auto;
            display: block;
        }

        label {
            display: inline; /* 각 레이블을 블록 요소로 만들어 줄바꿈이 일어나도록 함 */
            text-align: left; /* 텍스트를 왼쪽으로 정렬 */
            margin: 10px 0; /* 레이블과 다른 요소 사이의 여백을 줌 */
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

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label>아이디 : </label>
    <input type="text" id="id" name="id" pattern="[A-Za-z][A-Za-z0-9]{5,}$" minlength="5" maxlength="20" title="한글 아이디는 불가능하고, 아이디의 길이는 6글자이상 20글자 이합니다.">
    <br>

    <label>비밀번호 : </label>
    <input type="password" id="pw" name="pw" pattern="^[A-Za-z\d@$!%*?&]{5,}$" minlength="5" maxlength="20" title="비밀번호는 최소 6자리부터 가능합니다.">
    <br>

    <label>비밀번호 확인 : </label>
    <input type="password" id="inputPwCheck" name="inputPwCheck" pattern="^[A-Za-z\d@$!%*?&]{5,}$" title="동일한 비밀번호를 입력해주세요.">
    <br>

    <label>이메일 : </label>
    <input type="email" id="email" name="email" pattern="^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$" minlength="6" title="이메일 주소를 정확하게 입력해주세요.">
    <br>

    <label>주소 :</label>
    <input type="text" id="addr_det" name="addr_det" minlength="6" title="거주하고 있는 주소를 정확하게 입력해주세요.">
    <br>
    <br>

    <label>이름 : </label>
    <input type="text" id="name" name="name" pattern="^[A-Za-z가-힣]+$" title="이름을 입력 해주세요">
    <br>

    <label>별명 : </label>
    <input type="text" id="nick" name="nick" minlength="2" pattern="[A-Za-z가-힣]{2,}" title="별명은 2자리 이상입니다.">
    <br>

    <label>생년월일 : </label>
    <input type="date" id="birth" name="birth" title="생년월일을 입력해주세요">
    <br>

    <label>휴대전화번호 : </label>
    <input type="text" id="phone_num" name="phone_num" minlength="11" maxlength="11" title="하이픈(-)을 뺀 전화번호 11자리를 입력해주세요">
    <br>

    <label>성별   </label>
    <input required type="radio" name="gender" value="M" title="성별 하나를 선택 해주세요">남자
    <input required type="radio" name="gender" value="F" title="성별 하나를 선택 해주세요">여자<br>
    <br>

    <label>내외국인   </label>
    <input type="radio" name="foreigner" value="Y" checked>내국인
    <input type="radio" name="foreigner" value="N">외국인<br>

    <button type="submit">제출</button>
</form>

</body>
</html>

<script>

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