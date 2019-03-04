function pageInit(list, paginator, isLoadMore) {
    list = spliceItemList(list);
    if (list.length && !isLoadMore) makePaginator(paginator);
    return list;
}

function spliceItemList(list) {
    var resultArr = [];
    var len = list.length;
    for (var i = 0; i < Math.ceil(len / 6); i++) {
        resultArr.push(list.splice(0, 6));
    }
    return resultArr;
}

function makePaginator(paginator) {
    var html = '<nav style="display: flex;justify-content: center;"><ul class="pagination">';
    if (paginator.prev) {
        if (paginator.searchText) {
            html += '<li class="page-item"><a class="page-link" href="/?page=' + paginator.prev + '&searchText=' + paginator.searchText;
        } else {
            html += '<li class="page-item"><a class="page-link" href="/?page=' + paginator.prev;
        }
        html += '" aria-label="Previous"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
    } else {
        html += '<li class="page-item"><a class="page-link" href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
    }

    if (paginator.start < paginator.end) {
        for (var i = paginator.start; i <= paginator.end; i++) {
            if (paginator.page === i) {
                html += '<li class="page-item active"><a class="page-link" data-page="' + i + '" href="javascript:void(0);">' + i + '</a></li>';
            } else {
                html += '<li class="page-item"><a class="page-link" data-page="' + i + '" href="javascript:void(0);">' + i + '</a></li>';
            }
        }
    } else {
        html += '<li class="page-item active"><a class="page-link" data-page="' + paginator.page + '" href="javascript:void(0);">' + paginator.page + '</a></li>';
    }

    if (paginator.next) {
        if (paginator.searchText) {
            html += '<li class="page-item"><a class="page-link" href="/?page=' + paginator.next + '&searchText=' + paginator.searchText;
        } else {
            html += '<li class="page-item"><a class="page-link" href="/?page=' + paginator.next;
        }
        html += '" aria-label="Next"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
    } else {
        html += '<li class="page-item"><a class="page-link" href="javascript:void(0);" aria-label="Next"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
    }
    html += '</ul></nav>';
    $('#paginator').html(html);
}

function makePaginatorEmpty() {
    $('#paginator').html('');
}

function toggleActive($elem) {
    $('.page-item.active').removeClass('active');
    $elem.addClass('active');
}