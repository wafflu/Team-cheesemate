<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/event_styles.css">
<main class="maincontent">
    <div id = "titlebox">
        <p class="font30">
            이벤트
        </p>
    </div>
    <div class="topnav">
        <div class="menu">
            <ul>
                <li class="bun-ui <c:if test='${empty param.cd}'>bun-ui-selected</c:if>">
                    <a class="Allarr bun-ui" href="/event?cd=">전체 이벤트</a>
                </li>
                <li class="bun-ui <c:if test='${param.cd == "P"}'>bun-ui-selected</c:if>">
                    <a class="activearr" href="/event?cd=P">활성 이벤트</a>
                </li>
                <li class="bun-ui <c:if test='${param.cd == "B"}'>bun-ui-selected</c:if>">
                    <a class="Beforearr bun-ui <c:if test='${param.cd == "B"}'>bun-ui-selected</c:if>" href="/event?cd=B">시작전 이벤트</a>
                </li>
                <li class="bun-ui <c:if test='${param.cd == "F"}'>bun-ui-selected</c:if>">
                    <a class="finisharr" href="/event?cd=F">종료 이벤트</a>
                </li>
                <li class="bun-ui <c:if test='${param.cd == "C"}'>bun-ui-selected</c:if>">
                    <a class="cancelarr" href="/event?cd=C">취소 이벤트</a>
                </li>
            </ul>
        </div>
<%--        <div class="search-container">--%>
<%--            <form action="#">--%>
<%--                    <label for="searchCategory" id="searchCategory">검색조건</label>--%>
<%--                    <select name --%>
<%--                <input type="text" placeholder="검색" name="search">--%>
<%--                <button type="submit">검색</button>--%>
<%--            </form>--%>
<%--        </div>--%>
    </div>
    <div class="maincontents font14">
        <c:choose>
            <c:when test="${empty eventarr}">
                <div class="no-results">결과가 없습니다</div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${eventarr}" var="event">
                    <div class="contentsBox">
                        <div class="imgbox">
                            <a href="/event/read?evt_no=${event.evt_no}">
                                <img class="eventimg" src="/img/display?fileName=${event.img_full_rt}" alt="">
                            </a>
                        </div>
                        <div class="textbox">
                            <a href="/event/read?evt_no=${event.evt_no}">
                                <p class="title font18">${event.title}</p>
                            </a>
                            <p class="date">
                                <fmt:formatDate value="${event.s_date}" pattern="yyyy-MM-dd" />
                                ~<fmt:formatDate value="${event.e_date}" pattern="yyyy-MM-dd" />
                            </p>
<%--                            <p id="remainingDays-${event.evt_no}"></p>--%>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="Pagecontroller font18">
        <c:if test="${ph.prevPage}">
            <a href="/event?page=1&cd=${cd}"><<</a>
            <a href="/event?page=${ph.offset-1}&cd=${cd}"><</a>
        </c:if>
        <c:forEach var="page" begin="${ph.beginPage}" end="${ph.endPage}">
            <a href="/event?page=${page}&cd=${cd}">${page}</a>
        </c:forEach>
        <c:if test="${ph.nextPage}">
            <a href="/event?page=${ph.getEndPage()+1}&cd=${cd}">></a>
            <a href="/event?page=${ph.getMaxPageNum()}&cd=${cd}">>></a>
        </c:if>
    </div>
</main>
<script src="/js/event.js"></script>
<%@include file="../fixed/footer.jsp"%>
