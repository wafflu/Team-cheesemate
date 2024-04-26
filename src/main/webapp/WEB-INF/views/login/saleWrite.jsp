<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head>
    <title>sale</title>
    <style>
      .division-line {
        border-top: 1px solid #444444;
        margin: 30px 0px;
      }
      .preview-image {
        display: inline-block;
        position: relative;
        margin-right: 10px;
      }
    </style>
  </head>
  <body>
    <div>
      <form
        name="fileForm"
        method="POST"
        enctype="multipart/form-data"
        action="fileupload01_process.jsp"
      >
        <p>상품 정보</p>
        <div class="division-line"></div>
        <div id="register_img">
          <p>
            상품이미지(<span id="img_count">0</span>/10) <input type="file"
            id="imageBtn" name="imagename" ' multiple='multiple'>
          </p>
        </div>
        <p>
          제목 <input type="text" placeholder="판매/나눔글 제목을 입력하세요" />
        </p>
        <p>카테고리</p>
        <select id="category1" onchange="loadCategory2()">
            <option value="" disabled selected>대분류</option>
            <c:forEach var="category" items="${categories}">
                <c:if test="${fn:length(category.sal_cd) == 3}">
                    <option value="${category.sal_cd}">${category.name}</option>
                </c:if>
            </c:forEach>
        </select>
        
        <select id="category2" onchange="loadCategory3()">
            <option value="" disabled selected>중분류</option>
        </select>

        
        <select id="category3">
            <option value="" disabled selected>소분류</option>
        </select>
        </select>
        <p>상품상태</p>
        <input type="radio" name="pro_s_cd" />새상품(미사용) <br />
        <input type="radio" name="pro_s_cd" />사용감 없음 <br />
        <input type="radio" name="pro_s_cd" />사용감 적음 <br />
        <input type="radio" name="pro_s_cd" />사용감 많음 <br />
        <input type="radio" name="pro_s_cd" />고장/파손 상품 <br />
        <p>
          거래방법(2개 이하)
          <input type="checkbox" class="trade_s_cd" /> 온라인
          <input type="checkbox" class="trade_s_cd" /> 직거래
          <input type="checkbox" class="trade_s_cd" /> 택배거래
        </p>
        <p>설명</p>
        <textarea name="" id="" cols="30" rows="10"></textarea>
        <p>해시태그(선택) <input type="text" id="hashtagInput" /></p>
        <div id="hashtagContainer"></div>
        <input type="radio" name="tx_s_cd" value="sale" />판매
        <input type="radio" name="tx_s_cd" value="free" />나눔
        <p>
          상품가격
          <input type="text" placeholder="판매할 가격을 입력해주세요." />
        </p>
        <p id="proposal" hidden><input type="radio" /> 가격제안받기</p>
        <p id="trade" hidden><input type="radio" id="trade" /> 나눔신청받기</p>
        <p>
          상품정가(선택)
          <input type="text" placeholder="상품의 정가를 입력해주세요(선택)." />
        </p>
        <p>거래장소</p>
        <p>
          거래희망장소(선택)
          <input
            type="text"
            placeholder="거래를 희망하는 상세장소를 작성하세요."
          />
        </p>
        <p>
          브랜드(선택)
          <input type="text" placeholder="브랜드를 작성하세요(선택)." />
        </p>
        <input type="submit" value="등록하기" />
      </form>
    </div>
  </body>
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
    integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
    crossorigin="anonymous"
    referrerpolicy="no-referrer"
  ></script>
  <script>
    $(document).ready(function () {
      var img_count = 0;
      var max_images = 10;

      $("#imageBtn").change(function () {
        var files = this.files;
        if (img_count + files.length > max_images) {
          alert("최대 " + max_images + "개의 이미지까지만 등록할 수 있습니다.");
          return;
        }
        for (var i = 0; i < files.length; i++) {
          var reader = new FileReader();
          reader.onload = function (event) {
            var imageUrl = event.target.result;
            var imagePreview =
              "<div class='preview-image'><img src='" +
              imageUrl +
              "' width='150'><span class='delete-icon'>x</span></div>";
            $("#register_img").append(imagePreview);
          };
          reader.readAsDataURL(files[i]);
          img_count++;
          $("#img_count").text(img_count);
        }
      });

      $(document).on("click", ".delete-icon", function () {
        $(this).closest(".preview-image").remove();
        img_count--;
        $("#img_count").text(img_count);
      });
    });

    $(".trade_s_cd").change(function () {
      var checkedCount = $(".trade_s_cd:checked").length;
      if (checkedCount > 2) {
        $(this).prop("checked", false);
        alert("최대 2개의 거래방법까지 선택할 수 있습니다.");
      }
    });

    $("input[name='tx_s_cd']").change(function () {
      if ($(this).val() === "sale") {
        $("#proposal").show();
        $("#trade").hide();
      } else if ($(this).val() === "free") {
        $("#proposal").hide();
        $("#trade").show();
      }
    });

    document
      .getElementById("hashtagInput")
      .addEventListener("input", function () {
        let hashtags = this.value.split("#").filter(Boolean); // #을 기준으로 문자열을 나누고 빈 문자열 제거

        // 기존의 해시태그를 모두 삭제
        let container = document.getElementById("hashtagContainer");
        container.innerHTML = "";

        // 각 해시태그를 별도의 input 요소에 추가하여 표시
        hashtags.forEach(function (tag) {
          let input = document.createElement("input");
          input.type = "text";
          input.value = "#" + tag; // 해시태그 앞에 #을 붙여서 표시
          input.disabled = true; // 입력 불가능하도록 설정
          container.appendChild(input);

          // 해시태그 사이 간격 조절
          // 해시태그의 글자 수에 따라 너비 동적 조절
          input.style.width = (tag.length + 3) * 3 + "vw";

          container.appendChild(input);
          container.appendChild(document.createTextNode(" ")); // 각 해시태그 뒤에 공백 추가
        });
      });

      function loadCategory1() {
  $.ajax({
    type: "GET",
    url: "/sale/write",
    success: function (data) {
      let category1Select = document.getElementById("category1");
      category1Select.innerHTML = "<option value='' disabled selected>대분류</option>";
      data.forEach(function (category) {
        if (category.sal_cd.length === 3) { // 대분류 코드의 길이가 3인 경우만 처리
          let option = new Option(category.name, category.sal_cd);
          category1Select.add(option);
        }
      });
    },
    error: function (xhr, status, error) {
      console.error("Error fetching category1 data:", error);
    },
  });
}

