$(document).ready(function() {
    function categoryFaq(queId) {
        $.ajax({
            url: "/faq/major",
            type: "GET",
            data: { queId: queId },
            dataType: "json",
            success: function(data) {
                var tbody = $("#faq-table-body");
                tbody.empty();
                data.forEach(function(faq) {
                    var tr = $("<tr></tr>");
                    var td = $("<td></td>").addClass('faq-title');
                    var span = $("<span></span>").addClass('faq-prefix').text('Q');
                    td.append(span);
                    td.append(document.createTextNode(faq.title));
                    tr.append(td);
                    tbody.append(tr);

                    var contentTr = $("<tr class='faq-content-row'></tr>");
                    var contentTd = $("<td></td>").attr("colspan", "1").addClass("faq-content");
                    contentTr.append(contentTd);
                    tbody.append(contentTr);
                    tr.data('no', faq.no);
                });
            },
            error: function(error) {
                console.error("Error", error);
            }
        });
    }

    $(".faq-category-button").click(function() {
        var queId = $(this).val();
        categoryFaq(queId);
    });

    $(document).on('click', '.faq-title', function() {
        var tr = $(this).closest('tr');
        var contentTr = tr.next(".faq-content-row");
        var no = tr.data('no');

        if (!contentTr.is(':visible')) {
            $(".faq-content-row").hide();
            if (contentTr.find('.faq-content').html().trim() === "") {
                $.ajax({
                    url: '/faq/getContents',
                    method: 'GET',
                    data: { no: no },
                    success: function(content) {
                        contentTr.find('.faq-content').html(content);
                        contentTr.show();
                    },
                    error: function(xhr) {
                        console.error("Error fetching content:", xhr.responseText);
                    }
                });
            } else {
                contentTr.show();
            }
        } else {
            contentTr.hide();
        }
    });

    $("#searchButton").click(function() {
        var keyword = $("#searchInput").val().trim();
        if (keyword) {
            $.ajax({
                url: '/faq/search',
                type: 'GET',
                data: { keyword: keyword },
                dataType: 'json',
                success: function(data) {
                    var tbody = $("#faq-table-body");
                    tbody.empty();
                    if (data.length === 0) {
                        tbody.append('<tr><td colspan="1">해당 검색어가 없습니다.</td></tr>');
                    } else {
                        data.forEach(function(faq) {
                            var tr = $("<tr></tr>");
                            var td = $("<td></td>").addClass('faq-title');
                            var span = $("<span></span>").addClass('faq-prefix').text('Q');
                            td.append(span);
                            td.append(document.createTextNode(faq.title));
                            tr.append(td);
                            tbody.append(tr);

                            var contentTr = $("<tr class='faq-content-row'></tr>");
                            var contentTd = $("<td></td>").attr("colspan", "1").addClass("faq-content");
                            contentTr.append(contentTd);
                            tbody.append(contentTr);
                            tr.data('no', faq.no);
                        });
                    }
                },
                error: function(xhr) {
                    console.error("검색 Error:", xhr.responseText);
                }
            });
        }
    });
    categoryFaq(6);
});
