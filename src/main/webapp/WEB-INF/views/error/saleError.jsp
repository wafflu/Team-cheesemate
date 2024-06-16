<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp" %>

<style>
    .totalBox {
        height: fit-content;
        position: relative;
        margin-bottom: 20px;
        width: 1200px;
        height: 500px;
        margin: auto;
        text-align: center;
        /*top: 250px;*/
    }

    .warning {
        position: absolute;
        width: 100%;
        top: 200px; /* totalBox의 중간쯤에 위치하도록 설정 */
        z-index: 10;
        font-size: 30px; /* font30 클래스를 CSS로 직접 정의 */
    }

    .material-symbols-outlined {
        font-variation-settings: 'FILL' 0,
        'wght' 1000,
        'GRAD' 0,
        'opsz' 48;
        color: rgba(238,135,8,0.4);
        font-size: 300px !important;
        margin-top: 70px;
    }

    .font30 {
        font-weight: 800;
    }


</style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<%--<link rel="stylesheet"--%>
<%--      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>--%>
<div class="totalBox">
    <p class="warning font30">존재하지 않는 판매글입니다</p>
    <span class="material-symbols-outlined">
block
</span>

</div>
<%@include file="../fixed/footer.jsp" %>