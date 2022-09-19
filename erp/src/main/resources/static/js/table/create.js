var lineCount = 0; //현재 입력된 컬럼 갯수

var Table_dup_chk = false; //테이블 중복확인

$(document).ready(function () {//시작시 세팅
    $("#colnumcnt").val(lineCount);
});

function check_form(){
    if($("#tablename").val() == ""){
        alert("테이블명을 입력하세요");
        return false;
    }
    if($("#tablecomment").val() == ""){
        alert("테이블 설명을 입력하세요");
        return false;
    }
    if($("#colnumcnt").val() == 0){
        alert("컬럼을 추가하세요");
        return false;
    }else{
        for(var i=1; i<=lineCount; i++){
            if($("#field_"+i).val() == ""){
                alert("컬럼명을 입력하세요");
                return false;
            }
            if(($("#type_"+i).val() == "char" || $("#type_"+i).val() == "varchar")&&$("#length_"+i).val() == ""){
                alert(i+"열의 데이터 길이를 입력하세요");
                return false;
            }
        }
        for(var i=1; i<=lineCount; i++){
            for(var j=i+1; j<=lineCount; j++){
                if($("#field_"+i).val() == $("#field_"+j).val()){
                    alert("중복된 컬럼명이 있습니다.");
                    return false;
                }
            }
        }

    }
    return true;
}

