var pageurl;
var pagename;
var tablename;
var tableinfo;
var tabledata;
var columns = new Array();
var buttonlist = new Array();

$().ready(function () {
    pageurl = content_url;
    pagename = pageurl.substring(pageurl.lastIndexOf('/') + 1);
    set_page();
});

function set_page() {
    get_tableinfo(); // 테이블 정보 조회
    get_tabledata(); // 테이블 데이터 조회
    set_DataTable_Buttons(); //데이터 테이블 버튼 생성
    set_DataTable(); //데이터 테이블 생성
    set_DataTable_Toolbar(); //데이터 테이블 툴바 생성
    set_DataTableAction(); //데이터 테이블 액션 설정
    set_DataModal(); //데이터 입력용 모달 생성
}

function get_tableinfo() {
    url = "/api/tableinfo/" + pagename;
    $.ajax({
        type: "GET",
        url: encodeURI(url),
        dataType: 'json',
        async: false,
        success: function (data) {
            console.log("tableinfo | StatusCode:" + data.statusCode + "ResponseMessage:" + data.responseMessage);
            tableinfo = data.data;
            tablename = tableinfo.tablename;
        }
    });
};

function get_tabledata() {
    url = "/api/tabledata/" + tablename;
    $.ajax({
        type: "GET",
        url: url,
        dataType: 'json',
        async: false,
        success: function (data) {
            console.log("tabledata | StatusCode:" + data.statusCode + "ResponseMessage:" + data.responseMessage);
            tabledata = data.data;
        }
    });
};


/**
 * [데이터 검증]
 * @param value
 * @returns {boolean}
 */
function value_validation(value) {
    var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
    if (special_pattern.test(value) == true) {
        alert("특수문자는 입력할 수 없습니다.");
        return false;
    }
    return true;
}

/**
 * [데이터 검증 끝]
 */

/**
 * [데이터 미입력 상태 확인]
 * Required 속성이 있는 input 태그의 값이 없는 경우 경고창 출력
 * @returns {boolean}
 */
function chk_requried() {
    var chk = true;
    $('#dataForm').find('input,select').each(function () {
        if ($(this).attr('required') == 'required') {
            if ($(this).val() == '') {
                alert($(this).prev().text() + "는 필수 입력값입니다.");
                chk = false;
                return false;
            }
        }
    });
    return chk;
}

/**
 * [데이터 미입력 상태 확인 끝]
 */

/**
 * [데이터 추가 클릭시]
 */
function addBtn() {
    $('#status').val('add');
    $('#DataModalLabel').text('추가');
    $('#dataForm').find('input').val('');
    if (tableinfo.code != null) {
        $('#' + tableinfo.code.code_column).prev().append("(자동생성)");
        $('#' + tableinfo.code.code_column).val(tableinfo.code.code);
    }
    $("#DataModal").modal("show");
}

/**
 * [데이터 삭제 버튼 클릭 시 액션]
 * 1. 삭제 데이터 처리
 * 2. 삭제 데이터 DB에 반영
 */
function deleteData() {
    // 1. 삭제 데이터 처리

    var selected = "";
    $('.selected').each(function () {
        selected += $(this).find("td").eq(0).text() + ",";
    });
    if (selected == "") {
        alert("삭제할 데이터를 선택해주세요.");
        return;
    }
    selected = selected.substring(0, selected.length - 1);

    console.log(selected);
    var key_column = $('#key_column').val();
    var data = {
        key_column: key_column,
        selected: selected,
    };

    // 2. 삭제 데이터 DB에 반영
    var url = "/api/tabledata/" + tablename;
    $.ajax({
        type: 'Delete',
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data.statusCode == 200) {
                $("#DataModal").modal("hide");
                $('#dg').DataTable().ajax.reload();
            }
            $('#ResultModal').modal('show');
            $('#result').html(data.responseMessage);
        }
    });

};//e:deleteData()

/**
 * [데이터 삭제 버튼 클릭 시 액션 끝]
 */

/**
 * [데이터 저장 버튼 클릭 시 액션]
 * 1. 데이터 미입력 상태 확인
 * 2. 데이터 저장 상태 확인(추가: POST, 수정: PUT)
 * 3. 데이터 처리
 * 4. 데이터 DB에 반영
 */
