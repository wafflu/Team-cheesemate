

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

    <h3 id = "all">전체</h3>
    <h3 id = "hot">인기글</h3>
    <h3 id = "bla">블라블라</h3>
    <h3 id = "love">연애/썸</h3>
    <h3 id = "worry">고민/상담</h3>
  </div>

  <article>



  </article>

</section>
<footer>
</footer>


<script>
    $(document).ready(function(){
        $("#all").click(function(){
            <%--let allData = ${communityBoardDto.list};--%>


            $.ajax({
                type:"GET",
                url:'/test',
                dataType:"json",
                success:function(result){
                    let s= " ";
                    s+="<table>"
                    for(let i = 0; i<result.length; i++){
                        console.log(result[i]);
                        s+="<tr>"
                        s+="<td>" + result[i].no+ "</td>"
                        s+="<td>" +  "<a href='"+"${pageContext.request.contextPath}/read?no="+result[i].no+"'>" + result[i].title + "</a></td>"
                        s+="<td>" + result[i].nick+ "</td>"
                        s+="<td>" + result[i].view_cnt+ "</td>"
                        s+="<td>" + result[i].addr_name+ "</td>"
                    }
                    s+="</table>";
                    $('article').html(s);
                },
                error:function(){alert("error")}
            })
            // alert("asdasd");
        })

      $(document).ready(function(){
        $("#hot").click(function(){
          <%--let allData = ${communityBoardDto.list};--%>


          $.ajax({
            type:"GET",
            url:'/test',
            dataType:"json",
            success:function(result){
              let s= " ";
              s+="<table>"

              result.sort(function(a,b){
                return b.view_cnt - a.view_cnt;
              })
              for(let i = 0; i<result.length; i++){
                console.log(result[i]);
                s+="<tr>"
                s+="<td>" + result[i].no+ "</td>"
                s+="<td>" +  "<a href='"+"${pageContext.request.contextPath}/read?no="+result[i].no+"'>" + result[i].title + "</a></td>"
                s+="<td>" + result[i].nick+ "</td>"
                s+="<td>" + result[i].view_cnt+ "</td>"
                s+="<td>" + result[i].addr_name+ "</td>"


              }
              s+="</table>";
              $('article').html(s);
            },
            error:function(){alert("error")}
          })
          alert("hot");
        })



        $("#bla").click(function(){
            $.ajax({
                type:"GET",
                url:'/test',
                dataType: "json",
                success: function(result){
                    let s = " ";
                    s+= "<table>"
                    for (let i = 0; i < result.length; i++) {
                        if(result[i].commu_cd === 'commu_B'){
                            console.log(result[i])
                            s+="<tr>"
                            s+="<td>" + result[i].no+ "</td>"
                            s+="<td>" + result[i].addr_cd + "</td>"
                            s+="<td>" +  "<a href='"+"${pageContext.request.contextPath}/read?no="+result[i].no+"'>" + result[i].title + "</a></td>"
                            s+="<td>" + result[i].nick+ "</td>"
                            s+="<td>" + result[i].view_cnt+ "</td>"
                            s+="<td>" + result[i].addr_name+ "</td>"
                        }
                    }
                    s+="</table>";
                    $('article').html(s);

                }
            })
          alert("click bla");
        })



      $("#love").click(function(){
        $.ajax({
          type:"GET",
          url:'/test',
          dataType: "json",
          success: function(result){
            let s = " ";
            s+= "<table>"
            for (let i = 0; i < result.length; i++) {
              if(result[i].commu_cd === 'commu_L'){
                console.log(result[i])
                s+="<tr>"
                s+="<td>" + result[i].no+ "</td>"
                s+="<td>" + result[i].addr_cd + "</td>"
                s+="<td>" +  "<a href='"+"${pageContext.request.contextPath}/read?no="+result[i].no+"'>" + result[i].title + "</a></td>"
                s+="<td>" + result[i].nick+ "</td>"
                s+="<td>" + result[i].view_cnt+ "</td>"
                s+="<td>" + result[i].addr_name+ "</td>"
              }
            }
            s+="</table>";
            $('article').html(s);

          }
        })
        alert("click love");
      })


      $("#worry").click(function(){
        $.ajax({
          type:"GET",
          url:'/test',
          dataType: "json",
          success: function(result){
            let s = " ";
            s+= "<table>"
            for (let i = 0; i < result.length; i++) {
              if(result[i].commu_cd === 'commu_W'){
                console.log(result[i])
                s+="<tr>"
                s+="<td>" + result[i].no+ "</td>"
                s+="<td>" + result[i].addr_cd + "</td>"
                s+="<td>" +  "<a href='"+"${pageContext.request.contextPath}/read?no="+result[i].no+"'>" + result[i].title + "</a></td>"
                s+="<td>" + result[i].nick+ "</td>"
                s+="<td>" + result[i].view_cnt+ "</td>"
                s+="<td>" + result[i].addr_name+ "</td>"
              }
            }
            s+="</table>";
            $('article').html(s);

          }
        })
        alert("click worry");
      })
    })
    })


</script>
</body>
</html>
