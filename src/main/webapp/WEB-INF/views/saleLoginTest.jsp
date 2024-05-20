<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%
    // 세션에 sessionId가 존재하는지 확인
    String sessionId = (String) session.getAttribute("urId");
%>
<html>
<head>
    <title>판매/나눔</title>
</head>
<body>
<button type="button" onclick="btn()">판매/나눔</button>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>

    function btn() {
        // 선택된 주소 코드(addr_cd) 값 가져오기
        window.location.href = "/sale";
    }


</script>
</html>
