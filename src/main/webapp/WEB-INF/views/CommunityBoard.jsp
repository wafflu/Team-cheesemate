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
                    <h1 id="title">${communityBoardDto.title}</h1>
                        <div class="button-etc">
                    <input type="button" value="상세" id="modify" class="detail-button" data-user-id="${communityBoardDto.ur_id}" onclick="showPopup()">
                    <input type="button" value="목록" id="list" class="list-button">
                        </div>
                </div>
                <%--사용자 정보--%>

                <div class="post-user">

                    <img src="/img/display?fileName=${profile.img_full_rt}" class="profileimg">
                    <div class ="post-top">
                        <div class="post-user-nonImage">
                            <fmt:formatDate value="${communityBoardDto.r_date}" pattern="yyyy년 MM월 dd일 HH시" /><br>
                            ${communityBoardDto.nick}<br>
<%--                            ${communityBoardDto.addr_name}<br>--%>
                        </div>
                        <div class="post-reaction-wrapper">
                        <div class="post-reaction">
                            <p class="heartTotalCount" data-count-like="${communityBoardDto.like_cnt}">
                                <span class="icon"></span><span class="count">${communityBoardDto.like_cnt}</span>
                            </p>
                            <p></p>
                            <input type="hidden" id="postNo" value="${communityBoardDto.no}">
                            <p class="commentTotalCount">
                                <span class="icon"></span><span class="count">${communityBoardDto.comment_count}</span>
                            </p>
                            <p class="viewTotalCount">
                                <span class="icon"></span><span class="count">${communityBoardDto.view_cnt}</span>
                            </p>
                        </div>
                        </div>
                    </div>
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
                            <img src="/img/display?fileName=${img.img_full_rt}" style="width: 456px; height: 456px;">
                        </c:if>
<%--                        <c:forEach items="${imglist}" var="img">--%>
<%--                            <c:if test="${img.post_no eq communityBoardDto.no}"> <!-- 특정 글에 대한 이미지만 출력 -->--%>
<%--                                <img src="/img/display?fileName=${img.img_full_rt}" style="width: 148px; height: 148px;">--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
                    </c:forEach>
                </div>

                <%-- 반응--%>
                <div class="post-reaction">
                    <p id="heart" class="heartTotalCount" data-count-like="${communityBoardDto.like_cnt}">
                        <span class="icon"></span><span class="count">${communityBoardDto.like_cnt}</span>
                    </p>
                    <p></p>
                    <input type="hidden" value="${communityBoardDto.no}">
                    <p class="commentTotalCount">
                        <span class="icon"></span><span class="count">${communityBoardDto.comment_count}</span>
                    </p>
                    <p class="viewTotalCount">
                        <span class="icon"></span><span class="count">${communityBoardDto.view_cnt}</span>
                    </p>

                </div>
            </div>


            <div id="alertDiv" class="popup">
                <div class="popup-content">
                    <span class="close" onclick="closePopup()">&times;</span>
                    <p id="edit">수정/삭제</p>
                    <p id="alert">신고</p>
                </div>
            </div>


        </div>
    </form>
    <%--          댓글 컨테이너  --%>
    <div class="post-comment-container" id="comment_insert">
        <div id = "comment">
            <input type="hidden" id = "post_no" name="post_no" value="<c:out value='${communityBoardDto.no}'/>">


            <textarea class="comment-content" id="content" rows="5" cols="80" name="content" maxlength="300" placeholder="댓글은 최대 300자까지 입력가능합니다."></textarea>
            <input type = submit id ="input_comment" value="등록">


        </div>

        <input type="hidden" id = "no" name="post_no" value="${commentDto.post_no}">
        <div id = comment-container></div>
    </div>




