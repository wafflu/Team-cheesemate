

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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Example</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<form id="form" enctype="multipart/form-data">
    <div class="header">
        <input type="hidden" name="no" value="${communityBoardDto.no}">
        <div>
            <select name="commu_cd" label="카테고리를 선택해주세요">
                <option value="commu_B">블라블라</option>
                <option value="commu_W">고민/상담</option>
                <option value="commu_L">연애/썸</option>
            </select>
            <input type="hidden" name="commu_name" id="commu_name" value="">

            <c:if test="${empty communityBoardDto.no}">
                <input type="submit" value="등록" id="register">
            </c:if>

            <c:if test="${not empty communityBoardDto.no}">
                <input type="button" value="수정" id="modify">
                <input type="button" value="삭제" id="userStateChange" data-no="${communityBoardDto.no}" data-ur_state="${communityBoardDto.ur_state}">
            </c:if>
        </div>
        <div>
            <input id="title" type="text" name="title" value="${communityBoardDto.title}">
        </div>
<%--        <div class="form-group" style="height: 150px; width: 200px;">--%>
<%--            <label>이미지 파일 첨부</label>--%>
<%--            <input type="file" name="image" id="image"/>--%>
<%--            <img id="preview" src="#" alt="Image preview" style="max-width: 200px; max-height: 200px;"/>--%>
<%--        </div>--%>
    </div>
    <div class="main">
        <textarea name="contents" id="contents">${communityBoardDto.contents}</textarea>
    </div>
    <div class="footer"></div>
</form>

<script>
    $(document).ready(function(){
        $('#register').on("click", function (e){
            e.preventDefault(); // 기본 폼 제출을 막음

            if ($('#title').val() === '' || $('#contents').val() === '') {
                alert('제목과 내용을 작성하였는지 확인해주세요.');
                return false; // 유효성 검사 실패 시 폼 제출 중단
            }

            let no = "${communityBoardDto.no}";
            let ur_id = "${communityBoardDto.ur_id}";
            let addr_cd = "${communityBoardDto.addr_cd}";
            let addr_no = "${communityBoardDto.addr_no}";
            let addr_name = "${communityBoardDto.addr_name}";
            let nick = "${communityBoardDto.nick}";
            let commu_cd = $('select[name="commu_cd"]').val();
            let commu_name = commuName(commu_cd);
            let title = $('input[name="title"]').val();
            let contents = $('textarea[name="contents"]').val();

            let communityBoardDto = {
                "no": no,
                "ur_id": ur_id,
                "addr_cd": addr_cd,
                "addr_no": addr_no,
                "commu_cd": commu_cd,
                "commu_name": commu_name,
                "addr_name": addr_name,
                "title": title,
                "contents": contents,
                "nick": nick
            };

            let map = {
                "communityBoardDto": communityBoardDto
            };
            let jsonString = JSON.stringify(map);

            $.ajax({
                type: 'POST',
                url: '/community/register',
                contentType: 'application/json; charset=utf-8',
                data: jsonString,
                dataType: 'text',

                success: function (data) {
                    console.log(data);
                    window.location.replace(data);
                },
                error: function (xhr, status, error) {
                    console.log(xhr.responseText);
                    alert("애러발생",);
                }
            });
        });

        $('#modify').on("click", function(e){

            e.preventDefault(); // 기본 폼 제출을 막음

            if ($('#title').val() === '' || $('#contents').val() === '') {
                alert('제목과 내용을 작성하였는지 확인해주세요.');
                return false; // 유효성 검사 실패 시 폼 제출 중단
            }

            let no = "${communityBoardDto.no}";
            let ur_id = "${communityBoardDto.ur_id}";
            let addr_cd = "${communityBoardDto.addr_cd}";
            let addr_no = "${communityBoardDto.addr_no}";
            let addr_name = "${communityBoardDto.addr_name}";
            let nick = "${communityBoardDto.nick}";
            let commu_cd = $('select[name="commu_cd"]').val();
            let commu_name = commuName(commu_cd);
            let title = $('input[name="title"]').val();
            let contents = $('textarea[name="contents"]').val();

            let communityBoardDto = {
                "no": no,
                "ur_id": ur_id,
                "addr_cd": addr_cd,
                "addr_no": addr_no,
                "commu_cd": commu_cd,
                "commu_name": commu_name,
                "addr_name": addr_name,
                "title": title,
                "contents": contents,
                "nick": nick
            };
            let map = {
                "communityBoardDto": communityBoardDto
            };
            let jsonString = JSON.stringify(map);

            $.ajax({
                type: 'POST',
                url: '/community/modify',
                contentType: 'application/json; charset=utf-8',
                data: jsonString,
                dataType: 'text',

                success: function (data) {
                    console.log(data);
                    alert("글을 수정하였습니다.");
                    window.location.replace(data);
                },
                error: function (xhr, status, error) {
                    console.log(xhr.responseText);
                    alert(xhr.responseText);
                }
            });
        });
            <%--let form = $('#form');--%>
            <%--form.attr("action", "<c:url value='/community/modify'/>");--%>
            <%--form.attr("method", "post");--%>
            <%--form.submit();--%>
            <%--alert("수정되었습니다");--%>


        $('#userStateChange').click(function(){
            let no = $(this).data('no');
            let ur_state = $(this).data('ur_state');
            console.log(no);
            console.log(ur_state);

            let data = {
                no: no,
                ur_state: ur_state
            };

            $.ajax({
                url: '/community/userStateChange',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    console.log('성공적으로 처리되었습니다.');
                    console.log(response.message);
                    $('#userStateChange').data('ur_state', response.newState);
                    window.location.href = '/community/list';
                },
                error: function(xhr, status, error) {
                    console.error('에러가 발생했습니다:', error);
                }
            });
            alert("삭제되었습니다");
        });

        $('#image').change(function(){
            readURL(this);
        });

        function readURL(input){
            if(input.files && input.files[0]){
                let reader = new FileReader();
                reader.onload = function (e){
                    $('#preview').attr('src', e.target.result);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }

        function commuName(commuCd) {
            if (commuCd === 'commu_B') {
                return "블라블라";
            } else if (commuCd === 'commu_W') {
                return "고민/상담";
            } else if (commuCd === 'commu_L') {
                return "연애/썸";
            }
        }

    });
</script>
</body>
</html>
