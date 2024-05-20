$(document).ready(function() {
    // 기존 데이터를 기반으로 대분류 및 상세 유형을 설정
    var selectedType = "${qnaDto.que_i_cd}";
    var selectedDetailType = "${qnaDto.que_i_cd}";

    // 페이지가 로드되면 대분류 데이터를 가져옴
    $.ajax({
        url: '/qna/major',
        type: 'GET',
        dataType: 'json',
        success: function(categories) {
            var typeSelect = $('#type');
            categories.forEach(function(category) {
                var option = $('<option>', {
                    value: category.que_cd,
                    text: category.name
                });
                if (category.que_cd == selectedType) {
                    option.attr('selected', 'selected');
                }
                typeSelect.append(option);
            });
        }
    });

    // 기존에 선택된 대분류가 있는 경우, 해당 상세 유형을 로드
    if (selectedType) {
        $.ajax({
            url: '/qna/sub/' + selectedType,
            type: 'GET',
            dataType: 'json',
            success: function(subCategories) {
                var detailTypeSelect = $('#detailType');
                detailTypeSelect.empty().append($('<option>', { text: '상세 유형을 선택해주세요' }));
                subCategories.forEach(function(subCategory) {
                    var option = $('<option>', {
                        value: subCategory.que_cd,
                        text: subCategory.name
                    });
                    if (subCategory.que_cd == selectedDetailType) {
                        option.attr('selected', 'selected');
                    }
                    detailTypeSelect.append(option);
                });
            }
        });
    }

    $('#type').change(function() {
        let majorId = $(this).val();
        $.ajax({
            url: '/qna/sub/' + majorId,
            type: 'GET',
            dataType: 'json',
            success: function(subCategories) {
                let select = $('#detailType');
                select.empty().append($('<option>', { text: '상세 유형을 선택해주세요' }));
                subCategories.forEach(function(category) {
                    select.append($('<option>', { value: category.que_cd, text: category.name }));
                });
            },
            error: function(xhr, status, error) {
                console.error('Error fetching sub-categories:', error);
            }
        });
    });
});

function submitCheck() {
    var type = $('#type').val();
    var detailType = $('#detailType').val();
    var title = $('#title').val().trim();
    var content = $('#content').val().trim();

    if (!type) {
        alert('유형을 선택해주세요.');
        return false;
    }
    if (!detailType) {
        alert('상세 유형을 선택해주세요.');
        return false;
    }
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
