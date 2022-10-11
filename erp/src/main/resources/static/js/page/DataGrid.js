var page_data;
$().ready(function () {
    get_data();
});

/**
 * 페이지 첫 시작시 데이터 가져오고 그리드 생성, 그리드에 로드, 폼 생성
 */
function get_data() {
    var url = "/page/service/page?pagename=" + pagename;
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            page_data = data;
            // console.log(page_data.columns);
            // console.log(page_data.data);
            set_dg();
            $("#btn_page_delete").hide();
            $('#dg').datagrid('loadData', page_data.data);
        }
    });
}

/**
 * 그리드에 데이터 로드(새로고침)
 */
function reload_dg(){
    var url = "/page/service/page?pagename=" + pagename;
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            page_data = data;
            $('#dg').datagrid('loadData', page_data.data);
        }
    });
}

/**
 * 그리드 생성
 */
function set_dg() {

    var columns = new Array();

    for (var i = 0; i < page_data.columns.length; i++) {
        var column_name = page_data.columns[i]["column_name"];
        var column_comment = page_data.columns[i]["COLUMN_COMMENT"];
        var column_key = page_data.columns[i]["COLUMN_KEY"];
        var data_type = page_data.columns[i]["DATA_TYPE"];
        var data_length = page_data.columns[i]["CHARACTER_MAXIMUM_LENGTH"];

        var column = new Object();
        //데이터 그리드 헤더 제작용
        if ("idx" == column_name) {
            column.field = "idx";
            column.checkbox = true;
        } else {
            column.field = column_name;
            column.title = column_comment;
            column.width = 100;

            if ("sort_code" == column_name || "sort_lv1" == column_name || "sort_lv2" == column_name) {
                $div = $("<div></div>").attr("class", "input-group input-group-lg");
                $combogrid = $("<div></div>").attr("id", column_name).attr("class", "form-control");
                $div.append($("<span></span>").attr("class", "input-group-text").text(column_comment));
                $div.append($combogrid);
                $("#fr_inputlist").append($div);
                switch (column_name) {
                    case "sort_lv1":
                        var sort_col = [
                            {"field": "sort_lv1", "title": "대분류코드", "width": 100},
                            {"field": "sort_lv1_desc", "title": "대분류명", "width": 100}
                        ];
                        var url = '/admin/service/simplex/get_sort_lv1';
                        $("#sort_lv1").combogrid({
                            delay: 500,
                            mode: 'remote',
                            width: 100,
                            url: url,
                            idField: 'sort_lv1',
                            textField: 'sort_lv1_desc',
                            columns: [sort_col],
                            onSelect: function (index, row) {
                                $("#fr_data_sort_lv1").val(row.sort_lv1);
                                $('#sort_lv2').combogrid({
                                    url: '/admin/service/simplex/get_sort_lv2?sort_lv1='+row.sort_lv1
                                });
                                $('#sort_code').combogrid({
                                    loadData: []
                                })
                            }
                        });
                        break;

                    case "sort_lv2":
                        var sort_col = [
                            {"field": "sort_lv1", "title": "대분류코드", "width": 100,hidden:true},
                            {"field": "sort_lv2", "title": "중분류코드", "width": 100},
                            {"field": "sort_lv2_desc", "title": "중분류명", "width": 100}
                        ];
                        $("#sort_lv2").combogrid({
                            delay: 500,
                            mode: 'remote',
                            width: 100,
                            idField: 'sort_lv2',
                            textField: 'sort_lv2_desc',
                            columns: [sort_col],
                            onSelect: function (index, row) {
                                $("#fr_data_sort_lv2").val(row.sort_lv2);
                                $('#sort_code').combogrid({
                                    url: '/admin/service/simplex/get_sort_code?sort_lv1='+row.sort_lv1+'&sort_lv2='+row.sort_lv2
                                });
                            }
                        });
                        break;

                    case "sort_code":
                        var sort_col = [
                            {"field": "sort_code", "title": "소분류코드", "width": 100},
                            {"field": "sort_code_desc", "title": "분류명", "width": 100}
                        ];
                        $("#sort_code").combogrid({
                            delay: 500,
                            mode: 'remote',
                            width: 100,
                            idField: 'sort_code',
                            textField: 'sort_code_desc',
                            columns: [sort_col],
                            onSelect: function (index, row) {
                                $("#fr_data_sort_code").val(row.sort_code);

                            }
                        });
                        break;
                }
            } else {
                $div = $("<div></div>").attr("class", "input-group input-group-lg");
                $div.append($("<span></span>").attr("class", "input-group-text").text(column_comment));
                $div.append($("<input></input>").attr("type", "text").attr("class", "form-control").attr("id", column_name).attr("name", column_name).attr("class", "form-control"));
                $("#fr_inputlist").append($div);
            }
        }
        columns.push(column);
    }

    $('#dg').datagrid({
        // url: '/page/service/datagrid?pagename=ㅅㄷㄴㅅ',
        method: 'get',
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        striped: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [columns],
        onClickRow: function (index, row) {
            // console.log(row);
            $("#fr_data_status").val("update");
            $("#btn_page_delete").show();
            for (var i = 0; i < page_data.columns.length; i++) {
                var column_name = page_data.columns[i]["column_name"];
                var column_comment = page_data.columns[i]["COLUMN_COMMENT"];
                var column_key = page_data.columns[i]["COLUMN_KEY"];
                var data_type = page_data.columns[i]["DATA_TYPE"];
                var data_length = page_data.columns[i]["CHARACTER_MAXIMUM_LENGTH"];

                if ("idx" == column_name) {
                    $("#fr_data_idx").val(row.idx);
                } else {
                    if ("sort_code" == column_name || "sort_lv1" == column_name || "sort_lv2" == column_name) {
                        switch (column_name) {
                            case "sort_lv1":
                                $("#sort_lv1").combogrid('setValue', row.sort_lv1);
                                break;
                            case "sort_lv2":
                                $("#sort_lv2").combogrid('setValue', row.sort_lv2);
                                break;
                            case "sort_code":
                                $("#sort_code").combogrid('setValue', row.sort_code);
                                break;
                        }
                    } else {
                        $("#" + column_name).val(row[column_name]);
                    }
                }
            }
        }
    });
}//e: set_dg_pagelist()

