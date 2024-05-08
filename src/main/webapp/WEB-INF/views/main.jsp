<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<%--<c:set var="loginId" value="${sessionScope.id}"/>--%>
<c:set var="loginId" value="${userInfoDTO.ur_id}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<title>My Page</title>
	<style>
		.navigation {
			background-color: #333;
			color: white;
			width: 200px; /* 네비게이션 바의 폭 설정 */
			float: left; /* 왼쪽으로 부유시켜 세로로 배치 */
		}

		.navigation ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
		}

		.navigation ul li {
			display: block; /* 각 메뉴 아이템을 블록 요소로 표시하여 세로로 배치 */
		}

		.navigation ul li a {
			display: block;
			text-align: center;
			padding: 14px 16px;
			text-decoration: none;
			color: white;
		}

		.navigation ul li a:hover {
			background-color: #111;
		}

		.container {
			margin-left: 220px; /* 네비게이션 바 폭과 여백 고려하여 컨테이너 위치 조정 */
			padding: 20px;
		}

		.container h2, .container h4, .container textarea, .container p, .container .feeling_div {
			width: 100%;
			text-align: center;
			margin-bottom: 20px;
		}

		.feeling_div {
			margin-bottom: 0;
		}

		#commentList {
			width: 100%;
			text-align: center;
		}

		input[type="text"] {
			width: calc(100% - 140px);
			margin-bottom: 10px;
			padding: 8px;
			border: 1px solid #ccc;
			border-radius: 4px;
			box-sizing: border-box;
		}

		button[type="button"] {
			padding: 10px 20px;
			background-color: #2199e8;
			border: none;
			color: white;
			border-radius: 4px;
			cursor: pointer;
		}

		button[type="button"]:hover {
			background-color: #0c7dbd;
		}
	</style>
</head>
<script>
	let msg ="${msg}"
	if(msg=="MyPage Read Complete") alert("메인페이지 접근 성공");
</script>
<body>
<div class="navigation">
	<ul>
		<li><a href="/myPage/main">마이페이지</a></li>
		<li><a href="/about">거래정보</a></li>
		<li><a href="/contact">판매/나눔/구매내역</a></li>
		<li><a href="/contact">찜한 상품</a></li>
		<li><a href="/contact">내 정보 관리</a></li>
		<li><a href="/contact">개인 정보 수정</a></li>
		<li><a href="/contact">비밀번호 변경</a></li>
		<li><a href="/contact">회원 탈퇴</a></li>
	</ul>
</div>
<div class="container">
	<h2>닉네임 : ${userInfoDTO.nick} (ID : ${userInfoDTO.ur_id})</h2>
	<h4>방문자수 : ${userInfoDTO.view_cnt} 거래 성공수: ${userInfoDTO.complete_cnt} 신고 누적수 : ${userInfoDTO.rpt_cnt}</h4>
	<c:if test="${userInfoDTO.ur_id eq loginId}">
		<button id="modBtn">수정</button><br>
	</c:if>
	<textarea id="${userInfoDTO.ur_id}" name="contents" placeholder=" 내용을 입력해 주세요." readonly='readonly'><c:out value='${userInfoDTO.contents}'/></textarea><br>
	<c:choose>
		<c:when test="${userInfoDTO.r_date.time >= startOfToday}">
			<p>가입날짜 : <fmt:formatDate value="${userInfoDTO.r_date}" pattern="HH:mm" type="time"/></p>
		</c:when>
		<c:otherwise>
			<p>가입날짜 : <fmt:formatDate value="${userInfoDTO.r_date}" pattern="yyyy-MM-dd" type="date"/></p>
		</c:otherwise>
	</c:choose>
	<div class="feeling_div">
		<div class="button-container like-container">
			<button class="feeling_a">
				<i class="fa fa-heart-o"> 좋아요 </i>
			</button>
		</div>
		<div class="button-container dislike-container">
			<button class="feeling_a">
				<i class="fa fa-heart"> 싫어요 </i>
			</button>
		</div>
	</div>
