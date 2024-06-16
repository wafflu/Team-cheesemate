<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>


<link rel="stylesheet" type="text/css" href="/css/communitylist.css">
<div class="topic-container maincontent">
    <div class="topic-slide-container">

        <div id="commu_A" class="topic-slide topic-active">전체</div>
        <%--<h3 id="commu_H" class="topic-slide">인기글</h3>--%>
        <div id="commu_B" class="topic-slide">블라블라</div>
        <div id="commu_L" class="topic-slide">연애/썸</div>
        <div id="commu_W" class="topic-slide">고민/상담</div>
        <input type = "button" value = "글쓰기" onclick = "location.href='<c:url value="/community/write"/>'" id="write-btn">

        <article></article>
        <div id="pagination"></div>
        <input type="hidden" id="current-commu-cd" value="">
    </div>

    <div class="hidden-notFoundList">
        <p>아직 작성된 글이 없습니다.</p>
    </div>

<%--    <div class="hidden-removeByAdmin">--%>
<%--        <p>운영진에 의해 삭제된 글입니다.</p>--%>
<%--    </div>--%>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="/js/Etc.js"></script>
<script>


    const uploadImage = (function() {
        let imginfo = [];

        return {
            getImgInfo: function() {
                return imginfo;
            }
        };
    })();

    function truncateString(str, num){
        if(num < str.length){
            return str.slice(0,num) + "...";
        }else{
            return str;
        }
    }



    $(document).ready(function () {

        const contextPath = "<c:out value='${pageContext.request.contextPath}' />";

        const loadArticles = function (category, page, pageSize = 6) {

            $.ajax({
                type: "GET",
                url: `${contextPath}/community/story`,
                data: {
                    category: category,
                    page: page,
                    pageSize: pageSize
                },
                dataType: "json",
                success: function (result) {
                    if (!result.content || result.content.length === 0) { // 결과가 없을 경우
                        let e = "<div id='nonPost'>해당 게시판의 게시글이 없습니다.</div>"
                        console.log(e);
                        $('article').html(e);
                    }else{
                        let s = "";
                        s += "<table class = 'article-table'>";
                        for (let i = 0; i < result.content.length; i += 2) {
                            let item1 = result.content[i];
                            let item2 = result.content[i + 1];
                            let time1 = item1.r_date;
                            let time2 = item2 ? item2.r_date : null;

                            s += "<tr class='article-row'>";
                            s += "<td class='article-section'>";
                            // s += "<p class='article-no'>" + item1.no + "</p>";
                                s+="<div class='article-imgPart'>";
                                    if(item1.img_full_rt !== null){
                                        s += "<p class='article-img'> <img src='/img/display?fileName="+ item1.img_full_rt +  "' alt='이미지'/></p>";
                                    }
                                s+="</div>";
                                s+="<div class='article-postPart'>";
                                    s += "<p class='article-title'><a href='" + contextPath + "/community/read?no=" + item1.no + "'>" + truncateString(item1.title,8) + "</a></p>";
                                    s += "<div class = 'contents-wrapper'>";
                                    s += "<p class='article-contents'><a href='" + contextPath + "/community/read?no=" + item1.no + "'>" + truncateString(item1.contents,30) + "</a></p>";
                                    s += "</div>";

                                    s += "<div class='article-etcPart'>";
                                         s+="<div class='nonTime-wrapper'>";
                                        s+="<div class='article-user'>";
                                            s += "<p class='article-nick'>" + item1.nick + "</p>";
                                            s += "<p class='article-addr_name'>" + item1.addr_name + "</p>";
                                        s+="</div>";
                                        s+="<div class='article-reaction'>";
                                            s += "<p class='article-view_cnt'><span class='icon'></span>" + item1.view_cnt + "</p>";
                                            s += "<p class='article-comment_cnt'><span class='icon'></span>" + item1.comment_count + "</p>";
                                            s += "<p class='article-like_cnt'><span class='icon'></span>" + item1.like_cnt + "</p>";
                                        s += "</div>";
                                        s+="</div>";
                                        s += "<div class='article-time'>";
                                            s += `<p>` + remaindTime(new Date(time1))+ `</p>`;
                                        s += "</div>";
                                    s+="</div>";
                                s+="</div>";
                            s += "</div>";
                            s += "</td>";

                            if (item2 !== undefined) {
                                s += "<td class='article-section'>";
                                // s += "<p class='article-no'>" + item2.no + "</p>";
                                    s+="<div class='article-imgPart'>";
                                    if(item2.img_full_rt !== null){
                                        s += "<p class='article-img'> <img src='/img/display?fileName="+ item2.img_full_rt +  "' alt='이미지'/></p>";
                                    }

                                    s+="</div>";
                                    s+="<div class='article-postPart'>";
                                        s += "<p class='article-title'><a href='" + contextPath + "/community/read?no=" + item2.no + "'>" + truncateString(item2.title,8) + "</a></p>";
                                        s += "<div class = 'contents-wrapper'>";
                                        s += "<p class='article-contents'><a href='" + contextPath + "/community/read?no=" + item2.no + "'>" + truncateString(item2.contents,30) + "</a></p>";
                                        s += "</div>";

                                        s += "<div class='article-etcPart'>";
                                            s+="<div class='nonTime-wrapper'>";
                                            s+="<div class='article-user'>";
                                                s += "<p class='article-nick'>" + item2.nick + "</p>";
                                                s += "<p class='article-addr_name'>" + item2.addr_name + "</p>";
                                            s+="</div>";
                                            s+="<div class='article-reaction'>";
                                                s += "<p class='article-view_cnt'><span class='icon'></span>" + item2.view_cnt + "</p>";
                                                s += "<p class='article-comment_cnt'><span class='icon'></span>" + item2.comment_count + "</p>";
                                                s += "<p class='article-like_cnt'><span class='icon'></span>" + item2.like_cnt + "</p>";
                                            s += "</div>";
                                            s+="</div>";
                                            s += "<div class='article-time'>";
                                                s += `<p>` + remaindTime(new Date(time2))+ `</p>`;
                                            s += "</div>";

                                        s+="</div>";
                                    s += "</div>";
                                s+="</td>";
                            }
                            s += "</tr>";
                        }
                        s += "</table>";

                        $('article').html(s);

                        $("#pagination").html(generatePagination(result.ph));

                        setArticleIcons();
                        // 페이지 링크 클릭 시 active 클래스 추가
                        $('#pagination').find('.page-link').each(function() {
                            if ($(this).data('page') == page) {
                                $(this).addClass('active');
                            }
                        });
                    }



                },
                error: function () {
                    alert("error");
                }
            });
        };

        const setArticleIcons = function () {
            let imgInfo = {
                'Like2': 'img/Like2.png',
                'chat': 'img/chat.png',
                'person': 'img/person.png'
            };


            $(".article-like_cnt .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['Like2'] + "')");
            $(".article-comment_cnt .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['chat'] + "')");
            $(".article-view_cnt .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['person'] + "')");


        };


        const generatePagination = function (pagination) {
            console.log(pagination);
            console.log(pagination.totalCnt);

            let paginationHtml = '';
            $("#pagination").empty();

            if (pagination.totalCnt != null && pagination.totalCnt != 0) {
                let pageContainer = $('<div>').attr('id', 'pageNumber').css('text-align', 'center'); // 새로운 div 엘리먼트 생성
                if (pagination.prevPage) {
                    pageContainer.append('<button class="page-link" data-page="' + (pagination.beginPage - 1) + '">&lt;</button>');
                }
                for (let i = pagination.beginPage; i <= pagination.endPage; i++) {
                    // 페이지 번호 사이에 공백 추가
                    pageContainer.append('<span class="page-space"></span>');

                    // pageContainer.append('<a href="#" class="page-link" data-page="' + i + '">' + i + '</a>');
                    // pageContainer.append('<button class="' + (i == pagination.page ? "page-link" : "") + '" data-page="' + i + '">' + i + '</button>');
                    pageContainer.append('<button class="' + (i == pagination.page ? "page-link active" : "page-link") + '" data-page="' + i + '">' + i + '</button>');

                }
                if (pagination.nextPage) {
                    pageContainer.append('<span class="page-space"></span>');
                    pageContainer.append('<button class="page-link" data-page="' + (pagination.endPage + 1) + '">&gt;</button>');
                }
                $("#pagination").html(pageContainer); // 새로 생성한 페이지 컨테이너를 추가
            }



        };

        // Load initial data
        loadArticles('commu_A',1);

        $(document).on('click', '.topic-slide', function () {
            // Remove active class from all topic-slide elements
            $('.topic-slide').removeClass('topic-active');

            // Add active class to the clicked topic-slide element
            $(this).addClass('topic-active');

            // Get the category from the clicked element
            const category = $(this).attr('id') || 'commu_A';

            // Load articles for the selected category
            loadArticles(category, 1); // Load the first page of articles
        });

        // Click events for pagination links
        $(document).on('click', '.page-link', function () {
            let page = $(this).data('page');
            const category = $(".topic-slide.active").attr('id') || 'commu_A';
            loadArticles(category, page);
        });

        // 추가된 버튼 클릭 이벤트 핸들러
        $(document).on('click', 'button.page-link', function (event) {
            event.preventDefault(); // 기본 동작 방지
            $('.page-link').removeClass('active'); // 기존 active 클래스 제거
            console.log($(this));
            $(this).addClass('active'); // 클릭한 페이지 링크에 active 클래스 추가
            let page = $(this).data('page');
            const category = $(".topic-slide.active").attr('id') || 'commu_A';
            loadArticles(category, page);
        });



    });

</script>

<script src="/js/img.js"></script>
<%@include file="fixed/footer.jsp"%>
