<%-- Created by IntelliJ IDEA. User: gominjeong Date: 5/2/24 Time: 11:12 AM To
change this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ page session="false" %>
<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous"
></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
<html>
  <head>
    <link rel="stylesheet" href="/css/reset.css" />
    <link rel="stylesheet" href="/css/mystyle.css" />
    <link rel="stylesheet" href="/css/communityhome.css" />
    <title>Title</title>
  </head>
  <body>
    <article id="community-home">
      <div class="topic-best">
        <h2 class="topic-name">실시간 우리 지역 인기글</h2>
        <p class="btn-more">
          <a href="${pageContext.request.contextPath}/community/list"
            >더보기></a
          >
        </p>
        <hr id="topic-best-horizon" />
        <input type = "button" value = "글쓰기" onclick = "location.href='<c:url
          value="/community/write"
        />'" id="write-btn">
      </div>
      <div class="topic-bla">
        <h3 class="topic-name">블라블라</h3>
        <p class="btn-more">
          <a href="${pageContext.request.contextPath}/community/list"
            >더보기></a
          >
        </p>
      </div>
      <div class="topic-worry">
        <h3 class="topic-name">고민/상담</h3>
        <p class="btn-more">
          <a href="${pageContext.request.contextPath}/community/list"
            >더보기></a
          >
        </p>
      </div>

      <div class="topic-love">
        <h3 class="topic-name">연애/썸</h3>
        <p class="btn-more">
          <a href="${pageContext.request.contextPath}/community/list"
            >더보기></a
          >
        </p>
      </div>
    </article>
  </body>
  <script>
    $(document).ready(function () {
      $.ajax({
        url: "/community/home/story",
        type: "GET",
        dataType: "json",
        success: function (response) {
          console.log(response);
          displayTopTen(response);
          displayBlaBla(response);
          displayWorry(response);
          displayLove(response);
        },
        error: function () {
          console.log("error");
        },
      });

      function displayTopTen(data) {
        //인기글

        let getTopTen = data
          .sort(function (a, b) {
            return b.view_cnt - a.view_cnt;
          })
          .slice(0, 10);
        console.log(getTopTen[0].title);
        let s = "<table>";
        // getTopTen.forEach(((item)=> s+= createTableRow(item,index)));
        getTopTen.forEach((item, index) => {
          s += "<tr>";
          s += "<td>" + index + "</td>";
          s += "<td class='community-name'>" + item.commu_name + "</td>";
          s +=
            "<td class='community-title'> <a href = '/community/read?no=" +
            item.no +
            "'>" +
            item.title +
            "</a></td>";

          s +=
            "<td class='community-contents'>" +
            truncateString(item.contents, 5) +
            "</td>";
          s += "<td class='community-nick'>" + item.nick + "</td>";
          // s+="<td class ='community-date'>" + moment(item.r_date).format("MMM Do YY") + "</td>";
          s +=
            "<td class='community-view-count'>" +
            "👁️" +
            item.view_cnt +
            "</td>";
          s +=
            "<td class ='community-comment-count'>" +
            "💬" +
            item.comment_count +
            "</td>";
          s +=
            "<td class = 'community-like-cnt'>" +
            "❤️" +
            item.like_cnt +
            "</td>";
        });
        s += "</table>";
        console.log(s);
        $(".topic-best").append(s);
      }

      function displayBlaBla(data) {
        //블라블라
        let getBla = data
          .filter((item) => item.commu_cd === "commu_B")
          .slice(0, 5);
        let s = "<table>";
        if (getBla.length > 0) {
          getBla.forEach((item) => {
            s += "<tr>";
            s +=
              "<td class='community-title'> <a href = '/community/read?no=" +
              item.no +
              "'>" +
              item.title +
              "</a></td>";
            s +=
              "<td class='community-view-count'>" +
              "👁️" +
              item.view_cnt +
              "</td>";
          });

          s += "</table>";
        } else {
          s = "<div>해당 게시판의 게시글이 없습니다.</div>";
        }
        $(".topic-bla").append(s);
      }

      function displayWorry(data) {
        //고민상담
        let getBla = data
          .filter((item) => item.commu_cd === "commu_W")
          .slice(0, 5);
        let s = "<table>";
        if (getBla.length > 0) {
          getBla.forEach((item) => {
            s += "<tr>";
            s +=
              "<td class='community-title'> <a href = '/community/read?no=" +
              item.no +
              "'>" +
              item.title +
              "</a></td>";
            s +=
              "<td class='community-view-count'>" +
              "👁️" +
              item.view_cnt +
              "</td>";
          });

          s += "</table>";
        } else {
          s = "<div>해당 게시판의 게시글이 없습니다.</div>";
        }
        $(".topic-worry").append(s);
      }

      function displayLove(data) {
        //연애
        let getBla = data
          .filter((item) => item.commu_cd === "commu_L")
          .slice(0, 5);
        let s = "<table>";
        if (getBla.length > 0) {
          getBla.forEach((item) => {
            s += "<tr>";
            s +=
              "<td class='community-title'> <a href = '/community/read?no=" +
              item.no +
              "'>" +
              item.title +
              "</a></td>";
            s +=
              "<td class='community-view-count'>" +
              "😀" +
              item.view_cnt +
              "</td>";
          });

          s += "</table>";
        } else {
          s = "<div>해당 게시판의 게시글이 없습니다.</div>";
        }
        $(".topic-love").append(s);
      }

      function truncateString(str, num) {
        if (num < str.length) {
          return str.slice(0, num) + "...";
        } else {
          return str;
        }
      }
    });
  </script>
</html>
