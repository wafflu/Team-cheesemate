<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp"%>
<link rel="stylesheet" href="/css/saleBoard.css">
<div class="maincontent saleboardarea">
    <div class="saleboard-top-box">


        <div id="sale-slider-div" class="sale-img-box">
            <c:forEach items="${imglist}" var="img">
                <c:if test="${img.imgtype eq 'w'}">
                <div class="sale-img">
                    <img src="/img/display?fileName=${img.img_full_rt}" class="saleboardimg">
                </div>
                </c:if>
            </c:forEach>
        </div>

        <div class="saleinfobox">
            <div class="categorybox">
                <c:choose>
                    <c:when test="${empty category2Name}">
                        <span class="sel-catefory"> ${category1Name} </span>
                    </c:when>
                    <c:otherwise>
                        <span> ${category1Name} </span>
                    </c:otherwise>
                </c:choose>

                <c:if test="${not empty category2Name}">
                    <span> > </span>
                    <c:choose>
                        <c:when test="${empty category3Name}">
                            <span class="sel-catefory"> ${category2Name} </span>
                        </c:when>
                        <c:otherwise>
                            <span> ${category2Name} </span>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:if test="${not empty category3Name}">
                    <span> > </span>
                    <span class="sel-catefory">${category3Name} </span>
                </c:if>
            </div>


            <div class="titleinfo-box">
                <c:if test="${Sale.tx_s_cd == 'F'}">
                    <span class="share-title"><c:when test="${Sale.tx_s_cd == 'F'}">나눔</c:when></span>
                </c:if>
                <p class="saletitle">${Sale.title}</p>
                <p class="saleprice">${Sale.price}원</p>
            </div>

            <div class="tiem-view-box">
                <p class="saleboard-uptime">${Sale.h_date} </p>
                <p>·</p>
                <p>조회 : ${Sale.view_cnt}</p>
                <p>·</p>
                <p>찜 : ${Sale.like_cnt}</p>
            </div>

            <div id="tradeinfo-box">
                <div class="sb-p-state">
                    <p class="tr-title">제품상태</p>
                    <p class="tr-subtitle">
                        <c:choose>
                            <c:when test="${Sale.pro_s_cd == 'S'}">새상품(미사용)</c:when>
                            <c:when test="${Sale.pro_s_cd == 'A'}">사용감 없음</c:when>
                            <c:when test="${Sale.pro_s_cd == 'B'}">사용감 적음</c:when>
                            <c:when test="${Sale.pro_s_cd == 'C'}">사용감 많음</c:when>
                            <c:when test="${Sale.pro_s_cd == 'D'}">고장/파손 상품</c:when>
                        </c:choose>
                    </p>
                </div>
                <div class="sb-p-state">
                    <p class="tr-title">거래방식</p>
                    <p class="tr-subtitle">
                        <c:if test="${Sale.trade_s_cd_1 == 'O'}">
                            온라인
                        </c:if>
                        <c:if test="${Sale.trade_s_cd_1 == 'F' || Sale.trade_s_cd_2  == 'F'}">
                            직거래
                        </c:if>
                        <c:if test="${Sale.trade_s_cd_1 == 'D' || Sale.trade_s_cd_2  == 'D'}">
                            택배거래
                        </c:if>
                    </p>
                </div>
            </div>

            <div class="saleetc-box">
                <div class="tr-li">
                    <p class="tr-title tr-title2">주소명 : </p>
                    <p class="tr-subtitle tr-subtitle2">${Sale.addr_name}</p>
                </div>

                <div class="tr-li">
                    <p class="tr-title tr-title2">희망주소명 : </p>
                    <p class="tr-subtitle tr-subtitle2">
                    <c:choose>
                        <c:when test="${not empty Sale.pickup_addr_name}">
                            ${Sale.pickup_addr_name}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                    </p>
                </div>

                <div class="tr-li">
                    <p class="tr-title tr-title2">희망거래장소 : </p>
                    <p class="tr-subtitle tr-subtitle2">
                        <c:choose>
                            <c:when test="${not empty Sale.detail_addr}">
                                ${Sale.detail_addr}
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>

                <div class="tr-li">
                    <p class="tr-title tr-title2">브랜드</p>
                    <p class="tr-subtitle tr-subtitle2">
                        <c:choose>
                            <c:when test="${not empty Sale.brand}">
                                ${Sale.brand}
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>

                <div class="tr-li">
                    <p class="tr-title tr-title2">정가 : </p>
                    <p id="ori_price" class="tr-subtitle tr-subtitle2">
                        <c:choose>
                            <c:when test="${not empty Sale.reg_price}">
                                ${Sale.reg_price}
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>

            <div class="btn-box">
                <c:choose>
                    <c:when test="${Sale.seller_id eq sessionScope.userId}">
                        <button type="button" id="saleboard-myshop">내 상점 관리</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" id="saleboard-jjimbtn"></button>
                        <form id="form">
                        <button type="button" id="saleboard-charbtn" class="btn-salestyle">채팅하기</button>
                        </form>
                        <button type="button" id="payment-btn" class="btn-salestyle">안전결제</button>
                    </c:otherwise>
                </c:choose>

