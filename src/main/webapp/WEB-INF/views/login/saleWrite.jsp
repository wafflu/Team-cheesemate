<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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

            #openModalBtn {
              all: unset;
              background-color: steelblue;
              color: white;
              padding: 5px 20px;
              border-radius: 5px;
              cursor: pointer;
            }

            .modal {
              position: fixed;
              top: 0;
              left: 0;
              width: 100%;
              height: 100%;
              display: flex;
              justify-content: center;
              align-items: center;
            }

            .modal_overlay {
              background-color: rgba(0, 0, 0, 0.6);
              width: 100%;
              height: 100%;
              position: absolute;
            }

            .modal_content {
              background-color: white;
              padding: 50px 100px;
              text-align: center;
              position: relative;
              width: 50%;
              top: 0px;
              border-radius: 10px;
              box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
              overflow-y: auto;
              /* 세로 스크롤이 필요한 경우 스크롤 허용 */
              max-height: 70%;
              /* 모달 창의 최대 높이 설정 */
            }

            #closeModalBtn {
              position: absolute;
              top: 10px;
              /* 원하는 위치로 조정 */
              right: 10px;
              /* 원하는 위치로 조정 */
              background-color: transparent;
              /* 배경색 설정 */
              border: none;
              /* 테두리 제거 */
              cursor: pointer;
              /* 마우스 커서를 포인터로 변경 */
            }

            .table-wrapper {
              overflow-y: auto;
              max-height: 200px;
            }

            tr:hover {
              background-color: cornflowerblue;
              cursor: pointer;
            }


            #searchInput {
              /* 모달 창의 너비에 맞게 조정 */
              width: 100%;
              padding: 10px;
              /* 내부 여백 설정 */
              margin: 0 auto;
              /* 가운데 정렬 */
            }


            #addrTable {
              width: 100%;
              /* addrTable의 너비를 100%로 설정하여 모달 창에 맞춤 */
            }

            h1 {
              margin: 0;
            }

            .hidden {
              display: none;
            }

            /* input text 및 textarea 너비 조절 */
            input[type="text"],
            textarea {
              width: calc(100% - 20px);
              /* 전체 너비에서 여백을 뺀 값으로 설정 */
              padding: 5px;
              /* 내부 여백 설정 */
              margin-top: 10px;
            }
          </style>
        </head>

        <body>
          <div>
            <form id="writeForm" name="writeForm" method="POST" enctype="multipart/form-data">
              <p>상품 정보</p>
              <div class="division-line"></div>
              <div id="register_img">
                <p>
                  상품이미지(<span id="img_count">0</span>/10) <input type="file" id="imageBtn" name="imagename" ' multiple='
                    multiple'>
                </p>
              </div>
              <p>
                제목 <input name="title" type="text" placeholder="판매/나눔글 제목을 입력하세요" />
              </p>
              <p>카테고리</p>
              <select id="category1" name="sal_i_cd" onchange="loadCategory2()">
                <option value="" disabled selected>대분류</option>
                <c:forEach var="category" items="${saleCategory1}">
                  <option value="${category.sal_cd}">${category.name}</option>
                </c:forEach>
              </select>

              <select id="category2" name="sal_i_cd" onchange="loadCategory3()">
                <option value="" disabled selected>중분류</option>
              </select>

              <select id="category3" name="sal_i_cd">
                <option value="" disabled selected>소분류</option>
              </select>

              <p style="color: red;" id="salecategoryMsg"></p>

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
              <textarea name="contents" id="" cols="30" rows="10"></textarea>
              <p>해시태그(선택) <input type="text" id="hashtagInput" /></p>
              <div id="hashtagContainer"></div>
              <input type="radio" name="tx_s_cd" value="sale" />판매
              <input type="radio" name="tx_s_cd" value="free" />나눔
              <p style="color: red;" id="txMsg">판매, 나눔 중 한 가지를 선택해 주세요.</p>
              <p>
                상품가격
                <input name="price" type="text" placeholder="판매할 가격을 입력해주세요." />
              </p>
              <p id="proposal" hidden><input type="radio" /> 가격제안받기</p>
              <p id="trade" hidden><input type="radio" id="trade" /> 나눔신청받기</p>
              <p>
                상품정가(선택)
                <input type="text" name="reg_price" placeholder="상품의 정가를 입력해주세요(선택)." />
              </p>
              <button id="openModalBtn">거래희망 주소 검색</button>
              <div id="openModal" class="modal hidden">
                <div class="modal_overlay"> <!--모달창의 배경색--></div>
                <div class="modal_content">
                  <button id="closeModalBtn">x</button>
                  <h1>주소 검색</h1>
                  <input id="searchInput" type="text" placeholder="동(읍/면/리)을 입력해주세요.">
                  <div class="table-wrapper">
                    <table id="addrTable" class="table text-center">
                      <thead>
                        <tr>
                          <th style="width:150px;">행정동코드</th>
                          <th style="width:600px;">주소명</th>
                        </tr>
                      </thead>
                      <tbody id="addrList"></tbody>
                    </table>
                  </div>
                </div>
              </div>
              <p>거래장소 <input id="pickup_addr" name="pickup_addr_name" type="text" disabled></p>
              <p>
                거래희망장소(선택)
                <input type="text" name="detail_addr" placeholder="거래를 희망하는 상세장소를 작성하세요." />
              </p>
              <p>
                브랜드(선택)
                <input type="text" name="brand" placeholder="브랜드를 작성하세요(선택)." />
              </p>
              <input type="button" id="submitBtn" value="등록하기" />
            </form>
          </div>
        </body>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
          integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>

          // Enter키 누를 시 모달창 뜨는 것 방지
          $(document).ready(function () {
            $(document).on("keydown", function (event) {
              if (event.keyCode === 13) {
                event.preventDefault(); // Enter 키의 기본 동작 막기(input 창에서 Enter치면 모달창이 열리기 때문)
              }
            });
          });

          // 이미지 등록 개수 한도 정하기
          $(document).ready(function () {
            let img_count = 0;
            let max_images = 10;
            $("#imageBtn").change(function () {
              let files = this.files;
              if (img_count + files.length > max_images) {
                alert("최대 " + max_images + "개의 이미지까지만 등록할 수 있습니다.");
                return;
              }
              for (let i = 0; i < files.length; i++) {
                let reader = new FileReader();
                reader.onload = function (event) {
                  let imageUrl = event.target.result;
                  let imagePreview =
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


            // 이미지 삭제시 이미지 카운트 수 줄이기
            $(document).on("click", ".delete-icon", function () {
              $(this).closest(".preview-image").remove();
              img_count--;
              $("#img_count").text(img_count);
            });
          });

          // 거래방법 선택 최대 2개까지 한도 주기
          $(".trade_s_cd").change(function () {
            let checkedCount = $(".trade_s_cd:checked").length;
            if (checkedCount > 2) {
              $(this).prop("checked", false);
              alert("최대 2개의 거래방법까지 선택할 수 있습니다.");
            }
          });

          // 판매/나눔 선택
          $("input[name='tx_s_cd']").change(function () {
            if ($(this).val() === "sale") {
              $("#proposal").show();
              $("#trade").hide();
              // 그 외의 경우는 활성화
              $("input[name='price']").prop("disabled", false);
              $("input[name='price']").attr("placeholder", "판매할 가격을 입력해주세요.");
              $("#txMsg").text(""); // 메시지 제거
            } else if ($(this).val() === "free") {
              $("#proposal").hide();
              $("#trade").show();
              // 상품 가격 입력란을 비활성화
              $("input[name='price']").prop("disabled", true);
              $("input[name='price']").attr("placeholder", "나눔글입니다.");
              $("#txMsg").text(""); // 메시지 제거
            }
          });


          // 
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
                input.name = "tag";
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

          $(document).ready(function () {
            // 대분류 선택 전 메시지 설정
            $("#salecategoryMsg").text("대분류 > 중분류 > 소분류를 선택하세요.");

            // 대분류 선택 시 중분류 메시지
            $("#category1").change(function () {
              $("#salecategoryMsg").text("중분류 > 소분류를 선택하세요.");
              loadCategory2();
            });

            // 중분류 선택 시 소분류 메시지
            $("#category2").change(function () {
              $("#salecategoryMsg").text("소분류를 선택하세요.");
              loadCategory3();
            });

            // 소분류 선택 시 메시지 제거
            $("#category3").change(function () {
              $("#categoryMsg").text("");
            });
          });

          function loadCategory2() {
            let category1Value = $('#category1').val();
            console.log(category1Value)
            if (category1Value !== "") {
              $.ajax({
                type: "POST",
                url: "/sale/saleCategory2",
                dataType: "json", // 받을 값
                data: { category1: category1Value },
                success: function (data) {
                  // console.log(data); 
                  // console.log(typeof data);
                  // alert(data);
                  let category2Select = document.getElementById("category2");
                  category2Select.innerHTML = "<option value='' disabled selected>중분류</option>";
                  let category3Select = document.getElementById("category3");
                  category3Select.innerHTML = "<option value='' disabled selected>소분류</option>";
                  console.log("data.length : ", data.length);
                  if (data.length > 0) {
                    data.forEach(function (category) {
                      // console.log(typeof category);
                      if (category.sal_cd.startsWith(category1Value)) {
                        let option = new Option(category.name, category.sal_cd);
                        category2Select.add(option);
                      }
                    });
                  } else {
                    $("#salecategoryMsg").text("");
                  }
                },
                error: function (xhr, status, error) {
                  console.error("Error :", error);
                }
              });
            }
          }

          function loadCategory3() {
            let category2Value = $('#category2').val();
            console.log(category2Value)
            if (category2Value !== "") {
              $.ajax({
                type: "POST",
                url: "/sale/saleCategory3",
                dataType: "json",
                data: { category2: category2Value },
                success: function (data) {
                  // alert(data);
                  let category3Select = document.getElementById("category3");
                  category3Select.innerHTML = "<option value='' disabled selected>소분류</option>";
                  console.log("data.length : ", data.length);
                  if (data.length > 0) {
                    data.forEach(function (category) {
                      if (category.sal_cd.startsWith(category2Value)) {
                        let option = new Option(category.name, category.sal_cd);
                        category3Select.add(option);
                      }
                    });
                  } else {
                    $("#salecategoryMsg").text("");
                  }
                },
                error: function (xhr, status, error) {
                  console.error("Error :", error);
                }
              });
            }
          }

          // 소분류 선택 시 메시지 제거
          $("#category3").change(function () {
            $("#salecategoryMsg").text("");
          });

          document.getElementById('submitBtn').addEventListener('click', function () {
            // submit 버튼 클릭 시 form 제출
            document.getElementById('writeForm').submit();
          });

          const openModalBtn = document.getElementById("openModalBtn");
          const modal = document.querySelector(".modal");
          const overlay = modal.querySelector(".modal_overlay");
          const closeModalBtn = document.getElementById("closeModalBtn");
          const openModal = (event) => {
            event.preventDefault(); // form의 기본 동작을 막음
            modal.classList.remove("hidden");
          }
          const closeModal = () => {
            event.preventDefault(); // form의 기본 동작을 막음
            modal.classList.add("hidden");
          }
          overlay.addEventListener("click", closeModal);
          closeModalBtn.addEventListener("click", closeModal);
          openModalBtn.addEventListener("click", openModal);


          $(document).ready(function () {
            $("#searchInput").on("input", function () {
              let searchLetter = $(this).val();
              $.ajax({
                type: "POST",
                url: "/sale/searchLetter",
                data: { searchLetter: searchLetter },
                dataType: "json",
                success: function (data) {
                  // 검색 결과를 처리하여 addrTable에 추가
                  console.log(data);
                  $("#addrList").empty(); // 기존 내용 초기화
                  if (data.length > 0) {
                    data.forEach(function (addr) {
                      console.log("addr", addr);
                      let row = $("<tr>");
                      row.append($("<td>").text(addr.addr_cd)); // 행정구역 코드
                      row.append($("<td>").text(addr.addr_name)); // 주소명
                      $("#addrList").append(row);
                    });
                  } else {
                    $("#addrTable").append("<tr><td colspan='2'>검색 결과가 없습니다.</td></tr>");
                  }
                },
                error: function (xhr, status, error) {
                  console.error("Error: ", error);
                }
              });
            });
          });

          $(document).ready(function () {
            // 주소를 클릭했을 때의 이벤트 핸들러
            $("#addrList").on("click", "tr", function () {
              // 클릭한 행에서 주소 코드와 주소명을 가져옴
              let addrCode = $(this).find("td:eq(0)").text(); // 첫 번째 td 열의 텍스트 (주소 코드)
              let addrName = $(this).find("td:eq(1)").text(); // 두 번째 td 열의 텍스트 (주소명)

              // pickup_addr input에 선택한 주소 정보를 추가
              $("#pickup_addr").val(addrName);

              // 모달 닫기
              closeModal();
            });
          });

          // 등록하기 버튼 누르는 경우
          // function write() {
          //   // let queryString = $("form[name=writeForm]").serialize();
          //   // let queryString = $("#writeForm").serialize();
          //   // FormData 객체 생성
          //   let formData = new FormData(document.getElementById("writeForm"));
          //   console.log(queryString);
          //   alert(queryString);
          //   $.ajax({
          //     type: 'POST',
          //     url: '/sale/write',
          //     contentType: 'multipart/form-data',
          //     data: formData,
          //     dataType: 'json',
          //     success: function (data) {
          //       alert(data);
          //       location.reload();
          //     },
          //     error: function (xhr, status, error) {
          //       alert(error);
          //     }
          //   });
          // }

          $("#submitBtn").on("click", function () {
              alert("확인");
                // let queryString = $("form[name=writeForm]").serialize();
            // let queryString = $("#writeForm").serialize();
            // FormData 객체 생성
            let formData = new FormData(document.getElementById("writeForm"));
            console.log(queryString);
            alert(formData);
            $.ajax({
              type: 'POST',
              url: '/sale/write',
              contentType: 'multipart/form-data',
              data: formData,
              dataType: 'json',
              success: function (data) {
                alert(data);
                location.reload();
              },
              error: function (xhr, status, error) {
                alert(error);
              }
            });
          });
          

        </script>

        </html>