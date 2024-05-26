<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="fixed/header.jsp" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.userId}"/>
<c:set var="percentage" value="${userInfoDTO.star_avg * 20}" />
<c:set var="floorPercentage" value="${fn:substringBefore(percentage, '.')}"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="/css/myPage.css">
<div class="mypage-box">
	<div class="mypageinfobox">
		<div class="info">
			<div class="info-container">
				<div id="profileimg">
					<div class="form_section_content">
						<label for="profile" class="btn-upload"></label>
						<input type="file" id="profile" name='uploadFile'>
					</div>
					<div id="uploadResult"></div>
					<div id="profilesave_btn_area"></div>
				</div>
				<div class="container">
					<div class="container-header">
						<div class="container-name">
							<h2>${userInfoDTO.nick}</h2>
							<c:if test="${userInfoDTO.ur_id eq loginId}">
								<button class="sc-iBmynh hZjTaB">계정 설정</button>
							</c:if>
						</div>
					</div>
					<div class="container-contents">
						<div class="container-detail-c">
							<span class="material-symbols-outlined">storefront</span>
							상점오픈일
							<div class="sc-dTLGrV dNeqzX">
								<c:choose>
									<c:when test="${userInfoDTO.r_date.time >= startOfToday}">
										<fmt:formatDate value="${userInfoDTO.r_date}" pattern="HH:mm" type="time"/>
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${userInfoDTO.r_date}" pattern="yyyy-MM-dd" type="date"/>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="fZjeFM">
							<span class="material-symbols-outlined" style="color: #789DE5">
							accessibility_new
							</span>
							상점방문수
							<div class="dNeqzX">${userInfoDTO.view_cnt}</div>
						</div>
						<div class="fZjeFM">
							<span class="material-symbols-outlined" style="color: #78A75A">
							shopping_bag
							</span>
							상품판매
							<div class="dNeqzX">${userInfoDTO.complete_cnt} 회</div>
						</div>
					</div>
					<div class="buyynq">
						<c:if test="${userInfoDTO.ur_id eq loginId}">
							<textarea id="${userInfoDTO.ur_id}" name="contents" placeholder=" 내용을 입력해 주세요." readonly='readonly'><c:out value='${userInfoDTO.contents}'/></textarea><br>
						</c:if>
						<c:if test="${userInfoDTO.ur_id ne loginId}">
							<textarea id="${userInfoDTO.ur_id}" name="contents" placeholder=" 소개글이 없습니다." readonly='readonly'><c:out value='${userInfoDTO.contents}'/></textarea><br>
						</c:if>
					</div>
					<div class="fiWvqk">
						<c:if test="${userInfoDTO.ur_id eq loginId}">
							<button class="hZjTaB" type="button" id="modBtn">소개글 수정</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="subTab">
	<div class="subTab-main">
		<div class="subTab-container">
			<a class="subTab-link gnIlaT active" data-target="saleList" onclick="showBox(event, 'saleList')">
				상품
				<span class="iCtbOc">${saleCnt}</span>
			</a>
			<a class="subTab-link jqkJIn" data-target="reviewBox" onclick="showBox(event, 'reviewBox')">
				상점후기
				<span class="iCtbOc">${userInfoDTO.rv_cmt_cnt}</span>
			</a>
			<c:if test="${userInfoDTO.ur_id eq loginId}">
			<a class="subTab-link jqkJIn" data-target="wishList" onclick="showBox(event, 'wishList')">
				찜
				<span class="iCtbOc">0</span>
			</a>
			</c:if>
		</div>
	</div>
</div>
<div class="review-box" id="reviewBox" style="display: none;">
	<div class="review-box-main">
		<div class="ejYulE">
			<div>
				상점후기
				<span class="eERcxq">${userInfoDTO.rv_cmt_cnt}</span>
			</div>
			<div class="reviewStar">
				<div class="reviewStar-main">
					<div class="fWtYmf">
						<div class="fGpXKG">${userInfoDTO.star_avg}</div>
						<div class="star">
							 <span id="averageStarRating">
									<c:forEach begin="1" end="${userInfoDTO.star_avg}" var="i">
										<span class="filled-star">★</span>
									</c:forEach>
																 <!-- 빈 별표(☆) 표시 -->
									<c:forEach begin="${userInfoDTO.star_avg + 1}" end="5" var="i">
										<span class="empty-star">☆</span>
									</c:forEach>
								</span>
						</div>
					</div>
					<div class="qEvCW"></div>
					<div class="hlvDzf">
						<div class="hSIAij">${floorPercentage}%</div>
						<div class="eyeUYg">만족후기</div>
					</div>
				</div>
				<div class="hoyCQB"></div>
			</div>
		</div>
	</div>
	<div class="review-content"></div>
	<div class="cZDGoh"></div>
