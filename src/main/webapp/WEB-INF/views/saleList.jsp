<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<% // 세션에 sessionId가 존재하는지 확인
    String sessionId = (String) session.getAttribute("urId");
%>
<html>

<head>
    <title>판매/나눔</title>
</head>

<body>
<button type="button" onclick="writeBtn()">글쓰기</button>
<select id="addr_cd">
    <option id="selectAll" value="" selected>전체</option>
    <c:forEach var="AddrCd" items="${addrCdList}">
        <option value="${AddrCd.addr_cd}">${AddrCd.addr_name}</option>
    </c:forEach>
</select>
<table id="saleListTB">
    <tr>
        <th class="no">번호</th>
        <th class="title">제목</th>
        <th class="writer">이름</th>
        <th class="addr_name">주소명</th>
        <th class="regdate">등록일</th>
        <th class="viewcnt">조회수</th>
    </tr>
    <tbody id="saleList">
    <c:forEach var="Sale" items="${saleList}">
        <tr>
            <td>${Sale.no}</td>
            <td class="title"><a
                    href="<c:url value='/sale/read?no=${Sale.no}'/>">${Sale.title}</a></td>
            <td>${Sale.seller_nick}</td>
            <td>${Sale.addr_name}</td>
            <c:choose>
                <c:when test="${Sale.r_date.time >= startOfToday}">
                    <td class="regdate">
                        <fmt:formatDate value="${Sale.r_date}" pattern="HH:mm" type="time"/>
                    </td>
                </c:when>
                <c:otherwise>
                    <td class="regdate">
                        <fmt:formatDate value="${Sale.r_date}" pattern="yyyy-MM-dd"
                                        type="date"/>
                    </td>
                </c:otherwise>
            </c:choose>
            <td>${Sale.view_cnt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<div style="text-align: center">
    <a href="<c:url value="/sale/list?page=1"/>"> << </a>
    <c:choose>
        <c:when test="${(ph.page-1) < 1}">
            <a href="<c:url value="/sale/list?page=${ph.totalPage}"/>"> < </a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value="/sale/list?page=${ph.page - 1}"/>"> < </a>
        </c:otherwise>
    </c:choose>
    <c:forEach var="i" begin="${ph.startPage}" end="${ph.endPage}">
        <a class="page ${i==ph.page? "paging-active" : ""}" href="<c:url value="/sale/list?page=${i}"/>">${i}</a>
    </c:forEach>
    <c:choose>
        <c:when test="${(ph.page+1) > ph.totalPage}">
            <a href="<c:url value="/sale/list?page=1"/>"> > </a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value="/sale/list?page=${ph.page+1}"/>"> > </a>
        </c:otherwise>
    </c:choose>
    <a href="<c:url value="/sale/list?page=${ph.totalPage}"/>"> >> </a>
</div>
<br>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    // window.onload = function() {
    //     // 페이지가 로드될 때 실행할 코드 작성
    //     alert("페이지가 로드되었습니다!");
    // };

    // "전체" 옵션이 보이지 않도록 설정
    if ("${sessionId}" != null || "${sessionId}" != "") {
        document.getElementById("selectAll").style.display = "none"; // "전체" 옵션 숨기기

        // 첫 번째 옵션 선택
        var addrCdSelect = document.getElementById("addr_cd");
        addrCdSelect.selectedIndex = 1;
    }

    $(document).ready(function () {
        // AddrCd 선택이 변경될 때마다 실행되는 함수
        $("#addr_cd").change(function () {
            // 선택된 AddrCd 값 가져오기
            let addr_cd = $(this).val();

            // 서버에 선택된 AddrCd 값을 전송하여 새로운 addrCdList 받아오기
            $.ajax({
                type: "GET",
                url: "/sale/searchAddrCd", // 새로운 addrCdList를 반환하는 URL로 변경해야 함
                dataType: "json",
                data: {addr_cd: addr_cd},
                success: function (data) {
                    // startOfToday 값을 추출
                    let startOfToday = data.startOfToday;
                    // 판매 목록 업데이트 함수 호출 시 startOfToday 값을 함께 전달
                    updateSaleList(data.saleList, startOfToday);
                },
                error: function (xhr, status, error) {
                    console.error("Error:", error);
                }
            });
        });
    });

    // function writeBtn() {
    //     // 선택된 주소 코드(addr_cd) 값 가져오기
    //     let addrCdValue = $('#addr_cd').val();
    //
    //     window.location.href = "/sale/write?addr_cd=" + addrCdValue;
    // }

    function writeBtn() {
        // 선택된 주소 코드(addr_cd) 값 가져오기
        let addrCdValue = $('#addr_cd').val();

        // form 엘리먼트 생성
        let form = $('<form>', {
            method: 'POST', // POST 방식 설정
            action: '/sale/write' // 전송할 URL 설정
        });

        // hidden input 엘리먼트 생성
        $('<input>').attr({
            type: 'hidden',
            name: 'addr_cd',
            value: addrCdValue
        }).appendTo(form);

        // form을 body에 추가하고 자동으로 submit
        form.appendTo('body').submit();
    }

    // 업데이트된 saleList를 화면에 출력하는 함수
    function updateSaleList(saleList, startOfToday) {
        // 기존 saleList 테이블의 tbody를 선택하여 내용을 비웁니다.
        $("#saleList").empty();
        if (saleList.length > 0) {
            saleList.forEach(function (sale) {
                let row = $("<tr>");
                row.append($("<td>").text(sale.no)); // 판매 번호
                row.append($("<td>").addClass("title").html("<a href='/sale/read?no=" + sale.no + "'>" + sale.title + "</a>")); // 제목
                row.append($("<td>").text(sale.seller_nick)); // 판매자 닉네임
                row.append($("<td>").text(sale.addr_name)); // 주소명

                let saleDate = new Date(sale.r_date);
                let regdate;
                if (saleDate.getTime() >= startOfToday) {
                    regdate = $("<td>").addClass("regdate").text(formatDate(saleDate, "HH:mm"));
                } else {
                    regdate = $("<td>").addClass("regdate").text(formatDate(saleDate, "yyyy-MM-dd"));
                }

                row.append(regdate);
                row.append($("<td>").text(sale.view_cnt)); // 조회수
                $("#saleList").append(row);
            });
        } else {
            $("#saleList").append("<tr><td colspan='5'>데이터가 없습니다.</td></tr>");
        }
    };

    // 날짜 형식을 변환하는 함수
    function formatDate(date, format) {
        // 현재는 간단히 예시로만 구현한 함수입니다. 실제로는 더 복잡한 로직이 필요할 수 있습니다.
        return date.toLocaleString();
    }


</script>

</html>