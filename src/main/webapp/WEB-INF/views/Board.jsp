

<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 4/25/24
  Time: 8:50 AM
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
</head>
<body>
<form action = "" id ="form">
    <div class="header">
        <input type="hidden" name ="no" value="${CommunityBoardDto.no}" >
        <div>
            <select name ="commu_cd" label = "카테고리를 선택해주세요">
                <option  value="${"commu_B"}">블라블라</option>
                <option  value="${"commu_W"}">고민/상담</option>
                <option  value ="${"commu_L"}">연에/썸</option>
            </select>
            <input type = "submit" value="등록" id = "register">


            <input type ="button" value="수정" id = "modify">

            <a href = "${pageContext.request.contextPath}/remove?no=${CommunityBoardDto.no}" >
            <input type = "button" value="삭제" id = "remove">
            </a>

        </div>
        <div><input id = "title" type ="text" name = "title" value= "${CommunityBoardDto.title}"></div>
    </div>



    <div class="main">
        <textarea name = "contents">${CommunityBoardDto.contents}</textarea>
    </div>

    <div class = "footer">

    </div>
</form>

<%--<form method="CommunityBoard" action = "${pageContext.request.contextPath}/register" enctype="multipart/form-data">--%>
<%--<div class = "form-group" style = "height:150px; width:200px;">--%>
<%--    <label>이미지 파일 첨부</label>--%>
<%--    <input type="file" name="imgFile" onchange="readURL(this);"/>--%>
<%--    <img id="preview" src="#" width = 200 height = 150 alt = "선택된 이미지가 있습니다" style="align-content: flex-end;">--%>

<%--</div>--%>
</form>

<script>


    $(document).ready(function(){
        $('#register').on("click",function (){
            let form = $('#form');
            form.attr("action","<c:url value='register'/>");
            form.attr("method","post");
            form.submit();
        })




        $('#modify').on("click",function(){
            let form = $('#form');
            form.attr("action","<c:url value='modify'/>");
            form.attr("method","post");
            form.submit();
        })





    })



    // function readURL(input){
    //     const file = input.files[0];
    //     console.log(file)
    //     if(file !== ''){
    //         const reader = new FileReader();
    //         reader.readAsDataURL(file);
    //         reader.onload = function(e){
    //             console.log(e.target)
    //             console.log(e.target.result)
    //                 $('#preview').attr('src',e.target.result);
    //         }
    //     }
    // }
</script>
</body>
</html>

