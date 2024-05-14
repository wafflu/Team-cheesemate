<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/event.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>이벤트 페이지</title>
</head>
<body>
<header>

</header>
<aside>
    <div class="wrapper">
        <ul>
            <li>FAQ관리</li>
            <li>QnA</li>
            <li>이벤트</li>
        </ul>
    </div>
</aside>
<main>
    <div class="wrapper">
        <div class="nav">
            <a class="Allarr" href='event?cd='>전체 이벤트</a>
            <a class="Beforearr" href='event?cd="B"'>시작전 이벤트</a>
            <a class="activearr" href='event?cd="P"'>활성 이벤트</a>
            <a class="finisharr" href='event?cd="F"'>종료 이벤트</a>
            <a class="cancelarr" href='event?cd="C"'>취소 이벤트</a>
            <a href="/event/write">작성</a>
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
        </div>
        <c:forEach items="${eventarr}" var="event">
        <div class="imgbox">
            <a href="/event/read?evt_no=${event.evt_no}">
                <img src='${event.img_full_rt}' alt="">
            </a>
            <p class="title">${event.title}</p>
            <p class="date"><fmt:formatDate value="${event.s_date}" pattern="yyyy-MM-dd" />
                ~<fmt:formatDate value="${event.e_date}" pattern="yyyy-MM-dd" />
            </p>
        </div>
        </c:forEach>
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
        </div>
</main>

<footer>
    <div class="wrapper">

    </div>
</footer>

<script src="js/event.js"></script>

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
            dataType : "json",
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
    function showThumbnailImage(uploadResultArr){

        /* 전달받은 데이터 검증 */
        if(!uploadResultArr || uploadResultArr.length == 0){return}

        let uploadResult = $("#uploadResult");
        let str = "";

        for(let i = 0; i<uploadResultArr.length; i++){
            let obj = uploadResultArr[i];

            let fileCallPath = encodeURIComponent(obj.filert + "/s_" + obj.u_name + "_" + (obj.o_name+obj.e_name));

            str += "<div id='result_card'>";
            str += "<img src='/img/display?fileName=" + fileCallPath +"'>";
            str += "</div>";
        }

        uploadResult.append(str);
    }
</script>

</body>
</html>
