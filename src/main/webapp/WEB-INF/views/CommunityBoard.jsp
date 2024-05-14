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
        <p id="heart" data-count-like="${communityBoardDto.like_cnt}" >❤️${communityBoardDto.like_cnt}</p>
        <input type="hidden" id="postNo" value="${communityBoardDto.no}">



         </div>


</form>

<div id="comment_insert">

    <div id = "comment">
        <input type="hidden" id = "post_no" name="post_no" value="${communityBoardDto.no}">
    </div>
    <p>
        <label>&nbsp;&nbsp;댓글 등록</label> <br>
        <textarea id="content" rows="5" cols="80" name="content"
                  maxlength="300" placeholder="댓글은 최대 300자까지 입력가능합니다."></textarea>
    </p>
    <button id ="input_comment" type = submit>댓글 작성</button>
</div>

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
                    "no": postNo

                }),
                contentType: 'application/json',

                success: function (response) {

                    console.log("하트");
                    console.log(response.totalLikeCount);
                    $('#heart').text('❤️ ' + response.totalLikeCount); // HTML 요소에 좋아요 수를 업데이트
                    $('#heart').data('count-like', response.totalLikeCount); // 데이터 속성도 업데이트
                },
                error: function (xhr, status, error) {
                    console.error("좋아요 실패", error.responseText)
                }


            })

        })


        $('#input_comment').click(function () {

            let post_no = $('#post_no').val();
            let contents = $('#content').val();

            if (contents.trim() === "") {
                alert("내용을 입력하세요");
                return;
            }


            $.ajax({
                type: 'post',
                url: '/community/writeComment',
                data: JSON.stringify(
                    {
                        // "nick":nick,
                        // "ur_id":ur_id,
                        "post_no": post_no,
                        "contents": contents
                    }
                ),
                contentType: 'application/json',
                success: function (result) {  //결과 성공 콜백함수
                    let s = " ";
                    s += "<table>"
                    for (let i = 0; i < result.length; i++) {
                        console.log(result[i]);
                        s += "<tr>"
                        s += "<td>" + result[i].post_no + "</td>"
                        s += "<td>" + result[i].no + "</td>"
                        s += "<td>" + result[i].nick + "</td>"
                        s += "<td>" + result[i].contents + "</td>"
                        s += "<td>" + result[i].r_date + "</td>"
                    }
                    s += "</table>";
                    $('article').html(s);
                    alert("dsfsadfdsa");
                },
                error: function (request, status, error) {  //결과 에러 콜백함수
                    console.log(error);
                    alert("에러");
                }
            });
        })
    });
</script>

</body>
</html>
