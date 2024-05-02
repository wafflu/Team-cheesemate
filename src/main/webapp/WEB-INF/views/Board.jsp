

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
        <input type="hidden" name ="no" value="${communityBoardDto.no}" >
        <div>
            <select name ="commu_cd" label = "카테고리를 선택해주세요">
                <option  value="${"commu_B"}">블라블라</option>
                <option  value="${"commu_W"}">고민/상담</option>
                <option  value ="${"commu_L"}">연에/썸</option>
            </select>
            <input type = "submit" value="등록" id = "register">
            <input type ="button" value="수정" id = "modify">
            <input type = "button" value="삭제(상태병경)" id = "userStateChange" data-no="${communityBoardDto.no}" data-ur_state="${communityBoardDto.ur_state}">

        </div>
        <div><input id = "title" type ="text" name = "title" value= "${communityBoardDto.title}"></div>
    </div>

    <div class="main">
        <textarea name = "contents">${communityBoardDto.contents}</textarea>
    </div>
    <div class = "footer"></div>
</form>




<script>

    $(document).ready(function(){
        $('#register').on("click",function (){
            let form = $('#form');
            form.attr("action","<c:url value='/community/register'/>");
            form.attr("method","post");
            form.submit();
            alert("등록되었습니다");
        })

        <%--$('#register').on("click",function(){--%>
        <%--    let title = document.getElementById('title');--%>
        <%--    let contents = document.getElementById('contents');--%>
        <%--    let form = $('#form');--%>
        <%--    if(title == null || contents ==null){--%>
        <%--        alert("제목과 내용을 입력하세요");--%>
        <%--        return;--%>
        <%--    }--%>

        <%--    form.attr("action","<c:url value='/community/register'/>");--%>
        <%--    form.attr("method","post");--%>
        <%--    form.submit();--%>
        <%--    alert("등록되었습니다");--%>


        <%--})--%>



        $('#modify').on("click",function(){
            let form = $('#form');
            form.attr("action","<c:url value='/community/modify'/>");
            form.attr("method","post");
            form.submit();
            alert("수정되었습니다");
        })


        $("#userStateChange").click(function(){
            let no = $(this).data('no');
            let ur_state = $(this).data('ur_state');
            console.log(no);
            console.log(ur_state)
            let data = {
                no:no,
                ur_state:ur_state
            }
            $.ajax({
                url: '/community/userStateChange',
                type: 'POST', // 또는 GET
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {


                    // 성공적으로 처리되었을 때 할 작업
                    console.log('성공적으로 처리되었습니다.');
                    console.log(response.message);
                    // 예를 들어, 페이지 리로드 또는 다른 작업을 수행할 수 있습니다.
                    console.log(response.newState)
                    $('#userStateChange').data('ur_state', response.newState);

                },
                error: function(xhr, status, error) {
                    // 처리 중 에러가 발생했을 때 할 작업
                    console.error('에러가 발생했습니다:', error);
                }
            });
            alert("삭제되었습니다");
        })








    })

</script>
</body>
</html>
