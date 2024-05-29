<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="fixed/header.jsp" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.userId}"/>
<link rel="stylesheet" href="/css/saleInfo.css">
<div class="maincontent saleinfo-box">
	<div class="mainContainer">
		<a class="sc-jxOSlx kDyrQm bun-ui-header-back-button"  onclick="window.history.back();">
			<svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" role="img" style="position: relative; top: 37px;">
				<path d="M14.265 19.537a.9.9 0 1 0 1.27-1.274l-8.362-8.34 8.365-8.387A.9.9 0 0 0 14.263.264l-9 9.024a.902.902 0 0 0 .002 1.273l9 8.976z" fill="#1e1d29" fill-rule="evenodd"></path>
			</svg>
		</a>
		<div class="bun-ui-divider">
			<button class="bun-ui-tab bun-ui-tab-selected" id="seller">
				<span class="bun-ui-tab-label">판매내역</span>
			</button>
			<button class="bun-ui-tab" id="buyer">
				<span class="bun-ui-tab-label">구매내역</span>
			</button>
		</div>
		<section class="purchase-info">
			<div>
				<button class="bun-ui-tab bun-ui-tab-selected" id="A" data-label-seller="전체" data-label-buyer="전체">
					<span class="bun-ui-tab-label">전체</span>
				</button>
				<button class="bun-ui-tab" id="R" data-label-seller="예약중" data-label-buyer="예약중">
					<span class="bun-ui-tab-label">예약중</span>
				</button>
				<button class="bun-ui-tab" id="S" data-label-seller="판매중" data-label-buyer="구매중">
					<span class="bun-ui-tab-label">판매중</span>
				</button>
				<button class="bun-ui-tab" id="C" data-label-seller="거래완료" data-label-buyer="구매완료">
					<span class="bun-ui-tab-label">거래완료</span>
				</button>
			</div>
			<div class="sc-57cf470b-1 fFtTgD">
				<form id="searchForm">
					<div class="sc-cPiKLX irylio bun-ui-search-container">
						<svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" role="img">
							<path d="M8.99 16.162c-3.955 0-7.172-3.218-7.172-7.172A7.18 7.18 0 0 1 8.99 1.818c3.954 0 7.171 3.217 7.171 7.172 0 3.954-3.217 7.172-7.171 7.172zm6.963-1.494A8.953 8.953 0 0 0 17.98 8.99C17.98 4.032 13.946 0 8.99 0 4.033 0 0 4.032 0 8.99c0 4.957 4.033 8.99 8.99 8.99a8.943 8.943 0 0 0 5.676-2.027l3.781 3.78a.908.908 0 0 0 1.287 0 .909.909 0 0 0 0-1.285l-3.78-3.78z" fill="#b2b2b2" fill-rule="evenodd"></path>
						</svg>
						<input id="searchInput" placeholder="상품명으로 검색해보세요" enterkeyhint="search" class="bun-ui-search" value="">
					</div>
				</form>
				<button class="sc-57cf470b-3 ferXDa">
					<svg width="16" height="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 18" role="img">
						<path d="M6.6 11.3c1.562 0 2.866 1.063 3.265 2.5H19a.9.9 0 1 1 0 1.8H9.865c-.399 1.436-1.703 2.5-3.265 2.5s-2.866-1.064-3.265-2.5H1a.9.9 0 0 1 0-1.8h2.335c.4-1.437 1.703-2.5 3.265-2.5zm0 1.8c-.882 0-1.6.718-1.6 1.6 0 .882.718 1.6 1.6 1.6.882 0 1.6-.718 1.6-1.6 0-.882-.718-1.6-1.6-1.6zm6.801-11.2c1.562 0 2.866 1.063 3.265 2.5h2.335a.9.9 0 0 1 0 1.8h-2.335c-.399 1.436-1.703 2.5-3.265 2.5s-2.866-1.064-3.265-2.5H1.001a.9.9 0 1 1 0-1.8h9.135c.399-1.437 1.703-2.5 3.265-2.5zm0 1.8c-.882 0-1.6.718-1.6 1.6 0 .882.718 1.6 1.6 1.6.882 0 1.6-.718 1.6-1.6 0-.882-.718-1.6-1.6-1.6z" fill="#1e1d29" fill-rule="evenodd"></path>
					</svg>
				</button>
			</div>
		</section>
		<section id="historyList"></section>

		<div class="pageContainer"></div>

		<div id="myModal" class="modal" style="display: none;">
			<div class="modal-content">
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
					<button class="commentBtn" id="comment-sendBtn" type="button">등록</button>
					<button class="cancelBtn" id="cancelBtn" type="button">취소</button>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	// 세션id 변수로 선언
	let ur_id = "${loginId}";

	// 선택된 별점 값 담을 변수
	let selectedStar;

	// 판매자 ID
	let seller_id;

	// 판매글 no
	let no;

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
	});

	let selectedIndex;

	// 모달 창
	var modal = document.getElementById("myModal");

	// 모달 창을 닫는 함수
	function closeModal() {
		modal.style.display = "none";
	}
	// esc누를시 모달 창을 닫는 함수
	$(document).on('keydown', function(event) {
		if (event.key === 'Escape') {
			$('#myModal').hide();
		}
	});

	// 취소 버튼 기능 추가
	var cancelBtn = document.getElementById("cancelBtn");
	cancelBtn.onclick = function() {
		closeModal();
	}

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

		return yyyy+"."+mm+"."+dd;
	}

	// 내역 목록 보여주기
	let showList = function (page = 1, pageSize = 10, option = 'seller', sal_s_cd = '',keyword= '') {
		$.ajax({
			type: 'POST',       // 요청 메서드
			url: "/myPage/saleHistorys",  // 요청 URI
			headers: {"content-type": "application/json"}, // 요청 헤더
			dataType: 'json',
			data: JSON.stringify({
				ur_id: ur_id,
				page: page,
				pageSize: pageSize,
				option: option,
				sal_s_cd: sal_s_cd,
				keyword: keyword
			}),
			success: function (result) {
				// 내역 목록과 페이징 정보 가져오기
				let list = result.list;
				let ph = result.ph;
				$("#historyList").html(toHtml(list, ph,option,sal_s_cd,keyword));
				let mainContainerH = $(".mainContainer").outerHeight();
				// console.log(mainContainerH)
				if(mainContainerH >= 1000){
					mainContainerH = $(".mainContainer").outerHeight()+200;
				} else {
					mainContainerH = $(".mainContainer").outerHeight()+50;
				}
				$('.saleinfo-box').css('height', mainContainerH + 'px');
				// console.log(mainContainerH)
			},
			error: function (result) {
				alert(result.responseText);
			} // 에러가 발생했을 때, 호출될 함수
		}); // $.ajax()
	}

	let toHtml = function (list, ph, option, sal_s_cd, keyword) {
		let tmp = "";

		list.forEach(function (item, index) { // 인덱스를 추가합니다.
			let saleStatusText = ""; // saleStatusText를 초기화합니다.

			switch (item.sal_s_cd) {
				case 'S':
					saleStatusText = '판매중';
					break;
				case 'R':
					saleStatusText = '예약중';
					break;
				case 'C':
					if (option === 'buyer') {
						saleStatusText = '구매완료';
					} else {
						saleStatusText = '거래완료';
					}
					break;
				default:
					saleStatusText = '';
			}

			let saleTitle = item.title;
			if (saleTitle.length > 12) {
				saleTitle = saleTitle.substring(0, 12) + '...';
			}

			tmp += '<article>';

			// header 부분 생성
			tmp += '<header>';
			tmp += '<h3>' + dateToString(item.r_date) + '</h3>';
			tmp += '<svg class="clickable" data-no="' + item.no + '" width="12" height="12" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" role="img">';
			tmp += '<path d="M5.262 19.536a.9.9 0 0 0 1.273 0l9-8.974a.905.905 0 0 0 .265-.636.904.904 0 0 0-.263-.637l-9-9.024a.9.9 0 1 0-1.275 1.27l8.365 8.388-8.363 8.34a.9.9 0 0 0-.002 1.273" fill="#7f7f7f" fill-rule="evenodd"></path>';
			tmp += '</svg>';
			tmp += '</header>';

			// divider 추가
			tmp += '<hr class="bun-ui-divider">';

			// section 부분 생성
			tmp += '<section>';
			tmp += '<section class="content-section" style="display: flex; position: relative;">';  // Flexbox 적용 및 position 설정
			tmp += "<a href='/sale/read?no=" + item.no + "' class='imgboxlink'>"
			tmp += "<img class='imgClass' src='/img/display?fileName=" + item.img_full_rt + "' style='width: 150px; height: auto;'/>";  // 이미지 크기 조정
			if(saleStatusText !== "판매중"){
				tmp += "<div class='saleStatus-box'>";
				tmp += "<span class='saleStatusText'>" + saleStatusText + "</span>";
				tmp += "</div>";
			}
			tmp += '</a>';
			tmp += '<section class="text-section">';
			if (option === 'seller') {
				tmp += '<p class="mp-saleStatusText">' + saleStatusText + '</p>';
			} else {
				tmp += '<p class="mp-saleStatusText">' + saleStatusText + '</p>';
			}
			if(item.tx_s_cd === 'F'){
				tmp += '<p class="mp-tx_s_cd">[나눔]</p>';
			}
			tmp += '<p class="mp-saleTitle">제목: ' + saleTitle + '</p>';
			if ((option === 'seller' && item.sal_s_cd == 'R') || (option === 'seller' && item.sal_s_cd == 'C')) {
				// item.buyer_id가 null이 아닌 경우에만 태그 추가
				if (item.buyer_id !== null) {
					tmp += '<p class="mp-buyer_id">구매자: <a href="#" class="seller-link" data-seller-id="' + item.buyer_id + '">' + item.buyer_nick + '</a></p>';
				}
			}
			else if (option === 'buyer') {
				tmp += '<p class="mp-seller-link">판매자: <a href="#" class="seller-link" data-seller-id="' + item.seller_id + '">' + item.seller_nick + '</a></p>';
			}
			let salePrice = item.price;
			tmp += '<p class="mp-price">가격: ' + comma(salePrice) + '원</p>';


			let trade = "";
			if(item.trade_s_cd_1 === "O"){
				trade = "온라인";
			} else if(item.trade_s_cd_1 === "F" || item.trade_s_cd_2 === "F" ) {
				trade = "직거래";
			} else if(item.trade_s_cd_1 === "D" || item.trade_s_cd_2 === "F" ) {
				trade = "택배거래";

			}
			tmp += '<p class="mp-trade">거래방법: ' + trade + '</p>';
			tmp += '</section>';

			// footer 부분 생성
			if (item.rv_state === 'Y' && option === 'buyer' && item.sal_s_cd === 'C') {
				tmp += '<footer class="footer-visible">'; // 구매내역 버튼이 클릭된 경우에만 hidden 속성 추가하지 않음
			} else {
				tmp += '<footer class="footer-hidden">'; // 그 외의 경우 hidden 속성 추가
			}
			tmp += '<button class="writeBtn" type="button" data-id="' + item.seller_id + '" data-no="' + item.no + '" data-index="' + index + '">후기 남기기</button>';
			tmp += '</footer>';

			tmp += '</section>';
			tmp += '</section>';
			tmp += '</section>';

			tmp += '</article>';
		});
		let str = "";
		// 페이지 링크 추가
		if (ph) {
			if (ph.totalCnt == null || ph.totalCnt == 0) {
				str += '<div class="nopage">내역이 없습니다.</div>';
			}
			if (ph.totalCnt != null && ph.totalCnt != 0) {
				if (ph.prevPage) {
					str += '<button onclick="showList(' + (ph.beginPage - 1) + ', ' + ph.pageSize + ', \'' + option + '\', \'' + sal_s_cd + '\', \'' + keyword + '\'); window.scrollTo(0, 0);">&lt;</button>';
				}
				for (let i = ph.beginPage; i <= ph.endPage; i++) {
					// 페이지 번호 사이에 공백 추가
					str += '<span class="page-space"></span>';
					str += '<button class="page ' + (i == ph.page ? "paging-active" : "") + '" onclick="showList(' + i + ', ' + ph.pageSize + ', \'' + option + '\', \'' + sal_s_cd + '\', \'' + keyword + '\'); window.scrollTo(0, 0);">' + i + '</button>';
				}
				if (ph.nextPage) {
					str += '<span class="page-space"></span>';
					str += '<button onclick="showList(' + (ph.endPage + 1) + ', ' + ph.pageSize + ', \'' + option + '\', \'' + sal_s_cd + '\', \'' + keyword + '\'); window.scrollTo(0, 0);">&gt;</button>';
				}
			}
			$(".pageContainer").html(str);
		}
		return tmp;
	}

	$(document).ready(function(){

		showList();
		let option = $('#seller').hasClass('bun-ui-tab-selected') ? 'seller' : 'buyer';
		updateButtonLabels(option);

		// 판매내역, 구매내역 버튼 클릭 이벤트 설정
		$(".bun-ui-divider .bun-ui-tab").click(function () {
			// 같은 div 내의 모든 버튼에서 bun-ui-tab-selected 클래스를 제거
			$(".bun-ui-divider .bun-ui-tab").removeClass("bun-ui-tab-selected");
			// 클릭한 버튼에 bun-ui-tab-selected 클래스 추가
			$(this).addClass("bun-ui-tab-selected");

			// searchInput 값을 초기화
			$("#searchInput").val('');

			// '전체' 버튼에 bun-ui-tab-selected 클래스 추가
			$("#A").addClass("bun-ui-tab-selected");
			$("#R").removeClass("bun-ui-tab-selected");
			$("#S").removeClass("bun-ui-tab-selected");
			$("#C").removeClass("bun-ui-tab-selected");

			// 내역 목록 업데이트
			let option = $(this).attr('id');
			showList(1, 10, option);

			// 버튼 레이블 업데이트
			updateButtonLabels(option);
		});

		// 판매,구매상태 버튼 클릭 이벤트 설정
		$(".purchase-info .bun-ui-tab").click(function () {
			// 같은 div 내의 모든 버튼에서 bun-ui-tab-selected 클래스를 제거
			$(".purchase-info .bun-ui-tab").removeClass("bun-ui-tab-selected");
			// 클릭한 버튼에 bun-ui-tab-selected 클래스 추가
			$(this).addClass("bun-ui-tab-selected");

			let id = $(this).attr('id');
			let option = $('#seller').hasClass('bun-ui-tab-selected') ? 'seller' : 'buyer';
			let searchValue = $("#searchInput").val();

			updateButtonLabels(option);

			if (id === 'R') {
				showList(1, 10, option, 'R', searchValue);
			} else if (id === 'S') {
				showList(1, 10, option, 'S', searchValue);
			} else if (id === 'C') {
				showList(1, 10, option, 'C', searchValue);
			} else {
				showList(1, 10, option, '', searchValue);
			}
		});

		function updateButtonLabels(option) {
			const buttons = $(".purchase-info .bun-ui-tab");
			buttons.each(function() {
				const button = $(this);
				const label = option === 'buyer' ? button.attr('data-label-buyer') : button.attr('data-label-seller');
				button.find('.bun-ui-tab-label').text(label);
			});
			// 구매내역일 때 "판매중" 버튼 숨기기
			if (option === 'buyer') {
				$("#S").hide();
			} else {
				$("#S").show();
			}
		}

		// SVG 클릭 이벤트 처리
		$("#historyList").on("click", ".clickable", function() {
			let no = $(this).data("no");
			window.location.href = '/sale/read?no=' + no;
		});

		// 판매자ID,구매자ID 클릭 이벤트 처리
		$(document).on('click', '.seller-link', function (e) {
			e.preventDefault();  // 기본 동작을 막음
			const sellerId = $(this).data('seller-id');
			window.location.href = '/myPage/main?ur_id=' + sellerId;
		});


		$("#searchForm").submit(function (event) {
			event.preventDefault();
			let searchValue = $("#searchInput").val();
			let option = $('#seller').hasClass('bun-ui-tab-selected') ? 'seller' : 'buyer';
			let sal_s_cd = $(".purchase-info .bun-ui-tab.bun-ui-tab-selected").attr('id');
			if (sal_s_cd === 'A') {
				showList(1, 10, option, '', searchValue);
			} else {
				showList(1, 10, option, sal_s_cd, searchValue);
			}
		});

		$(document).on("click", ".writeBtn", function(event) {
			seller_id = $(this).data("id");
			no = $(this).data("no");
			selectedIndex = $(this).data("index"); // 인덱스 값 읽기

			$('#reviewContents').val('');
			$("input[name='reviewStar']").prop("checked", false);
			selectedStar = undefined;

			// 모달 창을 화면의 좌상단에 고정
			var modal = $('#myModal');

			modal.css({
				top: '0px',
				left: '0px',
				width: '103%',
				display: 'flex'
			});

			// .modal-content로 스크롤
			$('.modal-content')[0].scrollIntoView({
				behavior: 'smooth',
				block: 'center'
			});

			modal.data('index', selectedIndex); // 모달 창에 인덱스 값을 저장
		});


		$(document).on("click", "#cancelBtn, .modal", function(event) {
			if (event.target.className === 'modal' || event.target.id === 'cancelBtn') {
				$('#myModal').css('display', 'none');
			}
		});

		$(document).on("click", ".modal-content", function(event) {
			event.stopPropagation();
		});



		// 후기글 전송 버튼
		$(document).on("click", "#comment-sendBtn", function() {
			let contents = $("#reviewContents").val();
			selectedIndex = $('#myModal').data('index')
			if (contents.trim() == '') {
				alert("글을 작성해주세요");
				$("#reviewContents").focus();
				return;
			}

			$.ajax({
				type: 'POST',
				url: "/comments?no=" + no,
				headers: { "content-type": "application/json" },
				data: JSON.stringify({ sal_id: seller_id, contents: contents, reviewStar: selectedStar }),
				success: function(result) {
					// 후기가 성공적으로 작성되었음을 알리는 메시지
					alert('후기가 성공적으로 작성되었습니다.');

					// confirm 창을 사용하여 사용자에게 확인 요청
					if (confirm('판매자의 상점을 방문하시겠습니까?')) {
						const url = "/myPage/main?ur_id=" + encodeURIComponent(seller_id);
						window.location.href = url;
					} else {
						// 사용자가 취소를 선택한 경우, 추가 동작이 필요하면 여기에 추가
						console.log('상점 방문이 취소되었습니다.');
						closeModal();
						// 선택한 후기 남기기 버튼을 삭제시켜야함
						// 선택한 후기 남기기 버튼을 삭제
						$('.writeBtn[data-index="' + selectedIndex + '"]').remove();
					}
				},
				error: function(result) {
					alert(result.responseText);
				}
			});
		});
	});
</script>
<script src="/js/Etc.js"></script>
<script src="/js/img.js"></script>
<%@ include file="fixed/footer.jsp" %>