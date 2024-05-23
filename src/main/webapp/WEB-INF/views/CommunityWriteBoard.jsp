2024_0522(10:39)
<%--헤더 영역--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="fixed/header.jsp" %>
<link rel="stylesheet" href="/css/communityWriteBoard.css">
<%--바디영역--%>
<body>
<form id="form" enctype="multipart/form-data" class="maincontent">
    <div class="community-write-container">
        <%--    카테고리,제목, 등록, 수정,삭제 버튼--%>
        <div class="community-write-category">
            <input type="hidden" name="no" value="${communityBoardDto.no}">
            <%--            카테고리, 등록,수정,삭제 버튼--%>
            <div class="row">
                <select class="commu-cd" name="commu_cd" label="카테고리를 선택해주세요">
                    <option value="commu_B">블라블라</option>
                    <option value="commu_W">고민/상담</option>
                    <option value="commu_L">연애/썸</option>
                </select>
                <input type="hidden" name="commu_name" id="commu_name" value="">


                <c:if test="${empty communityBoardDto.no}">
                    <input type="submit" value="등록" id="register" class="commu-button">
                </c:if>

                <c:if test="${not empty communityBoardDto.no}">
                    <input type="button" value="수정" id="modify" class="commu-button">
                    <input type="button" value="삭제" id="userStateChange" class="commu-button"  data-no="<c:out value='${communityBoardDto.no}'/>" data-ur_state="<c:out value='${communityBoardDto.ur_state}'/>">
                </c:if>
            </div>

            <%--             재목 입력 공간--%>
            <div class="row">
                <input id="community-write-title" type="text" name="title" placeholder="제목을 입력해주세요" value="<c:out value='${communityBoardDto.title}'/>">

            </div>
        </div>


    </div>
    <%--    이미지 보이는 영역--%>

    <div class="form_section_content">
        <input type="file" id="fileItem" name="uploadFile" style="height: 30px;" multiple>
    </div>

    <div id="uploadResult">
        <c:forEach items="${imglist}" var="img">
            <c:if test="${img.imgtype eq 'r'}">
                <div id="result_card">
                    <img src="/img/display?fileName=${img.img_full_rt}" id="resizable">
                    <div class="imgDeleteBtn" data-file="${img.img_full_rt}"></div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <%--    내용입력 공간--%>
    <div class="community-contents-container">
        <textarea name="contents" id="contents" placeholder="내용을 입력하세요."><c:out value='${communityBoardDto.contents}'/></textarea>
    </div>
    <div class="footer"></div>
</form>

<script>
    //중복클릭 방지

    let isProcessing = false;
    function preventDoubleClick(){
        if(isProcessing){
            alert("처리 중입니다. 잠시만 기다려주세요.");
            return true; // 중복 클릭 방지
        }
        isProcessing = true;
        return false;


    }

    // 글자수 세기

    function countingCharacters(title,contents){
        //만약
            //제목의 글자수 100 이상

        //만약
            //내용의 글자수 300이상
        //alert
        //제출방지

        if(title.length>100){
            alert("제목의 글자수는 100이하만 가능합니다.")
            return false;
        }
        if(contents.length>1000){
            alert("컨텐츠의 글자수는 1000이하만 가능합니다.")
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $('#register').on("click", function (e){
            e.preventDefault(); // 기본 폼 제출을 막음

            //중복클릭방지
            if(preventDoubleClick())return;

            let no = "${communityBoardDto.no}";
            let ur_id = "${communityBoardDto.ur_id}";
            let addr_cd = "${communityBoardDto.addr_cd}";
            let addr_no = "${communityBoardDto.addr_no}";
            let addr_name = "${communityBoardDto.addr_name}";
            let nick = "${communityBoardDto.nick}";
            let commu_cd = $('select[name="commu_cd"]').val();
            let commu_name = commuName(commu_cd);
            let title = $('#community-write-title').val();
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
                "imgList": Image.getImgInfo()
            };

            if ($('#community-write-title').val() === '' || $('#contents').val() === '') {
                alert('제목과 내용을 작성하였는지 확인해주세요.');
                return false; // 유효성 검사 실패 시 폼 제출 중단
            }

            if(!countingCharacters(title,contents)){
                isProcessing = false;
                return;
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


            //중복클릭방지
            if(preventDoubleClick())return;




            let no = "${communityBoardDto.no}";
            let ur_id = "${communityBoardDto.ur_id}";
            let addr_cd = "${communityBoardDto.addr_cd}";
            let addr_no = "${communityBoardDto.addr_no}";
            let addr_name = "${communityBoardDto.addr_name}";
            let nick = "${communityBoardDto.nick}";
            let commu_cd = $('select[name="commu_cd"]').val();
            let commu_name = commuName(commu_cd);
            let title = $('#community-write-title').val();
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

            if(!countingCharacters(title,contents)){
                isProcessing = false;
                return;
            }

            let map = {
                "communityBoardDto": communityBoardDto,
                "imgList": Image.getImgInfo()
            };

            let jsonString = JSON.stringify(map);

            console.log(map);

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

            //중복클릭방지
            if(preventDoubleClick())return;

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
<%--푸터 영역--%>
<script src="/js/img.js"></script>
<%@ include file="fixed/footer.jsp" %>
</body>






