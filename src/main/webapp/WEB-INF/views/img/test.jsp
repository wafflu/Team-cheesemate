<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<form action="/img/reg_image3" method="post" id="testform">
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

<div id = "uploadResult">

</div>

<script src="/resources/js/img.js"></script>
<script>

  //이미지 등록하기
  $("#reg_img").click(function (){

    // let saletitle = $('input[name="title"]').val();
    // let salecontents = $('input[name="contents"]').val();
    //
    // let sale = {
    //   "title" : saletitle,
    //   "contents" : salecontents
    // }
    // alert("imginfo : "+imginfo)


    $("#testform").append("imgList", JSON.stringify(imginfo));
    $("#testform").submit();

    // let map =
    //   {
    //     "sale" : sale,
    //     "img" : imginfo
    //   };
    //
    // $.ajax({
    //   url: '/img/reg_image2',
    //   type : 'POST',
    //   contentType : 'application/json; charset=UTF-8',
    //   dataType : 'text',
    //   data : JSON.stringify(map),
    //   success: function (result) {
    //     location.replace(result);
    //   },
    //   error: function(xhr, status, error) {
    //     // 오류 발생 시 처리
    //     var errorMessage = xhr.responseText;
    //     console.error('Error:', error);
    //     alert("오류 발생: " + errorMessage); // 서버에서 전달된 예외 메시지를 알림창으로 표시
    //   }
    //   // error: function(result) {
    //   //   alert("선택된 이미지가 없습니다." + result)
    //   // }
    // });
    // alert("등록되었습니다.")
  })
</script>
</body>
</html>
