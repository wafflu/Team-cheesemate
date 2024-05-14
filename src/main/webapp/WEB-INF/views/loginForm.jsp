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
    <title>loginForm</title>
</head>
<body>

<h1>*** LOGIN FORM ***</h1>

<p>
    <%= cookieUserId%>
    <%= cookieUserPw%>
</p>

<form action="/login" method="post">
    아이디 : <input name="inputId" value="<%= cookieUserId %>"></input>
    <br>
    비밀번호 : <input type="password" name="inputPw" value="<%= cookieUserPw %>"></input>
    <br>
    <input type="checkbox" name="rememberAccountBnt" checked> 아이디 저장

    <input type="checkbox" name="keepLoginState"> 로그인 상태 유지

    ${loginErrorMSG}
    <br>
    <button>로그인</button>
</form>

<br>
<form action="/resisterForm", method="get">
    <button name="resisterFormBnt">회원가입</button>
</form>

</body>
</html>

