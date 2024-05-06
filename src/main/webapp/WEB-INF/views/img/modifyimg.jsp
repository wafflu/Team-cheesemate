<%--
  Created by IntelliJ IDEA.
  User: jehyeon
  Date: 4/26/24
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<form>
  <div class="form_section_title">
    <label>상품 이미지</label>
  </div>
  <div class="form_section_content">
    <input type="file" id ="fileItem" name='uploadFile' multiple>
  </div>
  <button id="reg_img">등록</button>
  <br>
  <c:forEach items="${list}" var="img">
    <c:if test="${img.imgtype eq 'r'}">
      <img src="/img/display?fileName=${img.img_full_rt}" id = "resizable">
      <div class='imgDeleteBtn' data-file="${img.img_full_rt}">x</div>
    </c:if>
  </c:forEach>
</form

<%--<c:forEach items="${list}" var="img">--%>
<%--  <c:if test="${img.imgtype eq 'r'}">--%>
<%--    <img src="/img/display?fileName=${img.img_full_rt}" id = "resizable">--%>
<%--    <div class='imgDeleteBtn' data-file="${img.img_full_rt}">x</div>--%>
<%--  </c:if>--%>
<%--</c:forEach>--%>

<p>${sale}</p>

<script>
  let oriimginfo = [];
  let imginfo = [];

  <c:forEach items="${list}" var="img">
  <c:if test="${img.imgtype eq 'r'}">
  oriimginfo.push("${img}");
  </c:if>
  </c:forEach>

  oriimginfo.push("${img}");

  console.log(oriimginfo);

  /* 이미지 등록 */
  $("input[type='file']").on("change", function(e){

    let fileInput = $('input[name="uploadFile"]');
    let fileList = fileInput[0].files;
    let formData = new FormData();

    for(let i = 0; i < fileList.length; i++){
      formData.append("uploadFile", fileList[i]);
    }

    $.ajax({
      url: '/img/uploadAjaxAction',
      type : 'POST',
      data : formData,
      processData : false,
      contentType : false,
      dataType : 'json',
      success : function(result){
        showUploadImage(result);
      },
      error : function(result){
        alert("이미지 파일이 아닙니다.");
      }
    });
    e.target.value = "";
  });

  /* 이미지 출력 */
  function showUploadImage(uploadResultArr){
    /* 전달받은 데이터 검증 */
    if(!uploadResultArr || uploadResultArr.length == 0){return}

    let uploadResult = $("#uploadResult");
    let str = "";

    for(let i = 0; i<uploadResultArr.length; i++){
      let obj = uploadResultArr[i];

      // let fileCallPath = encodeURIComponent(obj.uploadPath + "/r_" + obj.uuid + "_" + obj.fileName);
      let fileCallPath = encodeURIComponent(obj.file_rt + "/r_" + obj.u_name + "_" + (obj.o_name+obj.e_name));

      str += "<div id='result_card'>";
      str += "<img src='/img/display?fileName=" + fileCallPath +"'>";

      imginfo.push(obj);

      str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
      str += "</div>";
    }

    uploadResult.append(str);
  }

  //이미지 등록하기
  $("#reg_img").click(function (){
    // alert("asd")
    let loc;
    $.ajax({
      url: '/img/reg_image2',
      type : 'POST',
      contentType : 'application/json',
      dataType : 'text',
      // async : false,
      data : JSON.stringify(imginfo),
      success: function (result) {
        // alert("S : "+result)
        loc = result;
        location.replace(loc);
      },
      error: function(result) {
        alert("선택된 이미지가 없습니다.")
      }
    });
    alert("등록되었습니다.")
  })

  /* 파일 삭제 메서드 */
  function deleteFile(){

    let targetFile = $(".imgDeleteBtn").data("file");

    let targetDiv = $("#result_card");

    $.ajax({
      url: '/img/deleteFile',
      data : {fileName : targetFile},
      dataType : 'text',
      type : 'POST',
      success : function(result){
        console.log(result);

        targetDiv.remove();
        $("input[type='file']").val("");

      },
      error : function(result){
        console.log(result);

        alert("파일을 삭제하지 못하였습니다.")
      }
    });
  }

</script>
</body>
</html>
