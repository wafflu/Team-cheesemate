$(document).ready(function() {
    // 모달 초기 상태를 숨김으로 설정
    $("#questionWriteModal").hide();
    $("#questionListModal").hide();

    // 특정 카테고리에 대한 FAQ를 불러오는 함수
    function categoryFaq(queId) {
        $.ajax({
            url: "/faq/major",
            type: "GET",
            data: { queId: queId },
            dataType: "json",
            success: function(data) {
                let tbody = $("#faq-table-body");
                tbody.empty();
                data.forEach(function(faq) {
                    let tr = $("<tr></tr>");
                    let td = $("<td></td>").addClass('faq-title');
                    let span = $("<span></span>").addClass('faq-prefix').text('Q');
                    let arrow = $("<span></span>").addClass('faq-arrow down');
                    td.append(span);
                    td.append(document.createTextNode(faq.title));
                    td.append(arrow);
                    tr.append(td);
                    tbody.append(tr);

                    let contentTr = $("<tr class='faq-content-row'></tr>");
                    let contentTd = $("<td></td>").attr("colspan", "1").addClass("faq-content");
                    contentTr.append(contentTd);
                    tbody.append(contentTr);
                    tr.data('no', faq.no);
                });
                setEventHandlers();  // 이벤트 핸들러를 설정
            },
            error: function(error) {
                console.error("Error", error);
            }
        });
    }

    // FAQ 항목 클릭 시 내용 표시/숨김 처리 및 내용 로드 함수
    function setEventHandlers() {
        $(".faq-title").off('click').on('click', function() {
            let tr = $(this).closest('tr');
            let contentTr = tr.next(".faq-content-row");
            let no = tr.data('no');
            let arrow = $(this).find('.faq-arrow');

            if (!contentTr.is(':visible')) {
                $(".faq-content-row").hide();
                $(".faq-arrow").removeClass('up').addClass('down');
                if (contentTr.find('.faq-content').html().trim() === "") {
                    $.ajax({
                        url: '/faq/getContents',
                        method: 'GET',
                        data: { no: no },
                        success: function(content) {
                            let span = $("<span></span>").addClass('faq-prefix-answer').text('A');
                            let formattedContent = content.replace(/\n/g, "<br> &nbsp;&nbsp;&nbsp;&nbsp;");
                            contentTr.find('.faq-content').html('').append(span).append(formattedContent);
                            contentTr.show();
                            arrow.removeClass('down').addClass('up');
                            adjustContentHeight(contentTr);
                        },
                        error: function(xhr) {
                            console.error("Error fetching content:", xhr.responseText);
                        }
                    });
                } else {
                    contentTr.show();
                    arrow.removeClass('down').addClass('up');
                    adjustContentHeight(contentTr);
                }
            } else {
                contentTr.hide();
                arrow.removeClass('up').addClass('down');
            }
        });
    }

    // FAQ 내용의 높이를 조정하는 함수
    function adjustContentHeight(contentTr) {
        let contentTd = contentTr.find('.faq-content');
        let lineHeight = parseInt(contentTd.css('line-height'));
        let lines = contentTd.html().split('<br>').length;
        contentTd.css('height', (lineHeight * lines) + 'px');
    }

    // FAQ 검색 기능 함수
    function searchFaq() {
        let keyword = $("#faq-searchInput").val().trim();
        if (keyword) {
            $.ajax({
                url: '/faq/search',
                type: 'GET',
                data: { keyword: keyword },
                dataType: "json",
                success: function(data) {
                    let tbody = $("#faq-table-body");
                    tbody.empty();
                    if (data.length === 0) {
                        tbody.append('<tr><td colspan="1">해당 검색어가 없습니다.</td></tr>');
                    } else {
                        data.forEach(function(faq) {
                            let tr = $("<tr></tr>");
                            let td = $("<td></td>").addClass('faq-title');
                            let span = $("<span></span>").addClass('faq-prefix').text('Q');
                            let arrow = $("<span></span>").addClass('faq-arrow down');
                            td.append(span);
                            td.append(document.createTextNode(faq.title));
                            td.append(arrow);
                            tr.append(td);
                            tbody.append(tr);

                            let contentTr = $("<tr class='faq-content-row'></tr>");
                            let contentTd = $("<td></td>").attr("colspan", "1").addClass("faq-content");
                            contentTr.append(contentTd);
                            tbody.append(contentTr);
                            tr.data('no', faq.no);
                        });
                        setEventHandlers();  // 이벤트 핸들러를 설정
                    }
                },
                error: function(xhr) {
                    console.error("검색 Error:", xhr.responseText);
                }
            });
        }
    }

    // 검색 입력란에서 Enter 키를 눌렀을 때 FAQ 검색 실행
    $("#faq-searchInput").keypress(function(event) {
        if (event.keyCode === 13) {
            searchFaq();
        }
    });

    var questionWriteModal = $("#questionWriteModal");
    var span = $(".close");

    // "1:1 문의" 버튼 클릭 시 로그인 확인 및 모달 표시
    $(".questionWriteBtn").click(function() {
        $.ajax({
            url: '/qna/checkLogin',
            type: 'GET',
            dataType: 'json',
            success: function(response) {
                if(response.loggedIn) {
                    $.ajax({
                        url: '/qna/new',
                        type: 'GET',
                        success: function(data) {
                            $("#modal-body").html(data);
                            questionWriteModal.show();
                            document.body.style.overflow = 'hidden'; //외부 스크롤 막기
                        },
                        error: function(xhr) {
                            console.error("오류발생원인 : ", xhr.responseText);
                        }
                    });
                } else {
                    window.location.href = '/login?from=' + encodeURIComponent(window.location.pathname + window.location.search);
                }
            },
            error: function(xhr) {
                console.error("오류발생원인 : ", xhr.responseText);
            }
        });
    });

    // "문의 내역" 버튼 클릭 시 로그인 확인 및 모달 표시
    $(".questionListBtn").click(function() {
        $.ajax({
            url: '/qna/checkLogin',
            type: 'GET',
            dataType: 'json',
            success: function(response) {
                if(response.loggedIn) {
                    $.ajax({
                        url: '/qna/list',
                        type: 'GET',
                        success: function(data) {
                            $("#modal-body").html(data);
                            questionWriteModal.show();
                            document.body.style.overflow = 'hidden';
                        },
                        error: function(xhr) {
                            console.error("오류발생원인 : ", xhr.responseText);
                        }
                    });
                } else {
                    window.location.href = '/login?from=' + encodeURIComponent(window.location.pathname + window.location.search);
                }
            },
            error: function(xhr) {
                console.error("오류발생원인 : ", xhr.responseText);
            }
        });
    });

    // 모달 닫기 버튼 클릭 시 모달 닫기
    span.click(function() {
        questionWriteModal.hide();
        document.body.style.overflow = 'auto';
    });

    // 모달 외부 클릭 시 모달 닫기
    $(window).click(function(event) {
        if (event.target == questionWriteModal[0]) {
            questionWriteModal.hide();
            document.body.style.overflow = 'auto';
        }
    });

    // 페이지 초기화 시 "전체" 카테고리 FAQ 로드
    $(".faq-category-button[value='6']").addClass('active').css({
        'background-color': '#ee8703',
        'color': 'white'
    });
    categoryFaq(6);

    // FAQ 카테고리 버튼 클릭 시 해당 카테고리 FAQ 로드
    $(".faq-category-button").click(function() {
        let queId = $(this).val();
        categoryFaq(queId);

        $(".faq-category-button").css({
            'background-color': 'white',
            'color': 'black',
            'border': '1px solid #8A8989'
        }).removeClass('active');

        $(this).css({
            'background-color': '#ee8703',
            'color': 'white',
            'border': '1px solid'
        }).addClass('active');
    });

    // 페이지 초기화 시 이벤트 핸들러 설정
    setEventHandlers();

    // qnaForm 제출 시 AJAX로 처리하고 모달 내 내용을 갱신
    $(document).on('submit', '#qnaForm', function(e) {
        e.preventDefault(); // 기본 폼 제출 막기

        var formData = $(this).serialize();

        $.ajax({
            url: $(this).attr('action'),
            type: $(this).attr('method'),
            data: formData,
            dataType: 'json',
            success: function(response) {
                if(response.success) {
                    // 폼 제출 성공 시 모달 내용을 갱신
                    $.ajax({
                        url: '/qna/list',
                        type: 'GET',
                        success: function(data) {
                            $("#modal-body").html(data);
                            // 모달 내부에서 스크롤을 사용할 수 있도록 설정
                            document.body.style.overflow = 'hidden';
                        },
                        error: function(xhr) {
                            console.error("오류발생원인1 : ", xhr.responseText);
                        }
                    });
                } else {
                    alert(response.message || "폼 제출에 실패했습니다.");
                }
            },
            error: function(xhr) {
                console.error("오류발생원인2 : ", xhr.responseText);
            }
        });
    });
});
