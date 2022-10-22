$(document).ready(function () {
    $('#addHeadBtn').click(function () {
        $('#createstatus').val('thead');
        $('#TableCreateModal').modal('show');
    });

    $('#addFootBtn').click(function () {
        $('#createstatus').val('tfoot');
        $('#TableCreateModal').modal('show');
    });

    $('#mergeBtn').click(function () {
        mergeCells();
    });

    $('#deleterowBtn').click(function () {
        deleteRow();
    });

    $('#createBtn').click(function () {
        let status = $('#createstatus').val();
        let col = $('#col').val();
        let row = $('#row').val();
        for (let i = 0; i < row; i++) {
            let tr = $('<tr></tr>');
            for (let j = 0; j < col; j++) {
                let td = $('<td class="colresize"><input type="text" value=" "/></td>');
                tr.append(td);
            }
            $('#' + status).append(tr);
        }
        $('#TableCreateModal').modal('hide');
    });

    $(document).on('mouseover', '.colresize', function (e) {
        if (e.pageX - $(this).offset().left > $(this).width() - 5 ) {
            $(this).css('cursor', 'col-resize');
        }else if(e.pageY - $(this).offset().top > $(this).height() - 5){
            $(this).css('cursor', 'row-resize');
        }
        else{
            $(this).css('cursor', 'default');
        }
    });

    $(document).on('mousedown', '.colresize', function (e) {
        if (e.pageX - $(this).offset().left > $(this).width() - 5 ) {
            $(this).addClass('resizingx');
            $(document).mousemove(function (e) {
                e.preventDefault();
                let offset = $('.resizingx').offset();
                let x = e.pageX - offset.left;
                if (x > 10 && x < $(window).width() - 10) {
                    $('.resizingx').width(x);
                }else if(x < 10){
                    $('.resizingx').width(10);
                }
            });
        }
        if (e.pageY - $(this).offset().top > $(this).height() - 5 ) {
            $(this).parent().find('td').addClass('resizingy');
            $(this).addClass('resizingy_selected');
            $(document).mousemove(function (e) {
                e.preventDefault();
                let offset = $('.resizingy_selected').offset();
                let y = e.pageY - offset.top;
                if (y > 10 && y < $(window).height() - 10) {
                    console.log(y);
                    $('.resizingy').height(y);
                    // $(this).parent().find('.resizingy').height(y);
                }else if(y < 10){
                    $('.resizingy').height(10);
                    // $(this).parent().find('.resizingy').height(10);
                }

            });
        }

    });
    //on mouseup remove the resizing class and unbind the mousemove event
    $(document).on('mouseup', function (e) {
        e.preventDefault();
        $('.colresize').removeClass('resizingx');
        $('.colresize').removeClass('resizingy');
        $('.colresize').removeClass('resizingy_selected');
        $('.colresize').css('cursor', 'default');
        $(document).unbind('mousemove');

        $('#result_thead').val($('#thead').html());
        $('#result_tfoot').val($('#tfoot').html());
    });

    $(document).on('mousedown', '.colresize', function (e) {
        if($(this).hasClass('selected')){
            $(this).removeClass('selected');
            $(this).css('background-color', 'white');
        }else{
            $(this).addClass('selected');
            $(this).css('background-color', 'lightblue');
        }
    });

});

function deleteRow() {
    let selected = $('.selected');
    let tr = selected.first().parent();
    tr.remove();
}

function mergeCells() {
    let selected = $('.selected');
    let first = selected.first();
    let last = selected.last();
    let firstRow = first.parent().index();
    let firstCol = first.index();
    let lastRow = last.parent().index();
    let lastCol = last.index();
    let rowSpan = lastRow - firstRow + 1;
    let colSpan = lastCol - firstCol + 1;
    let value = first.html();

    let td = $('<td class="colresize" rowspan="' + rowSpan + '" colspan="' + colSpan + '"><input type="text" value="'+value+'"/></td>');

    let merged = false;
    selected.each(function () {
        //if already merged, do nothing
        if ($(this).attr('rowspan') || $(this).attr('colspan')) {
            merged = true;
            return true;
        }
    });
    if (merged) {
        unmergeCells();
    }else{
        first.replaceWith(td);
        selected.each(function () {
            if ($(this).index() != first.index() || $(this).parent().index() != first.parent().index()) {
                $(this).remove();
            }
        });
    }
}

function unmergeCells() {
    let selected = $('.selected');
    selected.each(function () {
        //while unmerging cell number should be kept same
        let rowSpan = $(this).attr('rowspan');
        let colSpan = $(this).attr('colspan');
        if (rowSpan || colSpan) {
            let value = $(this).html();
            let tr = $(this).parent();
            let index = $(this).index();
            $(this).remove();
            for (let i = 0; i < rowSpan; i++) {
                let tr = $('<tr></tr>');
                for (let j = 0; j < colSpan; j++) {
                    let td = $('<td class="colresize">'+value+'</td>');
                    tr.append(td);
                }
                tr.insertAfter(tr);
            }
        }

    });
}