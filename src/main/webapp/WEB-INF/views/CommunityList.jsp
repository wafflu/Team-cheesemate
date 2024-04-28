<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 4/28/24
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<html>
<head>
    <title>CommunityList</title>
</head>
<body>
<header>

</header>
<section>
  <div>
    <h3 id = "all"><a href = communityList></a>전체</h3>
    <h3 id = "hot">인기글</h3>
    <h3 id = "bla">블라블라</h3>
    <h3 id = "love">연애/썸</h3>
    <h3 id = "worry">고민/상담</h3>
  </div>
  <c:if test = "all">

  </c:if>

</section>
<footer>
</footer>


<script>
  let all = document.getElementById("all");
  all.addEventListener("click",function (){

          }
  )
</script>
</body>
</html>
