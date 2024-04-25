

<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 4/25/24
  Time: 8:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form action = "${pageContext.request.contextPath}/register" method="get">
    <div class="header">
        <div>
            <select name ="category" label = "카테고리를 선택해주세요">
                <option>블라블라</option>
                <option>고민/상담</option>
                <option>연에/썸</option>
            </select>
            <input type = "submit" value="등록" >
        </div>
        <div><input id = "title" type ="text" name = "title" value= "${postDto.title}"></div>
    </div>
    <div class ="main" >
        <textarea>${postDto.contents}</textarea>
    </div>
    <div class = "footer">

    </div>
</form>
<script>
    function writeCheck(){
        let title = document.getElementById("title")
        let contents = document.getElementsByTagName("textarea")
        if(title ==null || contents ==null){
            alert("제목을 입력하셔야 합니다.");
            return false;
        }
        return true;
    }

</script>
</body>
</html>

