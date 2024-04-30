
<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 4/28/24
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<html>
<head>
    <title>CommunityHome</title>
</head>
<body>
<header>

</header>
<section>
<article>

    <h2>실시간 우리 지역 인기글</h2>
    <p><a href = "${pageContext.request.contextPath}/communityList">더보기></a></p>
    <input type = "button" value = "글쓰기" onclick = "location.href='<c:url value="/write"/>'">

    <br>

    <th>
        <td>현재 순위</td>
        <td>카테고리</td>
        <td>제목</td>
        <td>닉네임</td>
        <td>조회수</td>
        <td>지역</td>
    <th>
    <br>

    <c:forEach var="CommunityBoardDto" items="${getTopTen}" varStatus="loop">

                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${CommunityBoardDto.name}</td>
                    <td> <a href="${pageContext.request.contextPath}/read?no=${CommunityBoardDto.no}">${CommunityBoardDto.title}</a></td>
                    <td>${CommunityBoardDto.nick}</td>
                    <td>${CommunityBoardDto.view_cnt}</td>
                    <%-- 댓글수 필요--%>
                    <td>${CommunityBoardDto.addr_name}</td>
                <tr>
                <br>
    </c:forEach>


<h2>블라블라</h2>
<c:forEach var = "CommunityBoardDto" items="${list}" varStatus="loop">
    <c:if test ="${loop.index <= 5}">
        <c:if test = "${CommunityBoardDto.commu_cd eq 'commu_B'}">
            <tr>
        <%--        <td>${communityBoardDto.commu_cd}</td>--%>

                <td>${CommunityBoardDto.name}</td>
                <td> <a href="${pageContext.request.contextPath}/read?no=${CommunityBoardDto.no}">${CommunityBoardDto.title}</a></td>
                <td>${CommunityBoardDto.nick}</td>
                <td>${CommunityBoardDto.view_cnt}</td>
                    <%-- 댓글수 필요--%>
                <td>${CommunityBoardDto.addr_name}</td>
                <td>${CommunityBoardDto.r_date}</td>

            </tr>
            <br>
        </c:if>
    </c:if>
 </c:forEach>
<h2>연애/썸</h2>

<h2>고민/상담</h2>
<h2>기타</h2>
</article>
</section>
<footer>

</footer>

</body>
</html>
