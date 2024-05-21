<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%
    Cookie[] cookies = request.getCookies();

    String cookieUserId = "";
    String cookieUserPw = "";

    for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals("userId")) {
            cookieUserId = cookies[i].getValue();
        } else if (cookies[i].getName().equals("userPw")) {
            cookieUserPw = cookies[i].getValue();
        }
    }
%>

<html>
<head>
    <title>치즈메이트-로그인화면</title>

    <style>

    input:invalid {
        border: 2px solid red;
    }

    form {
        border: 3px solid #959595;
        padding: 20px;
        width: 50%;
        margin: 0 auto;
        display: block;
    }

    label{
        display: inline;
        text-align: left; /* 텍스트를 왼쪽으로 정렬 */
        margin: 10px 0; /* 레이블과 다른 요소 사이의 여백을 줌 */
    }

    .inputPadding {
        margin-top: 3px;
        margin-bottom: 3px;
    }

    .inputBox {
        padding-top: 5px;
        padding-bottom: 5px;

        margin-top: 3px;
        margin-bottom: 3px;
    }

    .center {
        text-align: center;
    }

    </style>

</head>
<body>

<form class="center" action="/login" method="post">
    <h1>로그인</h1>

    <input class="inputBox" name="inputId" value="<c:out value='<%= cookieUserId %>' />" placeholder="아이디"></input>
    <br>
    <input class="inputBox" type="password" name="inputPw" value="<c:out value='<%= cookieUserPw %>' />" placeholder="비밀번호"></input>
    <br>
    <input class="inputPadding" type="checkbox" name="rememberAccountBnt" checked> 아이디 저장

    <input class="inputPadding" type="checkbox" name="keepLoginState"> 로그인 상태 유지

    <c:if test="${loginErrorMSG != null}">
        <br>
        <p style="color: red";>
            ${loginErrorMSG}
        </p>
    </c:if>

    <br>
    <input class="inputPadding" type="hidden" name="from" value="<c:out value='${param.from}' />">
    <button class="inputPadding">로그인</button>
</form>
<br>

<div style="text-align: center;">
    <button name="resisterFormBnt" onclick="goToResisterForm()">회원가입</button>
</div>

</body>
</html>

<script>
    function goToResisterForm() {
        window.location.href = "/resisterForm";
    }
</script>