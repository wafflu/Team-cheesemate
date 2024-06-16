<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../fixed/header.jsp"%>
    <style>
        header h1 {
            margin: 0;
            font-size: 2.5em;
        }
        main {
            max-width: 1000px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
        }
        .eventTitle {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #000;
        }
        .eventContents {
            font-size: 18px;
            margin-bottom: 20px;
            line-height: 1.6;
        }
        .read_eventimg {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 20px 0;
            border-radius: 8px;
        }
        .date, .prize {
            font-size: 16px;
            margin: 10px 0;
            font-weight: bold;
            color: #555;
        }
        h5 {
            margin: 20px 0 10px;
            font-size: 20px;
            color: #000;
        }
    </style>

<main>
    <h3 class="eventTitle">제목 : ${dto.title}</h3>
    <p class="eventContents">내용 : ${dto.contents}</p>
    <c:if test="${dto.group_no == 42}">
        <img src="/img/display?fileName=event/event.png" alt="이벤트 이미지" class="read_eventimg">
    </c:if>
    <c:if test="${dto.group_no != 42}">
        <img src="/img/display?fileName=${dto.img_full_rt}" alt="이벤트 이미지" class="read_eventimg">
    </c:if>

    <h5>날짜</h5>
    <p class="date"><fmt:formatDate value="${dto.s_date}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${dto.e_date}" pattern="yyyy-MM-dd" /></p>
    <h5>경품</h5>
    <p class="prize">${dto.prize}</p>
</main>
<%@include file="../fixed/footer.jsp"%>

