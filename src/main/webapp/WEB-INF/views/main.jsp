<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<%--<c:set var="loginId" value="${sessionScope.id}"/>--%>
<c:set var="loginId" value="${session_id}"/>
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
			width: 200px;
			float: left;
		}

		.navigation ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
		}

		.navigation ul li {
			display: block;
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
		.container h4 {
			margin-bottom: 10px; /* 기존 h4의 하단 여백 조정 */
		}

		.container p {
			display: inline-block; /* 요소를 인라인 블록 요소로 변경하여 가로 정렬 */
			margin-right: 20px; /* 각 정보 사이의 우측 여백 조정 */
		}

		.container textarea {
			resize: none;
			width: 500px;
			height: 200px;
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

		.modal {
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			background-color: rgba(0, 0, 0, 0.6);
			display: flex;
			align-items: center;
			justify-content: center;
			z-index: 1000;
		}

		.modal-content {
			background-color: #fff;
			padding: 20px;
			border-radius: 8px;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
			width: auto;
			max-width: 600px;
			margin: auto;
		}

		.close {
			float: right;
			font-size: 28px;
			font-weight: bold;
			cursor: pointer;
		}

		#myform fieldset {
			display: block;
			direction: rtl;
			border: 0;
			text-align: center;
			margin: 20px 0;
		}

		#starRating {
			display: flex;
			justify-content: center;
		}

		#myform input[type=radio] {
			position: absolute;
			opacity: 0;
			width: 0;
			height: 0;
		}

		#myform label {
			font-size: 3em;
			color: #ccc;
			cursor: pointer;
			transition: color 0.3s ease;
		}

		#myform input[type=radio]:checked ~ label,
		#myform label:hover,
		#myform label:hover ~ label {
			color: rgba(250, 208, 0, 0.99);
		}

		#reviewContents {
			width: calc(100% - 20px);
			height: 150px;
			padding: 10px;
			box-sizing: border-box;
			border: solid 1.5px #D3D3D3;
			border-radius: 5px;
			font-size: 16px;
			resize: none;
			outline: none;
		}

		.commentBtn {
			background-color: #007BFF;
			color: white;
			border: none;
			padding: 10px 20px;
			font-size: 16px;
			border-radius: 5px;
			cursor: pointer;
			transition: background-color 0.3s;
		}

		.commentBtn:hover {
			background-color: #0056b3;
		}

		.paging-active {
			background-color: rgb(216, 216, 216);
			border-radius: 5px;
			color: rgb(24, 24, 24);
		}

		.page-space {
			margin: 0 5px;
		}


		.info-container {
			display: flex;
			align-items: center;
		}

		.info-container p {
			margin-right: 10px; /* 각 정보 사이의 오른쪽 여백 조정 */
		}

		.info-container p:last-child {
			margin-right: 0; /* 마지막 요소의 오른쪽 여백 제거 */
		}
		.profile {
			border-radius: 20%;
		}
		div register_img{
			width: 300px;
			float: left;
			border: 1px solid;
		}

	/* 이미지 영역	*/
		#profileimg{
			width: 250px;
			height: 250px;
			margin-left: 220px;
			position: relative;
		}
		.form_section_content{
			position: absolute;
			width: 250px;
			height: 250px;
			right: 0;
			top: 0;
			z-index: 1;
		}

		#uploadResult{
			position: absolute;
			width: 250px;
			height: 250px;
			border-radius: 100%;
			border: 1px solid #000;
			overflow: hidden;

		}
		.profileimg{
			/*추후 js로 수정*/
			width: 250px;
			height: 250px;
		}

		.btn-upload {
			position: absolute;
			right: 0;
			bottom: 0;
			width: 250px;
			height: 250px;
			background: transparent;
			border-radius: 100%;
			cursor: pointer;

		}

		.btn-upload::before{
			content: '프로필 수정';
			display: block;
			width: 250px;
			text-align: center;
			line-height: 250px;
			border-radius: 100%;
			transition: all 0.2s ease-out;
			opacity: 0;
			box-sizing: border-box;
			font-size: 20px;
			color:#fff;
		}

		.btn-upload:hover::before{
			background-color: rgba(105, 105, 105, 0.5);
			opacity: 1;
		}

		#profile {
			display: none;
		}

		#profilesave_btn{
			position: absolute;
			bottom: -34px;
			left: 50%;
			transform: translateX(-50%);
			z-index: 3;
			background-color: transparent;
			border: 1px solid #FFA500;
			border-radius: 5px;
			padding: 5px;
			cursor: pointer;
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
		<li><a href="/myPage/saleInfo">판매/나눔/구매내역</a></li>
		<li><a href="/contact">찜한 상품</a></li>
		<li><a href="/contact">내 정보 관리</a></li>
		<li><a href="/contact">개인 정보 수정</a></li>
		<li><a href="/contact">비밀번호 변경</a></li>
		<li><a href="/contact">회원 탈퇴</a></li>
	</ul>
