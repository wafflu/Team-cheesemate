<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/css/mainslider.css">
<style>
    .eventImg{
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .saleImg{
        width: 100%;
    }

    .eventsliderimg{
        position: relative;
    }

    .eventImgtitle{
        position: absolute;
        left: 20px;
        bottom: 25px;
        font-weight: bold;
        font-size: 34px;
        color:#fff;
        width: 300px;
    }
</style>
<div class="maincontent">
    <div id="slider-div" class="slider-div"></div>

    <div id="sale_slider_box">
        <div id="home_sale_title">방금 등록된 상품</div>
        <a href="/sale/list" id="home_sale_more">더보기 ></a>
        <div id="sale-slider-div" class="slider-div"></div>
    </div>
</div>

<%@include file="CommunityHome.jsp"%>

<script src="/js/Etc.js"></script>
<script>
    const uploadImage = (function() {
        let imginfo = [];

        <c:forEach items="${imglist}" var="img">
        <c:if test="${img.imgtype eq 'r'}">
        imginfo.push(
            {
                "file_rt" : "${img.file_rt}",
                "o_name" : "${img.o_name}",
                "e_name" : "${img.e_name}"
            }
        )
        </c:if>
        </c:forEach>

        return {
            getImgInfo: function() {
                return imginfo;
            }
        };
    })();

    const EventImage = (function() {
        let str = "";

        <c:forEach items="${eventlist}" var="event">
        str += "<div class='eventsliderimg'>";
        str += `<a href="/event/read?evt_no=${event.evt_no}">`;
        str += `<img src="/img/display?fileName=${event.img_full_rt}" alt="${event.title}" class="eventImg">`;
        str += `<p class="eventImgtitle">${event.title}</p>`;
        str += `</a>`;
        str += "</div>";
        </c:forEach>

        $("#slider-div").html(str);

        return {
            getEventInfo: function() {
                return str;
            }
        };
    })();

    function truncateText(element) {
        const $element = $(element);
        const originalText = $element.text();
        const width = $element.width();
        let text = originalText;
        let truncated = false;

        while ($element[0].scrollWidth > width) {
            text = text.slice(0, -1);
            $element.text(text + "...");
            truncated = true;
        }

        if (!truncated) {
            $element.text(originalText);
        }
    }

    function truncateAllText(selector) {
        $(selector).each(function() {
            truncateText(this);
        });
    }

    const saleImage = (function() {
        let str = "";
        let time, price, text;
        <c:forEach items="${salelist}" var="sale">
        time = remaindTime(new Date("${sale.h_date}"));
        price = comma("${sale.price}");

        text = textlengover("${sale.title}", 13);
        str += "<div class='salesliderimg'>";
        str += `<a href="/sale/read?no=${sale.no}">`;
        str += `<img src="/img/display?fileName=${sale.img_full_rt}" alt="${sale.img_full_rt}" class="saleImg">`;
        str += `<h2 class="home_sale_title home_sale">`+text+`</h2>`;
        str += "<div class='saleinfo-info home_sale'>";
        str += `<p class="home_sale_price">`+price+`원</p>`;
        str += `<p class="home_sale_h_date">`+time+`</p>`;
        str += "</div>";
        str += `<p class="home_sale_addr_name home_sale">${sale.addr_name}</p>`;
        str += `</a>`;
        str += "</div>";
        </c:forEach>

        $("#sale-slider-div").html(str);


        return {
            getSaleInfo: function() {
                return str;
            }
        };
    })();

    const itemCount = $('#slider-div').children().length;
    if(itemCount > 0){
        $('#slider-div').slick({
            slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li
            infinite : true,     //무한 반복 옵션
            slidesToShow : 3,        // 한 화면에 보여질 컨텐츠 개수
            slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
            speed : 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
            arrows : true,         // 옆으로 이동하는 화살표 표시 여부
            dots : true,         // 스크롤바 아래 점으로 페이지네이션 여부
            autoplay : true,            // 자동 스크롤 사용 여부
            autoplaySpeed : 3000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
            pauseOnHover : false,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
            vertical : false,        // 세로 방향 슬라이드 옵션
            prevArrow : "<button type='button' class='slick-prev slick-btn'></button>",        // 이전 화살표 모양 설정
            nextArrow : "<button type='button' class='slick-next slick-btn'></button>",        // 다음 화살표 모양 설정
            dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
            draggable : false,     //드래그 가능 여부
        });
    }


    const saleitemCount = $('#sale-slider-div').children().length;
    if(saleitemCount > 0){
        $('#sale-slider-div').slick({
            slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li
            infinite : true,     //무한 반복 옵션
            slidesToShow : 5,        // 한 화면에 보여질 컨텐츠 개수
            slidesToScroll : 5,        //스크롤 한번에 움직일 컨텐츠 개수
            speed : 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
            arrows : true,         // 옆으로 이동하는 화살표 표시 여부
            dots : false,         // 스크롤바 아래 점으로 페이지네이션 여부
            autoplay : true,            // 자동 스크롤 사용 여부
            autoplaySpeed : 5000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
            pauseOnHover : false,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
            vertical : false,        // 세로 방향 슬라이드 옵션
            prevArrow : "<button type='button' class='slick-prev slick-btn'></button>",        // 이전 화살표 모양 설정
            nextArrow : "<button type='button' class='slick-next slick-btn'></button>",        // 다음 화살표 모양 설정
            dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
            draggable : false,     //드래그 가능 여부
        });
    }

    document.addEventListener("DOMContentLoaded", function() {
        let imgInfo = cssImage.getImgInfo();
        $(".slick-prev").css("background-image", "url('/img/display?fileName=" + imgInfo['larrow'] + "')");
        $(".slick-next").css("background-image", "url('/img/display?fileName=" + imgInfo['rarrow'] + "')");
    });
</script>
<script src="/js/img.js"></script>