var lineCount; //컬럼 갯수
var Table_dup_chk = false; //테이블 중복확인

$(document).ready(function () {//시작시 세팅
    lineCount = 0;
    $("#fr_table_create_colnumcnt").val(lineCount);
    set_dg_tablelist();
});

/**
 * 테이블 관리 기능
 * 1.set_dg_tablelist() : 테이블 목록 데이터그리드 생성
 * 2.fr_table_create_clear(): 테이블 생성 폼 초기화
 * 3.get_sort_category() : 테이블 카테고리 목록 가져오기
 * 4.check_form() : 테이블 생성 폼 체크
 * 5.Submit() : 테이블 생성 폼 전송
 * 6.Table_dup_chk_func() : 테이블 중복확인
 * 7.ClearCol(): 컬럼 전체 삭제
 * 8.Delete_row(): 컬럼 삭제
 * 9.AddCol(): 컬럼 추가
 */

/**
 * 1.set_dg_tablelist() : 테이블 목록 데이터그리드 초기화
 */
function set_dg_tablelist(){
    $('#dg_tablelist').datagrid({
        url: '../api/table/getTableList',
        method: 'get',
        fitColumns: false,
        singleSelect: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        columns: [[
            {field: 'table_name', title: '테이블 명', width: 150},
            {field: 'table_comment', title: '테이블 설명', width: 150}
        ]],
        onClickRow: function (rowIndex, rowData) {
            $("#fr_table_create_name").val(rowData.table_name);
            $("#fr_table_create_tablecomment").val(rowData.table_comment);
        }
    });
}//e:set_dg_pagecategory()

/**
 * 2.fr_table_create_clear() : 테이블 생성 폼 초기화
 */
function fr_table_create_clear() {
    $("#fr_table_create_table_name").val("");
    $("#fr_table_create_table_desc").val("");
    $("#fr_table_create_table_category").val("");
    ClearCol();
}//e:fr_table_create_clear()



/**
 * 4.check_form() : 테이블 생성 폼 체크
 */
function check_form() {
    if ($("#tablename").val() == "") {
        alert("테이블명을 입력하세요");
        return false;
    }
    if ($("#fr_table_create_tablecomment").val() == "") {
        alert("테이블 설명을 입력하세요");
        return false;
    }
    if ($("#fr_table_create_colnumcnt").val() == 0) {
        alert("컬럼을 추가하세요");
        return false;
    } else {
        for (var i = 1; i <= lineCount; i++) {
            if ($("#field_" + i).val() == "") {
                alert("컬럼명을 입력하세요");
                return false;
            }
            if (($("#type_" + i).val() == "char" || $("#type_" + i).val() == "varchar") && $("#length_" + i).val() == "") {
                alert(i + "열의 데이터 길이를 입력하세요");
                return false;
            }
        }
        for (var i = 1; i <= lineCount; i++) {
            for (var j = i + 1; j <= lineCount; j++) {
                if ($("#field_" + i).val() == $("#field_" + j).val()) {
                    alert("중복된 컬럼명이 있습니다.");
                    return false;
                }
            }
        }

    }
    return true;
}


/**
 * 5.Submit() : 테이블 생성 폼 전송
 */
