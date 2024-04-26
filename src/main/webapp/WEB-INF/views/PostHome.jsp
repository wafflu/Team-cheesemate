<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>PostHome</h1>

<table>
    <thead>
    <tr>
        <th>일련번호</th>
        <th>아이디</th>
        <th>행정구역코드</th>
        <th>번호</th>
        <th>주소명</th>
        <th>제목</th>
        <th>내용</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>좋아요</th>
        <th>조회수</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="postDto" items="${list}">
        <tr>
            <td>${postDto.sn}</td>
            <td>${postDto.ur_id}</td>
            <td>${postDto.addr_cd}</td>
            <td>${postDto.no}</td>
            <td>${postDto.commu_cd}</td>
            <td>${postDto.addr_name}</td>
            <td>
                <a href = "${pageContext.request.contextPath}/read?sn=${postDto.sn}">${postDto.title}
                </a>
            </td>
            <td>${postDto.contents}</td>
            <td>${postDto.nick}</td>
            <td>${postDto.r_date}</td>
            <td>${postDto.like_cnt}</td>
            <td>${postDto.view_cnt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="${pageContext.request.contextPath}/communityHome" method="get">
    <P>  게시글 목록 </P>
    <input type="button" value="글쓰기" onclick="location.href='<c:url value="/write"/>'">
</form>

</body>
</html>
