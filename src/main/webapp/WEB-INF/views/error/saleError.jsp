<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp" %>

<style>
    .totalBox {
        height: fit-content;
        position: relative;
        margin-bottom: 20px;
    }

    .saleListBox {
        /*border: 1px solid red;*/
        height: 80%;
        width: 1200px;
        margin: 0 auto;
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;
        gap: 10px;
    }

    .smallBox {
        border: 1px solid darkgray;
        /*background-color: pink;*/
        width: 210px;
        height: 300px;
        margin-bottom: 10px;
        background: white;
    }

    .img {
        width: 210px;
        height: 210px;
        margin: 0 auto;
    }

    #pageContainer {
        position: absolute;
        left: 50%;
        /*bottom: 20px;*/
        transform: translateX(-50%);
        text-align: center;
    }

    .info {
        display: flex;
        justify-content: space-between;
        width: 100%;
    }

    .division-line {
        border-top: 1px solid darkgray;
        margin: auto;
    }

    /*#saleListBox {*/
    /*    margin: 0 auto; !* 수평 가운데 정렬 *!*/
    /*    width: 80%; !* 테이블의 너비 설정 *!*/
    /*    text-align: center; !* 텍스트 가운데 정렬 *!*/
    /*}*/

    .page-space {
        margin: 0 5px; /* 공백 크기 조절 */
    }

    /* 임시로 style 추가 : css 수정 필요*/
    .imgClass {
        width: 100px;
        height: 100px;
    }

    .header-actions {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 10px;
    }

    .saleBtn {
        padding: 5px 20px;
        background-color: rgba(245, 157, 28, 1);
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    #addr_cd {
        margin-right: auto; /* Ensures the select is on the left */
    }


</style>

<div class="maincontent totalBox">
    <p>없는 판매글 번호입니다.<p>
</div>
<%@include file="../fixed/footer.jsp" %>