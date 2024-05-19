function modify() {
    $('#title').attr('contenteditable', 'true');
    $('#title').on('input', function() {
        $('#hiddenTitle').val($(this).text());
    });
    $('#content').removeAttr('readonly');
    $('#editBtn').hide();
    $('#saveBtn').show();
}

function validateForm() {
    var title = $('#hiddenTitle').val().trim();
    var content = $('#content').val().trim();

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

function confirmDelete() {
    return confirm('정말 삭제하시겠습니까?');
}
