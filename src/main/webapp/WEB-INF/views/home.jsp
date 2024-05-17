<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>

    <style>
        .center {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="center">
    <h1>*** HOME ***</h1>
    <h1>HOME</h1>
    <h1>HOME</h1>
    <h1>HOME</h1>

    <c:if test="${sessionScope.userId != null}">
        <p>일반 유저 입니다.</p>
        <p>아이디 : ${sessionScope.userId}</p>
        <p>닉네임 : ${sessionScope.userNick}</p>
        <ul>
            <c:forEach var="addr_cd" items="${sessionScope.userAddrCdDtoList}">
                <li>${addr_cd.no}, ${addr_cd.addr_cd}</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${sessionScope.adminId != null}">
        <p>관리자 입니다.</p>
        <p>아이디 : ${sessionScope.adminId}</p>
    </c:if>


    <c:choose>
        <c:when test="${sessionScope.userId == null && sessionScope.adminId == null}">
            <form action="/loginForm", method="get">
                <button name="loginBtn">로그인</button>
            </form>
        </c:when>
        <c:otherwise>
            <form action="/logout", method="get">
                <button name="logoutBtn">로그아웃</button>
            </form>
        </c:otherwise>
    </c:choose>

    <form action="/board" method="get">
        <button id="boardBnt">Board</button>
    </form>

    <form action="/board_2" method="get">
        <button id="board_2_Bnt">Board_2</button>
    </form>

    <form action="/sale" method="get">
        <button id="SaleBnt">판매/나눔</button>
    </form>
</div>


</body>
</html>