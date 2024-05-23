<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="fixed/header.jsp"%>
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

    <style>

        input:invalid {
            border: 2px solid red;
        }

        form {
            border: 1px solid rgb(218 222 229);
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
            width: 100%;
            padding-top: 5px;
            padding-bottom: 5px;
            padding-left: 10px;
            margin-top: 3px;
            margin-bottom: 3px;
            border: 1px solid rgb(218 222 229);
        }

        .center {
            max-width: 450px;
            height: 400px;
            margin: 30px auto;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
        }

        .login_title{
            margin-top: 20px;
            text-align: center;
            font-size: 30px;
            font-weight: 900;
        }

        .login_title_h1{
            font-size: 26px;
            text-align: left;
        }

        .title-img-box{
            /*width: 200px;*/
            width: 350px;
            height: 240px;
            margin: 30px auto 0;
            overflow: hidden;
        }

        #loginlogo{
            width: 350px;
            display: inline-block;
        }

        .LoginFrom-box{
            padding-top: 50px;
        }

        .login_btn{
            display: inline-block;
            width: 100%;
            height: 30px;
            background-color: #ed9523;
            border-radius: 5px;
            color: white;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .reg_title2{
            margin-top: 30px;
            display: inline-block;
            font-weight: 500;
            color: #ed9523;
            text-align: center;
            width: 100%;
        }

        .reg_btn{
            display: inline-block;
            font-weight: 500;
            border: 1px solid #ed9523;
            padding: 3px;
            border-radius: 5px;
            color: #ed9523;
        }
    </style>

<%--<div class="login_title"><span class="upper_word">C</span>HEESE MATE</div>--%>
<form class="center" action="/login" method="post">

    <h1 class="login_title_h1">CHEESE MATE에 오신것을 <br/>환영합니다.</h1>

    <div class="LoginFrom-box">
        <input class="inputBox" name="inputId" value="<c:out value='<%= cookieUserId %>' />" placeholder="아이디"/>
        <br>
        <input class="inputBox" type="password" name="inputPw" value="<c:out value='<%= cookieUserPw %>' />" placeholder="비밀번호"/>
    </div>

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
    <button class="login_btn">로그인</button>
    <p class="reg_title2">우리들의 행복한 거래 치즈 메이트 <a href="/resisterForm" class="reg_btn">회원가입</a><p>
</form>

<%@include file="fixed/footer.jsp"%>