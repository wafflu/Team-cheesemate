<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<form>
  <input type="text" name="title">
  <input type="text" name="contents">
  <div class="form_section_title">
    <label>상품 이미지</label>
  </div>
  <div class="form_section_content">
    <input type="file" id ="fileItem" name='uploadFile' style="height: 30px;" multiple>
  </div>
  <button id="reg_img">등록</button>
  <%--        <input type="submit" onclick="return imgreg()">--%>
</form>

<h1 id="h1">click</h1>


<%--    <div id="imgboxtest">--%>
<%--        <div class="imgDeleteBtn">x</div>--%>
<%--&lt;%&ndash;        <img src="../../resources/images/cho.png">&ndash;%&gt;--%>
<%--        <img src="/display?fileName=2024_04_13/cho.png">--%>
<%--    </div>--%>

<div id = "uploadResult">

</div>

<script>
  let imginfo = [];

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

  /* 이미지 삭제 버튼 동작 */
  $("#uploadResult").on("click", ".imgDeleteBtn", function(e){
    var index = $(".imgDeleteBtn").index(this);
    // console.log("index : "+index)
    imginfo.splice(index, 1);
    console.log(imginfo);
    deleteFile();
  });

  //
  // $(".imgDeletBtn").click(function(){
  //     deleteFile();
  // });

  // $("#h1").click(function(){
  //   let test =[1,2,3,4,5];
  //
  //   $.ajax({
  //     url: '/reg_image2',
  //     type: 'GET', // POST 요청으로 변경
  //     // contentType: 'application/json',
  //     // data: JSON.stringify(test), // JSON 형식으로 데이터 전송
  //     dataType: 'json',
  //     success: function(result) {
  //       alert("S : " + result);
  //     },
  //     error: function(result) {
  //       alert("F : " + result);
  //     }
  //   });
  // })

  // let info = {
  //   "imglist" : imginfo,
  //   "person": { "name": "lee", "age": 24 }
  // }


  //이미지 등록하기
  $("#reg_img").click(function (){

    let saletitle = $('input[name="title"]').val();
    let salecontents = $('input[name="contents"]').val();

    let sale = {
      "title" : saletitle,
      "contents" : salecontents
    }

    let map =
      {
        "sale" : sale,
        "img" : imginfo
      };

    $.ajax({
      url: '/img/reg_image2',
      type : 'POST',
      contentType : 'application/json',
      dataType : 'text',
      data : JSON.stringify(map),
      success: function (result) {
        location.replace(result);
      },
      error: function(result) {
        alert("선택된 이미지가 없습니다." + result)
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