</div>
<div class="saleList" id="saleList">
	<div class="saleList-main">
		<div class="saleList-main-box">
			<div>
				상품
				<span class="cefQuP">${saleCnt}</span>
			</div>
			<div class="gnpSLd">
				<a class="jVzTFl" onclick="updateOption('R', this)">최신순</a>
				<a class="kFYwqy" onclick="updateOption('P', this)">인기순</a>
				<a class="kFYwqy" onclick="updateOption('L', this)">저가순</a>
				<a class="kFYwqy" onclick="updateOption('H', this)">고가순</a>
			</div>
		</div>
		<div class="kFaLlW">
			<div class="gFaYhg">
				<c:if test="${userInfoDTO.ur_id eq loginId}">
					<button class="hZjTaB" type="button" id="myHistoryButton">나의 내역보기</button>
				</c:if>
			</div>
			<div class="hdZbBo">
				<div class="saleListBox"></div>
				<div id="pageContainer"></div>
			</div>
			<div class="cZDGoh"></div>
		</div>
	</div>
</div>
<div class="wishList" id="wishList" style="display: none;">
	<!-- Wishlist content -->
</div>



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
<script>
	document.addEventListener("DOMContentLoaded", function() {
		var myHistoryButton = document.getElementById("myHistoryButton");

		myHistoryButton.addEventListener("click", function() {
			window.location.href = "/myPage/saleInfo";
		});
	});

	function showBox(event, boxId) {
		// Prevent default link behavior
		event.preventDefault();

		// Get all boxes and links
		const boxes = document.querySelectorAll('.review-box, .saleList, .wishList');
		const links = document.querySelectorAll('.subTab-link');

		// Hide all boxes
		boxes.forEach(box => box.style.display = 'none');

		// Remove 'active' class from all links
		links.forEach(link => link.classList.remove('active'));

		// Show the selected box
		document.getElementById(boxId).style.display = 'block';

		// Add 'active' class to the clicked link
		event.currentTarget.classList.add('active');
	}

	document.addEventListener("DOMContentLoaded", function() {
		// Initialize first box as visible
		document.getElementById('saleList').style.display = 'block';
	});

	const uploadImage = (function() {
		let imginfo = [];

		<c:forEach items="${imglist}" var="img">
		<c:if test="${img.imgtype eq 'r'}">
		imginfo.push(
				{
					"file_rt" : "${img.file_rt}",
					"o_name" : "${img.o_name}",
					"e_name" : "${img.e_name}"
				}
		)
		</c:if>
		</c:forEach>

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
			data : JSON.stringify(uploadImage.getImgInfo()),
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
		return value > 9 ? value : "0" + value;
	}

	let dateToString = function(ms=0) {
		let now = new Date();
		let date = new Date(ms);

		let diff = now - date; // 시간 차이(ms)
		let diffSeconds = Math.floor(diff / 1000);
		let diffMinutes = Math.floor(diffSeconds / 60);
		let diffHours = Math.floor(diffMinutes / 60);
		let diffDays = Math.floor(diffHours / 24);

		if (diffDays > 0) {
			return diffDays + "일 전";
		} else if (diffHours > 0) {
			return diffHours + "시간 전";
		} else if (diffMinutes > 0) {
			return diffMinutes + "분 전";
		} else {
			return diffSeconds + "초 전";
		}
	}
	function dateToString2(ms = 0, startOfToday) {
		let date = new Date(ms);

		let yyyy = date.getFullYear();
		let mm = addZero(date.getMonth() + 1);
		let dd = addZero(date.getDate());

		let HH = addZero(date.getHours());
		let MM = addZero(date.getMinutes());
		let ss = addZero(date.getSeconds());

		let now = new Date();

		if (date.getTime() >= startOfToday) {
			let secondsAgo = Math.floor((now.getTime() - date.getTime()) / 1000);
			if (secondsAgo < 60) {
				return secondsAgo + "초 전";
			} else {
				let minutesAgo = Math.floor(secondsAgo / 60);
				if (minutesAgo < 60) {
					return minutesAgo + "분 전";
				} else {
					let hoursAgo = Math.floor(minutesAgo / 60);
					return hoursAgo + "시간 전";
				}
			}
		}
		let daysAgo = Math.floor((now.getTime() - date.getTime()) / (1000 * 60 * 60 * 24)) + 1;
		return daysAgo + "일 전";
	}

	// 후기글 작성 버튼
	let writeBtn = document.getElementById("writeBtn");

	// 모달 창
	const modal = document.getElementById("myModal");

	// 모달 창 닫기 버튼
	let closeBtn = document.getElementsByClassName("close")[0];

	// 모달 창을 닫는 함수
	function closeModal() {
		modal.style.display = "none";
	}

	// 선택된 별점 값 담을 변수
	let selectedStar;

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
		// alert("선택된 별점 값: " + selectedStar);
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
				let list = result.list;
				$(".review-content").html(toHtml(comments,ph,sal_id,list));
				closeModal();
			},
			error   : function(result){ alert(result.responseText) } // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	let toHtml = function (comments, ph, sal_id, list) {
		let tmp = "<div class='review-content'>"; // 전체 리뷰 컨테이너 시작

		comments.forEach(function (comment, index) { // 인덱스 추가
			tmp += "<div class='review-content-main' data-no='" + comment.no + "' data-sal_id='" + comment.sal_id + "'>"; // data-no와 data-sal_id를 여기 설정
			tmp += '<a class="kafLtc">';
			if (list[index]) { // list 배열에서 인덱스를 사용하여 매칭된 이미지 사용
				tmp += "<img src='/img/display?fileName=" + list[index] + "' width='60' height='60'>";
			}
			tmp += '</a>';
			tmp += '<div class="isNONu">';
			tmp += '<div class="gITkRS">';
			tmp += '<div class="klTXgX">';
			tmp += '<a class="dHvBOk">';
			tmp += '<span class="buy_id clickable" data-buy_id="' + comment.buy_id + '">' + comment.buy_nick + '</span></a>';
			tmp += '<div class="jiCdsX">' + dateToString(comment.m_date) + '</div>'; // 날짜
			tmp += '</div>';
			tmp += '<a class="kYkcZG">';
			tmp += '<div class="hkIXgx">';
			// 별표(★) 개수만큼 추가
			for (let i = 0; i < comment.reviewStar; i++) {
				tmp += '<span class="filled-star">★</span>';
			}
			// 빈 별표 추가
			for (let i = comment.reviewStar; i < 5; i++) {
				tmp += '<span class="empty-star">☆</span>';
			}
			tmp += '</div>';
			tmp += '</a>';
			tmp += '</div>';
			tmp += '<a class="cDFhiy">';
			tmp += '<a href="/sale/read?no=' + comment.sal_no + '" class="llefdR">' + comment.psn + '</a>'; // 판매글 링크를 버튼 스타일로 적용
			tmp += '</a>';
			tmp += '<div class="ldWWHF">' + '<span class="contents">' + comment.contents + '</span>' + '</div>'; // 내용
			tmp += '<div class="glxCHJ"></div>';
			tmp += '<div class="bxbDWR">';
			tmp += '<a class="bWGJaI">신고하기</a>';
			tmp += '<button class="deleteBtn bWGJaI" data-page="' + ph.page + '" data-pageSize="' + ph.pageSize + '"' +
					(comment.buy_id === "${loginId}" ? '' : ' style="display:none;"') + '>삭제</button>';
			tmp += '<button class="modifyBtn bWGJaI" data-reviewStar="' + comment.reviewStar + '"' +
					'data-page="' + ph.page + '" data-pageSize="' + ph.pageSize + '"' +
					(comment.buy_id === "${loginId}" ? '' : ' style="display:none;"') + '>수정</button>';
			tmp += '</div>';
			tmp += '</div>';
			tmp += '</div>';
		});

		// 페이지 링크 추가
		if (ph && sal_id) {
			tmp += '<div class="pageContainer" style="text-align:center">';
			if (ph.totalCnt == null || ph.totalCnt == 0) {
				tmp += '<div>후기가 없습니다.</div>';
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

		tmp += "</div>"; // 전체 리뷰 컨테이너 종료
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
			starHtml += '<span class="filled-star">★</span>';
		}

		// 빈 별표(☆) 표시하는 HTML 생성
		let emptyStarHtml = '';
		for (let i = endValue; i <5; i++) {
			emptyStarHtml += '<span class="empty-star">☆</span>';
		}

		// HTML을 동적으로 삽입
		let averageStarRatingSpan = document.getElementById('averageStarRating');
		averageStarRatingSpan.innerHTML = '<span>' + starHtml + '</span><span>' + emptyStarHtml + '</span>';

		// text박스의 readonly 속성 읽어오기
		let isReadonly =$("textarea[name=contents]").attr('readonly');

		// 수정버튼으로 바꿈 & text박스를 readonly로
		if(isReadonly!='readonly') {
			$("#modBtn").html("소개글 수정");
			$("textarea[name=contents]").attr('readonly', true);
		}
	}

	function comma(num){
		return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
	}

	// 정렬 옵션에 따라 saleList 함수를 호출하고 클릭된 a 태그에 active 클래스를 추가하는 함수
	function updateOption(option, element) {
		// 모든 a 태그의 active 클래스를 제거
		document.querySelectorAll('.gnpSLd a').forEach(function(a) {
			a.classList.remove('active');
		});
		// 클릭된 a 태그에 active 클래스를 추가
		element.classList.add('active');
		// saleList 함수 호출
		window.saleList(1, 20, 'null', 'null', option);
	}

	window.saleList = function (page = 1, pageSize = 20,title='null',sal_s_cd='null',option='null') {
		$.ajax({
			type: 'GET',       // 요청 메서드
			url: "/sale/managePage?page=" + page + "&pageSize=" + pageSize +  "&title=" + title+ "&sal_s_cd=" + sal_s_cd + "&option=" + option,  // 요청 URI
			headers: {"content-type": "application/json"}, // 요청 헤더
			dataType: 'json',
			success: function (data) {
				// 후기글 목록과 페이징 정보 가져오기
				// let comments = result.comments;
				let ph = data.ph;
				let saleList = data.saleList;
				let startOfToday = data.startOfToday;
				$(".saleListBox").html(updateSaleList(saleList, startOfToday, ph,title,sal_s_cd,option));
			},
			error: function (result) {
				alert("화면 로딩 중 오류 발생");
				// alert(result.responseText)
			} // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	// 업데이트된 saleList를 화면에 출력하는 함수
	function updateSaleList(saleList, startOfToday, ph,title,sal_s_cd,option) {
		// 기존 saleList 테이블의 tbody를 선택하여 내용을 비웁니다.
		$(".saleListBox").empty();

		if (saleList.length > 0) {
			let str = "";
			// 판매 상태에 따라 텍스트 설정
			saleList.forEach(function (sale) {
				switch (sale.sal_s_cd) {
					case 'S':
						saleStatusText = '판매중';
						break;
					case 'R':
						saleStatusText = '예약중';
						break;
					case 'C':
						saleStatusText = '판매<br>완료';

						break;
					default:
						saleStatusText = '';
				}

				let saleTitle = sale.title;
				if (saleTitle.length > 13) {
					saleTitle = saleTitle.substring(0, 13) + '...';
				}

				let salePrice = sale.price;
				let saleTxSCd = sale.tx_s_cd;
				if (saleTxSCd === "S") {
					salePrice = comma(salePrice)+"원";
				} else {
					salePrice = "나눔";
				}

				let saleDate = new Date(sale.h_date);
				str += "<div class='smallBox'>";
				str += "<a href='/sale/read?no=" + sale.no + "'>";
				str += "<div class='info-imgbox'>";
				str += "<img class='img' src='/img/display?fileName=" + sale.img_full_rt + "'/>";
				if(saleStatusText !== "판매중"){
					str += "<div class='saleStatus-box'>";
					str += "<span class='saleStatusText'>" + saleStatusText + "</span>";
					str += "</div>";
				}
				let saleBidCd = sale.bid_cd;
				if (saleBidCd === "P") {
					str += "<p class='active-nego'>" + "가격제시" + "</p>";
				} else if (saleBidCd === "T") {
					str += "<p class='active-nego'>" + "나눔신청" + "</p>";
				}
				str += "</div>";
				str += "<div class='info-title info'>";
				str += "<span class='saleTitle'>" + saleTitle + "</span>";
				str += "</div>";
				str += "<div class='info-sale info'>";
				str += "<span class='salePrice'>" + salePrice + "</span>";
				str += "<span class='saledate'>" + dateToString2(sale.h_date, startOfToday) + "</span>";
				str += "</div>";
				str += "<div class='addr-box info'>";
				str += "<p class='saleaddr'>" + sale.addr_name + "</p>";
				str += "</div>";
				str += "</a>";
				str += "</div>";


				$(".saleListBox").html(str);
			});
		} else {
			$(".saleListBox").html("<p style='font-size: 20px; text-align: center;'>데이터가 없습니다.</p>");
		}

		$("#pageContainer").empty(); // 기존에 있는 페이지 내용 비우기

		if (ph.totalCnt != null && ph.totalCnt != 0) {
			let pageContainer = $('<div>').attr('id', 'pageContainer').css('text-align', 'center'); // 새로운 div 엘리먼트 생성
			if (ph.prevPage) {
				pageContainer.append('<a onclick="saleList(' + (ph.beginPage - 1) + ', ' + ph.pageSize + ', \'' + title + '\', \'' + sal_s_cd + '\', \'' + option + '\')">&lt;</a>');
			}
			for (let i = ph.beginPage; i <= ph.endPage; i++) {
				// 페이지 번호 사이에 공백 추가
				pageContainer.append('<span class="page-space"></span>');
				pageContainer.append('<a class="page ' + (i == ph.page ? "paging-active" : "") + '" href="#" onclick="saleList(' + i + ', ' + ph.pageSize + ', \'' + title + '\', \'' + sal_s_cd + '\', \'' + option + '\')">' + i + '</a>');
			}
			if (ph.nextPage) {
				pageContainer.append('<span class="page-space"></span>');
				pageContainer.append('<a onclick="saleList(' + (ph.endPage + 1) + ', ' + ph.pageSize + ', \'' + title + '\', \'' + sal_s_cd + '\', \'' + option + '\')">&gt;</a>');
			}
			$("#pageContainer").html(pageContainer); // 새로 생성한 페이지 컨테이너를 추가
		} else {
			let pageContainer = $('<div>').attr('id', 'pageContainer').css('text-align', 'center'); // 새로운 div 엘리먼트 생성
			pageContainer.append('<div>후기가 없습니다.</div>');
		}
	}

	$(document).ready(function() {
		saleList();
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
					// alert(result);
					showList(ur_id,page,pageSize);
					showUserInfo(ur_id);
				},
				error   : function(result){ alert(result.responseText) } // 에러가 발생했을 때, 호출될 함수
			}); // $.ajax()
		});

		// 후기글 수정 버튼 클릭시
		$(".review-content").on("click", ".modifyBtn", function() {
			console.log("Modify button clicked");

			let no = $(this).closest(".review-content-main").data("no");
			let sal_id = $(this).closest(".review-content-main").data("sal_id");
			let contents = $(this).closest(".review-content-main").find("span.contents").text();
			let reviewStar = parseInt($(this).attr("data-reviewStar")); // 후기글의 별점값 가져오기
			let page = $(this).data("page"); // 현재 페이지
			let pageSize = $(this).data("pageSize"); // 페이지 크기

			// 모달 창에 후기글 정보 채우기
			$('#reviewContents').attr("data-no", no);
			$('#reviewContents').attr("data-sal_id", sal_id); // 추가된 부분
			$('#reviewContents').attr("data-page", page);
			$('#reviewContents').attr("data-pageSize", pageSize);
			$('#reviewContents').val(contents);

			// 모달 창의 별점 설정
			if (reviewStar === 0) {
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
		$(".review-content").on("click", ".deleteBtn", function() {
			console.log("Delete button clicked");

			let no = $(this).closest(".review-content-main").data("no");
			let sal_id = $(this).closest(".review-content-main").data("sal_id");
			let page = $(this).data("page");
			let pageSize = $(this).data("pageSize");

			// confirm 메시지로 삭제 여부 묻기
			if (confirm("후기글 재작성이 불가능합니다. 그래도 삭제하시겠습니까?")) {
				$.ajax({
					type: 'DELETE',       // 요청 메서드
					url: '/comments/' + no + '?sal_id=' + sal_id,  // 요청 URI
					success: function(result) {
						// alert(result);
						showList(sal_id, page, pageSize);
						showUserInfo(ur_id);
					},
					error: function(result) {
						alert(result.responseText); // 에러가 발생했을 때, 호출될 함수
					}
				}); // $.ajax()
			}
		});

		// buy_id 클릭 이벤트 처리
		$(".review-content").on("click", ".clickable", function() {
			console.log("Buy ID clicked");

			let buyId = $(this).data("buy_id");
			window.location.href = '/myPage/main?ur_id=' + buyId;
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
					// alert(result);
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
</div>

<script src="/js/img.js"></script>
<%@ include file="fixed/footer.jsp" %>