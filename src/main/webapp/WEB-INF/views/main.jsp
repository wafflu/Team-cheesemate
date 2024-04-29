<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${userInfoDTO.ur_id}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<title>My Page</title>
	<style>
		textarea {
			width: 50%;
			height: 6.25em;
			resize: none;
		}
	</style>
</head>
<script>
	let msg ="${msg}"
	if(msg=="MyPage Read Complete") alert("메인페이지 접근 성공");
</script>
<body>
<div class="container">
	<h2>닉네임 : ${userInfoDTO.nick} (ID : ${userInfoDTO.ur_id}) </h2>
	<h4>방문자수 : ${userInfoDTO.view_cnt} 거래 성공수: ${userInfoDTO.complete_cnt} 신고 누적수 : ${userInfoDTO.rpt_cnt}</h4>
	<c:if test="${userInfoDTO.ur_id eq loginId}">
	<button id="modBtn">수정</button><br>
	</c:if>
	<textarea id="${userInfoDTO.ur_id}" name="contents"  placeholder=" 내용을 입력해 주세요." readonly='readonly'> <c:out value='${userInfoDTO.contents}'/> </textarea><br>
	<c:choose>
		<c:when test="${userInfoDTO.r_date.time >= startOfToday}">
			<p>가입날짜 : <fmt:formatDate value="${userInfoDTO.r_date}" pattern="HH:mm" type="time"/></p>
		</c:when>
		<c:otherwise>
			<p>가입날짜 : <fmt:formatDate value="${userInfoDTO.r_date}" pattern="yyyy-MM-dd" type="date"/></p>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${userInfoDTO.m_date.time >= startOfToday}">
			<p id="m_date">수정날짜 :  <fmt:formatDate value="${userInfoDTO.m_date}" pattern="HH:mm" type="time"/></p>
		</c:when>
		<c:otherwise>
			<p id="m_date">수정날짜 :  <fmt:formatDate value="${userInfoDTO.m_date}" pattern="yyyy-MM-dd" type="date"/></p>
		</c:otherwise>
	</c:choose>
<%--	<c:choose>--%>
<%--		<c:when test="${userInfoDTO.ur_id eq loginId}">--%>
<%--			<p> 좋아요 : ${userInfoDTO.like_cnt}</p>--%>
<%--		</c:when>--%>
<%--		<c:otherwise>--%>
			<button id="like">좋아요 : ${userInfoDTO.like_cnt}</button>
<%--		</c:otherwise>--%>
<%--	</c:choose>--%>
<%--	<c:choose>--%>
<%--		<c:when test="${userInfoDTO.ur_id eq loginId}">--%>
<%--			<p> 싫어요 : ${userInfoDTO.hate_cnt}</p>--%>
<%--		</c:when>--%>
<%--		<c:otherwise>--%>
			<button id="hate">싫어요 : ${userInfoDTO.hate_cnt}</button>
<%--		</c:otherwise>--%>
<%--	</c:choose>--%>
	<h3>후기글 : ${userInfoDTO.rv_cmt_cnt}</h3>
	comment : <input type="text" name="comment"><br>
	<button id="sendBtn" type="button">등록</button>
	<button id="modifyBtn" type="button">수정</button>
	<div id="commentList"></div>

</div>
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
			tmp += '<button class="modifyBtn">수정</button>'
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
				error: function () {
					alert("error");
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