<%--	<script>--%>
<%--		$('.like-container > .feeling_a, .dislike-container  > .feeling_a').on('click', function() {--%>
<%--			event.preventDefault();--%>
<%--			$('.active').removeClass('active');--%>
<%--			$(this).addClass('active');--%>
<%--		});--%>
<%--	</script>--%>
</div>
<h3 style="margin-bottom: 10px;">후기글 : ${userInfoDTO.rv_cmt_cnt}</h3>
<input type="text" name="comment" style="margin-right: 10px;"><button id="sendBtn" type="button">등록</button><button id="modifyBtn" type="button">수정</button>
<div id="commentList"></div>
<script>
	let showList = function (sal_id) {
		$.ajax({
			type:'GET',       // 요청 메서드
			url: "/comments?sal_id="+sal_id,  // 요청 URI
			headers: {"content-type": "application/json"}, // 요청 헤더
			dataType : 'json',
			success : function(result){
				$("#commentList").html(toHtml(result));
			},
			error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	let toHtml = function (comments) {
		let tmp = "<ul>";

		comments.forEach(function (comment) {
			tmp += '<li data-no=' + comment.no
			tmp += ' data-sal_id=' + comment.sal_id + '>'
			tmp += ' buy_id = <span class="buy_id">' + comment.buy_id + '</span>'
			tmp += ' cotents = <span class="contents">' + comment.contents + '</span>'
			tmp += ' 등록날짜 =' + comment.m_date
			tmp += '<button class="deleteBtn">삭제</button>'
			tmp += '<button class="modify1Btn">수정</button>'
			tmp += '<button class="likeBtn">좋아요: <span class="like">' + comment.like_cnt + '</span></button>';
			tmp += '</li>'
		});
		return tmp + "</ul>";
	}

	let showUserInfo = function (ur_id) {
		$.ajax({
			type:'GET',       // 요청 메서드
			url: '/userInfo/' + ur_id ,  // 요청 URI
			headers: {"content-type": "application/json"}, // 요청 헤더
			dataType : 'json',
			success : function(result){
				alert("Success Change");
				toChange(result);
			},
			error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	let toChange = function (userInfoDTO) {
		let newValue = userInfoDTO.contents;
		$("textarea[name=contents]").val(newValue);
		let newDate = new Date(userInfoDTO.m_date);
		let formattedDate = newDate.getFullYear() + '-' + (newDate.getMonth() + 1).toString().padStart(2, '0') + '-' + newDate.getDate().toString().padStart(2, '0');
		$("p[id=m_date]").text("수정날짜 : "+formattedDate);
		let isReadonly =$("textarea[name=contents]").attr('readonly');
		if(isReadonly!='readonly') {
			$("#modBtn").html("수정");
			$("textarea").attr('readonly', true);
		}
	}


	$(document).ready(function() {
		let ur_id = $("textarea[name=contents]").attr('id');
		showList(ur_id);

		$("#modifyBtn").click(function(){
			let contents = $("input[name=comment]").val();
			let no = $("input[name=comment]").attr("data-no");
			$.ajax({
				type:'PATCH',       // 요청 메서드
				url: '/comments/'+no+'?sal_id='+ur_id,  // 요청 URI
				headers : { "content-type": "application/json"}, // 요청 헤더
				// dataType : 'text', // 전송받을 데이터의 타입
				data : JSON.stringify({no:bno,contents:contents}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
				success : function(result){
					alert(result);
					showList(ur_id);
				},
				error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});


		$("#commentList").on("click",".modBtn",function(){
			let cno = $(this).parent().attr("data-no");
			let contents = $(this).parent().find("span.contents").text();

			$("input[name=comment]").val(contents);
			$("input[name=comment]").attr("data-no",no);
		});

		$("#sendBtn").click(function(){
			let contents = $("input[name=comment]").val();

			if(comment.trim()==''){
				alert("댓글을 입력해주세요");
				$("input[name=comment]").focus()
				return
			}

			$.ajax({
				type:'POST',       // 요청 메서드
				url: '/comments',  // 요청 URI
				headers : { "content-type": "application/json"}, // 요청 헤더
				dataType : 'json', // 전송받을 데이터의 타입
				data : JSON.stringify({contents:contents}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
				success : function(result){
					alert(result);
					showList(bno);
				},
				error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()

		});
		// 동적으로 생성되는 요소에 이벤트 거는법
		$("#commentList").on("click",".deleteBtn",function(){
			let no = $(this).parent().attr("data-no");
			let sal_id = $(this).parent().attr("data-sal_id");
			$.ajax({
				type:'DELETE',       // 요청 메서드
				url: '/comments/'+no+'?sal_id='+sal_id,  // 요청 URI
				success : function(result){
					alert(result);
					showList(sal_id);
				},
				error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});

		$("#modBtn").click(function () {
			let ur_id = $("textarea[name=contents]").attr('id');
			let contents = $("textarea[name=contents]").val();
			let isReadonly =$("textarea[name=contents]").attr('readonly');

			if(isReadonly=='readonly') {
				$("#modBtn").html("등록");
				$("textarea").attr('readonly', false);
				return;
			}
			$.ajax({
				type: 'PATCH',       // 요청 메서드
				url: '/userInfo/' + ur_id ,  // 요청 URI
				headers: {"content-type": "application/json"}, // 요청 헤더
				// dataType : 'text', // 전송받을 데이터의 타입
				data: JSON.stringify({contents: contents}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
				success: function (result) {
					alert(result);
					showUserInfo(ur_id);
				},
				error: function (result) {
					alert(result.responseText);
				} // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});

		$("#like").click(function () {
			let ur_id = $("textarea[name=contents]").attr('id');
			$.ajax({
				type: 'PATCH',       // 요청 메서드
				url: '/userInfo/like/' + ur_id ,  // 요청 URI
				// headers: {"content-type": "application/json"}, // 요청 헤더
				// dataType : 'text', // 전송받을 데이터의 타입
				success: function (result) {
					alert(result);
					let likeCount = parseInt($("#like").text().split(":")[1].trim()); // 좋아요 수 가져오기
					likeCount++; // 좋아요 수 증가
					$("#like").text("좋아요 : " + likeCount); // 수정된 좋아요 수로 버튼 텍스트 업데이트
				},
				error: function () {
					alert("error");
				} // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});

		$("#hate").click(function () {
			let ur_id = $("textarea[name=contents]").attr('id');
			$.ajax({
				type: 'PATCH',       // 요청 메서드
				url: '/userInfo/hate/' + ur_id  ,  // 요청 URI
				// headers: {"content-type": "application/json"}, // 요청 헤더
				// dataType : 'text', // 전송받을 데이터의 타입
				// data: JSON.stringify({contents: contents}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
				success: function (result) {
					alert(result);
					let hateCount = parseInt($("#hate").text().split(":")[1].trim()); // 좋아요 수 가져오기
					hateCount++; // 좋아요 수 증가
					$("#hate").text("싫어요 : " + hateCount); // 수정된 좋아요 수로 버튼 텍스트 업데이트
				},
				error: function () {
					alert("error");
				} // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});
	});
</script>
</body>
</html>