<%--                <c:choose>--%>
<%--                    <c:when test="${Sale.bid_cd == 'P'}">--%>
<%--                        <button type="button" id="pricehaggle" class="btn-salestyle">가격제시</button>--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${Sale.bid_cd == 'T'}">--%>
<%--                        <button type="button" id="sharerequest" class="btn-salestyle">나눔신청</button>--%>
<%--                    </c:when>--%>
<%--                </c:choose>--%>

            </div>
        </div>
    </div>
        <div class="Productsinfobox">
            <div class="Productinfo">
                <div class="producttitle">
                    <p>상품 정보</p>
                </div>
                <div class="productcontent">
                    <p class="productcontent-title">${Sale.contents}</p>
                </div>

                <div class="htrade-box">
                    <div class="tr-li2">
                        <p class="tr-title">희망지역</p>
                        <p class="tr-subtitle">${Sale.pickup_addr_name}</p>
                    </div>

                    <div class="tr-li2">
                        <p class="tr-title">희망거래장소</p>
                        <p class="tr-subtitle">${Sale.detail_addr}</p>
                    </div>
                </div>

                <div id="tagDiv">
                    <c:forEach var="Tag" items="${tagList}">
                        <span class="tag-name" value="${Tag.no}">#${Tag.contents}</span>
                    </c:forEach>
                </div>
            </div>
            <div class="sallerinfo">
                <div class="producttitle">
                    <p>가게 정보</p>
                </div>
                <div class="userinfo-box">
                    <div class="userinfo">
                        <p class="seller_nick"><a href="/myPage/main?ur_id=${Sale.seller_id}">${Sale.seller_nick}</a></p>
                        <div class="userprofile-box">
                            <img src="/img/display?fileName=${user.img_full_rt}" alt="${Sale.seller_nick}" id="userprofile">
                        </div>
                    </div>

                    <div id="userinfo-score-box">
                        <div class="sb-p-state">
                            <p class="tr-title">판매자 후기</p>
                            <p class="tr-subtitle">${user.rv_cmt_cnt}</p>
                        </div>
                        <div class="sb-p-state">
                            <p class="tr-title">판매자 평점</p>
                            <p class="tr-subtitle">${user.star_avg}</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>


