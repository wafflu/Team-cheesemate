<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
    <title>sale</title>
</head>
<body>
<div>
    <form id="form" action="" method="post">
        <c:choose>
            <c:when test="${sessionScope.userId == Sale.seller_id}">
                <select id="sal_s_cd">
                    <option value="S" ${Sale.sal_s_cd == 'S' ? 'selected' : ''}>판매중</option>
                    <option value="R" ${Sale.sal_s_cd == 'R' ? 'selected' : ''}>예약중</option>
                    <option value="C" ${Sale.sal_s_cd == 'C' ? 'selected' : ''}>거래완료</option>
                </select>
            </c:when>
            <c:otherwise>
                <p>
                    <c:choose>
                        <c:when test="${Sale.sal_s_cd == 'S'}">판매중</c:when>
                        <c:when test="${Sale.sal_s_cd == 'R'}">예약중</c:when>
                        <c:when test="${Sale.sal_s_cd == 'C'}">거래완료</c:when>
                    </c:choose>
                </p>
            </c:otherwise>
        </c:choose>
        <div id="saleBoard">

        </div>

        <p>sale : ${Sale.no}</p>
        <p>행정동 코드 : ${Sale.addr_cd}</p>
        <p>주소명 : ${Sale.addr_name}</p>
        <p>판매자id : ${Sale.seller_id}</p>
        <p>판매자닉네임 : <a href="/userInfo/${Sale.seller_id}">${Sale.seller_nick}</a></p>
        <p>판매 카테고리 : ${Sale.sal_i_cd}</p>
        <p>판매 카테고리명 : ${Sale.sal_name}</p>
        <p>사용상태 : ${Sale.pro_s_cd}</p>
        <p>판매/나눔 : ${Sale.tx_s_cd}</p>
        <p>거래방식1 : ${Sale.trade_s_cd_1}</p>
        <p>거래방식2 : ${Sale.trade_s_cd_2}</p>
<%--        <p>거래상태 : ${Sale.sal_s_cd}</p>--%>
        <p>제목 : ${Sale.title}</p>
        <br>내용 : <div style="white-space:pre;">${Sale.contents}</div>
        <p>가격 : ${Sale.price}</p>
        <p>가격제시/나눔신청 : ${Sale.bid_cd}</p>
        <c:choose>
            <c:when test="${Sale.bid_cd == 'P'}">
                <button type="button">가격제시</button>
            </c:when>
            <c:when test="${Sale.bid_cd == 'T'}">
                <button type="button">나눔신청</button>
            </c:when>
        </c:choose>
        <p>희망행성구역코드 : ${Sale.pickup_addr_cd}</p>
        <p>희망주소명 : ${Sale.pickup_addr_name}</p>
        <p>희망거래장소 : ${Sale.detail_addr}</p>
        <p>브랜드 : ${Sale.brand}</p>
        <p>정가 : ${Sale.reg_price}</p>
        <%--    <p>구매자id : ${Sale.buyer_id}</p>--%>
        <%--    <p>구매자 닉네임 : ${Sale.buyer_nick}</p>--%>
        <p>찜횟수 : ${Sale.like_cnt}</p>
        <p>조회수 : ${Sale.view_cnt}</p>
        <p>판매글 등록일 : ${Sale.r_date}</p>
        <%--    <p>판매글 수정일 : ${Sale.m_date}</p>--%>
        <%--    <p>끌올횟수 : ${Sale.up_cnt}</p>--%>
        <%--    <p>끌어올리기일 : ${Sale.hoisting}</p>--%>
        <p>가격제안/나눔신청 인원수 : ${Sale.bid_cnt}</p>
        <%--    <p>판매글 노출여부 : ${Sale.ur_state}</p>--%>
        <%--    <p>관리자 관리상태 : ${Sale.ad_state}</p>--%>

        <div id="tagDiv">
            <c:forEach var="Tag" items="${tagList}">
                <span value="${Tag.no}">#${Tag.contents}</span>
            </c:forEach>
        </div>
        <br>

        <c:if test="${Sale.seller_id == sessionScope.userId}">
            <button type="button" id="removeBtn">삭제하기</button>
            <button type="button" id="modifyBtn">수정하기</button>
        </c:if>
        <button type="button" id="returnBtn">목록</button>
    </form>
</div>
<div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        loadSaleInfo();
        // 판매 정보를 불러오는 함수
        function loadSaleInfo() {
            $.ajax({
                type: "GET",
                url: "/sale/read?no=${Sale.no}",
                success: function (data) {
                    alert(Sale);

                    // 성공적으로 데이터를 받아오면 saleInfo 엘리먼트에 출력
                    $("#saleBoard").html("확인");
                },
                error: function (xhr, status, error) {
                    alert("판매 정보를 불러오는데 실패하였습니다.");
                }
            });
        }


        $("#removeBtn").on("click", function () {
            if (confirm("삭제 하시겠습니까?")) {
                $("#form").attr("action", "/sale/remove?no=${Sale.no}");
                $("#form").submit();
            }
        });

        $("#modifyBtn").on("click", function () {
            if (confirm("수정 하시겠습니까?")) {
                let saleNo = "${Sale.no}";
                $("<input>").attr({
                    type: "hidden",
                    name: "no",
                    value: saleNo
                }).appendTo("#form");
                $("#form").attr("action", "/sale/modify");
                $("#form").submit();
            }
        });

        $("#returnBtn").on("click", function () {
            if (confirm("목록으로 돌아가시겠습니까?")) {
                window.location.href = "<c:url value='/sale/list'/>";
            }
        });

        $("#sal_s_cd").on("change", function () {
            let selectedValue = $(this).val();
            let sal_s_name = $("#sal_s_cd option:checked").text();
            $.ajax({
                type: "POST",
                url: "/sale/updateSaleSCd",
                data: {no: "${Sale.no}", sal_s_cd: selectedValue, seller_id: "${Sale.seller_id}"},
                success: function (data) {
                    alert("판매글 상태가 " + sal_s_name + "(으)로 변경되었습니다.");
                },
                error: function (xhr, status, error) {
                    alert("판매글 상태 변경이 실패하였습니다.");
                }
            });
        });
    });
</script>
</body>
</html>
