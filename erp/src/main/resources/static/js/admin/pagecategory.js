$(document).ready(function () {
    set_dg_pagecategory();
});

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
        url: '/admin/service/simplex/get_page_category',
        method: 'get',
        fitColumns: false,
        singleSelect: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'idx', checkbox: false},
            {field: 'page_category', title: '분류명', width: 150},
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
            url: '/page/service/duplex/delete_page_category',
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
 * =========================================================
 */
