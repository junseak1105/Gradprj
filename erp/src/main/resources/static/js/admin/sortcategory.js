$(document).ready(function () {
    set_dg_sort_lv1();
    set_dg_sort_lv2();
    set_dg_sort_code();
});

/**
 * 대분류 목록 데이터그리드 관리
 * 1.set_dg_sort_lv1()
 * 2.delete_sort_lv1()
 * 3.add_sort_lv1()
 * 4.update_sort_lv1()
 */

/**
 * 1.set_dg_sortcategory() : 카테고리 목록 데이터그리드 초기화
 */
function set_dg_sort_lv1(){
    $('#dg_sort_lv1').datagrid({
        url: '/admin/service/simplex/get_sort_lv1',
        method: 'get',
        fitColumns: false,
        singleSelect: true,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'sort_lv1', title: '대분류코드',width: 150},
            {field: 'sort_lv1_desc', title: '대분류명', width: 150},
            {
                field: 'Operation', title: ' 삭제 ', width: 80,
                formatter: function (value, rec, index) {
                    var del = "<button class='btn btn-danger' onclick= delete_sort_lv1('"+rec.sort_lv1+"')>삭제</button>";
                    return del;
                }
            }
        ]],
        onClickRow: function (index, row) {
            get_dg_sort_lv2(row.sort_lv1);
        }
    });
}//e:set_dg_pagecategory()


/**
 * 대분류 목록 데이터그리드 관리 끝
 * =========================================================
 */

/**
 * 중분류 목록 데이터그리드 관리
 * 1.set_dg_sort_lv1()
 * 2.delete_sort_lv1()
 * 3.add_sort_lv1()
 * 4.update_sort_lv1()
 */

/**
 * 1.set_dg_sort_lv2() : 카테고리 목록 데이터그리드 생성
 */
function set_dg_sort_lv2(){
    $('#dg_sort_lv2').datagrid({
        fitColumns: false,
        singleSelect: true,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'sort_lv1', title: '대분류코드',hidden: true},
            {field: 'sort_lv2', title: '중분류코드', width: 150},
            {field: 'sort_lv2_desc', title: '중분류코드명', width: 150},
            {
                field: 'Operation', title: ' 삭제 ', width: 80,
                formatter: function (value, rec, index) {
                    var del = "<button class='btn btn-danger' onclick= delete_sort_lv2('"+rec.sort_lv1+"','"+rec.sort_lv2+"')>삭제</button>";
                    return del;
                }
            }
        ]],
        onClickRow: function (index, row) {
            get_dg_sort_code(row.sort_lv1,row.sort_lv2);
        }
    });
}//e:set_dg_pagecategory()

/**
 * 2.get_dg_sort_lv2()
 */
function get_dg_sort_lv2(sort_lv1){
    $('#dg_sort_lv2').datagrid({
        url: '/admin/service/simplex/get_sort_lv2?sort_lv1='+sort_lv1
    });
}//e:get_dg_sortcode()

/**
 * 중분류 데이터그리드 관리 끝
 * =========================================================
 */


/**
 * 분류코드 목록 데이터그리드 관리
 * 1.set_dg_sort_lv1()
 * 2.delete_sort_lv1()
 * 3.add_sort_lv1()
 * 4.update_sort_lv1()
 */
/**
 * 1.set_dg_sort_code() : 카테고리 목록 데이터그리드 생성
 */
function set_dg_sort_code(){
    $('#dg_sort_code').datagrid({
        fitColumns: false,
        singleSelect: true,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'sort_code', title: '소분류코드',width: 150},
            {field: 'sort_code_desc', title: '소분류코드명', width: 150},
            {field: 'sort_lv1', title: '대분류코드', hidden: true},
            {field: 'sort_lv2', title: '중분류코드', hidden: true},
            {
                field: 'Operation', title: ' 삭제 ', width: 80,
                formatter: function (value, rec, index) {
                    var del = "<button class='btn btn-danger' onclick= delete_sort_code('"+rec.sort_lv1+"','"+rec.sort_lv2+"','"+rec.sort_code+"')>삭제</button>";
                    return del;
                }
            }
        ]]
    });
}//e:set_dg_pagecategory()

/**
 * 2.get_dg_sort_lv2()
 */
function get_dg_sort_code(sort_lv1,sort_lv2){
    $('#dg_sort_code').datagrid({
        url: '/admin/service/simplex/get_sort_code?sort_lv1='+sort_lv1+'&sort_lv2='+sort_lv2
    });
}//e:get_dg_sortcode()
/**
 * 분류코드 데이터그리드 관리 끝
 * =========================================================
 */

/**
 * delete_sort : 분류 삭제
 */
function delete_sort_lv1(sort_lv1){
    delete_sort("/admin/service/simplex/delete_sort_lv1?sort_lv1="+sort_lv1);
}
function delete_sort_lv2(sort_lv1,sort_lv2){
    delete_sort("/admin/service/simplex/delete_sort_lv2?sort_lv1="+sort_lv1+"&sort_lv2="+sort_lv2);
}
function delete_sort_code(sort_lv1,sort_lv2,sort_code){
    delete_sort("/admin/service/simplex/delete_sort_code?sort_lv1="+sort_lv1+"&sort_lv2="+sort_lv2+"&sort_code="+sort_code);
}
function delete_sort(url){
    if(confirm("정말로 삭제하시겠습니까? 하위 분류코드 또한 전체 삭제됩니다.")){
        $.ajax({
            url: url,
            type: 'get',
            success: function (data) {
                var json = JSON.parse(data);
                if (json.result) {
                    alert("삭제되었습니다.");
                    $('#dg_sort_lv1').datagrid('reload');
                    $('#dg_sort_lv2').datagrid('reload');
                    $('#dg_sort_code').datagrid('reload');
                } else {
                    alert("삭제에 실패하였습니다.");
                }
            }
        });
    }
}//e:deletePageCategory()