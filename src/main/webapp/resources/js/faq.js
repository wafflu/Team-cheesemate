$(document).ready(function() {
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

    function adjustContentHeight(contentTr) {
        let contentTd = contentTr.find('.faq-content');
        let lineHeight = parseInt(contentTd.css('line-height'));
        let lines = contentTd.html().split('<br>').length;
        contentTd.css('height', (lineHeight * lines) + 'px');
    }

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

    $("#faq-searchInput").keypress(function(event) {
        if (event.keyCode === 13) {
            searchFaq();
        }
    });

    var questionWriteModal = $("#questionWriteModal");
    var span = $(".close");

    $(".questionWriteBtn").click(function() {
        $.ajax({
            url: '/qna/checkLogin',
            type: 'GET',
            dataType: 'json', // Ensure the response is parsed as JSON
            success: function(response) {
                if(response.loggedIn) {
                    $.ajax({
                        url: '/qna/new',
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
                    confirm("로그인이 필요합니다. 로그인 후 다시 진행해 주세요.");
                    window.location.href = '/login?from=' + encodeURIComponent(window.location.pathname + window.location.search);
                }
            },
            error: function(xhr) {
                console.error("오류발생원인 : ", xhr.responseText);
            }
        });
    });

    span.click(function() {
        questionWriteModal.hide();
        document.body.style.overflow = 'auto';
    });

    $(window).click(function(event) {
        if (event.target == questionWriteModal[0]) {
            questionWriteModal.hide();
            document.body.style.overflow = 'auto';
        }
    });

    // 초기화 시 "전체" 카테고리 FAQ 로드
    $(".faq-category-button[value='6']").addClass('active').css({
        'background-color': '#ee8703',
        'color': 'white'
    });

    categoryFaq(6);

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
});
