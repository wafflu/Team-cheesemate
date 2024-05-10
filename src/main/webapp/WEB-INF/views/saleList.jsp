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
<form method="GET">
    <button type="button" onclick="loadAddrCd()">글쓰기</button>
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
                            value="${Sale.r_date}" pattern="HH:mm" type="time"/></td>
                </c:when>
                <c:otherwise>
                    <td class="regdate"><fmt:formatDate
                            value="${Sale.r_date}" pattern="yyyy-MM-dd" type="date"/></td>
                </c:otherwise>
            </c:choose>
            <td>${Sale.view_cnt}</td>
        </tr>
        </c:forEach>
</form>
</table>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    // window.onload = function() {
    //     // 페이지가 로드될 때 실행할 코드 작성
    //     alert("페이지가 로드되었습니다!");
    // };

    // "전체" 옵션이 보이지 않도록 설정
    if ("${sessionId}" != "" || "${sessionId}" != null) {
        document.getElementById("selectAll").style.display = "none";

        // 첫 번째 옵션 선택
        var addrCdSelect = document.getElementById("addr_cd");
        addrCdSelect.selectedIndex = 1;
    }

    function loadAddrCd() {
        var addrCdValue = $('#addr_cd').val(); // 선택된 addr_cd 값 가져오기
        console.log(addrCdValue); // 값이 잘 가져와졌는지 확인하기 위해 콘솔에 출력

        // 선택된 addr_cd 값이 비어 있지 않은 경우에만 AJAX 요청 보내기
        if (addrCdValue !== "") {
            $.ajax({
                type: "GET", // GET 방식 사용
                url: "/sale/list", // 요청을 보낼 URL
                data: { addr_cd: addrCdValue }, // 선택된 addr_cd 값을 요청에 포함
                success: function (data) {
                    window.location.href = "/sale/write?addr_cd=" + addrCdValue;
                },
                error: function (xhr, status, error) {
                    console.error('Error:', error);
                }
            });
        }
    }

</script>
</html>
