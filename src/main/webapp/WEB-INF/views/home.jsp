<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<html>
<head>
    <title>Title</title>
</head>
<body>

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
<a href="/img/testview">판매 리스트 보기</a>
<a href="/chat2">치즈톡</a>

</body>
</html>

<script>
    // *** board 버튼을 선택할 경우 ***
    // 1. 세션에 로그인 정보가 있는 경우 board로 정상 이동
    // 2.                   없는 경우 loginForm으로 이동
    $(document).ready(function() {
        $('#boardBnt').click(function(event) {
            var userId = '${sessionScope.userId}';
            var adminId = '${sessionScope.adminId}';

            if((userId == null || userId == "") && (adminId == null || adminId == "")) {
                event.preventDefault();
                alert('로그인 후 사용 가능한 서비스 입니다.');
                window.location.href = '/loginForm';
            }
        });
    });
</script>