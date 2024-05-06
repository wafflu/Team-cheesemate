<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page session="false" %>
<html>
<head>
    <title>판매/나눔</title>
</head>
<body>
<button type="button" formmethod="get" onclick="location.href='write'">글쓰기</button>
<table>
    <tr>
        <th class="no">번호</th>
        <th class="title">제목</th>
        <th class="writer">이름</th>
        <th class="regdate">등록일</th>
        <th class="viewcnt">조회수</th>
    </tr>
    <c:forEach var="Sale" items="${saleList}">
        <tr>
            <td>${Sale.no}</td>
                <%-- <td>${Board.title}</td> --%>
            <td class="title"><a href="<c:url value='/sale/read?no=${Sale.no}'/>">${Sale.title}</a></td>
            <td>${Sale.seller_nick}</td>
            <c:choose>
                <c:when test="${Sale.r_date.time >= startOfToday}">
                    <td class="regdate"><fmt:formatDate
                            value="${Sale.r_date}" pattern="HH:mm" type="time" /></td>
                </c:when>
                <c:otherwise>
                    <td class="regdate"><fmt:formatDate
                            value="${Sale.r_date}" pattern="yyyy-MM-dd" type="date" /></td>
                </c:otherwise>
            </c:choose>
            <td>${Sale.view_cnt}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
