<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	PostHome
</h1>
<form action = "/PostHome" method = "get">
<P>  게시글 목록 </P>

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
			<th>수정일</th>
			<th>좋아요</th>
			<th>조회수</th>
			<th>게시글 상태</th>
			<th>관리자 상태</th>
		</tr>
	</thead>
	<c:forEach var = "postDto" items="${list}">
	<tbody>
		<tr>
			<td>${list.}</td>
		</tr>
	</tbody>
</c:forEach>
</form>
</body>

</html>