//제출
function Submit() {
    if(check_form()) {
        if (Table_dup_chk) {
            $.ajax({
                type: "POST",            // HTTP method type(GET, POST) 형식이다.
                url: "/table/service/create",       // 컨트롤러에서 대기중인 URL 주소이다.
                data: form_to_json($("#table_data_frm").serializeArray()),            // Json 형식의 데이터이다.
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


function Table_dup_chk_func() {
    // json 형식으로 데이터 set


    $.ajax({
        type: "POST",            // HTTP method type(GET, POST) 형식이다.
        url: "/table/service/name_dupchk",       // 컨트롤러에서 대기중인 URL 주소이다.
        data: form_to_json($("#table_data_frm").serializeArray()),            // Json 형식의 데이터이다.
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

function ClearCol() {
    $("#New_table").empty();
    lineCount = 0;
    $("#colnumcnt").val(lineCount);
}

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
    $("#colnumcnt").val(lineCount);
}

/**
 * 컬럼추가 function
 * 컬럼번호, 컬럼명, 데이터타입, 데이터길이, 널여부, 컬럼기본값, 컬럼 키선택,컬럼설명, 컬럼삭제버튼
 */
function AddCol() { //컬럼 추가 기능

    lineCount++; //컬럼 갯수 증가

    //컬럼 생성
    var $tr = $("<tr></tr>").attr("id", "colno_" + lineCount);

    //컬럼 번호 표기(데이터랑 무관계 only view)
    var $td_colcnt = $("<td></td>").attr("id", "col_view_no_" + lineCount).text(lineCount);
    $tr.append($td_colcnt);

    //컬럼명(ex:colname_1)
    var $td_colname = $("<td></td>");
    var $td_colname_input = $("<input></input>").attr("type", "text").attr("placeholder", "컬럼명").attr("name", "field_" + lineCount).attr("id", "field_" + lineCount).attr("class", "field");
    $td_colname.append($td_colname_input);
    $tr.append($td_colname);

    //데이터타입
    var $td_type = $("<td></td>");
    var $td_type_select = $("<select></select>").attr("name", "type_" + lineCount).attr("id", "type_" + lineCount);


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
    /*
    on select change, if select value is not varchar or char, then disable length input
    set limit for varchar equals 32672
    set limit for char equals 255
     */

    $td_type_select.change(function () {
        var $this = $(this);
        if ($this.val() == "varchar" || $this.val() == "char") {
            $(this).parent().next().children().prop("disabled", false);
        } else {
            $(this).parent().next().children().prop("disabled", true);
        }
    });

    $td_type.append($td_type_select);
    $tr.append($td_type);

    //데이터길이
    var $td_length = $("<td></td>");
    var $td_length_input = $("<input></input>").attr("disabled", true).attr("type", "number").attr("placeholder", "데이터길이").attr("name", "length_" + lineCount);

    //onchange, if value is not number, then set value to 0
    $td_length_input.change(function () {
        var $this = $(this);
        if (isNaN($this.val()) || $this.val() < 0) {
            $this.val(0);
        }
        if ($td_type_select.val() == "varchar") {
            $td_length_input.attr("max", 32672);
            if($this.val() > 32672){
                $this.val(32672);
            }
        } else {
            $td_length_input.attr("max", 255);
            if($this.val() > 255){
                $this.val(255);
            }
        }

    });

    $td_length.append($td_length_input);
    $tr.append($td_length);

    //nn(널 여부 선택)
    var $td_nn = $("<td></td>");
    var $td_nn_select = $("<select></select>").attr("name", "null_" + lineCount);
    var $td_nn_select_option1 = $("<option>빈값 허용</option>").attr("value", "null");
    var $td_nn_select_option2 = $("<option>빈값 미허용</option>").attr("value", "not null");
    $td_nn_select.append($td_nn_select_option1);
    $td_nn_select.append($td_nn_select_option2);
    $td_nn.append($td_nn_select);
    $tr.append($td_nn);

    //컬럼 기본값
    var $td_default = $("<td></td>");
    var $td_default_input = $("<input></input>").attr("type", "text").attr("placeholder", "컬럼 기본값").attr("name", "default_" + lineCount).attr("value", "");
    $td_default.append($td_default_input);
    $tr.append($td_default);

    //컬럼 키선택
    var $td_key = $("<td></td>");
    //create map for key
    var key_map = new Map();
    key_map.set("primary", "기본키");
    key_map.set("foreign", "외래키");
    key_map.set("unique", "고유키");
    key_map.set("index", "인덱스");
    key_map.set("none", "없음");

    var $td_key_div = $("<div></div>").attr("class", "btn-group").attr("data-toggle", "buttons").attr("name","key");
    for (var [key, value] of key_map) {
        var $td_key_label = $("<label></label>").attr("class", "btn btn-default");
        var $td_key_input = $("<input></input>").attr("type", "checkbox").attr("name", "key_" + lineCount).attr("value", key).attr("autocomplete", "off");
        $td_key_label.append($td_key_input);
        $td_key_label.append(value);
        $td_key_div.append($td_key_label);
    }
    $td_key_div.children().eq(4).prop("checked", true);
    $td_key_div.children().eq(4).addClass("active");

    $td_key_div.change(function () {
        var $this = $(this);
        /*
        없음 제외한 나머지 클릭 시 없음 체크 해제 및 active 클래스 제거 필요
         */

        //없음 클릭 시 기능
        if($this.children().eq(4).children().eq(0).prop("checked")){
            $this.children().not($this.children().eq(4)).children().prop("checked", false);
            $this.children().not($this.children().eq(4)).removeClass("active");
            console.log("none checked");
        }

    });
    $td_key.append($td_key_div);
    $tr.append($td_key);

    //컬럼 설명
    var $td_comment = $("<td></td>");
    var $td_comment_input = $("<input></input>").attr("type", "text").attr("placeholder", "컬럼 설명").attr("name", "comment_" + lineCount);
    $td_comment.append($td_comment_input);
    $tr.append($td_comment);

    //삭제 버튼 추가
    var $td_remove = $("<td></td>");
    var $td_remove_input = $("<button>삭제</button>").attr("class", "btn btn-default").attr("id", "col_remove_" + lineCount).attr("type", "button").attr("onclick", "Delete_row(" + lineCount + ")");
    $td_remove.append($td_remove_input);
    $tr.append($td_remove);

    $("#colnumcnt").val(lineCount); //컬럼 갯수 세팅
    $("#New_table").append($tr); //컬럼 추가
}