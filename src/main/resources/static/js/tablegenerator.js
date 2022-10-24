var isMouseDown = false,
    isHighlighted;

$(document).ready(function () {
    set_contextmenu();
    define_tableaction();
});

function updateResult() {
    $('#print_head').val($('#thead').html());
    $('#print_foot').val($('#tfoot').html());
}

function addRow() {
    let tr = $('<tr></tr>');
    let tdCount = $('.ctmselected').parent().find('td').length;
    for (let i = 0; i < tdCount; i++) {
        let td = $('<td class="colresize" style="width:100px;height:50px"></td>');
        tr.append(td);
    }
    $('.ctmselected').parent().after(tr);
    updateResult();
}

function deleteRow() {
    $('.ctmselected').first().parent().remove();
}

function addCol() {
    $('.ctmselected').after('<td class="colresize" style="width : 50px; height:50px;"></td>');
    updateResult();
}

function deleteCell() {
    $('.highlighted').each(function () {
        $(this).remove();
    });
    updateResult();
}

function unhighlight() {
    $('.highlighted').removeClass('highlighted');
    isHighlighted = false;
}

function set_font(font) {
    switch (font) {
        case 'bold':
            $('.ctmselected').css('font-weight', 'bold');
            break;
        case 'italic':
            $('.ctmselected').css('font-style', 'italic');
            break;
        case 'underline':
            $('.ctmselected').css('text-decoration', 'underline');
            break;
        case 'normal':
            $('.ctmselected').css('font-style', 'normal');
            $('.ctmselected').css('text-decoration', 'none');
            $('.ctmselected').css('font-weight', 'normal');
            break;
        case 'sizeup':
            let size = $('.ctmselected').css('font-size');
            size = parseInt(size) + 1;
            $('.ctmselected').css('font-size', size + 'px');
            break;
        case 'sizedown':
            let size2 = $('.ctmselected').css('font-size');
            size2 = parseInt(size2) - 1;
            $('.ctmselected').css('font-size', size2 + 'px');
            break;
    }
    updateResult();
}

function mergeCells() {
    let selected = $('.highlighted');
    let first = selected.first();
    let last = selected.last();
    let firstRow = first.parent().index();
    let firstCol = first.index();
    let lastRow = last.parent().index();
    let lastCol = last.index();
    let rowSpan = lastRow - firstRow + 1;
    let colSpan = lastCol - firstCol + 1;
    let height = first.height();
    let width = first.width() * colSpan;
    let value = first.html();

    let td = $('<td class="colresize merged" rowspan="' + rowSpan + '" colspan="' + colSpan + '" ' +
        'style="width: ' + width + ';height: ' + height + '">' + value + '</td>');

    let merged = false;
    selected.each(function () {
        if ($(this).hasClass('merged')) {
            merged = true;
            return true;
        }
    });
    if (merged) {
        $('.highlighted').each(function () {
            if ($(this).hasClass('merged')) {
                let rowSpan = $(this).attr('rowspan');
                let colSpan = $(this).attr('colspan');
                let height = $(this).height() / rowSpan;
                let width = $(this).width() / colSpan;
                let value = $(this).html();
                for (let i = 0; i < rowSpan; i++) {
                    for (let j = 0; j < colSpan; j++) {
                        let td = $('<td class="colresize" style="width: ' + width + ';height: ' + height + '">' + value + '</td>');
                        $(this).parent().after(td);
                    }
                }
                $(this).remove();
            }
        });
    } else {
        first.replaceWith(td);
        selected.each(function () {
            if ($(this).index() != first.index() || $(this).parent().index() != first.parent().index()) {
                $(this).remove();
            }
        });
    }
}

