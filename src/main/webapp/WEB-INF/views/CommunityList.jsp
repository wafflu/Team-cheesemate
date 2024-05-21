<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/css/communitylist.css">
<div class="maincontent">

    <style>
        /* Add some basic styling for the pagination buttons */
        #pagenation {
            margin: 20px 0;
            text-align: center;
        }

        .page-link {
            display: inline-block;
            padding: 8px 16px;
            margin: 0 4px;
            border: 1px solid #ddd;
            color: #333;
            cursor: pointer;
        }

        .page-link.active {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }

        .page-link.disabled {
            color: #ddd;
            cursor: not-allowed;
        }

        .page-link:hover:not(.disabled) {
            background-color: #ddd;
        }
    </style>



<h3 id="commu_A" class="topic-slide">전체</h3>
<%--<h3 id="commu_H" class="topic-slide">인기글</h3>--%>
<h3 id="commu_B" class="topic-slide">블라블라</h3>
<h3 id="commu_L" class="topic-slide">연애/썸</h3>
<h3 id="commu_W" class="topic-slide">고민/상담</h3>

<article></article>
<div id="pagenation"></div>
<input type="hidden" id="current-commu-cd" value="">

</div>
<script>

    $(document).ready(function () {
        const contextPath = '${pageContext.request.contextPath}';

        const loadArticles = function (category, page, pageSize = 5) {
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
                    s += "<table>";
                    for (let i = 0; i < result.content.length; i++) {
                        let item = result.content[i];
                        s += "<tr>";
                        s += "<td>" + item.no + "</td>";
                        s += "<td><a href='" + contextPath + "/community/read?no=" + item.no + "'>" + item.title + "</a></td>";
                        s += "<td>" + item.nick + "</td>";
                        s += "<td>" + item.view_cnt + "</td>";
                        s += "<td>" + item.addr_name + "</td>";
                        s += "<td>👁️" + item.view_cnt + "</td>";
                        s += "<td>💬" + item.comment_count + "</td>";
                        s += "<td>❤️" + item.like_cnt + "</td>";
                        s += "</tr>";
                    }
                    s += "</table>";
                    $('article').html(s);
                    // Update pagination
                    let page = generatePagination(result.ph);
                    console.log("page : "+page)
                    $("#pagination").html(page);

                },
                error: function () {
                    alert("error");
                }
            });
        };

        const generatePagination = function (pagenation) {
            let pagination = JSON.stringify(pagenation);
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
            console.log("paginationHtml : "+paginationHtml);
            return paginationHtml;
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
