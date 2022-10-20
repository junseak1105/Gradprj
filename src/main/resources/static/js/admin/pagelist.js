$(document).ready(function () {
    set_dg_pagelist();
    get_tablelist();
    get_category();
});

/**
 * 페이지 목록 데이터그리드 관리
 * 1.set_dg_pagelist() : 페이지 목록 데이터그리드 초기화
 * 2.fr_pagelist_clear() : 페이지 정보 폼 초기화
 * 3.fr_pagelist_save() : 페이지 정보 저장
 */

/**
 * 1.set_dg_pagelist() : 페이지 목록 데이터그리드 초기화
 */
function set_dg_pagelist(){
    $('#dg_pagelist').datagrid({
        url: '../api/page/getPageList',
        method: 'get',
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        striped: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'idx', checkbox: true},
            {field: 'page_name', title: '페이지명', width: 100},
            {field: 'page_desc', title: '설명', width: 100},
            {field: 'page_table', title: '사용테이블', width: 100},
            {field: 'page_category', title: '분류', width: 100}
        ]],
        onClickRow: function (index, row) {
            $("#fr_pagelist_status").val("update");
            $("#fr_pagelist_idx").val(row.idx);
            $("#fr_pagelist_page_name").val(row.page_name);
            $("#fr_pagelist_page_desc").val(row.page_desc);
            $("#fr_pagelist_page_table").children().val(row.page_table);
            $("#fr_pagelist_page_category").children().attr("value",row.page_category);
            $("#btn_page_delete").show();
        }
    });
}//e: set_dg_pagelist()

/**
 * 2.fr_pagelist_clear() : 페이지 정보 폼 초기화
 */
function fr_pagelist_clear() {
    $("#fr_pagelist_status").val("new");
    $("#fr_pagelist_idx").val("-1");
    $("#fr_pagelist_page_name").val("");
    $("#fr_pagelist_page_desc").val("");
    $("#fr_pagelist_page_table").children().val("");
    $("#fr_pagelist_page_category").children().val("");
    $("#btn_page_delete").hide();
}//e:fr_pagelist_clear()

/**
 * 3.fr_pagelist_save() : 페이지 정보 저장
 */
function fr_pagelist_save(){
    var status = $("#fr_pagelist_status").val(); //new, update
    var url = "";
    if(status == "new"){
        url = "../api/page/addPage";
    }else{
        url = "../api/page/addPage";
    }
    $.ajax({
        url: url,
        type: 'post',
        data: form_to_json($("#fr_pagelist").serializeArray()),
        dataType:'json',
        contentType: 'application/json',
        success: function (data) {
            if(data.result == "success"){
                alert("저장되었습니다.");
                $('#dg_pagelist').datagrid('reload');
                fr_pagelist_clear();
            }else{
                alert("저장에 실패하였습니다.");
            }
        }
    });
}//e:fr_pagelist_save()

function fr_pagelist_delete(){
    var idx = $("#fr_pagelist_idx").val();
    if(idx == -1){
        alert("삭제할 페이지를 선택해주세요.");
        return;
    }
    $.ajax({
        url: "../api/page/service/deletePage",
        data: form_to_json($("#fr_pagelist").serializeArray()),
        type: 'post',
        dataType:'json',
        contentType: 'application/json',
        success: function (data) {
            if(data.result == "success"){
                alert("삭제되었습니다.");
                $('#dg_pagelist').datagrid('reload');
                fr_pagelist_clear();
            }else{
                alert("삭제에 실패하였습니다.");
            }
        }
    });
}
/**
 * 페이지 목록 데이터그리드 관리 끝
 * ======================================================
 */

/**
 * 수정창 호출
 * 1.get_category() : 카테고리 목록 조회
 * 2.get_tablelist() : 테이블 목록 조회
 */

/**
 * 1.get_category() : 페이지 분류 가져오기
 */
function get_category(){
    $.ajax({
        url: "../api/page/get/PageCategory",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var $key_select = $("<select></select>").attr("name", "page_category").attr("class", "form-select");
            $key_select.append($("<option>없음</option>").attr("value", ""));
            for (var i = 0; i < data.length; i++) {
                var $key_select_option = $("<option></option>").attr("value", data[i].page_category).text(data[i].page_category);
                $key_select_option.append("<input type='hidden' value='"+data[i].page_url+"'>");
                $key_select.append($key_select_option);
            }
            $("#fr_pagelist_page_category").append($key_select);
        }
    });
    // $("#fr_pagelist_page_category").change(function () {
    //     $("#fr_pagelist_page_url").val("/");
    // });

}

/**
 * 2.get_tablelist() : 페이지 분류 가져오기
 */
function get_tablelist(){
    $.ajax({
        url: "../api/table/get/getTableList",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var $key_select = $("<select></select>").attr("name", "page_table").attr("class", "form-select");
            $key_select.append($("<option>선택해주세요</option>").attr("value", "none"));
            for (var i = 0; i < data.length; i++) {
                var $key_select_option = $("<option></option>").attr("value", data[i].table_name).text(data[i].table_name+"("+data[i].table_comment+")");
                $key_select.append($key_select_option);
            }
            $("#fr_pagelist_page_table").append($key_select);
        }
    });

}