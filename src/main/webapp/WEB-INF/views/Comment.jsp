<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 5/14/24
  Time: 10:08 AM
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
    <form id = "comment_insert" method="post">
    <div>
        ${commentDto.nick}
        <input type="hidden" data-comment-ur_id ="${commentDto.ur_id}">
    </div>
<%--    게시글 정보--%>
    <div>
        <input type = "hidden" name = "post_no" value="${communityBoardDto.no}" data-comment-post_no = "${communityBoardDto.no}">
        <input type = "hidden" name = "no" data-comment-no = "${commentDto.no}">
        ${commentDto.r_date}
    </div>
    <div class="main">
        <textarea name="contents" id ="contents">
        ${commentDto.contents}
        </textarea>
    </div>
        <button id="comment_register" type="submit">댓글 달기</button>


    </form>

    <article>

    </article>



<script>
    //1. '댓글달기' 버튼을 클릭한다.
    //2.json으로 전달할 파라미터 변수를 선언한다.
        //post_no, no, psn, nick, ur_id, contents가 필요하다.
    //ur_id가 없을시 로그인 후 이용
    //conten가 없을 시 내용입력 부탁
$('#comment_register').click(function (){
    <%--let nick = ${commentDto.nick};--%>
    <%--let ur_id= ${commentDto.ur_id};--%>
    let post_no = $('input[name="post_no"]').val();
    let no = $('input[name="no"]').val();
    let contents = $('#contents').val();

    if(ur_id === ("" || null)){
        alert("로그인 후 이용해주세요");
        return;
    }else if(contents === ("" || null)){
        alert ("내용을 입력해주세요");
    }

    $.ajax({
        type:'post',
        url:'/community/writeComment',
        data:JSON.stringify(
            {
                // "nick":nick,
                // "ur_id":ur_id,
                "post_no":post_no,
                "no":no,
                "contents":contents
            }
        ),
        contentType:'application/json',
        success: function(result){  //결과 성공 콜백함수
           let s = " ";
           s+="<table>"
               for(let i = 0; i <result.length; i++){
                   console.log(result[i]);
                   s+="<tr>"
                   s+="<td>" + result[i].post_no + "</td>"
                   s+="<td>" + result[i].no+"</td>"
                   s+="<td>" + result[i].nick+"</td>"
                   s+="<td>" + result[i].contents+"</td>"
                   s+="<td>" + result[i].r_date+"</td>"
           }
           s+="</table>";
           $('article').html(s);

        },
        error:function(request,status,error){  //결과 에러 콜백함수
            console.log(error);
            alert("에러");
        }
    })

})
</script>
</body>
</html>
