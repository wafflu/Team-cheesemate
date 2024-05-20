<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>

<head>
    <title>Board</title>
</head>
<body>

<h1>*** BOARD ***</h1>
<h1>BOARD</h1>
<h1>BOARD</h1>
<h1>BOARD</h1>

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

<form action="/home" method="get">
    <button name="homeBnt">Home</button>
</form>
</body>
</html>
