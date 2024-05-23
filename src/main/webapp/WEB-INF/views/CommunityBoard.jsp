<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 5/3/24
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="fixed/header.jsp" %>
<link rel="stylesheet" href="/css/communityBoard.css">
<div class="maincontent">
<form action = "${pageContext.request.contextPath}/community/read" id ="form" enctype="multipart/form-data">

    <div class="post-content">


            <div class="post-top-container">
                <%--제목--%>
                <div class="post-title">
                    <h1>${communityBoardDto.title}</h1>
                    <input type="button" value="상세" id="modify" class="detail-button" data-user-id="${communityBoardDto.ur_id}">
                </div>
                    <%--사용자 정보--%>
                <div class="post-user">
                    <fmt:formatDate value="${communityBoardDto.r_date}" pattern="yyyy년 MM월 dd일 HH시" /><br>
                    ${communityBoardDto.nick}<br>
                    ${communityBoardDto.addr_name}<br>
                </div>
             </div>

            <%-- 글 정보           --%>
             <div class="post-bottom-container">
            <%--내용--%>
                 <div class="post-content">
                 <p>${communityBoardDto.contents}</p>
                 </div>

            <%--  이미지--%>
                <div class="post-image">
                 <c:forEach items="${imglist}" var="img">
                     <c:if test="${img.imgtype eq 'w'}">
                         <img src="/img/display?fileName=${img.img_full_rt}" style="width: 148px; height: 148px;">
                     </c:if>
                 </c:forEach>
                </div>
                <%-- 반응--%>
                <div class="post-reaction">
                    <p id="heart" data-count-like="${communityBoardDto.like_cnt}" >❤️${communityBoardDto.like_cnt}</p>
                    <p>  </p>
                    <input type="hidden" id="postNo" value="${communityBoardDto.no}">
                    <p>💬 ${communityBoardDto.comment_count}</p>
                    <p>  </p>
                    <p>👁️${communityBoardDto.view_cnt}</p>
                </div>
             </div>





            <div style="display:none;"  id ="alertDiv">
                <p id = "edit">수정/삭제</p>
                <p id="alert">신고</p>

            </div>

    <%--    commmunityHeart--%>

    <%--        <p id="heart" data-count-like="${communityHeartDto.countLike}">❤️</p>--%>

    </div>
    </form>
<%--          댓글 컨테이너  --%>
        <div class="post-comment-container" id="comment_insert">
                <div id = "comment">
                    <input type="hidden" id = "post_no" name="post_no" value="<c:out value='${communityBoardDto.no}'/>">


                    <textarea class="comment-content" id="content" rows="5" cols="80" name="content"
                              maxlength="300" placeholder="댓글은 최대 300자까지 입력가능합니다.">
                    </textarea>
                    <input type = submit id ="input_comment" value="등록">


                </div>

            <input type="hidden" id = "no" name="post_no" value="${commentDto.post_no}">
            <div id = comment-container></div>
        </div>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script>


    function initComment(){
        document.getElementById("content").value="";
    }

    $(document).ready(function () {
        loadComments($('#post_no').val());


        $('.detail-button').on("click", function () {
            $('#alertDiv').show();

        })
        $('#edit').on("click", function (e) {
            e.preventDefault(); // 기본 링크 동작을 막음
            if (confirm('이 게시물을 수정하시겠습니까?')) {
                // 확인 버튼을 클릭하면 수정 페이지로 이동
                window.location.href = '/community/edit?no=${communityBoardDto.no}';
            } else {
                // 취소 버튼을 클릭하면 읽기 페이지로 이동
                window.location.href = '/community/read?no=${communityBoardDto.no}';
            }

        })

        $('#heart').on("click", function () {
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
                    if (xhr.status === 401) {
                        alert("로그인 먼저 해주세요");
                    } else if (xhr.status === 500) {
                        alert("서버 에러가 발생했습니다.");
                    } else {
                        alert("좋아요 실패: " + xhr.responseText);
                    }
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
                cache: false,
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify(
                    {
                        "post_no": post_no,
                        "contents": contents
                    }
                ),

                dataType: 'json',
                success: function (comments) {
                    console.log(comments);
                    loadComments(post_no);
                    initComment();

                },
                error: function (xhr,status,error) {
                    if(xhr.status===200){
                        alert("로그인을 먼저 해주세요.");
                    } else if (xhr.status === 500) {
                        alert("서버 에러가 발생했습니다.");
                    } else {
                        alert("댓글을 작성하는데 실패했습니다.: " + xhr.responseText);
                    }
                }
            });
        })


        function loadComments(postId) {
            $.ajax({
                url: '/community/comments?postId=' + postId,
                type: 'GET',
                cache: false,
                dataType: 'json',
                // data:{post_no:postId},
                success: function (comments) {
                    const commentsContainer = $('#comment-container');
                    commentsContainer.empty();

                    let str = "";
                    comments.forEach(comment => {
                        console.log(
                            comment.contents
                        )
                        str += `<div class="comment">`;
                        str += `<p>` + comment.nick + `</p>`;
                        str += `<p class="comment-contents">` + comment.contents + `</></p>`;
                        str += `<p>` + moment(comment.r_date).calendar() + `</p>`;
                        str += `</div>`;

                    });
                    commentsContainer.append(str);


                },
                error: function () {
                    alert('댓글을 불러오는 데 실패했습니다.');
                }
            });
        }


    });
</script>

</body>
</html>