function Submit() {
    // alert("test");
    if (check_form()) {
        if (Table_dup_chk) {
            // alert(form_to_json($("#fr_table_create").serializeArray()));
            $.ajax({
                type: "POST",            // HTTP method type(GET, POST) 형식이다.
                url: "../api/table/createTable",       // 컨트롤러에서 대기중인 URL 주소이다.
                data: form_to_json($("#fr_table_create").serializeArray()),            // Json 형식의 데이터이다.
                dataType: 'json',          //text,html,xml,csv 등
                contentType: 'application/json',
                success: function (data) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                    jsonList = JSON.parse(data);  //json형식의 데이터를 파싱한다.
                    console.log(jsonList.title);
                },
                error: function (request, status, error) { // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
                    console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        } else {
            alert("테이블 중복확인을 해주세요");
        }
    }
}

/**
 * 6.Table_dup_chk_func() : 테이블명 중복확인
 */
function Table_dup_chk_func() {
    // json 형식으로 데이터 set

    if($("#fr_table_create_name").val() == ""){
        alert("테이블명을 입력하세요");
    }else {
        $.ajax({
            type: "POST",            // HTTP method type(GET, POST) 형식이다.
            url: "../api/table/nameDupchk",       // 컨트롤러에서 대기중인 URL 주소이다.
            data: form_to_json($("#fr_table_create").serializeArray()),            // Json 형식의 데이터이다.
            dataType: 'json',          //text,html,xml,csv 등
            contentType: 'application/json',
            success: function (data) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                if (data.result) {
                    Table_dup_chk = true;
                    alert("사용가능한 테이블명입니다.");
                } else {
                    Table_dup_chk = false;
                    alert("중복된 테이블명입니다.");
                }
            },
            error: function (request, status, error) { // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }
}

/**
 * 7.ClearCol(): 컬럼 전체 삭제
 */
function ClearCol() {
    $("#fr_table_create_New_table").empty();
    lineCount = 0;
    $("#fr_table_create_colnumcnt").val(lineCount);
}

/**
 * 8.Delete_row(): 컬럼 삭제
 *
 */
function Delete_row(colnum) {
    //컬럼 삭제
    $("#colno_" + colnum).remove();

    //컬럼 번호 재정렬 코드
    for (colnum += 1; colnum <= lineCount; colnum++) {
        // alert("colnum:"+colnum+"linecount:"+lineCount);

        //컬럼번호 앞으로 당기기
        $("#colno_" + colnum).attr("id", "colno_" + (colnum - 1));

        //컬럼 번호 id 수정
        $("#col_view_no_" + colnum).attr("id", "col_view_no_" + (colnum - 1));

        //컬럼 번호 수정
        $("#col_view_no_" + (colnum - 1)).html(colnum - 1);

        //컬럼삭제버튼 id 수정
        $("#col_remove_" + colnum).attr("id", "col_remove_" + (colnum - 1));

        //삭제버튼 클릭시 삭제할 컬럼번호 수정
        $("#col_remove_" + (colnum - 1)).attr("onclick", "Delete_row(" + (colnum - 1) + ")");

        //삭제버튼 순서 오류 검증용 코드
        // $("#col_remove_" + (colnum - 1)).attr("value", "삭제"+(colnum - 1));
    }


    lineCount--;
    $("#fr_table_create_colnumcnt").val(lineCount);
}

/**
 * 9.AddCol(): 컬럼추가
 * 컬럼번호, 컬럼명, 데이터타입, 데이터길이, 널여부, 컬럼기본값, 컬럼 키선택,컬럼설명, 컬럼삭제버튼 생성
 */
function AddCol() { //컬럼 추가 기능

    lineCount++; //컬럼 갯수 증가

    var $table = $("<table></table>").attr("id", "colno_" + lineCount);
    //컬럼 생성
    var $tr1 = $("<tr></tr>");
    var $tr2 = $("<tr></tr>");
    var $tr3 = $("<tr></tr>")

    //컬럼 번호 표기(데이터랑 무관계 only view)
    var $td_colcnt = $("<td rowspan='2'></td>").attr("id", "col_view_no_" + lineCount).text(lineCount);

    $tr1.append($td_colcnt);

    //컬럼명(ex:colname_1)
    var $td_colname = $("<td></td>");
    var $td_colname_input = $("<input></input>").attr("type", "text").attr("placeholder", "컬럼명").attr("name", "field_" + lineCount).attr("id", "field_" + lineCount).attr("class", "form-control");
    $td_colname.append($td_colname_input);
    $tr1.append($td_colname);

    //데이터타입
    var $td_type = $("<td></td>");
    var $td_type_select = $("<select></select>").attr("name", "type_" + lineCount).attr("id", "type_" + lineCount).attr("class", "form-select");


    //데이터타입 option
    var type_map = new Map();
    type_map.set("int", "정수");
    type_map.set("varchar", "제한있는 문자열");
    type_map.set("datetime", "날짜시간");
    type_map.set("text", "제한없는텍스트");
    type_map.set("double", "실수");
    type_map.set("char", "문자1개");

    //데이터타입 option 생성
    for (var [key, value] of type_map) {
        var $td_type_option = $("<option></option>").attr("value", key).text(value);
        $td_type_select.append($td_type_option);
    }
    $td_type_select.change(function () {
        var $this = $(this);
        if ($this.val() == "varchar" || $this.val() == "char") {
            $(this).parent().next().children().prop("disabled", false);
        } else {
            $(this).parent().next().children().prop("disabled", true);
        }
    });

    $td_type.append($td_type_select);
    $tr1.append($td_type);

    //데이터길이
    var $td_length = $("<td></td>");
    var $td_length_input = $("<input></input>").attr("disabled", true).attr("type", "number").attr("placeholder", "데이터길이").attr("name", "length_" + lineCount).attr("class", "form-control");

    //onchange, if value is not number, then set value to 0
    $td_length_input.change(function () {
        var $this = $(this);
        if (isNaN($this.val()) || $this.val() < 0) {
            $this.val(0);
        }
        if ($td_type_select.val() == "varchar") {
            $td_length_input.attr("max", 32672);
            if ($this.val() > 32672) {
                $this.val(32672);
            }
        } else {
            $td_length_input.attr("max", 255);
            if ($this.val() > 255) {
                $this.val(255);
            }
        }

    });

    $td_length.append($td_length_input);
    $tr1.append($td_length);

    //nn(널 여부 선택)
    var $td_nn = $("<td></td>");
    var $td_nn_select = $("<select></select>").attr("name", "null_" + lineCount).attr("class", "form-select");
    var $td_nn_select_option1 = $("<option>빈값 허용</option>").attr("value", "null");
    var $td_nn_select_option2 = $("<option>빈값 미허용</option>").attr("value", "not null");
    $td_nn_select.append($td_nn_select_option1);
    $td_nn_select.append($td_nn_select_option2);
    $td_nn.append($td_nn_select);
    $tr2.append($td_nn);

    //컬럼 기본값
    var $td_default = $("<td></td>");
    var $td_default_input = $("<input></input>").attr("type", "text").attr("placeholder", "컬럼 기본값").attr("name", "default_" + lineCount).attr("value", "").attr("class", "form-control");
    $td_default.append($td_default_input);
    $tr2.append($td_default);

    //컬럼 키선택
    var $td_key = $("<td></td>");
    //create switch for 분류코드추가, 유일값 with bootstrap
    var $td_unique_key_switch = $("<div></div>").attr("class", "form-check");
    $td_unique_key_switch.append("<input type='text' name='extra_" + lineCount + "' value='' hidden>");
    $td_unique_key_switch.append("<input class='form-check-input' type='checkbox' id='extra_" + lineCount + "''> ");
    $td_unique_key_switch.append("<label class='form-check-label' for='unique'>유일값</label>");

    $td_key.append($td_unique_key_switch);
    $tr2.append($td_key);

    //삭제 버튼 추가
    var $td_remove = $("<td></td>");
    var $td_remove_input = $("<button>삭제</button>").attr("class", "btn btn-default").attr("id", "col_remove_" + lineCount).attr("type", "button").attr("onclick", "Delete_row(" + lineCount + ")");
    $td_remove.append($td_remove_input);
    $tr3.append($td_remove);

    //컬럼 설명
    var $td_comment = $("<td colspan='2'></td>");
    var $td_comment_input = $("<input></input>").attr("type", "text").attr("placeholder", "컬럼 설명").attr("name", "comment_" + lineCount).attr("class", "form-control");
    $td_comment.append($td_comment_input);
    $tr3.append($td_comment);


    $("#fr_table_create_colnumcnt").val(lineCount); //컬럼 갯수 세팅
    $table.append($tr1);
    $table.append($tr2);
    $table.append($tr3);
    $table.append("<tr><td colspan='4' style='border-bottom: 10px solid #ccc;'></td></tr>");
    $("#fr_table_create_New_table").append($table); //컬럼 추가

}