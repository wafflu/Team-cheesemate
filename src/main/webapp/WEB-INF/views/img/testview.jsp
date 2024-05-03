<%--
  Created by IntelliJ IDEA.
  User: jehyeon
  Date: 4/26/24
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
    <c:forEach items="${list}" var="img">
        <a href="/img/testdetail?no=${img.no}">
            <img src="/img/display?fileName=${img.img_full_rt}" style="width: 148px; height: 148px;">
        <p>작성자 : ${img.seller_nick}</p>
        <p>제목 : ${img.title}</p>
        <p>가격 : ${img.reg_price}</p>
        <p>등록일 : ${img.r_date}</p>
        <p>거래지역 : ${img.addr_name}</p>
        </a>
    </c:forEach>


<script>

    function showThumbnailImage(imgDto){
        /* 전달받은 데이터 검증 */
        if(imgDto.length == 0){return}

        let uploadResult = $("#uploadResult");
        let str = "";
        let obj = imgDto;
        let fileCallPath = encodeURIComponent(obj.filert + "/s_" + obj.u_name + "_" + (obj.o_name+obj.e_name));
        str += "<img src='/display?fileName=" + fileCallPath +"'>";

        uploadResult.append(str);
    }
</script>
</body>
</html>
