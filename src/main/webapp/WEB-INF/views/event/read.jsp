<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 2024-05-19
  Time: 오후 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>이벤트 페이지</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            padding: 0;
        }
        header {
            background: linear-gradient(90deg, #007bff, #00c6ff);
            color: white;
            text-align: center;
            padding: 1.5em 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
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
            color: #007bff;
        }
        .eventContents {
            font-size: 18px;
            margin-bottom: 20px;
            line-height: 1.6;
        }
        img {
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
            color: #007bff;
        }
    </style>
</head>
<body>
<header>
    <h1>이벤트 페이지</h1>
</header>
<main>
    <h3 class="eventTitle">제목 : ${dto.title}</h3>
    <p class="eventContents">내용 : ${dto.contents}</p>
    <img src="${dto.img_full_rt}" alt="이벤트 이미지">
    <h5>날짜</h5>
    <p class="date"><fmt:formatDate value="${dto.s_date}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${dto.e_date}" pattern="yyyy-MM-dd" /></p>
    <h5>경품</h5>
    <p class="prize">${dto.prize}</p>
</main>
</body>
</html>

