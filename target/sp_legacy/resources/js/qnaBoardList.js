$(document).ready(function() {
    // 제목 클릭 시 AJAX로 상세 페이지 로드
    $('.qnaBoardList_title').on('click', function(event) {
        event.preventDefault(); // 기본 링크 클릭 동작을 막음

        var qnaNo = $(this).attr('href').split('=')[1]; // URL에서 문의 번호 추출

        $.ajax({
            url: '/qna/read', // 상세 페이지 URL
            type: 'GET',
            data: { no: qnaNo },
            success: function(data) {
                $('#modal-body').html(data); // 모달 내용 업데이트
                $('#questionWriteModal').show(); // 모달 열기
                document.body.style.overflow = 'hidden'; // 외부 스크롤 막기
            },
            error: function(xhr) {
                console.error("오류 발생:", xhr.responseText);
            }
        });
    });

    // 모달 닫기 버튼 클릭 시 모달 닫기
    $('.close').click(function() {
        closeModal();
    });

    // 모달 외부 클릭 시 모달 닫기
    $(window).click(function(event) {
        if (event.target == $('#questionWriteModal')[0] || event.target == $('#questionListModal')[0]) {
            closeModal();
        }
    });

    // 페이지네이션 링크 클릭 시 페이지 이동 처리
    $(document).on('click', '#qnaBoardList_pagination a', function(event) {
        event.preventDefault();
        var pageUrl = $(this).attr('href').replace('/faq/', '/qna/');
        loadQnaList(pageUrl);
    });

    // QnA 리스트를 로드하는 함수
    function loadQnaList(url) {
        $.ajax({
            url: url,
            type: 'GET',
            success: function(data) {
                $('#modal-body').html(data); // 모달 내용 업데이트
                // 페이지네이션 링크에 이벤트 다시 바인딩
                bindPaginationLinks();
            },
            error: function(xhr) {
                console.error("오류 발생:", xhr.responseText);
            }
        });
    }

    // 페이지네이션 링크 초기 바인딩
    function bindPaginationLinks() {
        $('#qnaBoardList_pagination a').off('click').on('click', function(event) {
            event.preventDefault();
            var pageUrl = $(this).attr('href').replace('/faq/', '/qna/');
            loadQnaList(pageUrl);
        });
    }

    // 모달 닫기 함수
    function closeModal() {
        $('#questionWriteModal').hide();
        $('#questionListModal').hide();
        $('#modal-body').empty();
        document.body.style.overflow = 'auto'; // 외부 스크롤 복구
    }

    // 페이지네이션 링크 처음에 바인딩
    bindPaginationLinks();
});