function saveData() {
    // 1. 데이터 미입력 상태 확인
    if (!chk_requried()) return;

    // 2. 데이터 저장 상태 확인(추가: POST, 수정: PUT)
    var status = $('#status').val();
    var url = "/api/tabledata/" + tablename;
    var type = "POST";
    if (status == 'edit') {
        type = "PUT";
    }

    // 3. 데이터 처리
    var column = "";
    var value = "";
    key_column = $('#key_column').val();
    key_value = $('#key_value').val();

    validation = true;
    $('#dataForm').find('input,select').each(function () {
        if (!value_validation($(this).val())) {
            validation = false;
            return;
        }
        if ($(this).val() != '') {
            column += $(this).attr('id') + ",";
            info_column = $(this).attr('id');
            if (tableinfo.code != null) {
                if (tableinfo.code.code_column == info_column) {
                    value += "NewCode('" + tableinfo.code.code + "'),";
                }
            } else {
                value += "'" + $(this).val() + "',";
            }
        }
    });
    if (!validation) return;

    column = column.substring(0, column.length - 1);
    value = value.substring(0, value.length - 1);
    console.log(tablename);
    var data = {
        tablename: tablename,
        key_column: key_column,
        key_value: key_value,
        column: column,
        value: value
    };
    console.log(data);
// 4. 데이터 DB에 반영
    $.ajax({
        type: type,
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data.statusCode == 200) {
                $("#DataModal").modal("hide");
                $('#dg').DataTable().ajax.reload();
            }
            $('#ResultModal').modal('show');
            $('#result').html(data.responseMessage);
        }
    });
}
;//e:saveData()


/**
 * [데이터 입력용 모달 생성]
 */
