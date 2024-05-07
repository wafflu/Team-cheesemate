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
</form>

<div id = "uploadResult">
  <c:forEach items="${list}" var="img">
    <c:if test="${img.imgtype eq 'r'}">
    <div id='result_card'>
      <img src="/img/display?fileName=${img.img_full_rt}" id = "resizable">
      <div class='imgDeleteBtn' data-file="${img.img_full_rt}">x</div>
    </div>
    </c:if>
  </c:forEach>
</div>

<%--<c:forEach items="${list}" var="img">--%>
<%--  <c:if test="${img.imgtype eq 'r'}">--%>
<%--    <img src="/img/display?fileName=${img.img_full_rt}" id = "resizable">--%>
<%--    <div class='imgDeleteBtn' data-file="${img.img_full_rt}">x</div>--%>
<%--  </c:if>--%>
<%--</c:forEach>--%>

<p>${sale}</p>

<script>

  let imginfo = [];

  <c:forEach items="${list}" var="img">
  <c:if test="${img.imgtype eq 'r'}">
    imginfo.push(
            {
              "file_rt" : "${img.file_rt}",
              "o_name" : "${img.o_name}",
              "e_name" : "${img.e_name}"
            }
    )
  <%--imginfo.push("${img}");--%>
  </c:if>
  </c:forEach>

  <%--oriimginfo.push("${img}");--%>

  console.log(imginfo);

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
      let fileCallPath = encodeURIComponent(obj.img_full_rt);

      str += "<div id='result_card'>";
      str += "<img src='/img/display?fileName=" + fileCallPath +"'>";

      imginfo.push(obj);

      str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
      str += "</div>";
    }

    uploadResult.append(str);
  }

  /* 이미지 삭제 버튼 동작 */
  $("#uploadResult").on("click", ".imgDeleteBtn", function(e){
    var index = $(".imgDeleteBtn").index(this);
    // console.log("index : "+index)
    imginfo.splice(index, 1);
    let targetDiv = $("#result_card");
    targetDiv.remove();
    $("input[type='file']").val("");
    // deleteFile();
  });

  let sale = {
    "title" : "${sale.title}",
    "contents" : "${sale.contents}",
    "group_no" : ${sale.group_no}
  }

  //이미지 등록하기
  $("#reg_img").click(function (){

    // let saletitle = $('input[name="title"]').val();
    // let salecontents = $('input[name="contents"]').val();

    alert("asdasd")

    alert("sale : "+sale)
    let map =
            {
              "sale" : sale,
              "img" : imginfo
            };

    <%--alert(sale);--%>

    $.ajax({
      url: '/img/modify_image',
      type : 'POST',
      contentType : 'application/json; charset=UTF-8',
      dataType : 'text',
      data : JSON.stringify(map),
      success: function (result) {
        location.replace(result);
      },
      error: function(xhr, status, error) {
        // 오류 발생 시 처리
        var errorMessage = xhr.responseText;
        console.error('Error:', error);
        alert("오류 발생: " + errorMessage); // 서버에서 전달된 예외 메시지를 알림창으로 표시
      }
      // error: function(result) {
      //   alert("선택된 이미지가 없습니다." + result)
      // }
    });
    alert("등록되었습니다.")
  })


  function showThumbnailImage(uploadResultArr){

    /* 전달받은 데이터 검증 */
    if(!uploadResultArr || uploadResultArr.length == 0){return}

    let uploadResult = $("#uploadResult");
    let str = "";

    for(let i = 0; i<uploadResultArr.length; i++){
      let obj = uploadResultArr[i];

      let fileCallPath = encodeURIComponent(obj.filert + "/s_" + obj.u_name + "_" + (obj.o_name+obj.e_name));

      str += "<div id='result_card'>";
      str += "<img src='/img/display?fileName=" + fileCallPath +"'>";
      str += "</div>";
    }

    uploadResult.append(str);
  }

</script>
</body>
</html>
