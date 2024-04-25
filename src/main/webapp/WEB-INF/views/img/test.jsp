<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <input type="file" id ="fileItem" name='uploadFile' style="height: 30px;" multiple>
  </div>
  <button id="reg_img">등록</button>
  <%--        <input type="submit" onclick="return imgreg()">--%>
</form>


<%--    <div id="imgboxtest">--%>
<%--        <div class="imgDeleteBtn">x</div>--%>
<%--&lt;%&ndash;        <img src="../../resources/images/cho.png">&ndash;%&gt;--%>
<%--        <img src="/display?fileName=2024_04_13/cho.png">--%>
<%--    </div>--%>

<div id = "uploadResult">

</div>

<script>
  let regex = new RegExp("(.*?)\.(jpg|png)$"); //jpg와 png만 허용
  let maxSize = 1048576; //1MB
  let imginfo = [];

  function fileCheck(fileName, fileSize){

    if(fileSize >= maxSize){
      alert("파일 사이즈 초과");
      return false;
    }

    if(!regex.test(fileName)){
      alert("해당 종류의 파일은 업로드할 수 없습니다.");
      return false;
    }

    return true;

  }

  $("input[type='file']").on("change", function(e){

    let fileInput = $('input[name="uploadFile"]');
    let fileList = fileInput[0].files;
    let formData = new FormData();

    for(let i = 0; i < fileList.length; i++){
      formData.append("uploadFile", fileList[i]);
    }

    $.ajax({
      url: '/uploadAjaxAction',
      type : 'POST',
      data : formData,
      processData : false,
      contentType : false,
      dataType : 'json',
      success : function(result){
        console.log("res : "+result);
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
      let fileCallPath = encodeURIComponent(obj.filert + "/r_" + obj.u_name + "_" + (obj.o_name+obj.e_name));

      str += "<div id='result_card'>";
      str += "<img src='/display?fileName=" + fileCallPath +"'>";

      imginfo.push(obj);
      str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
      // str += "<input type='hidden' name='imageList["+i+"].fileName' value='"+ obj.fileName +"'>";
      // str += "<input type='hidden' name='imageList["+i+"].uuid' value='"+ obj.uuid +"'>";
      // str += "<input type='hidden' name='imageList["+i+"].uploadPath' value='"+ obj.uploadPath +"'>";
      str += "</div>";
    }

    uploadResult.append(str);
  }

  /* 이미지 삭제 버튼 동작 */
  $("#uploadResult").on("click", ".imgDeleteBtn", function(e){
    var index = $(".imgDeleteBtn").index(this);
    console.log("index : "+index)
    imginfo.splice(index, 1);
    console.log(imginfo);
    deleteFile();
  });

  //
  // $(".imgDeletBtn").click(function(){
  //     deleteFile();
  // });

  $("#reg_img").on('click',function (){
    alert("asdasdasd");
    $.ajax({
      url: '/reg_image',
      type : 'POST',
      contentType : 'application/json',
      dataType : 'json',
      data : JSON.stringify(imginfo)
    });
  })


  /* 파일 삭제 메서드 */
  function deleteFile(){

    let targetFile = $(".imgDeleteBtn").data("file");

    let targetDiv = $("#result_card");

    $.ajax({
      url: '/deleteFile',
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

  $(document).ready(
          $.ajax({
            url: '/loadThumbnailImage',
            type : 'POST',
            processData : false,
            contentType : false,
            dataType : 'json',
            data : JSON.stringify(imginfo),
            success : function(result){
              showThumbnailImage(result);
            },
            error : function(result){
              alert("로딩 오류");
            }
          })
  );

  function showThumbnailImage(uploadResultArr){

    /* 전달받은 데이터 검증 */
    if(!uploadResultArr || uploadResultArr.length == 0){return}

    let uploadResult = $("#uploadResult");
    let str = "";

    for(let i = 0; i<uploadResultArr.length; i++){
      let obj = uploadResultArr[i];

      let fileCallPath = encodeURIComponent(obj.filert + "/s_" + obj.u_name + "_" + (obj.o_name+obj.e_name));

      str += "<div id='result_card'>";
      str += "<img src='/display?fileName=" + fileCallPath +"'>";
      str += "</div>";
    }

    uploadResult.append(str);
  }

</script>
</body>
</html>