<%--    <form id="form" action="" method="post">--%>
<%--        <c:choose>--%>
<%--            <c:when test="${sessionScope.userId == Sale.seller_id}">--%>
<%--                <select id="sal_s_cd">--%>
<%--                    <option value="S" ${Sale.sal_s_cd == 'S' ? 'selected' : ''}>판매중</option>--%>
<%--                    <option value="R" ${Sale.sal_s_cd == 'R' ? 'selected' : ''}>예약중</option>--%>
<%--                    <option value="C" ${Sale.sal_s_cd == 'C' ? 'selected' : ''}>거래완료</option>--%>
<%--                </select>--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--                <p> 거래상태 :--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${Sale.sal_s_cd == 'S'}">판매중</c:when>--%>
<%--                        <c:when test="${Sale.sal_s_cd == 'R'}">예약중</c:when>--%>
<%--                        <c:when test="${Sale.sal_s_cd == 'C'}">거래완료</c:when>--%>
<%--                    </c:choose>--%>
<%--                </p>--%>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>
<%--    </form>--%>
<%--        <!-- <p>sale : ${Sale.no}</p>--%>
<%--        <p>행정동 코드 : ${Sale.addr_cd}</p>--%>
<%--        <p>판매 카테고리명 : ${Sale.sal_name}</p>--%>
<%--        <p>희망행성구역코드 : ${Sale.pickup_addr_cd}</p>--%>
<%--        <p>끌올횟수 : ${Sale.hoist_cnt}</p>--%>
<%--        <p>가격제안/나눔신청 인원수 : ${Sale.bid_cnt}</p>--%>
<%--        <c:if test="${(Sale.seller_id == sessionScope.userId) && Sale.hoist_cnt != 3}">--%>
<%--            <button type="button" id="hoistingBtn">끌어올리기</button>--%>
<%--        </c:if>--%>

<%--        <c:if test="${Sale.seller_id == sessionScope.userId}">--%>
<%--            <button type="button" id="removeBtn">삭제하기</button>--%>
<%--            <button type="button" id="modifyBtn">수정하기</button>--%>
<%--        </c:if>--%>
<%--        <button type="button" id="returnBtn">목록</button>--%>
</div>

