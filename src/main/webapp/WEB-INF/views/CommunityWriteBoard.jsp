<%--헤더 영역--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="fixed/header.jsp" %>
<form id="form" enctype="multipart/form-data" class="maincontent">
    <div>
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
            <input id="title" type="text" name="title" value="<c:out value='${communityBoardDto.title}'/>">
        </div>

        <div class="form_section_content">
            <input type="file" id="fileItem" name="uploadFile" style="height: 30px;" multiple>
        </div>
        <div id="uploadResult">
            <c:forEach items="${imglist}" var="img">
                <c:if test="${img.imgtype eq 'r'}">
                    <div id="result_card">
                        <img src="/img/display?fileName=${img.img_full_rt}" id="resizable">
                        <div class="imgDeleteBtn" data-file="${img.img_full_rt}">x</div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <div class="main">
        <textarea name="contents" id="contents"><c:out value='${communityBoardDto.contents}'/></textarea>
    </div>
    <div class="footer"></div>
</form>
<script>

    const uploadImage = (function() {
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

    $(document).ready(function(){
        $('#register').on("click", function (e){
            e.preventDefault(); // 기본 폼 제출을 막음

            let no = "${communityBoardDto.no}";
            let ur_id = "${communityBoardDto.ur_id}";
            let addr_cd = "${communityBoardDto.addr_cd}";
            let addr_no = "${communityBoardDto.addr_no}";
            let addr_name = "${communityBoardDto.addr_name}";
            let nick = "${communityBoardDto.nick}";
            let commu_cd = $('select[name="commu_cd"]').val();
            let commu_name = commuName(commu_cd);
            let title = $('#title').val();
            let contents = $('#contents').val();
            let group_no = "${communityBoardDto.group_no}";

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
                "nick": nick,
                "group_no": group_no
            };

            let map = {
                "communityBoardDto": communityBoardDto,
                "imgList": ImageUploader.getImgInfo()
            };

            if ($('#title').val() === '' || $('#contents').val() === '') {
                alert('제목과 내용을 작성하였는지 확인해주세요.');
                return false; // 유효성 검사 실패 시 폼 제출 중단
            }

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
                    alert("애러발생");
                }
            });
        });

        $('#modify').on("click", function(e){
            e.preventDefault(); // 기본 폼 제출을 막음

            let no = "${communityBoardDto.no}";
            let ur_id = "${communityBoardDto.ur_id}";
            let addr_cd = "${communityBoardDto.addr_cd}";
            let addr_no = "${communityBoardDto.addr_no}";
            let addr_name = "${communityBoardDto.addr_name}";
            let nick = "${communityBoardDto.nick}";
            let commu_cd = $('select[name="commu_cd"]').val();
            let commu_name = commuName(commu_cd);
            let title = $('#title').val();
            let contents = $('#contents').val();
            let group_no = "${communityBoardDto.group_no}";

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
                "nick": nick,
                "group_no": group_no
            };

            let map = {
                "communityBoardDto": communityBoardDto,
                "imgList": ImageUploader.getImgInfo()
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
                    alert("에러 : " + xhr.responseText);
                }
            });
        });

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
<script src="/js/img.js"></script>
<%--푸터 영역--%>
<%@ include file="fixed/footer.jsp" %>