function set_DataModal() {
    $form = $("<form></form>").attr("id", "dataForm").attr("name", "dataForm");

    //테이블 컬럼 갯수 만큼 생성
    for (var i = 0; i < tableinfo.data.length; i++) {

        //라벨 추가
        $form.append("<label For='" + tableinfo.data[i].column_Name + "'>" + tableinfo.data[i].column_Comment + "</label>");

        //필수값 처리 nullable 여부로 판단
        requried = "";
        if (tableinfo.data[i].is_Nullable == 'NO') {
            requried = "required";
        }

        //외래키인 경우 select option으로 생성
        if (tableinfo.data[i].ref_Table != null) {
            $select = $("<select></select>").attr("id", tableinfo.data[i].column_Name).attr("name", tableinfo.data[i].column_Name).attr("class", "form-control").attr("required", requried);
            $select.append("<option value=''>선택</option>");
            ref_table = tableinfo.data[i].ref_Table;
            //외래키 테이블의 데이터를 가져와서 select option에 추가, 현재는 pk만 찾아서 값으로 추가
            var pri_column;
            for (var j = 0; j < tableinfo[ref_table].info.length; j++) {
                column_key = tableinfo[ref_table].info[j].column_Key;
                if (column_key == 'PRI') {
                    pri_column = tableinfo[ref_table].info[j].column_Name;
                    break;
                }
            }
            for (var j = 0; j < tableinfo[ref_table].data.length; j++) {
                $select.append("<option value='" + tableinfo[ref_table].data[j][pri_column] + "'>" + tableinfo[ref_table].data[j][pri_column] + "</option>");
            }
            $form.append($select);
        } else {
            //일반 컬럼인 경우 input으로 생성
            if (tableinfo.code != null) {
                if (tableinfo.code.code_column == tableinfo.data[i].column_Name) {
                    $form.append("<input type='text' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control'  value = " + tableinfo.code.code + " readonly>");
                } else {
                    switch (tableinfo.data[i].data_Type) {
                        case 'date':
                            $form.append("<input type='date' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + ">");
                            break;
                        case 'int':
                            $form.append("<input type='number' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + " min='0'>");
                            break;
                        case 'varchar':
                            $form.append("<input type='text' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + " maxlength='" + tableinfo.data[i].data_Length + "'>");
                            break;
                        case 'tinyint':
                            $form.append("<select id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + "><option value='1'>사용</option><option value='0'>미사용</option></select>");
                            break;
                    }
                }
            } else {
                switch (tableinfo.data[i].data_Type) {
                    case 'date':
                        $form.append("<input type='date' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + ">");
                        break;
                    case 'int':
                        $form.append("<input type='number' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + " min='0'>");
                        break;
                    case 'varchar':
                        $form.append("<input type='text' id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + " maxlength='" + tableinfo.data[i].data_Length + "'>");
                        break;
                    case 'tinyint':
                        $form.append("<select id='" + tableinfo.data[i].column_Name + "' name='" + tableinfo.data[i].column_Name + "' class='form-control' " + requried + "><option value='1'>사용</option><option value='0'>미사용</option></select>");
                        break;
                }
            }
        }
    }
    $("#DataModalBody").append($form);
}//e:set_DataModal();


/**
 * [페이지 생성, 초기 설정]
 */
function set_DataTable() {
    $("#tablename").val(tablename);
    $("#key_column").val(tableinfo.key_column);
    $(".page-header").text(pagename);
    $(".panel-heading").text(tablename);
    /**
     *    [테이블 thead&tfoot 제작]
     *    헤더명: column에 지정된 comment로 생성됨.
     *    <주의사항>
     *      각 테이블 생성 시 column에 comment를 반드시 지정해야 함.
     *      comment 없을 시 테이블 헤더가 빈값으로 지정됨.
     */
    $tr = $("<tr></tr>");
    $tr2 = $("<tr></tr>");
    for (var i = 0; i < tableinfo.data.length; i++) {
        $tr.append("<th>" + tableinfo.data[i].column_Comment + "</th>");
        $tr2.append("<th>" + tableinfo.data[i].column_Comment + "</th>");
        column = new Object();
        columnDef = new Object();
        column.data = tableinfo.data[i].column_Name;
        column.className = tableinfo.data[i].column_Name;
        columns.push(column);
    }
    $("#table-header").append($tr);
    $("#table-footer").append($tr2);
    /**
     * [DataTable 초기 생성]
     */
    $('#dg').DataTable({
        ajax: '/api/tabledata/' + tablename,
        columns: columns,
        paging: true,
        toolbar: "#toolbar",
        scroller: true,
        info: true,
        autoWidth: false,
        responsive: true,
        lengthChange: true,
        lengthMenu: [
            [5, 10, 25, 50, -1],
            [5, 10, 25, 50, 'All'],
        ],
        dom: '<"top"<"left-col"B><"right-col"f>>' +
            'rt'+
            '<"pagenation"p>'+
            '<"bottom"<"left-col"i><"right-col"l>>',
        buttons: buttonlist
    });
    /**
     * [DataTable 초기 생성 끝]
     */


}//e: set_DataTable();


/**
 * [데이터 테이블 액션 설정]
 */

function set_DataTableAction() {
    var table = $('#dg').DataTable();

    // 데이터 클릭 시 색 변경
    $('#dg tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        $('.odd').css("background-color", "#F2F2F2");
        $('.even').css("background-color", "#FFFFFF");
        $('.selected').css("background-color", "#BCCEF8");

    });

    //데이터 더블 클릭 시 수정창 생성
    $('#dg tbody').on('dblclick', 'tr', function () {
        $("#DataModal").modal("show");
        $('#status').val('edit');
        $('#DataModalLabel').text('수정');
        $(this).find("td").each(function () {
            var name = $(this).attr("class");
            name = name.trim();
            if (name.search(/\s/) != -1) {
                name = name.substring(0, name.search(/\s/));
            }
            var value = $(this).text();
            //tinyint용 변환
            if (value == 'true') {
                value = 1;
            } else if (value == 'false') {
                value = 0;
            }

            //keyvalue 저장용
            var key_column = $('#key_column').val();
            if (name == key_column) {
                $('#key_value').val(value);
            }

            // console.log(name + " : " + value);
            $("#" + name).val(value);
        });
    });

    //데이터 추가 버튼 클릭 시 액션
    $('#addBtn').on('click', function () {
        $('#status').val('add');
        $('#DataModalLabel').text('추가');
        $('#dataForm').find('input').val('');
        if (tableinfo.code != null) {
            $('#' + tableinfo.code.code_column).prev().append("(자동생성)");
            $('#' + tableinfo.code.code_column).val(tableinfo.code.code);
        }
        $("#DataModal").modal("show");
    });


    //[데이터 저장 버튼 클릭 시 액션
    $('#saveBtn').click(function () {
        saveData();
    });
}//e: set_DataTableAction();

/**
 * [DataTable 툴바 생성]
 */
function set_DataTable_Toolbar() {
    $(".top").find(".left-col").css("width", "70%").css("float", "left");
    $(".top").find(".right-col").css("width", "30%").css("float", "right");

    $(".pagenation").css("display", "flex").css("justify-content", "center");

    $(".bottom").find(".left-col").css("width", "70%").css("float", "left");
    $(".bottom").find(".right-col").css("float", "right");
}

/**
 * [DataTable 버튼 생성]
 */
function set_DataTable_Buttons() {
    buttonlist = [
        {
            extend: 'copy',
            text: 'Copy',
            className: 'btn btn-default',
        },
        {
            extend: 'excel', //파일명
            title: 'excel',
            className: 'btn btn-success',
        },
        {
            extend: 'print',
            className: 'btn btn-info',
            title: '품번출력',
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
                $header = $('<table border="1px solid black" style="width: 100%">' +
                    '<tr>' +
                    '<td>이름</td>' +
                    '<td>이메일</td>' +
                    '<td>전화번호</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<td>홍길동</td>' +
                    '<td>email</td>' +
                    '<td>010-1234-5678</td>' +
                    '</tr>' +
                    '</table>')
                $footer = $('<table border="1px solid black" style="width: 100%" >' +
                    '<tr>' +
                    '<td>이름</td>' +
                    '<td>이메일</td>' +
                    '<td>전화번호</td>' +
                    '</tr>' +
                    '</table>');
                $(win.document.body).find('table').prev().append($header);
                $(win.document.body).append($footer);
            }
        },
        {
            text: '삭제',
            className: 'btn btn-danger',
            float: 'right',
            action: function (e, dt, node, config) {
                deleteData();
            }
        },
        {
            text: '추가',
            float: 'right',
            className: 'btn btn-primary',
            action: function (e, dt, node, config) {
                addBtn();
            }
        },

    ];

}