<script>
    $(document).ready(function () {
        let time, price, oriprice, text;

        time = remaindTime(new Date("${Sale.h_date}"));
        price = comma("${Sale.price}");
        oriprice = comma("${Sale.reg_price}");
        text = textlengover("${Sale.title}", 25);

        if(${Sale.reg_price > 0}){
            $("#ori_price").text(oriprice+"원");
        }
        $(".saleprice").text(price+"원");

        $(".saleboard-uptime").text(time);
        $(".saletitle").text(text);

        $("#saleboard-jjimbtn").on("click", function (){
            if($("#saleboard-jjimbtn").hasClass("saleboard-jjimbtn")){
                $("#saleboard-jjimbtn").removeClass("saleboard-jjimbtn");
                $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like1'] + "')");

            } else {
                $("#saleboard-jjimbtn").addClass("saleboard-jjimbtn");
                $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like2'] + "')");
            }
        });

        $(".userinfo").on("click", function (){
            <%--location.replace("/myPage/main?ur_id=${Sale.seller_id}");--%>
            window.location.href = "/myPage/main?ur_id=${Sale.seller_id}"
        });

        // $(".prev").on("click", function() {
        //     plusSlides(-1);
        // });
        //
        // $(".next").on("click", function() {
        //     plusSlides(1);
        // });
        //
        // let slideIndex = 1;
        // showSlides(slideIndex);
        //
        // function plusSlides(n) {
        //     showSlides(slideIndex += n);
        // }
        //
        // function currentSlide(n) {
        //     showSlides(slideIndex = n);
        // }
        //
        // function showSlides(n) {
        //     let i;
        //     let slides = document.getElementsByClassName("mySlides");
        //     let dots = document.getElementsByClassName("dot");
        //     if (n > slides.length) {slideIndex = 1}
        //     if (n < 1) {slideIndex = slides.length}
        //     for (i = 0; i < slides.length; i++) {
        //         slides[i].style.display = "none";
        //     }
        //     for (i = 0; i < dots.length; i++) {
        //         dots[i].className = dots[i].className.replace(" active", "");
        //     }
        //     slides[slideIndex-1].style.display = "block";
        //     dots[slideIndex-1].className += " active";
        // }

        $("#hoistingBtn").on("click", function () {
            if (confirm("끌어올리겠습니까?")) {
                let saleNo = "${Sale.no}";
                $("<input>").attr({
                    type: "hidden",
                    name: "no",
                    value: saleNo
                }).appendTo("#form");
                $("#form").attr("action", "/hoisting");
                $("#form").submit();
            }
        });

        $("#removeBtn").on("click", function () {
            if (confirm("삭제 하시겠습니까?")) {
                $("#form").attr("action", "/sale/remove?no=${Sale.no}");
                $("#form").submit();
            }
        });

        $("#modifyBtn").on("click", function () {
            if (confirm("수정 하시겠습니까?")) {
                let saleNo = "${Sale.no}";
                $("<input>").attr({
                    type: "hidden",
                    name: "no",
                    value: saleNo
                }).appendTo("#form");
                $("#form").attr("action", "/sale/modify");
                $("#form").submit();
            }
        });

        $("#returnBtn").on("click", function () {
            if (confirm("목록으로 돌아가시겠습니까?")) {
                window.location.href = "<c:url value='/sale/list'/>";
            }
        });

        $("#sal_s_cd").on("change", function () {
            let selectedValue = $(this).val();
            let sal_s_name = $("#sal_s_cd option:checked").text();
            $.ajax({
                type: "POST",
                url: "/sale/updateSaleSCd",
                data: {no: "${Sale.no}", sal_s_cd: selectedValue, seller_id: "${Sale.seller_id}"},
                success: function (data) {
                    alert("판매글 상태가 " + sal_s_name + "(으)로 변경되었습니다.");
                },
                error: function (xhr, status, error) {
                    alert("판매글 상태 변경이 실패하였습니다.");
                }
            });
        });

        $("#saleboard-charbtn").on("click", function () {
            let saleNo = "${Sale.no}";
            let sellerid = "${Sale.seller_id}";
            let seller_nick = "${Sale.seller_nick}";
            $("<input>").attr({
                type: "hidden",
                name: "sno",
                value: saleNo
            }).appendTo("#form");

            $("<input>").attr({
                type: "hidden",
                name: "id",
                value: sellerid
            }).appendTo("#form");

            $("<input>").attr({
                type: "hidden",
                name: "nick",
                value: seller_nick
            }).appendTo("#form");

            // action 속성 설정
            $("#form").attr("action", "/callchat");
            $("#form").attr("method", "POST");

            // form submit
            $("#form").submit();
        });

        const itemCount = $('#sale-slider-div').children().length;
        if(itemCount > 0){
            $('#sale-slider-div').slick({
                slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li
                infinite : true,     //무한 반복 옵션
                slidesToShow : 1,        // 한 화면에 보여질 컨텐츠 개수
                slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
                speed : 300,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
                arrows : true,         // 옆으로 이동하는 화살표 표시 여부
                dots : true,         // 스크롤바 아래 점으로 페이지네이션 여부
                autoplay : false,            // 자동 스크롤 사용 여부
                autoplaySpeed : 5000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
                pauseOnHover : false,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
                vertical : false,        // 세로 방향 슬라이드 옵션
                prevArrow : "<button type='button' class='slick-prev slick-btn'></button>",        // 이전 화살표 모양 설정
                nextArrow : "<button type='button' class='slick-next slick-btn'></button>",        // 다음 화살표 모양 설정
                dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
                draggable : false,     //드래그 가능 여부
            });
        }

        let imgInfo = cssImage.getImgInfo();
        $(".slick-prev").html('<img src="/img/display?fileName=' + imgInfo['larrow'] + '" alt="Previous" class="arrow-img prev-img">');
        $(".slick-next").html('<img src="/img/display?fileName=' + imgInfo['rarrow'] + '" alt="Next" class="arrow-img next-img">');
    });



</script>
<script src="../js/Etc.js"></script>
<%@include file="../fixed/footer.jsp"%>