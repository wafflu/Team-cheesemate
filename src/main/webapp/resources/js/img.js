const ImageUploader = (function() {
    let imginfo =  uploadImage.getImgInfo() || [];
    let img_count = imginfo.length;
    let max_images = 10;

    $(document).ready(function () {
        $(".imgcnt").html(img_count+"/"+max_images);

        $("#fileItem").on("change", function (e) {
            let fileInput = $('input[name="uploadFile"]');
            let fileList = fileInput[0].files;
            let formData = new FormData();

            if (img_count + fileList.length > max_images) {
                alert("최대 " + max_images + "개의 이미지까지만 등록할 수 있습니다.");
                e.target.value = "";
                return;
            }

            for (let i = 0; i < fileList.length; i++) {
                formData.append("uploadFile", fileList[i]);
                img_count++;
            }

            $.ajax({
                url: '/img/uploadAjaxAction',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                dataType: 'json',
                success: function (result) {
                    showUploadImage(result, 0, 0, img_count);
                },
                error: function (result) {
                    alert("이미지 파일이 아닙니다.");
                }
            });

            e.target.value = "";
        });

        $("#profile").on("change", function (e) {
            let fileInput = $('input[name="uploadFile"]');
            let fileList = fileInput[0].files;
            let formData = new FormData();

            for (let i = 0; i < fileList.length; i++) {
                formData.append("uploadFile", fileList[i]);
            }
            $.ajax({
                url: '/img/uploadoneimg',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                dataType: 'json',
                success: function (result) {
                    showUploadImage(result, 250, 250, 0);
                },
                error: function (result) {
                    alert("이미지 파일이 아닙니다.");
                }
            });
            e.target.value = "";
        });

        function showUploadImage(uploadResultArr, width, height, imgcount) {
            if (!uploadResultArr || uploadResultArr.length == 0) {
                return;
            }

            let uploadResult = $("#uploadResult");
            if (width != 0 && height != 0) {
                uploadResult.children().remove();
            }
            let str = "";

            for (let i = 0; i < uploadResultArr.length; i++) {
                let obj = uploadResultArr[i];
                imginfo.push(obj);
                let fileCallPath = encodeURIComponent(obj.img_full_rt);

                str += "<div id='result_card'>";
                if (width != 0 && height != 0) {
                    str += "<img src='/img/display?fileName=" + fileCallPath + "' class='profileimg'>";
                } else {
                    str += "<img src='/img/display?fileName=" + fileCallPath + "'>";
                    str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
                }
                str += "</div>";
            }
            uploadResult.append(str);

            $(".imgcnt").html(imgcount+"/"+max_images);

            let progilesavebtn = $("#profileimg");
            let str2 = "";
            if (progilesavebtn.children("#profilesave_btn").length === 0) {
                // str2 += "<button id='profilesave_btn'>프로필 저장</button>";
                str2 += '<button type="button" id="profilesave_btn"><span class="material-symbols-outlined" style="color:#fff">save</span></button>';

                progilesavebtn.append(str2);
            }
        }

        $("#uploadResult").on("click", ".imgDeleteBtn", function (e) {
            let index = $(".imgDeleteBtn").index(this);
            imginfo.splice(index, 1);
            let targetDiv = $("#uploadResult");
            targetDiv.children().get(index).remove();
            $("input[type='file']").val("");
            img_count--;
            $(".imgcnt").html(img_count+"/"+max_images);
        });
    });

    return {
        getImgInfo: function() {
            return imginfo;
        }
    };
})();