<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp"%>

<div class="maincontent">
    <div class="wrapper">
        <div class="nav">
            <a class="Allarr" href='/event?cd='>전체 이벤트</a>
            <a class="Beforearr" href='/event?cd=B'>시작전 이벤트</a>
            <a class="activearr" href='/event?cd=P'>활성 이벤트</a>
            <a class="finisharr" href='/event?cd=F'>종료 이벤트</a>
            <a class="cancelarr" href='/event?cd=C'>취소 이벤트</a>
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
                    <img src='/img/display?fileName=${event.img_full_rt}' alt="" style="width: 100px; height: 100px;">
                </a>
                <p class="title">${event.title}</p>
                <p class="date"><fmt:formatDate value="${event.s_date}" pattern="yyyy-MM-dd" />
                    ~<fmt:formatDate value="${event.e_date}" pattern="yyyy-MM-dd" />
                </p>
            </div>
        </c:forEach>
        <c:if test="${ph.prevPage}">
            <a href="/event?page=1&cd=${cd}"><<</a>
            <a href="/event?page=${ph.offset-1}&cd=${cd}"><</a>
        </c:if>
        <c:forEach var="page" begin="${ph.beginPage}" end="${ph.endPage}">
            <a href="/event?page=${page}&cd=${cd}">${page}</a>
        </c:forEach>
        <!-- Add more page links here -->
        <c:if test="${ph.nextPage}">
            <a href="/event?page=${ph.getEndPage()+1}&cd=${cd}">></a>
            <a href="/event?page=${ph.getMaxPageNum()}&cd=${cd}">>></a>
        </c:if>
    </div>
</div>
<script src="/js/event.js"></script>

<%@include file="../fixed/footer.jsp"%>