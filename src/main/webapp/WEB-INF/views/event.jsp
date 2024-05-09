<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 2024-04-23
  Time: 오전 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>이벤트 페이지</title>
    <link rel="stylesheet" href="/resources/css/event.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<button type="button" class="activearr" onClick="location.href='/event?cd=P'">활성 이벤트 목록</button>
<button type="button" class="finisharr" onClick="location.href='/event?cd=F'">완료 이벤트 목록</button>
<button type="button" class="cancelarr" onClick="location.href='/event?cd=C'">취소 이벤트 목록</button>
<div id="search">
    <form id="searchform" name="searchform" method="post">
        <fieldset>
            <label>검색 조건</label>
            <select id="user_select" name="cd" label="회원 분류">
                <option value="title">제목</option>
                <option value="contents">내용</option>
                <option value="ad_id">작성자</option>
            </select>
            <input type= "text" id="inputcontents" name="contents" />
            <button id="submit">검색</button>
        </fieldset>
    </form>

</div>
<a href="/event/write">작성</a>
<table id = "contents" border="1" cellspacing="0">
    <tr><th>이벤트 번호</th> <th>이벤트 제목</th><th>이벤트 내용</th><th>상품</th><th>시작일</th><th>종료일</th><th>활성 상태</th></tr>
    <c:forEach items="${eventarr}" var="event">
        <tr>
            <td>${event.evt_no}</td>
            <td>
                <a href="/event/read?evt_no=${event.evt_no}">${event.title}</a>
            </td>
            <td>
                <p>${event.contents}</p>
            </td>
            <td>${event.prize}</td>
            <td>${event.s_date}</td>
            <td>${event.e_date}</td>
            <td>${event.active_s_cd}</td>
        </tr>

    </c:forEach>
</table>
<c:if test="${ph.isNotFirstPage()}">
    <a href="/event?page=1&cd=${cd}"><<</a>
    <a href="/event?page=${ph.getstartPage()-1}&cd=${cd}"><</a>
</c:if>
<c:forEach var="page" begin="${ph.getstartPage()}" end="${ph.getEndPage()}">
    <a href="/event?page=${page}&cd=${cd}">${page}</a>
</c:forEach>
<!-- Add more page links here -->
<c:if test="${ph.isNotLastPage()}">
    <a href="/event?page=${ph.getEndPage()+1}&cd=${cd}">></a>
    <a href="/event?page=${ph.getMaxPageNum()}&cd=${cd}">>></a>
</c:if>
<script>
    function loadDocArr(){
        // 폼 데이터를 담을 객체 생성
        // 폼 안의 각 input 및 select 요소를 순회하면서 값을 읽어와서 formData 객체에 추가
        let cd = $("#user_select").val();
        let inputcontents = $("#inputcontents").val();
        let formData={cd:cd, contents:inputcontents};
        let arrdata = {};
        // 만들어진 formData 객체를 사용하여 원하는 작업을 수행
        console.log("함수 실행");
        console.log(formData);
        console.log(JSON.stringify(formData));
        $.ajax({
            url: "/event/search",
            type : "POST",
            contentType: "application/json;",
            dataType : "text",
            data : JSON.stringify(formData),
            success : function (data) {
                arrdata=JSON.parse(data);
                let str = "<tr><th>이벤트 번호</th><th>이벤트 제목</th><th>이벤트 내용</th><th>상품</th><th>시작일</th><th>종료일</th><th>활성 상태</th></tr>";
                for(let i of arrdata){
                    str+="<tr>";
                    str+="<td>" + i.evt_no + "</td>";
                    str+="<td><a href='/event/read?evt_no=" + i.evt_no + "'>" + i.title + "</a></td>";
                    str+="<td><p>" + i.contents + "</p></td>";
                    str+="</tr>"
                }
                $("#contents").html(str);
            }
        })
    }
    $(document).ready(function() {
        $("#searchform").submit(function(e) {
            e.preventDefault(); // 폼의 기본 동작 방지
            loadDocArr();
        });
    });
</script>

</body>
</html>