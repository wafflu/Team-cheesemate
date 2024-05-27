function qnaBoard_modify() {
    $('#qnaBoard_title').removeAttr('readonly');
    $('#qnaBoard_title').on('input', function() {
        $('#qnaBoard_hiddenTitle').val($(this).val());
    });
    $('#qnaBoard_content').removeAttr('readonly');
    $('#qnaBoard_editBtn').hide();
    $('#qnaBoard_saveBtn').show();
}

function loadQnaListModal() {
    $.ajax({
        url: '/qna/list',
        type: 'GET',
        success: function() {
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
        },
        error: function(xhr) {
            console.error("오류 발생: ", xhr.responseText);
            alert("문의 내역을 불러오는 데 실패했습니다.");
        }
    });
}

function qnaBoard_validateForm() {
    var title = $('#qnaBoard_hiddenTitle').val().trim();
    var content = $('#qnaBoard_content').val().trim();

    if (!title) {
        alert('제목을 입력해주세요.');
        return false;
    }
    if (!content || content.length < 20) {
        alert('내용을 20자 이상 입력해주세요.');
        return false;
    }
    return true; // 모든 검사가 통과하면 폼 제출을 허용
}

function qnaBoard_confirmDelete() {
    return confirm('정말 삭제하시겠습니까?');
}

function autoResizeTextarea(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = (textarea.scrollHeight) + 'px';
}

$(document).ready(function() {
    var msg = $('body').data('msg');
    if (msg) {
        alert(msg);
    }

    // 페이지 로드 시 textarea 크기를 자동 조절합니다.
    var textareas = document.querySelectorAll("textarea");
    textareas.forEach(function(textarea) {
        autoResizeTextarea(textarea);
    });

    $("#qnaBoard_saveBtn").click(function(e) {
        e.preventDefault(); // 기본 폼 제출 막기
        if (!qnaBoard_validateForm()) {
            return false;
        }
        var formData = $("#qnaBoard_modifyForm").serialize();

        $.ajax({
            url: "/qna/modify",
            type: "POST",
            data: formData,
            dataType: "json",
            success: function(response) {
                if (response.success) {
                    alert(response.message);
                    loadQnaReadModal(response.qnaNo); // 수정 후 read 페이지 로드
                } else {
                    alert(response.message);
                }
            },
            error: function(xhr) {
                console.error("오류 발생: ", xhr.responseText);
                alert("오류가 발생했습니다. 다시 시도해주세요.");
            }
        });
    });

    $(".qnaBoard_deleteButton").click(function(e) {
        e.preventDefault(); // 기본 폼 제출 막기
        if (!qnaBoard_confirmDelete()) {
            return false;
        }
        var formData = $(this).closest("form").serialize();

        $.ajax({
            url: "/qna/delete",
            type: "POST",
            data: formData,
            dataType: "json",
            success: function(response) {
                if (response.success) {
                    // 삭제 성공 시 모달 내용을 갱신
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
                    alert(response.message);
                }
            },
            error: function(xhr) {
                console.error("오류 발생: ", xhr.responseText);
                alert("오류가 발생했습니다. 다시 시도해주세요.");
            }
        });
    });

    // read 페이지를 로드하여 모달에 표시하는 함수
    function loadQnaReadModal(qnaNo) {
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
    }

});
