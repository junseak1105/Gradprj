$(document).ready(function () {
    set_dg_pagelist();
    set_dg_pagecategory();




    // $('#dg3').datagrid({
    //     url: './daja501x.asp?src=list',
    //     queryParams: {
    //         jin_date1      : $('#sch_pc_sdate').datebox('getValue'),
    //         jin_date2      : $('#sch_pc_edate').datebox('getValue'),
    //     }
    // });

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
        url: '/page/service/get_page_list',
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
            {field: 'page_url', title: '경로', width: 100},
            {field: 'page_table', title: '사용테이블', width: 100},
            {field: 'page_category', title: '분류', width: 100}
        ]],
        onClickRow: function (index, row) {
            $("#fr_pagelist_idx").val(row.idx);
            $("#fr_pagelist_page_name").val(row.page_name);
            $("#fr_pagelist_page_desc").val(row.page_desc);
            $("#fr_pagelist_page_url").val(row.page_url);
            $("#fr_pagelist_page_table").val(row.page_table);
            $("#fr_pagelist_page_category").val(row.page_category);
        }
    });
}//e: set_dg_pagelist()

/**
 * 2.fr_pagelist_clear() : 페이지 정보 폼 초기화
 */
function fr_pagelist_clear() {
    $("#fr_pagelist_idx").val("-1");
    $("#fr_pagelist_page_name").val("");
    $("#fr_pagelist_page_desc").val("");
    $("#fr_pagelist_page_url").val("");
    $("#fr_pagelist_page_table").val("");
    $("#fr_pagelist_page_category").val("");
}//e:fr_pagelist_clear()

/**
 * 3.fr_pagelist_save() : 페이지 정보 저장
 */
function fr_pagelist_save(){
    var idx = $("#fr_pagelist_idx").val();
    var page_name = $("#fr_pagelist_page_name").val();
    var page_desc = $("#fr_pagelist_page_desc").val();
    var page_url = $("#fr_pagelist_page_url").val();
    var page_table = $("#fr_pagelist_page_table").val();
    var page_category = $("#fr_pagelist_page_category").val();

    if(page_name == ""){
        alert("페이지명을 입력해주세요.");
        return false;
    }

    if(page_url == ""){
        alert("페이지 경로를 입력해주세요.");
        return false;
    }

    if(page_table == ""){
        alert("페이지 테이블을 입력해주세요.");
        return false;
    }

    if(page_category == ""){
        alert("페이지 분류를 입력해주세요.");
        return false;
    }

    $.ajax({
        url: '/page/service/create_page',
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

/**
 * 페이지 목록 데이터그리드 관리 끝
 * ======================================================
 */


/**
 * 카테고리 목록 데이터그리드 관리
 * 1.set_dg_pagecategory() : 카테고리 목록 데이터그리드 초기화
 * 2.deletePageCategory() : 카테고리 삭제
 */

/**
 * 1.set_dg_pagecategory() : 카테고리 목록 데이터그리드 초기화
 */
function set_dg_pagecategory(){
    $('#dg_pagecategory').datagrid({
        url: '/page/service/get_page_category',
        method: 'get',
        fitColumns: false,
        singleSelect: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'idx', checkbox: false},
            {field: 'page_category', title: '분류명', width: 150},
            {field: 'page_url', title: '경로', width: 150},
            {
                field: 'Operation', title: ' 삭제 ', width: 80,
                formatter: function (value, rec, index) {
                    var del = "<button class='btn btn-danger' onclick= deletePageCategory("+rec.idx+",'"+rec.page_category+"')>삭제</button>";
                    return del;
                }
            }
        ]]
    });
}//e:set_dg_pagecategory()

/**
 * 2.deletePageCategory() : 카테고리 삭제
 */
function deletePageCategory(idx, page_category) {
    //미구현
    if(confirm(page_category + "를 정말로 삭제하시겠습니까?")){
        $.ajax({
            url: '/page/service/delete_page_category',
            type: 'post',
            data: {
                idx: idx
            },
            success: function (data) {
                if (data == "success") {
                    alert("삭제되었습니다.");
                    $('#dg_pagecategory').datagrid('reload');
                } else {
                    alert("삭제에 실패하였습니다.");
                }
            }
        });
    }
}//e:deletePageCategory()

/**
 * 카테고리 목록 데이터그리드 관리 끝
 */