<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp"%>
<link rel="stylesheet" href="/css/saleBoard.css">
<c:set var="userId" value="${sessionScope.userId != null ? sessionScope.userId : ''}" />
<div class="maincontent saleboardarea">
    <div class="saleboard-top-box">
            <div id="sale-slider-div" class="sale-img-box">
                <c:forEach items="${imglist}" var="img">
                    <c:if test="${img.imgtype eq 'w'}">
                        <div class="sale-img">
                            <c:if test="${Sale.sal_s_cd eq 'R'}">
                                <div class='saleStatus-box'>
                                    <span class='saleStatusText'>예약중</span>
                                </div>
                            </c:if>
                            <c:if test="${Sale.sal_s_cd eq 'C'}">
                                <div class='saleStatus-box'>
                                    <span class='saleStatusText'>판매완료</span>
                                </div>
                            </c:if>
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
                <div class="title-info">
                    <c:if test="${Sale.tx_s_cd == 'F'}">
                        <span class="share-title">[나눔]</span>
                    </c:if>
                    <p class="saletitle">${Sale.title}</p>
                </div>

                <p class="saleprice">${Sale.price}원</p>
            </div>

            <div class="tiem-view-box">
                <p class="saleboard-uptime">${Sale.h_date} </p>
                <p>·</p>
                <p>조회 : ${Sale.view_cnt}</p>
                <p>·</p>
                찜 : <p id="jjimCnt">${Sale.like_cnt}</p>
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
                        <button type="button" id="saleboard-jjimbtn">
                        <p class="like_cnt" id="likeCount">${Sale.like_cnt}</p>
                        </button>
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
                <p class="productcontent-content">${Sale.contents}</p>
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

</div>
<script src="/js/jjim.js"></script>
<script>
    $(document).ready(function () {
        // 판매글 번호
        let sal_no = "${Sale.no}";
        // 세션 아이디
        let ur_id = "${userId}";

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

        let checkhart = ${result.check_like};

        if(checkhart===1) {
            $("#saleboard-jjimbtn").addClass("saleboard-jjimbtn"); // 찬 하트로
            $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like2'] + "')");
        }else {
            $("#saleboard-jjimbtn").removeClass("saleboard-jjimbtn"); // 빈 하트로
            $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like1'] + "')");
        }

        // $("#saleboard-jjimbtn").on("click", function (){     // 클릭했을떄
        //     if($("#saleboard-jjimbtn").hasClass("saleboard-jjimbtn")){ // 색깔이 채워져있으면
        //         $("#saleboard-jjimbtn").removeClass("saleboard-jjimbtn"); // 빈 하트로
        //         $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like1'] + "')");
        //
        //     } else { // 안채워져있으면
        //         $("#saleboard-jjimbtn").addClass("saleboard-jjimbtn"); // 찬 하트로
        //         $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like2'] + "')");
        //     }
        // });
        $("#saleboard-jjimbtn").on("click", function(){

            $.ajax({
                type:'GET',       // 요청 메서드
                url : "/myPage/like?sal_no="+sal_no+ "&ur_id="+ur_id,
                headers: {"content-type": "application/json"}, // 요청 헤더
                dataType : 'json',
                success:function(data){
                    let row = data.row;
                    let likeCnt = data.likeCnt;
                    if(row==1){
                        $("#saleboard-jjimbtn").addClass("saleboard-jjimbtn"); // 찬 하트로
                        $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like2'] + "')");

                        // alert("상품을 찜 하셨습니다.");
                        let result = confirm('찜목록으로 이동하시겠습니까?');
                        if (result) {
                            //yes
                            //찜 리스트 페이지 생성 후 -> 찜리스트 페이지 이동으로 변경
                            setCookie("JJIM", true, 1);
                            location.href='/myPage/main';
                        }
                        // like_cnt 업데이트
                        $("#likeCount").text(likeCnt);
                        $("#jjimCnt").text(likeCnt);

                    } else if(row == -1){
                        alert("로그인이 필요한 서비스입니다.");
                    }
                    else {
                        // alert("상품을 찜 취소하셨습니다. ");
                        $("#saleboard-jjimbtn").removeClass("saleboard-jjimbtn"); // 빈 하트로
                        $("#saleboard-jjimbtn").css("background-image", "url('/img/display?fileName=" + cssImage.getImgInfo()['Like1'] + "')");
                        // like_cnt 업데이트
                        $("#likeCount").text(likeCnt);
                        $("#jjimCnt").text(likeCnt);
                    }

                },
                error:function(error){
                    console.log(error);
                }
            });
        });

        $(".userinfo").on("click", function (){
            <%--location.replace("/myPage/main?ur_id=${Sale.seller_id}");--%>
            window.location.href = "/myPage/main?ur_id=${Sale.seller_id}"
        });

        $("#saleboard-myshop").on("click", function (){
            window.location.href = "/sale/manage"
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