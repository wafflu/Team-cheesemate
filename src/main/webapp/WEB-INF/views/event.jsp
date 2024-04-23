<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 2024-04-23
  Time: 오전 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>이벤트 페이지</title>
</head>
<body>

<tbody>
    <table border="1" cellspacing="0">
    <c:forEach items="${eventarr}" var="event">
        <tr>
            <td>${event.evt_no}</td>
            <th>
                <a href="/event/read?bno=${event}">${event.title}</a>
                <p>${event.contents}</p>
            </th>
            <td>${event.prize}</td>
            <td>${event.s_date}</td>
            <td>${event.e_date}</td>
        </tr>
    </c:forEach>
    </table>
</tbody>
</body>
</html>
