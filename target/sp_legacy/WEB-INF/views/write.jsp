<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 글쓰기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        h2{
            align-items: center;
        }
        textarea{
            height: 303px;
        }
        .comment-form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .comment-form label {
            font-weight: bold;
        }

        .comment-form input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .comment-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
        }

        .comment-form input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>이벤트 글쓰기</h2>
    <form id="form" action="/event/write" method="post">
        <div class="form-group">
            <label for="title">제목</label>
            <!-- placeholder 속성 입력한 데이터가 없는 경우 배경으로 나타난다.실제적으로 입력을 100자까지로 지정 -->
            <!-- required 속성을 설정하면 필수입력 사항이된다. -->
            <!-- pattern 속성을 이용한 정규표현식으로 데이터의 유효성 검사를 할 수 있다. -->
            <input type="text" class="form-control" id="title"
                   placeholder="제목 입력(2-100)" name="title"
                   maxlength="100" required="required"
                   pattern=".{2,100}" value="${dto.title}" ${readonly}>
        </div>
        <div class="form-group">
            <label>내용</label>
            <!--  여러줄의 데이터를 입력하고 하고자 할때 textarea 태그를 사용한다. -->
            <!--  textarea 안에 있는 모든 글자는 그대로 나타난다. 공백문자, tag, enter -->
            <textarea class="form-control" rows="5" id="contents"
                      name="contents" placeholder="내용 작성" ${readonly}>${dto.contents}</textarea>
        </div>
        <div class="form_section_content">
            <input type="file" id ="fileItem" name='uploadFile' style="height: 30px;" multiple>
        </div>
        <div id = "uploadResult"></div>
        <div class="form-group">
            <label>작성자</label>
            <input type="text" class="form-control" id="nickname"
                   placeholder="작성자(2자-10자)" name="EventDto.ad_id" ${readonly} value=${ad_id==null?dto.ad_id:ad_id}>
        </div>
        <div class="form-group">
            <label>시작일</label>
            <input type="date" class="form-control" id="s_date"
                   name="s_date" ${readonly} value=<fmt:formatDate value="${dto.s_date}" pattern="yyyy-MM-dd" />>
        </div>
        <div class="form-group">
            <label>종료일</label>
            <input type="date" class="form-control" id="e_date"
                    name="e_date" ${readonly} value=<fmt:formatDate value="${dto.e_date}" pattern="yyyy-MM-dd" />>
        </div>
        <div class="form-group">
            <select id="user_select" name="evt_cd" label="회원 분류" ${readonly==readonly?disabled:""} />
            <option value="A">전체</option>
            <option value="N">신규</option>
            <option value="R">복귀</option>
            </select>
        </div>
        <div class="form-group">
            <label>경품</label>
            <input type="text" class="form-control" id="prize"
                   placeholder="경품(2자-10자)" name="prize" ${readonly} value=${dto.prize}>
        </div>
        <c:choose>
            <c:when test="${requestScope['javax.servlet.forward.servlet_path']=='/read'}">
                <button type="button" class="btn btn-default" onclick="location.href='modify?evt_no=${dto.evt_no}'">수정</button>
                <button type="button" class="btn btn-default" onclick="location.href='remove?evt_no=${dto.evt_no}'">삭제</button>
            </c:when>
            <c:otherwise>
                <c:if test="${requestScope['javax.servlet.forward.servlet_path']!='/read'}">
                    <div id= "submitform" class="btn btn-default">123등록</div>
                </c:if>
            </c:otherwise>
        </c:choose>
    </form>

    <a href="write" class="btn btn-default">쓰기</a>
    <script src="/resources/js/img.js"></script>
    <script>
        document.getElementById("submitform").onclick = function (){
            // $("#submitform").on("click","#submitform",function() {
                alert("asdasd");
                let form = document.getElementById("form");
                let newInput = document.createElement("input");
                let newInput1 = document.createElement("input");
                newInput.type = 'hidden'; // 타입은 hidden으로 설정합니다.
                newInput.name = 'img_full_rt'; // 이름을 설정합니다.
                newInput.value = imginfo[0].img_full_rt; // 이름을 설정합니다.
                // newInput.value = fileCallPath;
                form.appendChild(newInput);
                newInput1.type = 'hidden';
                newInput1.name = 'imgname';
                newInput1.value = imginfo[0].o_name + imginfo[0].e_name;
                form.appendChild(newInput1);
                console.log(form)
                form.submit();
            }
    </script>

<%--        // function writeData() {--%>
<%--        //     var title = document.getElementById("title").value;--%>
<%--        //     var contents = document.getElementById("contents").value;--%>
<%--        //     var ad_id = document.getElementById("nickname").value;--%>
<%--        //     var s_date = document.getElementById("s_date").value;--%>
<%--        //     var e_date = document.getElementById("e_date").value;--%>
<%--        //     var evt_cd = document.getElementById("user_select").value;--%>
<%--        //     var prize = document.getElementById("prize").value;--%>
<%--        //     var eventDto = {--%>
<%--        //         title: title,--%>
<%--        //         contents: contents,--%>
<%--        //         ad_id: ad_id,--%>
<%--        //         s_date: s_date,--%>
<%--        //         e_date: e_date,--%>
<%--        //         evt_cd: evt_cd,--%>
<%--        //         prize: prize--%>
<%--        //     };--%>
<%--        //     $.ajax({--%>
<%--        //         url : "event/write",--%>
<%--        //         type: "POST",--%>
<%--        //         contentType: 'application/json',--%>
<%--        //         dataType: "json",--%>
<%--        //         data: JSON.stringify(eventDto),--%>
<%--        //         success: function (data){--%>
<%--        //--%>
<%--        //         }--%>
<%--        //         })--%>
<%--        // }--%>
</body>
</html>