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
        imginfo.push(obj);
        let fileCallPath = encodeURIComponent(obj.img_full_rt);

        str += "<div id='result_card'>";
        str += "<img src='/img/display?fileName=" + fileCallPath +"'>";
        str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
        str += "</div>";
    }

    uploadResult.append(str);
}

/* 이미지 삭제 버튼 동작 */
$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
    let index = $(".imgDeleteBtn").index(this);
    imginfo.splice(index, 1);
    let targetDiv = $("#result_card");
    targetDiv.remove();
    $("input[type='file']").val("");
});