function loadCategory2() {
  let category1Value = document.getElementById("category1").value;
  if (category1Value !== "") {
    // 대분류에서 선택한 값의 길이가 3인 경우에 대해 중분류 코드 생성
    if (category1Value.length === 3) {
      let category1Prefix = category1Value; // 대분류 코드의 앞 3글자
      $.ajax({
        type: "GET",
        url: "/sale/write",
        success: function (data) {
          let category2Select = document.getElementById("category2");
          category2Select.innerHTML =
            "<option value='' disabled selected>중분류</option>";
          data.forEach(function (category) {
            // 중분류 코드의 앞 3글자가 대분류 코드의 앞 3글자와 일치하는 경우에만 추가
            if (category.sal_cd.startsWith(category1Prefix)) {
              let option = new Option(category.name, category.sal_cd);
              category2Select.add(option);
            }
          });
        },
        error: function (xhr, status, error) {
          console.error("Error fetching category2 data:", error);
        },
      });
    }
  }
}

function loadCategory3() {
  let category2Value = document.getElementById("category2").value;
  if (category2Value !== "") {
    // 중분류에서 선택한 값의 길이가 6인 경우에 대해 소분류 코드 생성
    if (category2Value.length === 6) {
      let category2Prefix = category2Value; // 중분류 코드의 앞 6글자
      $.ajax({
        type: "GET",
        url: "/sale/write",
        success: function (data) {
          let category3Select = document.getElementById("category3");
          category3Select.innerHTML =
            "<option value='' disabled selected>소분류</option>";
          data.forEach(function (category) {
            // 소분류 코드의 앞 6글자가 중분류 코드와 일치하는 경우에만 추가
            if (category.sal_cd.startsWith(category2Prefix)) {
              let option = new Option(category.name, category.sal_cd);
              category3Select.add(option);
            }
          });
        },
        error: function (xhr, status, error) {
          console.error("Error fetching category3 data:", error);
        },
      });
    }
  }
}
  </script>
</html>
