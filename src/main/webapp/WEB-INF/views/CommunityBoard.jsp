<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 5/3/24
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<html>
<head>
    <title>Title</title>
    <style>
        .title-container{
            display: flex;
        }
    </style>
</head>
<body>
<form action = "${pageContext.request.contextPath}/community/read" id ="form" enctype="multipart/form-data">
<div class="post-content">

    <%--사용자 정보--%>
    <div>
        ${communityBoardDto.nick}
        ${communityBoardDto.r_date}
        ${communityBoardDto.addr_name}
    </div>

    <%--제목--%>
    <div class="title-container">
        <h1>${communityBoardDto.title}</h1>
        <p class="detail-button" data-user-id="${communityBoardDto.ur_id}">:</p>
    </div>

    <p>${communityBoardDto.contents}</p>
    <c:if test="${not empty communityBoardDto.img_full_rt}">
        <img src="/resources/img/${communityBoardDto.img_full_rt}" alt="Post Image" style="max-width: 200px; max-height: 200px;"/>
    </c:if>
    <c:if test="${empty communityBoardDto.img_full_rt}">
        <p>이미지가 없습니다.</p>
    </c:if>

    ${communityBoardDto.view_cnt}
</div>
</form>
<script>
    let detailButton = document.getElementsByClassName("detail-button");

    detailButton.forEach(button => {
        button.addEventListener("click",function (){
            const documentUserId = this.getAttribute('data-user-id');
        })
    })



</script>

</body>
</html>
