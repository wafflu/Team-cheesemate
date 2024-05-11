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
        <div style="display:none;"  id ="alertDiv">
            <p id = "edit">수정/삭제</p>
            <p id="alert">신고</p>

        </div>

<%--    commmunityHeart--%>

<%--        <p id="heart" data-count-like="${communityHeartDto.countLike}">❤️</p>--%>
        <p id="heart" data-count-like="${communityHeartDto.countLike}" >❤️${communityHeartDto.countLike}</p>
        <input type="hidden" id="postNo" value="${communityBoardDto.no}">






    </div>


</form>
<script>
    $(document).ready(function() {
        $('.detail-button').on("click",function(){
            $('#alertDiv').show();

        })
        $('#edit').on("click",function (){
            window.location.href = '/community/edit?no=${communityBoardDto.no}';

        })

        $('#heart').on("click",function() {
            const postNo = $('#postNo').val();



            if (!postNo) {
                console.error("데이터가 올바르지 않습니다.");
                return; // 데이터가 없으면 함수 종료
            }

            $.ajax({
                url: '/community/doLike',
                type: 'PATCH',
                data: JSON.stringify({
                    "post_no": postNo,

                }),
                contentType: 'application/json',
                success: function (response) {


                    if(response.hasOwnProperty('countLike')) {
                        $('#heart').data('count-like',response.countLike);
                        $('#heart').text('❤️ ' + response.countLike);


                    }
                    console.log("하트");
                    console.log(response.countLike);
                },
                error: function (xhr, status, error) {
                    console.error("좋아요 실패", xhr.responseText)
                }


            })



            //   $.ajax({
            //       url:'/community/countLike',
            //       type:'PATCH',
            //       data:JSON.stringify({
            //           "countLike":countLike
            //           }),
            //       contentType:'application/json',
            //       success:function(response){
            //           console.log(response);
            //
            //           displayCntLike(response)
            //       },
            //       error:function (xhr,status,error){
            //           console.log("error");
            //       }
            //
            //   });
            // });
            //
            //
            // function displayCntLike(data){
            //     let totalLikes = 0;
            //
            //     data.forEach((item)=>{
            //         totalLikes += item.countLike;
            //     });
            //     $('#heart').data('count-like',totalLikes)
            //
            //  }
        })
    })
</script>

</body>
</html>
