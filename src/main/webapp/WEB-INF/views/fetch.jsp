<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 4/29/24
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="aaddr_nonymous"></script>

<head>
    <title>Title</title>
</head>
<body>
<article>

</article>
    <input type="button" value ="fetch" onclick="fetch('commu_A').then(function(response){
        response.text().then(function(text){
            alert(text);
            document.querySelector('article').innerHTML = text;
        })
    })
">
</body>
</html>
