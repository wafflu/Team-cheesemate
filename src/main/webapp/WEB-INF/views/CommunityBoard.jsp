<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<style>
    /*.title-container{*/
    /*    display: flex;*/
    /*}*/
</style>

<form action = "${pageContext.request.contextPath}/community/read" id ="form" enctype="multipart/form-data">
    <div class="post-content">

        <%--사용자 정보--%>
        <div>
            ${communityBoardDto.nick}
            <fmt:formatDate value="${communityBoardDto.r_date}" pattern="yyyy년 MM월 dd일 HH시" />
            ${communityBoardDto.addr_name}
        </div>

        <%--제목--%>
        <div class="title-container">
            <h1>${communityBoardDto.title}</h1>
            <p class="detail-button" data-user-id="${communityBoardDto.ur_id}">✏️편집✏️</p>
        </div>

        <p>${communityBoardDto.contents}</p>

        <c:forEach items="${imglist}" var="img">
            <c:if test="${img.imgtype eq 'w'}">
                <img src="/img/display?fileName=${img.img_full_rt}" style="width: 148px; height: 148px;">
            </c:if>
        </c:forEach>

        <div style="display:none;"  id ="alertDiv">
            <p id = "edit">수정/삭제</p>
            <p id="alert">신고</p>

        </div>

        <%--    commmunityHeart--%>

        <%--        <p id="heart" data-count-like="${communityHeartDto.countLike}">❤️</p>--%>
        <p id="heart" data-count-like="${communityBoardDto.like_cnt}" >❤️${communityBoardDto.like_cnt}</p>
        <input type="hidden" id="postNo" value="${communityBoardDto.no}">
        <p>💬 ${communityBoardDto.comment_count}</p>
        <p>👁️${communityBoardDto.view_cnt}</p>
    </div>




</form>

<div id="comment_insert">

    <div id = "comment">
        <input type="hidden" id = "post_no" name="post_no" value="<c:out value='${communityBoardDto.no}'/>">;
    </div>
    <p>
        <textarea id="content" rows="5" cols="80" name="content"
                  maxlength="300" placeholder="댓글은 최대 300자까지 입력가능합니다."></textarea>
    </p>
    <button id ="input_comment" type = submit>댓글 작성</button>
</div>

<input type="hidden" id = "no" name="post_no" value="${commentDto.post_no}">
<div id = comment-container>

</div>

<script>

    let uploadImage = (function() {
        let imginfo = [];

        <c:forEach items="${imglist}" var="img">
        <c:if test="${img.imgtype eq 'r'}">
        imginfo.push(
            {
                "file_rt" : "${img.file_rt}",
                "o_name" : "${img.o_name}",
                "e_name" : "${img.e_name}"
            }
        )
        </c:if>
        </c:forEach>

        return {
            getImgInfo: function() {
                return imginfo;
            }
        };
    })();

    $(document).ready(function() {


        loadComments($('#post_no').val());


        $('.detail-button').on("click",function(){
            $('#alertDiv').show();

        })
        $('#edit').on("click",function (){
            var confirmation = confirm("이 게시물을 수정하시겠습니까?");
            alert(confirmation);
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

                    $('#heart').text('❤️ ' + response.totalLikeCount); // HTML 요소에 좋아요 수를 업데이트
                    $('#heart').data('count-like', response.totalLikeCount); // 데이터 속성도 업데이트
                },
                error: function (xhr, status, error) {
                    if(xhr.status ===401){
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
                headers : { "content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify(
                    {
                        "post_no": post_no,
                        "contents": contents
                    }
                ),

                dataType : 'json',
                success:function (comments) {
                    console.log(comments);
                    loadComments(post_no);
                },
                error:function (){
                    alert('댓글을 작성하는데 실패했습니다..');
                }
            });
        })


        function loadComments(postId){
            $.ajax({
                url:'/community/comments?postId='+postId,
                type:'GET',
                cache: false,
                dataType:'json',
                // data:{post_no:postId},
                success:function (comments) {
                    const commentsContainer = $('#comment-container');
                    commentsContainer.empty();

                    let str = "";
                    comments.forEach(comment => {
                        str+=`<div>`;
                        str+=`<p>`+comment.contents + `</p>`;
                        str+=`<p>`+comment.nick+ `</p>`;
                        str+=`<p>`+moment(comment.r_date).calendar()+`</p>`;
                        str+=`</div>`;

                    });
                    commentsContainer.append(str);


                },
                error:function (){
                    alert('댓글을 불러오는 데 실패했습니다.');
                }
            });
        }

    });
</script>


<script src="/js/img.js"></script>

<%@include file="fixed/footer.jsp"%>