<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/mystyle.css">
<link rel="stylesheet" type="text/css" href="/css/communitylist.css">
<div class="topic-container maincontent">
    <div id="commu_A" class="topic-slide">전체</div>
    <%--<h3 id="commu_H" class="topic-slide">인기글</h3>--%>
    <div id="commu_B" class="topic-slide">블라블라</div>
    <div id="commu_L" class="topic-slide">연애/썸</div>
    <div id="commu_W" class="topic-slide">고민/상담</div>
    <input type = "button" value = "글쓰기" onclick = "location.href='<c:url value="/community/write"/>'" id="write-btn">

    <article></article>
    <div id="pagination"></div>
    <input type="hidden" id="current-commu-cd" value="">

</div>
<script>

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
                    let s = "";
                    s += "<table class = 'article-table'>";
                    for (let i = 0; i < result.content.length; i += 2) {
                        let item1 = result.content[i];
                        let item2 = result.content[i + 1];
                        console.log("i2 : "+(item2!== undefined))
                        s += "<tr class='article-row'>";

                        s += "<td class='article-section'>";
                        s += "<p class='article-no'>" + item1.no + "</p>";
                        s += "<p class='article-title'><a href='" + contextPath + "/community/read?no=" + item1.no + "'>" + item1.title + "</a></p>";
                        s += "<p class='article-nick'>" + item1.nick + "</p>";
                        s += "<p class='article-view_cnt'>" + item1.view_cnt + "</p>";
                        s += "<p class='article-addr_name'>" + item1.addr_name + "</p>";
                        s += "<p class='article-view_cnt'>👁️" + item1.view_cnt + "</p>";
                        s += "<p class='article-comment_cnt'>💬" + item1.comment_count + "</p>";
                        s += "<p class='article-like_cnt'>❤️" + item1.like_cnt + "</p>";
                        s += "</td>";

                        if (item2 !== undefined) {
                            s += "<td class='article-section'>";
                            s += "<p class='article-no'>" + item2.no + "</p>";
                            s += "<p class='article-title'><a href='" + contextPath + "/community/read?no=" + item2.no + "'>" + item2.title + "</a></p>";
                            s += "<p class='article-nick'>" + item2.nick + "</p>";
                            s += "<p class='article-view_cnt'>" + item2.view_cnt + "</p>";
                            s += "<p class='article-addr_name'>" + item2.addr_name + "</p>";
                            s += "<p class='article-view_cnt'>👁️" + item2.view_cnt + "</p>";
                            s += "<p class='article-comment_cnt'>💬" + item2.comment_count + "</p>";
                            s += "<p class='article-like_cnt'>❤️" + item2.like_cnt + "</p>";
                            s += "</td>";
                        // } else {
                        //     s += "<td class='article-section'></td>"; // 두 번째 열이 없는 경우 빈 열 추가
                        }

                        s += "</tr>";
                    }
                    s += "</table>";
                    $('article').html(s);

                    console.log("ㄹㅣ절트 ㅍ;" + result.ph.totalPage);
                    
                    $("#pagination").html(generatePagination(result.ph));

                },
                error: function () {
                    alert("error");
                }
            });
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
        $(".topic-slide").click(function () {
            const category = $(this).attr('id');
            $(".topic-slide").removeClass('active');
            $(this).addClass('active');
            loadArticles(category,1);
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
<%@include file="fixed/footer.jsp"%>
