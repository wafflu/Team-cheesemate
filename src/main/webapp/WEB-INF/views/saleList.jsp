<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%
    // 세션에 sessionId가 존재하는지 확인
    session.setAttribute("sessionId", "david234");
    String sessionId = (String) session.getAttribute("sessionId");
%>
<html>
<head>
    <title>판매/나눔</title>
</head>
<body>
<button type="button" formmethod="get" onclick="location.href='write'">글쓰기</button>
<select id="addr_cd">
    <option id="selectAll" value="" selected>전체</option>
    <c:forEach var="AddrCd" items="${addrCdList}">
        <option value="${AddrCd.addr_cd}">${AddrCd.addr_name}</option>
    </c:forEach>
</select>
<table>
    <tr>
        <th class="no">번호</th>
        <th class="title">제목</th>
        <th class="writer">이름</th>
        <th class="addr_name">주소명</th>
        <th class="regdate">등록일</th>
        <th class="viewcnt">조회수</th>
    </tr>
    <c:forEach var="Sale" items="${saleList}">
        <tr>
            <td>${Sale.no}</td>
            <td class="title"><a href="<c:url value='/sale/read?no=${Sale.no}'/>">${Sale.title}</a></td>
            <td>${Sale.seller_nick}</td>
            <td>${Sale.addr_name}</td>
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
<script>
    // window.onload = function() {
    //     // 페이지가 로드될 때 실행할 코드 작성
    //     alert("페이지가 로드되었습니다!");
    // };

    // "전체" 옵션이 보이지 않도록 설정
    if("${sessionId}" != "") {
        document.getElementById("selectAll").style.display = "none";

        // 첫 번째 옵션 선택
        var addrCdSelect = document.getElementById("addr_cd");
        addrCdSelect.selectedIndex = 1;
    }
</script>
</body>
</html>
