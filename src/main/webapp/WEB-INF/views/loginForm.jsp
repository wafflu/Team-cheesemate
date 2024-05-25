<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="fixed/header.jsp"%>

<link rel="stylesheet" href="/css/loginform.css">
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


<%--<div class="login_title"><span class="upper_word">C</span>HEESE MATE</div>--%>
<div class="maincontent">
<form class="center" action="/login" method="post">

    <h1 class="login_title_h1">CHEESE MATE에 오신것을 <br/>환영합니다.</h1>

    <div class="LoginFrom-box">
        <input class="inputBox" name="inputId" value="<c:out value='<%= cookieUserId %>' />" placeholder="아이디"/>
        <br>
        <input class="inputBox" type="password" name="inputPw" value="<c:out value='<%= cookieUserPw %>' />" placeholder="비밀번호"/>
    </div>

    <c:if test="${loginErrorMSG != null}">
        <p class="loginerrormsg">
                ${loginErrorMSG}
        </p>
    </c:if>

    <div class="login-info-box">
        <div class="state-check-box">
            <%--        <input class="inputPadding" type="checkbox" name="rememberAccountBnt" checked> 아이디 저장--%>

                <label class="checkbox-container">자동 로그인
                    <input id="login-save" class="inputPadding" type="checkbox" name="keepLoginState">
                    <span class="checkmark"></span>

<%--            <input id="login-save" class="inputPadding" type="checkbox" name="keepLoginState"><label for="login-save" class="login-save-check">자동로그인</label>--%>
        </div>
        <div class="find-box">
            <button class="find-id font14">아이디 찾기</button>
            <button class="find-pass font14">비밀번호 찾기</button>
        </div>
    </div>


    <br>
    <input class="inputPadding" type="hidden" name="from" value="<c:out value='${param.from}' />">
    <button class="login_btn">로그인</button>
    <div class="social-login">
        <button type="button" class="kakao-login-btn s-login-btn"></button>
        <button type="button" class="naver-login-btn s-login-btn"></button>
        <button type="button" class="google-login-btn s-login-btn"></button>
    </div>
    <p class="reg_title2">우리들의 행복한 거래 치즈 메이트 <a href="/resisterForm" class="reg_btn">회원가입</a><p>
</form>
</div>

<%@include file="fixed/footer.jsp"%>