function set_contextmenu() {
    $('.contextmenu').append('<li class="contextli"><a href="javascript:void(0);" onclick="unhighlight()">선택 취소</a></li>');
    $('.contextmenu').append('<li class="contextli"><a href="javascript:void(0);" onclick="deleteRow()">행 삭제</a></li>');
    $('.contextmenu').append('<li class="contextli"><a href="javascript:void(0);" onclick="addRow()">행 추가</a></li>');
    $('.contextmenu').append('<li class="contextli"><a href="javascript:void(0);" onclick="addCol()">열 추가</a></li>');
    $('.contextmenu').append('<li class="contextli"><a href="javascript:void(0);" onclick="deleteCell()">선택값 삭제</a></li>');
    $('.contextmenu').append('<li class="contextli"><a href="javascript:void(0);" onclick="mergeCells()">병합 혹은 취소</a></li>');
    $li = $('<li class="contextli"><a href="javascript:void(0);">Font</a></li>');
    $ul = $('<ul class="contextul"></ul>');
    $ul.append('<li class="contextfontli"><a href="javascript:void(0);" onclick="set_font(\'bold\')">Bold</a></li>');
    $ul.append('<li class="contextfontli"><a href="javascript:void(0);" onclick="set_font(\'italic\')">Italic</a></li>');
    $ul.append('<li class="contextfontli"><a href="javascript:void(0);" onclick="set_font(\'underline\')">Underline</a></li>');
    $ul.append('<li class="contextfontli"><a href="javascript:void(0);" onclick="set_font(\'normal\')">Normal</a></li>');
    $ul.append('<li class="contextfontli"><a href="javascript:void(0);" onclick="set_font(\'sizeup\')">Size Up</a></li>');
    $ul.append('<li class="contextfontli"><a href="javascript:void(0);" onclick="set_font(\'sizedown\')">Size Down</a></li>');
    $li.append($ul);
    $('.contextmenu').append($li);

    $('.contextul').hide();

    $('.contextli').hover(function () {
        $(this).children('.contextul').show();
    });

    $('.contextli').on('mouseleave', function () {
        $(this).children('.contextul').hide();
    });

}

function define_tableaction() {
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


    $('.colresize').bind("selectstart", function () {
        return false;
    });


    $(document).on('mouseover', '.colresize', function (e) {

        if (e.pageX - $(this).offset().left > $(this).width() - 5) {
            $(this).css('cursor', 'col-resize');
        } else if (e.pageY - $(this).offset().top > $(this).height() - 5) {
            $(this).css('cursor', 'row-resize');
        } else {
            $(this).css('cursor', 'default');

        }

        if (isMouseDown) {
            $(this).toggleClass("highlighted", isHighlighted);
        }
        $(this).toggleClass("currenttd");
    });

    $(document).on('mouseout', '.colresize', function () {
        $(this).removeClass("currenttd");
    });

    $(document).on('mousedown', '.colresize', function (e) {
        if (e.pageX - $(this).offset().left > $(this).width() - 5) {
            $(this).addClass('resizingx');
            $(document).mousemove(function (e) {
                e.preventDefault();
                let offset = $('.resizingx').offset();
                let x = e.pageX - offset.left;
                if (x > 10 && x < $(window).width() - 10) {
                    $('.resizingx').width(x);
                } else if (x < 10) {
                    $('.resizingx').width(10);
                }
            });
        } else if (e.pageY - $(this).offset().top > $(this).height() - 5) {
            $(this).parent().find('td').addClass('resizingy');
            $(this).addClass('resizingy_selected');
            $(document).mousemove(function (e) {
                e.preventDefault();
                let offset = $('.resizingy_selected').offset();
                let y = e.pageY - offset.top;
                if (y > 10 && y < $(window).height() - 10) {
                    $('.resizingy').height(y);
                    // $(this).parent().find('.resizingy').height(y);
                } else if (y < 10) {
                    $('.resizingy').height(10);
                    // $(this).parent().find('.resizingy').height(10);
                }

            });
        } else {
            switch (e.which) {
                case 1: //left mouse button
                    isMouseDown = true;
                    $(this).toggleClass("highlighted");
                    isHighlighted = $(this).hasClass("highlighted");
                    break;
            }
        }


    });

    $(document).on('mouseup', function (e) {
        e.preventDefault();
        $('.colresize').removeClass('resizingx');
        $('.colresize').removeClass('resizingy');
        $('.colresize').removeClass('resizingy_selected');
        $('.colresize').css('cursor', 'default');
        $(document).unbind('mousemove');
        isMouseDown = false;
        updateResult();
    });


    $(document).on('dblclick', '.colresize', function (e) {
        let value = $(this).text();
        input = $('<input type="text" value="' + value + '"' +
            'style="width:100%;height:100%;padding:10px;margin:0px' +
            'box-sizing: border-box;' +
            '-moz-box-sizing: border-box;' +
            '-webkit-box-sizing: border-box">');
        $(this).html(input);
        input.focus();
    });

    $(document).on('focusout', '#table input', function () {
        $(this).parent().html($(this).val());
        updateResult();
    });


    $(document).on('contextmenu', '.colresize', function (e) {
        e.preventDefault();
        $(this).addClass('ctmselected');


        var posX = $(this).position().left;
        var posY = $(this).position().top;

        $('.contextmenu').show().css({
            left: posX,
            top: posY
        });
        return false;
    });
    $(document).bind("mousedown", function (e) {

        if (!$(e.target).parents(".contextmenu").length > 0) {
            $(".contextmenu").hide();
            $('.colresize').removeClass('ctmselected');
            if (!$(e.target).parents("#table").length > 0 ) {
                $('.colresize').removeClass('highlighted');
                isMouseDown = false;
            }
        }

    });

}