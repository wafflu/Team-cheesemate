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
    <form id="form" action="" method="post">
        <p>sale : ${Sale.no}</p>
        <p>행정동 코드 : ${Sale.addr_cd}</p>
        <p>주소명 : ${Sale.addr_name}</p>
        <p>판매자id : ${Sale.seller_id}</p>
        <p>판매자닉네임 : ${Sale.seller_nick}</p>
        <p>판매 카테고리 : ${Sale.sal_i_cd}</p>
        <p>판매 카테고리명 : ${Sale.sal_name}</p>
        <p>사용상태 : ${Sale.pro_s_cd}</p>
        <p>판매/나눔 : ${Sale.tx_s_cd}</p>
        <p>거래방식1 : ${Sale.trade_s_cd_2}</p>
        <p>거래방식2 : ${Sale.trade_s_cd_2}</p>
        <p>거래상태 : ${Sale.sal_s_cd}</p>
        <p>제목 : ${Sale.title}</p>
        <p>내용 : ${Sale.contents}</p>
        <p>가격 : ${Sale.price}</p>
        <p>가격제시/나눔신청 : ${Sale.bid_cd}</p>
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

        <button type="button" id="removeBtn">삭제하기</button>
        <button type="button" id="modifyBtn">수정하기</button>
        <button type="button" id="returnBtn">목록</button>
    </form>
</div>
<div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $("#removeBtn").on("click", function () {
            if (confirm("삭제 하시겠습니까?")) { // 삭제 여부 확인
                $.ajax({
                    type: "POST",
                    url: "<c:url value='/sale/remove?no=${Sale.no}'/>",
                    success: function (data) {
                        alert("삭제되었습니다.");
                        // alert(data);
                        window.location.replace(data); // 페이지 새로고침
                    },
                    error: function (xhr, status, error) {
                        alert("삭제 중 오류가 발생했습니다.");
                    }
                });
            }
        });
    });

    <%--$(document).ready(function () {--%>
    <%--    $("#modifyBtn").on("click", function () {--%>
    <%--        if (confirm("수정 하시겠습니까?")) { // 삭제 여부 확인--%>
    <%--            $.ajax({--%>
    <%--                type: "POST",--%>
    <%--                url: "<c:url value='/sale/modify?no=${Sale.no}'/>",--%>
    <%--                contentType: "application/json",--%>
    <%--                success: function (data) {--%>
    <%--                },--%>
    <%--                error: function (xhr, status, error) {--%>
    <%--                    alert("오류가 발생했습니다.");--%>
    <%--                }--%>
    <%--            });--%>
    <%--        }--%>
    <%--    });--%>
    <%--});--%>
    $(document).ready(function () {
        $("#modifyBtn").on("click", function () {
            if (confirm("수정 하시겠습니까?")) {
                $("#form").attr("action", "<c:url value='/sale/modify?no=${Sale.no}'/>");
                $("#form").attr("method", "post");
                $("#form").submit();
            }
        });
    });

    $(document).ready(function () {
        $("#returnBtn").on("click", function () {
            if (confirm("목록으로 돌아가시겠습니까?")) { // 삭제 여부 확인
                $.ajax({
                    type: "POST",
                    url: "<c:url value='/sale/list'/>",
                    success: function (data) {
                        window.location.href = "/sale/list";
                    },
                    error: function (xhr, status, error) {
                        alert("오류가 발생했습니다.");
                    }
                });
            }
        });
    });


    <%--$("#returnBtn").on("click", function() {--%>
    <%--    let form = $("#form");--%>
    <%--    form.attr("action", "<c:url value='/sale/list'/>");--%>
    <%--    form.attr("method", "post");--%>
    <%--    confirm("목록으로 돌아가시겠습니까?");--%>
    <%--    form.submit();--%>
    <%--});--%>

</script>
</body>
</html>