</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="/js/Etc.js"></script>
<script>
    function showPopup() {
        document.getElementById('alertDiv').style.display = 'block';
    }

    function closePopup() {
        document.getElementById('alertDiv').style.display = 'none';
    }

    function initComment() {
        document.getElementById("content").value = "";
    }

    document.getElementById('list').addEventListener('click', function() {
        window.location.href = '${pageContext.request.contextPath}/community/list';
    });



    let uploadImage = (function () {
        let imginfo = [];

        <c:forEach items="${imglist}" var="img">
        <c:if test="${img.imgtype eq 'r'}">
        imginfo.push(
            {
                "file_rt": "${img.file_rt}",
                "o_name": "${img.o_name}",
                "e_name": "${img.e_name}"
            }
        )
        </c:if>
        </c:forEach>

        return {
            getImgInfo: function () {
                return imginfo;
            }
        };
    })();

    $(document).ready(function () {
        // 에러 메시지가 있을 경우 alert로 출력
        <c:if test="${not empty errorMessage}">
        alert("${errorMessage}");
        </c:if>


        let imgInfo = cssImage.getImgInfo();
        $(".heartTotalCount .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['Like2'] + "')");
        $(".commentTotalCount .icon").css("background-image","url('/img/display?fileName=" + imgInfo['chat'] + "')");
        $(".viewTotalCount .icon").css("background-image","url('/img/display?fileName=" + imgInfo['person'] + "')");
        loadComments($('#post_no').val());

        $('#comment-container').on('click','.comment',function(){
            let commentNo = $(this).closest('.comment').find('p#no').text();
            let postNo = $('#post_no').val();
            let a = $(this).index();
            console.log("a : "+a)
            getComment(postNo,commentNo);
        })





    $('.detail-button').on("click", function () {
            $('#alertDiv').show();

        })
        $('#edit').on("click", function (e) {
            e.preventDefault(); // 기본 링크 동작을 막음\
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
                dataType: 'json',
                success: function (response) {
                    console.log("리스폰스" + response);
                    console.log("리스폰스2" + response.countLike);
                    if (response && response.countLike !== undefined) {

                        console.log("하트" + response.countLike);
                        console.log("하트 총합" + response.totalLike);
                        // $('#heart').text('❤️ ' + response.countLike); // HTML 요소에 좋아요 수를 업데이트
                        // $('.heartTotalCount').data('count-like', response.totalLike); // 데이터 속성도 업데이트
                        // $('.heartTotalCount').text(response.countLike);
                        // Update the like count
                        let likeCountElement = $('.heartTotalCount');
                        likeCountElement.data('count-like', response.totalLike);
                        likeCountElement.find('.count').text(response.countLike);
                    } else {
                        console.error("응답에 총 좋아요 수가 없습니다:", response);
                    }
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
                error: function (xhr, status, error) {
                    if (xhr.status === 200) {
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


                        str += `<div class="comment" >`;
                        str += `<p class="comment-nick">` + comment.nick + `</p>`;
                        str += `<p class="comment-contents">` + comment.contents + `</></p>`;
                        str += `<p class="comment-r_date">` + remaindTime(new Date(comment.r_date)) + `</p>`;
                        str += `<p type="hidden" id="no" style="display: none">` +comment.no + `</p>`;
                        str += `</div>`;

                    });
                    commentsContainer.append(str);
                    console.log(comments[0]);
                    const commentDivs = document.getElementsByClassName('comment');
                    if (commentDivs.length > 0) {
                        const commentDiv = commentDivs[0];
                        let no1 = commentDiv.dataset.no;
                        let no2 = commentDiv.getAttribute('data-no');
                        console.log("no1: " + no1);
                        console.log("no2: " + no2);
                    }


                },
                error: function () {
                    alert('댓글을 불러오는 데 실패했습니다.');
                }
            });
        }


        //댓글 하나를 읽어오는 함수
        function getComment(post_no,comment_no){

            $.ajax({
                url:'/community/getComment',
                type:'GET',
                data:{
                    "post_no": post_no,
                    "no": comment_no
                },
                dataType: 'json',
                success: function (response) {
                    console.log(response);

                    let comment = response.comment;
                    let sessionUserId = response.sessionUserId;

                    // 동일 아이디일 때 수정/삭제
                    if (comment.ur_id === sessionUserId) {
                        if (confirm("편집하시겠습니까?")) {
                            // 편집 가능하게 처리
                            editComment(comment);
                        }
                    } else {
                        // 다른 아이디일 때 대댓글 처리
                        replyToComment(comment);
                    }
                },
                error:function (xhr){
                    if (xhr.status === 401) {
                        alert("로그인 먼저 해주세요");
                    } else if (xhr.status === 403) {
                        alert("접근 권한이 없습니다.");
                    } else {
                        alert('댓글을 불러오는 데 실패했습니다.');
                    }
                }
            });

        }


        function editComment(existingComment){
            console.log("existingComment");
            console.log(existingComment);
            console.log("this");
            // console.log("comment의 no" + commentDiv);
            // $("#comment").children(this.index()).remove();
            let a = $(this).index();
            console.log(a)
            $("#comment").children().eq(a).remove();

            //
            //
            // let str = "";
            //     str += `<p class="comment-nick">` + comment.nick + `</p>`;
            //     str += `<textarea class="comment-edit-contents">` + comment.contents + `</textarea>`;
            //     str += `<p class="comment-r_date">` + remaindTime(new Date(comment.m_date)) + `</p>`;
            //     str += `<p type="hidden" id="no">` +comment.no + `</p>`;
            //
            // $(this).append(str);
        }



    });


</script>
<%@ include file="fixed/footer.jsp" %>

