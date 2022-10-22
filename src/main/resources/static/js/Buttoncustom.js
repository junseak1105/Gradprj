const copy_Btn = {
    extend: 'copy',
    text: 'Copy',
    className: 'btn btn-default',
}
const excel_Btn = {
    extend: 'excel', //파일명
    title: 'excel',
    className: 'btn btn-success',
}

const delete_Btn = {
    text: '삭제',
    className: 'btn btn-danger',
    float: 'right',
    action: function (e, dt, node, config) {
        deleteData();
    }
}
const add_Btn = {

    text: '추가',
    float: 'right',
    className: 'btn btn-primary',
    action: function (e, dt, node, config) {
        addBtn();
    }
}

function get_print_Btn(title,header,footer){
    return {
        extend: 'print',
        className: 'btn btn-info',
        title: title,
        customize: function (win) {
            $(win.document.body)
                .css('font-size', '10pt')
                .prepend(
                    '<img src="http://datatables.net/media/images/logo-fade.png" style="position:absolute; top:0; left:0;" />'
                );
            $(win.document.body).find('table')
                .addClass('compact')
                .css('font-size', 'inherit');
            $(win.document.body).find('h1').remove();
            $tfoot = $("<tfoot></tfoot>").attr('id','print_footer');
            $(win.document.body).find('tbody').after($tfoot);
            $(win.document.body).find('thead').prepend(header);
            $(win.document.body).find('thead').prepend('<tr><td colspan="100%" style="text-align: center;"><h3>'+title+'</h3></td></tr>');
            $(win.document.body).find('#print_footer').append(footer);

            // console.log($(win.document.body).find('table').html());

        }
    }
}