<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>sale</title>
</head>
<body>
<div>
    <c:forEach items="${imglist}" var="img">
        <c:if test="${img.imgtype eq 'w'}">
            <img src="/img/display?fileName=${img.img_full_rt}" style="width: 148px; height: 148px;">
        </c:if>
    </c:forEach>
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
                <p> 거래상태 :
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
        <p>판매자닉네임 : <a href="/userInfo/${Sale.seller_id}">${Sale.seller_nick}</a></p>
        <p>판매 카테고리명 : ${Sale.sal_name}</p>
        <p>
            사용상태 :
            <c:choose>
                <c:when test="${Sale.pro_s_cd == 'S'}">새상품(미사용)</c:when>
                <c:when test="${Sale.pro_s_cd == 'A'}">사용감 없음</c:when>
                <c:when test="${Sale.pro_s_cd == 'B'}">사용감 적음</c:when>
                <c:when test="${Sale.pro_s_cd == 'C'}">사용감 많음</c:when>
                <c:when test="${Sale.pro_s_cd == 'D'}">고장/파손 상품</c:when>
            </c:choose>
        </p>
        <p>판매/나눔 :
            <c:choose>
                <c:when test="${Sale.tx_s_cd == 'S'}">판매</c:when>
                <c:when test="${Sale.tx_s_cd == 'F'}">나눔</c:when>
            </c:choose>
        </p>
        <p>거래방식 :
                <c:if test="${Sale.trade_s_cd_1 == 'O'}">
                    온라인
                </c:if>
                <c:if test="${Sale.trade_s_cd_1 == 'F' || Sale.trade_s_cd_2  == 'F'}">
                    직거래
                </c:if>
                <c:if test="${Sale.trade_s_cd_1 == 'D' || Sale.trade_s_cd_2  == 'D'}">
                    택배거래
                </c:if>
        </p>
        <p>제목 : ${Sale.title}</p>
        <br>내용 :
        <div style="white-space:pre;">${Sale.contents}</div>
        <p>가격 : ${Sale.price}</p>
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
        <p>찜횟수 : ${Sale.like_cnt}</p>
        <p>조회수 : ${Sale.view_cnt}</p>
        <p>끌올횟수 : ${Sale.hoist_cnt}</p>
        <p>등록일 : ${Sale.h_date}</p> <%-- 끌어올리기 일도 등록하면 now()로 되기 때문에 등록일을 h_date를 기준으로 진행 --%>
        <p>가격제안/나눔신청 인원수 : ${Sale.bid_cnt}</p>
        <%--    <p>판매글 노출여부 : ${Sale.ur_state}</p>--%>
        <%--    <p>관리자 관리상태 : ${Sale.ad_state}</p>--%>

        <c:if test="${Sale.seller_id == sessionScope.userId && Sale.hoist_cnt != 3}">
            <button type="button" id="hoistingBtn">끌어올리기</button>
        </c:if>

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
        <button type="button" id="charbtn">채팅하기</button>
    </form>
</div>
<div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {

        $("#hoistingBtn").on("click", function () {
            if (confirm("끌어올리겠습니까?")) {
                let saleNo = "${Sale.no}";
                $("<input>").attr({
                    type: "hidden",
                    name: "no",
                    value: saleNo
                }).appendTo("#form");
                $("#form").attr("action", "/hoisting");
                $("#form").submit();
            }
        });

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

    $(document).ready(function () {
        $("#charbtn").on("click", function () {
            let saleNo = "${Sale.no}";
            let sellerid = "${Sale.seller_id}";
            let seller_nick = "${Sale.seller_nick}";
            $("<input>").attr({
                type: "hidden",
                name: "sno",
                value: saleNo
            }).appendTo("#form");

            $("<input>").attr({
                type: "hidden",
                name: "id",
                value: sellerid
            }).appendTo("#form");

            $("<input>").attr({
                type: "hidden",
                name: "nick",
                value: seller_nick
            }).appendTo("#form");

            // action 속성 설정
            $("#form").attr("action", "/callchat");

            // form submit
            $("#form").submit();
        });
    });

</script>
</body>
</html>
