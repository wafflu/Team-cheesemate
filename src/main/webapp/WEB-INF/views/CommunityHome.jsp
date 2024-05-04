
<%--
  Created by IntelliJ IDEA.
  User: gominjeong
  Date: 5/2/24
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<article>

    <div class = "section1">

        <h2>실시간 우리 지역 인기글</h2>
        <p><a href = "${pageContext.request.contextPath}/community/list">더보기></a></p>
        <input type = "button" value = "글쓰기" onclick = "location.href='<c:url value="/community/write"/>'">

    </div>
    <div class = "section2">
        <h3>블라블라</h3>
        <p><a href = "${pageContext.request.contextPath}/community/list">더보기></a></p>
    </div>
    <div class = "section3">
        <h3>고민/상담</h3>
        <p><a href = "${pageContext.request.contextPath}/community/list">더보기></a></p>

    </div>

    <div class = "section4">
        <h3>연애/썸</h3>
        <p><a href = "${pageContext.request.contextPath}/community/list">더보기></a></p>

    </div>

</article>
</body>
<script>
    $(document).ready(function(){

        $.ajax({
            url:'/community/story',
            type:"GET",
            dataType:"json",
            success:function(response){
                console.log(response);
                displayTopTen(response);
                displayBlaBla(response);
                displayWorry(response);
                displayLove(response);
            },
            error:function(){
                console.log("error");
            }
        })



        function displayTopTen(data){//인기글

            let getTopTen = data.sort(function(a,b){
                return b.view_cnt - a.view_cnt;
            }).slice(0,10);
            console.log(getTopTen[0].title);
            let s = "<table>";
            // getTopTen.forEach(((item)=> s+= createTableRow(item,index)));
            getTopTen.forEach((item,index)=>{
                s+="<tr>";
                s+="<td>"+index+"</td>";
                s+="<td>"+item.commu_name+"</td>";
                s+= "<td> <a href = '/community/read?no=" + item.no +"'>" + truncateString(item.title,5)+ "</a></td>";

                s+="<td>" + truncateString(item.contents,5)+"</td>";
                s+="<td>" + item.nick +"</td>";
                s+="<td>" + item.view_cnt+"</td>";
                s+="<td>" + item.r_date + "</td>";

            });
            s+="</table>";
            console.log(s);
            $('.section1').append(s);
        }

        function displayBlaBla(data){//블라블라
            let getBla = data.filter(item=>item.commu_cd==='commu_B').slice(0,5);
            let s = "<table>";
            getBla.forEach((item) =>{
                s+="<tr>";
                s+= "<td> <a href = '/community/read?no=" + item.no +"'>" + item.title+ "</a></td>";
                s+="<td>"+item.view_cnt+"</td>";
            });


            s+="</table>";
            $('.section2').append(s);
        }




        function displayWorry(data){//고민상담
            let getBla = data.filter(item=>item.commu_cd==='commu_W').slice(0,5);
            let s = "<table>";
            getBla.forEach((item) =>{
                s+="<tr>";
                s+= "<td> <a href = '/community/read?no=" + item.no +"'>" + item.title+ "</a></td>";
                s+="<td>"+item.view_cnt+"</td>";
            });


            s+="</table>";
            $('.section3').append(s);
        }




        function displayLove(data){//연애
            let getBla = data.filter(item=>item.commu_cd==='commu_L').slice(0,5);
            let s = "<table>";
            getBla.forEach((item) =>{
                s+="<tr>";
                s+= "<td> <a href = '/community/read?no=" + item.no +"'>" + item.title+ "</a></td>";
                s+="<td>"+item.view_cnt+"</td>";
            });


            s+="</table>";
            $('.section4').append(s);
        }



        function truncateString(str, num){
            if(num < str.length){
                return str.slice(0,num) + "...";
            }else{
                return str;
            }
        }




    })

</script>
</html>