</div>

<div id="profileimg">
	<div class="form_section_content">
		<label for="profile" class="btn-upload"></label>
		<input type="file" id ="profile" name='uploadFile'>
	</div>
	<div id = "uploadResult">

<%--		<img src="/img/display?fileName=Noneprofile.jpg" class="profileimg">--%>
	</div>
	<div id="profilesave_btn_area">

	</div>
</div>

<div class="container">
	<h2>닉네임 : ${userInfoDTO.nick} (ID : ${userInfoDTO.ur_id})</h2>
	<div class="info-container">
		<p>
			<c:choose>
				<c:when test="${userInfoDTO.r_date.time >= startOfToday}">
					가입날짜 : <fmt:formatDate value="${userInfoDTO.r_date}" pattern="HH:mm" type="time"/>
				</c:when>
				<c:otherwise>
					가입날짜 : <fmt:formatDate value="${userInfoDTO.r_date}" pattern="yyyy-MM-dd" type="date"/>
				</c:otherwise>
			</c:choose>
		</p>
		<p>상점방문수 : ${userInfoDTO.view_cnt}</p>
		<p>상품 판매: ${userInfoDTO.complete_cnt}회</p>
	</div>
	<c:if test="${userInfoDTO.ur_id eq loginId}">
		<button id="modBtn">소개글 수정</button><br>
	</c:if>
	<textarea id="${userInfoDTO.ur_id}" name="contents" placeholder=" 내용을 입력해 주세요." readonly='readonly'><c:out value='${userInfoDTO.contents}'/></textarea><br>
</div>
<h3 style="margin-bottom: 10px;">상점 후기 <span id="rv_cmt_cnt"> ${userInfoDTO.rv_cmt_cnt}</span></h3>
<h4>평균 별점<span id="star_avg"> ${userInfoDTO.star_avg}</span> <span id="averageStarRating">
        <c:forEach begin="1" end="${userInfoDTO.star_avg}" var="i">
			★
		</c:forEach>
	<!-- 빈 별표(☆) 표시 -->
        <c:forEach begin="${userInfoDTO.star_avg + 1}" end="5" var="i">
			☆
		</c:forEach>
</span></h4>
<%--<button id="writeBtn" type="button">후기글 작성</button>--%>
<!-- 모달 창 -->
<div id="myModal" class="modal" >
	<div class="modal-content">
		<span class="close">&times;</span>
		<!-- 후기글 작성 폼 -->
		<form class="mb-3" name="myform" id="myform">
			<fieldset>
				<span class="text-bold">별점을 선택해주세요</span>
				<div id="starRating">
					<input type="radio" name="reviewStar" value="5" id="rate1"><label for="rate1">★</label>
					<input type="radio" name="reviewStar" value="4" id="rate2"><label for="rate2">★</label>
					<input type="radio" name="reviewStar" value="3" id="rate3"><label for="rate3">★</label>
					<input type="radio" name="reviewStar" value="2" id="rate4"><label for="rate4">★</label>
					<input type="radio" name="reviewStar" value="1" id="rate5"><label for="rate5">★</label>
				</div>
			</fieldset>
			<div>
				<textarea type="text" id="reviewContents" name="comment" placeholder="리뷰를 남겨주세요!!"></textarea>
			</div>
			<button class="commentBtn" id="" type="button"></button>
			<button class="cancelBtn" id="cancelBtn" type="button">취소</button>
		</form>
	</div>