/**
 * 폼 데이터 초기화
 */
function fr_data_clear() {
    $(".form-control").val("");
    $("#fr_data_status").val("new");
    $("#btn_page_delete").hide();
    $("#sort_lv1").combogrid('setValue', "");
    $("#sort_lv2").combogrid('setValue', "");
    $("#sort_code").combogrid('setValue', "");
    $('#sort_lv2').combogrid({
        loadData: []
    });
    $('#sort_code').combogrid({
        loadData: []
    });
}//e: fr_data_clear()

/**
 * 폼 데이터 저장
 */
function fr_data_save() {
    //serialize form to json and log
    var json = new Object();
    json.formdata = $("#fr_data").serializeArray();
    json.columns = page_data.columns;
    json.pagename= pagename;

    // console.log(JSON.stringify(json));
    $.ajax({
        url: "/page/service/edit/save",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(json),
        success: function (data) {
            console.log(data);
            if (data.result == "success") {
                alert("저장되었습니다.");
                fr_data_clear();
                reload_dg();
            } else {
                alert("저장에 실패하였습니다.");
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "");
        }
    });
}//e: fr_data_save()

/**
 * 폼 데이터 삭제(미구현)
 */
function fr_data_delete() {
    //serialize form to json and log
    var json = new Object();
    json.formdata = $("#fr_data").serializeArray();
    json.columns = page_data.columns;
    json.pagename= pagename;

    // console.log(JSON.stringify(json));
    $.ajax({
        url: "/page/service/edit/delete",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(json),
        success: function (data) {
            console.log(data);
            if (data.result == "success") {
                alert("삭제되었습니다.");
                reload_dg();
            } else {
                alert("삭제에 실패하였습니다.");
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "");
        }
    });
}//e: fr_data_save()
