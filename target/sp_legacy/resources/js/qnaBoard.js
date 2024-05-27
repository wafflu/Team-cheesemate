function qnaBoard_modify() {
    $('#qnaBoard_title').removeAttr('readonly');
    $('#qnaBoard_title').on('input', function() {
        $('#qnaBoard_hiddenTitle').val($(this).val());
    });
    $('#qnaBoard_content').removeAttr('readonly');
    $('#qnaBoard_editBtn').hide();
    $('#qnaBoard_saveBtn').show();
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

$(document).ready(function() {
    var msg = $('#flashMsg').val();
    if (msg) {
        alert(msg);
    }
});