</div>
<div id="commentList" ></div>

<script>
	//이미지 등록하기

	let Image = (function() {
		let imginfo = [];

		return {
			getImgInfo: function() {
				return imginfo;
			}
		};
	})();

	$(document).on("click", "#profilesave_btn", function(e) {
		e.preventDefault();
		$.ajax({
			url: '/saveporfile',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			dataType : 'text',
			data : JSON.stringify(Image.getImgInfo()),
			success: function (result) {
				location.replace(result);
			},
			error: function(xhr, status, error) {
				// 오류 발생 시 처리
				var errorMessage = xhr.responseText;
				console.error('Error:', error);
				alert("오류 발생: " + errorMessage); // 서버에서 전달된 예외 메시지를 알림창으로 표시
			}
		});
		alert("등록되었습니다.")
		// 나머지 코드를 여기에 추가하세요.
	});

	//


	let ur_id = $("textarea[name=contents]").attr('id'); // 해당 유저아이디 변수선언

	let addZero = function(value=1){
		return value > 9 ? value : "0"+value;
	}

	let dateToString = function(ms=0) {
		let date = new Date(ms);

		let yyyy = date.getFullYear();
		let mm = addZero(date.getMonth() + 1);
		let dd = addZero(date.getDate());

		let HH = addZero(date.getHours());
		let MM = addZero(date.getMinutes());
		let ss = addZero(date.getSeconds());

		return yyyy+"."+mm+"."+dd+ " " + HH + ":" + MM + ":" + ss;
	}

	// 후기글 작성 버튼
	var writeBtn = document.getElementById("writeBtn");

	// 모달 창
	var modal = document.getElementById("myModal");

	// 모달 창 닫기 버튼
	var closeBtn = document.getElementsByClassName("close")[0];

	// 모달 창을 닫는 함수
	function closeModal() {
		modal.style.display = "none";
	}

	// 선택된 별점 값 담을 변수
	var selectedStar;

	// 별점 클릭시 변수에 값 담기
	$("input[name='reviewStar']").on("click", function() {
		var clickedStar = $(this).val(); // 클릭된 별점 값

		// 클릭한 별점이 이미 선택된 경우
		if ($(this).is(":checked")) {
			// 선택된 별점과 이전에 선택된 별점이 같은 경우
			if (clickedStar === selectedStar) {
				// 이전에 선택된 별점을 취소하여 0점으로 설정
				$(this).prop("checked", false);
				selectedStar = 0;
			} else {
				// 클릭한 별점 값으로 설정
				selectedStar = clickedStar;
			}
		} else {
			// 선택되지 않은 경우는 0점으로 설정
			selectedStar = 0;
		}
		// 선택된 별점 값을 콘솔에 출력하여 확인
		alert("선택된 별점 값: " + selectedStar);
	});

	// 취소 버튼 기능 추가
	var cancelBtn = document.getElementById("cancelBtn");
	cancelBtn.onclick = function() {
		closeModal();
	}

	// 후기글 목록 보여주기
	let showList = function (sal_id,page=1,pageSize=5) {
		$.ajax({
			type:'GET',       // 요청 메서드
			url: "/comments?sal_id=" + sal_id + "&page=" + page + "&pageSize=" + pageSize,  // 요청 URI
			headers: {"content-type": "application/json"}, // 요청 헤더
			dataType : 'json',
			success : function(result){
				// 후기글 목록과 페이징 정보 가져오기
				let comments = result.comments;
				let ph = result.ph;
				$("#commentList").html(toHtml(comments,ph,sal_id));
				closeModal();
			},
			error   : function(result){ alert(result.responseText) } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	// 후기글 목록을 보여주기 위한 함수
	let toHtml = function (comments,ph,sal_id) {
		let tmp = "<ul>";

		comments.forEach(function (comment) {
			tmp += '<li data-no=' + comment.no
			tmp += ' data-sal_id=' + comment.sal_id + '>'
			tmp += ' buy_id = <span class="buy_id">' + comment.buy_id + '</span>'
			tmp += ' cotents = <span class="contents">' + comment.contents + '</span>'
			tmp += ' 별점 ='; // 별점 표시 부분 추가

			// 별표(★) 개수만큼 추가
			for (let i = 0; i < comment.reviewStar; i++) {
				tmp += '★';
			}
			// 빈 별표 추가
			for (let i = comment.reviewStar; i < 5; i++) {
				tmp += '☆';
			}
			tmp += ' 작성날짜 =' + dateToString(comment.m_date)
			// 수정 및 삭제 버튼 추가 (JSTL을 사용하여 서버 측에서 조건부로 렌더링)
			tmp += '<button class="deleteBtn" data-page="' + ph.page + '" data-pageSize="' + ph.pageSize + '"' +
					(comment.buy_id === "${loginId}" ? '' : ' style="display:none;"') + '>삭제</button>';
			tmp += '<button class="modifyBtn" data-reviewStar="' + comment.reviewStar + '"' +
					'data-page="' + ph.page + '" data-pageSize="' + ph.pageSize + '"' +
					(comment.buy_id === "${loginId}" ? '' : ' style="display:none;"') + '>수정</button>';
			tmp += '</li>'
		});
		// 페이지 링크 추가
		if (ph, sal_id) {
			tmp += '<div class="pageContainer" style="text-align:center">';
			if (ph.totalCnt == null || ph.totalCnt == 0) {
				tmp += '<div>게시물이 없습니다.</div>';
			}
			if (ph.totalCnt != null && ph.totalCnt != 0) {
				if (ph.prevPage) {
					tmp += '<a href="#" onclick="showList(\'' + sal_id + '\', ' + (ph.beginPage - 1) + ', ' + ph.pageSize + ')">&lt;</a>';
				}
				for (let i = ph.beginPage; i <= ph.endPage; i++) {
					// 페이지 번호 사이에 공백 추가
					tmp += '<span class="page-space"></span>';
					tmp += '<a class="page ' + (i == ph.page ? "paging-active" : "") + '" href="#" onclick="showList(\'' + sal_id + '\', ' + i + ', ' + ph.pageSize + ')">' + i + '</a>';
				}
				if (ph.nextPage) {
					tmp += '<span class="page-space"></span>';
					tmp += '<a href="#" onclick="showList(\'' + sal_id + '\', ' + (ph.endPage + 1) + ', ' + ph.pageSize + ')">&gt;</a>';
				}
			}
			tmp += '</div>';
		}

		tmp += "</ul>";
		return tmp;
	}

	// 소개글 읽기
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
			error   : function(result){ alert(result.responseText) } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	// 소개글 수정
	let toChange = function (userInfoDTO) {
		let newValue = userInfoDTO.contents;
		$("textarea[name=contents]").val(newValue);
		// rv_cmt_cnt와 star_avg 값을 가져와서 HTML 요소에 넣어줌
		$("#rv_cmt_cnt").text(userInfoDTO.rv_cmt_cnt);
		$("#star_avg").text(userInfoDTO.star_avg);

		// begin과 end 값 변수로 선언
		let beginValue = 1;
		let endValue = userInfoDTO.star_avg;

		// 별표(★) 표시하는 HTML 생성
		let starHtml = '';
		for (let i = beginValue; i <= endValue; i++) {
			starHtml += ' ★';
		}

		// 빈 별표(☆) 표시하는 HTML 생성
		let emptyStarHtml = '';
		for (let i = endValue; i <= 5; i++) {
			emptyStarHtml += ' ☆';
		}

		// HTML을 동적으로 삽입
		let averageStarRatingSpan = document.getElementById('averageStarRating');
		averageStarRatingSpan.innerHTML = '<span>' + starHtml + '</span><span>' + emptyStarHtml + '</span>';

		// text박스의 readonly 속성 읽어오기
		let isReadonly =$("textarea[name=contents]").attr('readonly');

		// 수정버튼으로 바꿈 & text박스를 readonly로
		if(isReadonly!='readonly') {
			$("#modBtn").html("수정");
			$("textarea[name=contents]").attr('readonly', true);
		}
	}


	$(document).ready(function() {
		showList(ur_id); // 후기글 목록 읽어오기
		modal.style.display = "none"; // 모달창 숨기기

		// 모달 창 닫기 버튼 클릭 시 모달 창 닫기
		closeBtn.onclick = closeModal;

		// 후기글 수정
		$(document).on("click", "#comment-modBtn", function() {
			let contents = $("#reviewContents").val();
			let no = $("#reviewContents").attr("data-no");
			let page = $('#reviewContents').attr("data-page");
			let pageSize = $('#reviewContents').attr("data-pageSize");

			if(contents.trim()==''){
				alert("글을 작성해주세요");
				$("#reviewContents").focus()
				return;
			}

			$.ajax({
				type:'PATCH',       // 요청 메서드
				url: '/comments/'+no+'?sal_id='+ur_id,  // 요청 URI
				headers : { "content-type": "application/json"}, // 요청 헤더
				// dataType : 'text', // 전송받을 데이터의 타입
				data : JSON.stringify({no:no,contents:contents,reviewStar: selectedStar}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
				success : function(result){
					alert(result);
					showList(ur_id,page,pageSize);
				},
				error   : function(result){ alert(result.responseText) } // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});

		// 후기글 수정 버튼 클릭시
		$("#commentList").on("click", ".modifyBtn", function() {
			let no = $(this).parent().attr("data-no");
			let contents = $(this).parent().find("span.contents").text();
			let reviewStar = parseInt($(this).attr("data-reviewStar")); // 후기글의 별점값 가져오기
			let page = $(this).data("page"); // 현재 페이지
			let pageSize = $(this).data("pageSize"); // 페이지 크기

			// 모달 창에 후기글 정보 채우기
			$('#reviewContents').attr("data-no", no);
			$('#reviewContents').attr("data-page", page);
			$('#reviewContents').attr("data-pageSize", pageSize);
			$('#reviewContents').val(contents);

			// 모달 창의 별점 설정
			if(reviewStar === 0) {
				// 후기글의 별점이 0일 때는 아무 것도 체크되지 않도록 설정
				$("input[name='reviewStar']").prop("checked", false);
			} else {
				$("input[name='reviewStar'][value='" + reviewStar + "']").prop("checked", true);
			}

			// 모달 창 열기
			modal.style.display = "block";

			// 등록 버튼을 수정 버튼으로 변경
			$(".commentBtn").text("수정");
			$(".commentBtn").attr("id", "comment-modBtn");
		});


		// 후기글 삭제 버튼 클릭시
		$("#commentList").on("click", ".deleteBtn", function(){
			let no = $(this).parent().attr("data-no");
			let sal_id = $(this).parent().attr("data-sal_id");
			let page = $(this).data("page");
			let pageSize = $(this).data("pageSize");

			// confirm 메시지로 삭제 여부 묻기
			if (confirm("후기글 재작성이 불가능합니다. 그래도 삭제하시겠습니까?")) {
				$.ajax({
					type: 'DELETE',       // 요청 메서드
					url: '/comments/' + no + '?sal_id=' + sal_id,  // 요청 URI
					success: function(result){
						alert(result);
						showList(sal_id, page, pageSize);
						showUserInfo(ur_id);
					},
					error: function(result){
						alert(result.responseText); // 에러가 발생했을 때, 호출될 함수
					}
				}); // $.ajax()
			}
		});

		// 소개글 수정 버튼 클릭시
		$("#modBtn").click(function () {
			let ur_id = $("textarea[name=contents]").attr('id');
			let contents = $("textarea[name=contents]").val();
			let isReadonly =$("textarea[name=contents]").attr('readonly');

			if(isReadonly=='readonly') {
				$("#modBtn").html("소개글 등록");
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



		let userprofile = "${userInfoDTO.img_full_rt}";
		let uploadResult = $("#uploadResult");


		uploadResult.children().remove();
		let str = "";
		if(!userprofile){
			str += "<img src='/img/display?fileName=Noneprofile.png' class='profileimg'>";
			// alert("1")
		} else {
			str += "<img src='/img/display?fileName=" + userprofile + "' class='profileimg'>";
			// alert("2")
		}
		uploadResult.append(str);
	});
</script>

<script src="/js/img.js"></script>
</body>
</html>
