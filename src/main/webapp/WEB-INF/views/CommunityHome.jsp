<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/css/mycommustyle.css">
<article class="maincontent community-home-box">
    <div class="topic-best tobic-commuhome">
        <h2 class="topic-name topic-best-h2">실시간 우리 지역 인기글</h2>
        <p class="commuhome-btn-more"><a href="${pageContext.request.contextPath}/community/list" class="commulistlink">더보기 ></a></p>
        <hr id="topic-best-horizon"/>
    </div>
<%--    <div class="topic-bla tobic-commuhome">--%>
<%--        <h3 class="topic-name">블라블라</h3>--%>
<%--        <p class="commuhome-btn-more"><a href="${pageContext.request.contextPath}/community/list">더보기></a></p>--%>
<%--    </div>--%>
<%--    <div class="topic-worry tobic-commuhome">--%>
<%--        <h3 class="topic-name">고민/상담</h3>--%>
<%--        <p class="commuhome-btn-more"><a href="${pageContext.request.contextPath}/community/list">더보기></a></p>--%>
<%--    </div>--%>
<%--    <div class="topic-love tobic-commuhome">--%>
<%--        <h3 class="topic-name">연애/썸</h3>--%>
<%--        <p class="commuhome-btn-more"><a href="${pageContext.request.contextPath}/community/list">더보기></a></p>--%>
<%--    </div>--%>
</article>

<script>
    $(document).ready(function() {
        $.ajax({
            url: '/community/home/story',
            type: "GET",
            dataType: "json",
            success: function(response) {
                displayTopTen(response);
                displayBlaBla(response);
                displayWorry(response);
                displayLove(response);

                // 아이콘 설정
                setArticleIcons();
            },
            error: function() {

            }
        });

        function displayTopTen(data) {
            let getTopTen = data.sort(function(a, b) {
                return b.view_cnt - a.view_cnt;
            }).slice(0, 10);
            let s = "<table id='community-top'>";
            getTopTen.forEach((item, index) => {
                s += "<tr>";
                s += "<td class='community-rank'>" + (index+1) + "</td>";
                s += "<td class='community-name'>" + item.commu_name + "</td>";
                s += "<td class='community-title'><a href='/community/read?no=" + item.no + "'>" + item.title + "</a></td>";
                s += "<td class='community-contents'>" + truncateString(item.contents, 5) + "</td>";
                s += "<td class='community-nick'>" + item.nick + "</td>";
                s += "<td class='community-icons'>";
                s += "<div class='icon-wrapper'><span class='icon view-icon'></span><span>" + item.view_cnt + "</span></div>";
                s += "<div class='icon-wrapper'><span class='icon comment-icon'></span><span>" + item.comment_count + "</span></div>";
                s += "<div class='icon-wrapper'><span class='icon like-icon'></span><span>" + item.like_cnt + "</span></div>";
                s += "</td>";
                s += "</tr>";
            });
            s += "</table>";
            $('.topic-best').append(s);
        }

        function displayBlaBla(data) {
            let getBla = data.filter(item => item.commu_cd === 'commu_B').slice(0, 5);
            let s = "<table>";
            if (getBla.length > 0) {
                getBla.forEach((item) => {
                    s += "<tr>";
                    s += "<td class='community-title'><a href='/community/read?no=" + item.no + "'>" + item.title + "</a></td>";
                    s += "<td class='community-icons'>";
                    s += "<div class='icon-wrapper'><span class='icon view-icon'></span><span>" + item.view_cnt + "</span></div>";
                    s += "</td>";
                    s += "</tr>";
                });
                s += "</table>";
            } else {
                s = "<div>해당 게시판의 게시글이 없습니다.</div>";
            }
            $('.topic-bla').append(s);
        }

        function displayWorry(data) {
            let getBla = data.filter(item => item.commu_cd === 'commu_W').slice(0, 5);
            let s = "<table>";
            if (getBla.length > 0) {
                getBla.forEach((item) => {
                    s += "<tr>";
                    s += "<td class='community-title'><a href='/community/read?no=" + item.no + "'>" + item.title + "</a></td>";
                    s += "<td class='community-icons'>";
                    s += "<div class='icon-wrapper'><span class='icon view-icon'></span><span>" + item.view_cnt + "</span></div>";
                    s += "</td>";
                    s += "</tr>";
                });
                s += "</table>";
            } else {
                s = "<div>해당 게시판의 게시글이 없습니다.</div>";
            }
            $('.topic-worry').append(s);
        }

        function displayLove(data) {
            let getBla = data.filter(item => item.commu_cd === 'commu_L').slice(0, 5);
            let s = "<table>";
            if (getBla.length > 0) {
                getBla.forEach((item) => {
                    s += "<tr>";
                    s += "<td class='community-title'><a href='/community/read?no=" + item.no + "'>" + item.title + "</a></td>";
                    s += "<td class='community-icons'>";
                    s += "<div class='icon-wrapper'><span class='icon view-icon'></span><span>" + item.view_cnt + "</span></div>";
                    s += "</td>";
                    s += "</tr>";
                });
                s += "</table>";
            } else {
                s = "<div>해당 게시판의 게시글이 없습니다.</div>";
            }
            $('.topic-love').append(s);
        }

        function truncateString(str, num) {
            if (num < str.length) {
                return str.slice(0, num) + "...";
            } else {
                return str;
            }
        }

        function setArticleIcons() {
            let imgInfo = {
                'Like2': 'img/Like2.png',
                'chat': 'img/chat.png',
                'person': 'img/person.png'
            };

            $(".like-icon").css("background-image", "url('/img/display?fileName=" + imgInfo['Like2'] + "')");
            $(".comment-icon").css("background-image", "url('/img/display?fileName=" + imgInfo['chat'] + "')");
            $(".view-icon").css("background-image", "url('/img/display?fileName=" + imgInfo['person'] + "')");
        }
    });
</script>
