<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>


<link rel="stylesheet" type="text/css" href="/css/communitylist.css">
<div class="topic-container maincontent">
    <div class="topic-slide-container">

        <div id="commu_A" class="topic-slide active">전체</div>
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
                    console.log(result.length);


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
                            let time2 = item2.r_date;

                            console.log("i1 : "+(item1!== undefined))
                            s += "<tr class='article-row'>";
                            s += "<td class='article-section'>";
                            // s += "<p class='article-no'>" + item1.no + "</p>";
                                s+="<div class='article-imgPart'>";
                                    if(item1.img_full_rt !== ""){
                                        s += "<p class='article-img'> <img src='/img/display?fileName="+ item1.img_full_rt +  "' alt='이미지'/></p>";
                                    }
                                s+="</div>";
                                s+="<div class='article-postPart'>";
                                    s += "<p class='article-title'><a href='" + contextPath + "/community/read?no=" + item1.no + "'>" + truncateString(item1.title,8) + "</a></p>";
                                    s += "<div class = 'contents-wrapper'>";
                                    s += "<p class='article-contents'><a href='" + contextPath + "/community/read?no=" + item1.no + "'>" + truncateString(item1.contents,50) + "</a></p>";
                                    s += "</div>";

                                    s += "<div class='article-etcPart'>";
                                         s+="<div class='nonTime-wrapper'>";
                                        s+="<div class='article-user'>";
                                            s += "<p class='article-nick'>" + item1.nick + "</p>";
                                            s += "<p class='article-addr_name'>" + item1.addr_name + "</p>";
                                        s+="</div>";
                                        s+="<div class='article-reaction'>";
                                            // s += "<p class='article-view_cnt'>😀" + item1.view_cnt + "</p>";
                                            // s += "<p class='article-comment_cnt'>💬" + item1.comment_count + "</p>";
                                            // s += "<p class='article-like_cnt'>❤️" + item1.like_cnt + "</p>";

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
                                    if(item2.img_full_rt !== ""){
                                        s += "<p class='article-img'> <img src='/img/display?fileName="+ item2.img_full_rt +  "' alt='이미지'/></p>";
                                    }

                                    s+="</div>";
                                    s+="<div class='article-postPart'>";
                                        s += "<p class='article-title'><a href='" + contextPath + "/community/read?no=" + item2.no + "'>" + truncateString(item2.title,8) + "</a></p>";
                                        s += "<div class = 'contents-wrapper'>";
                                        s += "<p class='article-contents'><a href='" + contextPath + "/community/read?no=" + item2.no + "'>" + truncateString(item2.contents,50) + "</a></p>";
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

                                // } else {
                                //     s += "<td class='article-section'></td>"; // 두 번째 열이 없는 경우 빈 열 추가
                            }

                            s += "</tr>";
                        }
                        s += "</table>";


                        $('article').html(s);



                        $("#pagination").html(generatePagination(result.ph));

                        setArticleIcons();
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

            // 콘솔 로그로 imgInfo 객체와 URL 출력
            console.log('imgInfo:', imgInfo);
            console.log("URL Like2:", "/img/display?fileName=" + imgInfo['Like2']);
            console.log("URL chat:", "/img/display?fileName=" + imgInfo['chat']);
            console.log("URL person:", "/img/display?fileName=" + imgInfo['person']);

            $(".article-like_cnt .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['Like2'] + "')");
            $(".article-comment_cnt .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['chat'] + "')");
            $(".article-view_cnt .icon").css("background-image", "url('/img/display?fileName=" + imgInfo['person'] + "')");

            // 각 요소의 CSS 배경 이미지가 올바르게 설정되었는지 확인하기 위해 추가
            console.log("Like2 element background image:", $(".article-like_cnt .icon").css("background-image"));
            console.log("Chat element background image:", $(".article-comment_cnt .icon").css("background-image"));
            console.log("Person element background image:", $(".article-view_cnt .icon").css("background-image"));
        };


        const generatePagination = function (pagination) {
            console.log(pagination);
            console.log(pagination.totalCnt);

            let paginationHtml = '';


            <%--paginationHtml += '<span class="page-space">${pagenation.page}</span>';--%>
            // for (let i = 1; i <= pagenation.totalPage; i++) {
            //
            // }

            <%--if (pagination.nextPage) {--%>
            <%--    paginationHtml += `<span class="page-link" data-page="${pagination.page + 1}">Next</span>`;--%>
            <%--    paginationHtml += `<span class="page-link" data-page="${pagination.totalPage}">Last</span>`;--%>
            <%--} else {--%>
            <%--    paginationHtml += `<span class="page-link disabled">Next</span>`;--%>
            <%--    paginationHtml += `<span class="page-link disabled">Last</span>`;--%>
            <%--}--%>


            $("#pagination").empty(); // 기존에 있는 페이지 내용 비우기


            if (pagination.totalCnt != null && pagination.totalCnt != 0) {
                let pageContainer = $('<div>').attr('id', 'pageNumber').css('text-align', 'center'); // 새로운 div 엘리먼트 생성
                if (pagination.prevPage) {
                    // pageContainer.append('<a href="#" class="page-link">&lt;</a>');
                    pageContainer.append('<a href="#" class="page-link" data-page="' + (pagination.beginPage - 1) + '">&lt;</a>');
                }
                for (let i = pagination.beginPage; i <= pagination.endPage; i++) {
                    // 페이지 번호 사이에 공백 추가
                    pageContainer.append('<span class="page-space"></span>');
                    // pageContainer.append('<a href="#" class="page-link">' + i + '</a>');
                    pageContainer.append('<a href="#" class="page-link" data-page="' + i + '">' + i + '</a>');
                }
                if (pagination.nextPage) {
                    pageContainer.append('<span class="page-space"></span>');
                    // pageContainer.append('<a href="#" class="page-link">&gt;</a>');
                    pageContainer.append('<a href="#" class="page-link" data-page="' + (pagination.endPage + 1) + '">&gt;</a>');
                }
                $("#pagination").html(pageContainer); // 새로 생성한 페이지 컨테이너를 추가
            }



        };

        // Load initial data
        loadArticles('commu_A',1);

        // Click events for category buttons
        // $(".topic-slide").click(function () {
        //
        //     $(".topic-slide").removeClass('active');
        //     const category = $(this).attr('id');
        //     $(this).addClass('active');
        //     loadArticles(category,1);
        // });
        //
        // // Click events for pagination links
        // $(document).on('click', '.page-link', function () {
        //     let page = $(this).data('page');
        //     console.log("Clicked page : " + page);
        //     const category = $(".topic-slide.active").attr('id') || 'commu_A';
        //     loadArticles(category, page);
        // });



        $(document).on('click', '.topic-slide', function () {
            // Remove active class from all topic-slide elements
            $('.topic-slide').removeClass('active');

            // Add active class to the clicked topic-slide element
            $(this).addClass('active');

            // Get the category from the clicked element
            const category = $(this).attr('id') || 'commu_A';

            // Load articles for the selected category
            loadArticles(category, 1); // Load the first page of articles
        });

        // Click events for pagination links
        $(document).on('click', '.page-link', function () {
            let page = $(this).data('page');
            console.log("Clicked page : " + page);
            const category = $(".topic-slide.active").attr('id') || 'commu_A';
            loadArticles(category, page);
        });



    });

</script>

<script src="/js/img.js"></script>
<%@include file="fixed/footer.jsp"%>
