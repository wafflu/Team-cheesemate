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
        <img src="/display?fileName=${img.filert}/${img.u_name}${img.o_name}${img.e_name}">
        <p>${img.s_title}</p>
        <p>${img.s_price}</p>
        <p>${img.s_r_date}</p>
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
