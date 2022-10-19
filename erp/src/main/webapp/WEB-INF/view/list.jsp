<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="includes/injection.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">${pagename}</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${tablename}
                <%--				<button id='regBtn' type="button" class="btn btn-xs pull-right">Register--%>
                <%--					New Board</button>--%>
            </div>
            <button id="deleteBtn" type="button" class="btn btn-xs pull-right">Delete
                Selected
            </button>
            <button id="addBtn" type="button" class="btn btn-xs pull-right">Add data
            </button>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table id="dg" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <%--
                            [테이블 헤더 제작]
                            헤더명: column에 지정된 comment로 생성됨.
                            <주의사항>
                            각 테이블 생성 시 column에 comment를 반드시 지정해야 함.
                            comment 없을 시 테이블 헤더가 빈값으로 지정됨.
                        --%>
                        <c:forEach items="${tableinfo.data}" var="info">
                            <th><c:out value="${info.column_Comment}"/></th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <%--
                        [테이블 전체 데이터 출력]
                        name : 컬럼명으로 지정
                        value : 컬럼값으로 지정
                    --%>
                    <c:forEach items="${tabledata.data}" var="data">
                        <tr>
                            <c:forEach items="${tableinfo.data}" var="info">
                                <td name="${info.column_Name}"><c:out value="${data[info.column_Name]}"/></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <%--
                            [테이블 footer 제작] 헤더와 동일 삭제해도 무관(시인성 전용 기능x)
                            footer명: column에 지정된 comment로 생성됨.
                            <주의사항>
                            각 테이블 생성 시 column에 comment를 반드시 지정해야 함.
                            comment 없을 시 테이블 footer가 빈값으로 지정됨.
                        --%>
                        <c:forEach items="${tableinfo.data}" var="info">
                            <th><c:out value="${info.column_Comment}"/></th>
                        </c:forEach>
                    </tr>

                    </tfoot>
                </table>
            </div>

            <%--
                [데이터 수정용 Modal]
                id : DataModal
            --%>
            <div class="modal fade" id="DataModal" tabindex="-1" role="dialog"
                 aria-labelledby="DataModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;
                            </button>
                            <h4 class="modal-title" id="DataModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <%--
                                [데이터 처리용 hidden 값]
                                id : tablename : 사용중인 테이블명
                                id : status : 데이터 처리 상태 (edit,new)(수정,신규)
                                id : key_column : 테이블의 primary key column명
                            --%>
                            <input type="text" name="tablename" id="tablename" value="${tablename}" hidden>
                            <input type="text" name="status" id="status" value="add" hidden>
                            <input type="text" id="key_column" value="${key_column}" hidden>
                            <input type="text" id="key_value" value="" hidden>

                            <%--
                                [데이터 수정용 Form]
                                id : dataForm
                                name : dataForm
                            --%>
                            <form id="dataForm" name="dataForm">
                                <%-- 테이블 컬럼 갯수 만큼 생성 --%>
                                <c:forEach items="${tableinfo.data}" var="info">
                                    <label for="${info.column_Name}">${info.column_Comment}</label>

                                    <%-- 필수값 처리 nullable 여부로 판단 --%>
                                    <c:set var="required" value=" "/>
                                    <c:if test="${info.is_Nullable == 'NO'}">
                                        <c:set var="required" value="required"/>
                                    </c:if>

                                    <%--
                                        외래키인 경우 select option으로 생성
                                    --%>
                                    <c:if test="${info.ref_Table!=null}">
                                        <select id="${info.column_Name}" name="${info.column_Name}"
                                                class="form-control" ${required}>
                                            <c:forEach items="${tableinfo[info.ref_Table].info}" var="refinfo">
                                                <c:if test="${refinfo.column_Key=='PRI'}">
                                                    <c:forEach items="${tableinfo[info.ref_Table].data}" var="refdata">
                                                        <option value="${refdata[refinfo.column_Name]}">
                                                                ${refdata[refinfo.column_Name]}
                                                        </option>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </c:if>

                                    <%--
                                        외래키 아닌 경우 input으로 생성
                                    --%>
                                    <c:if test="${info.ref_Table==null}">
                                        <c:choose>
                                            <c:when test="${code.code_column eq info.column_Name}">
                                                <input type="text" id="${info.column_Name}"
                                                       name="${info.column_Name}" class="form-control"
                                                       placeholder="${info.column_Comment}"
                                                       value="${code.code}"
                                                       readonly/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${info.data_Type=='date'}">
                                                        <input type="date" id="${info.column_Name}"
                                                               name="${info.column_Name}" class="form-control"
                                                            ${required}/>
                                                    </c:when>
                                                    <c:when test="${info.data_Type=='int'}">
                                                        <input type="number" id="${info.column_Name}"
                                                               name="${info.column_Name}" class="form-control"
                                                            ${required} min="0"/>
                                                    </c:when>
                                                    <c:when test="${info.data_Type=='varchar'}">
                                                        <input type="text" id="${info.column_Name}"
                                                               name="${info.column_Name}" class="form-control"
                                                            ${required} maxlength="${info.data_Length}"/>
                                                    </c:when>
                                                    <c:when test="${info.data_Type=='tinyint'}">
                                                        <select id="${info.column_Name}" name="${info.column_Name}"
                                                                class="form-control" ${required}>
                                                            <option value="0">미사용</option>
                                                            <option value="1">사용</option>
                                                        </select>
                                                    </c:when>
                                                </c:choose>

                                            </c:otherwise>

                                        </c:choose>
                                    </c:if>


                                </c:forEach>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Close
                            </button>
                            <button type="button" class="btn btn-primary" id="saveBtn">Save
                                changes
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.데이터 수정용 modal -->

            <%--
                [결과 출력 Modal]
                id : DataModal
            --%>
            <div class="modal fade" id="ResultModal" tabindex="-1" role="dialog"
                 aria-labelledby="ResultModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;
                            </button>
                            <h4 class="modal-title" id="ResultModalLabel">처리 결과</h4>
                        </div>
                        <div class="modal-body">
                            <div id="result"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">닫기
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.결과 출력 modal -->

            <!--  end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->

<script>
    $(document).ready(function () {
        /**
         * [DataTable 초기 생성]
         */
        $('#dg').DataTable({
            paging: true,
            searching: true,
            info: true,
            autoWidth: false,
            responsive: true,
            lengthChange: true,
            lengthMenu: [
                [10, 25, 50, -1],
                [10, 25, 50, 'All'],
            ],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'copy',
                    text: 'Copy',
                },
                {
                    extend: 'excel', //파일명
                    title: 'excel',
                },
                {
                    extend: 'pdf',
                    title: 'pdf',
                },
                {
                    extend: 'print',
                    title: 'print',
                },
            ]
        });
        /**
         * [DataTable 초기 생성 끝]
         */

        /**
         * [데이터 클릭 시 액션]
         * 1. 데이터 클릭 시 색 변경
         * 2. 데이터 더블 클릭 시 수정창 생성
         */
        var table = $('#dg').DataTable();

        // 1. 데이터 클릭 시 색 변경
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

        /* 2. 데이터 더블 클릭 시 수정창 생성
            * 1. 수정창 생성
            * 2. 데이터 수정 상태 변경
            * 3. 수정창에 데이터 삽입
         */
        $('#dg tbody').on('dblclick', 'tr', function () {
            $("#DataModal").modal("show");
            $('#status').val('edit');
            $('#DataModalLabel').text('수정');
            $(this).find("td").each(function () {
                var name = $(this).attr("name");
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
                $("#" + name).val(value);
            });
        });

        /**
         * [데이터 클릭 시 액션 끝]
         */

        /**
         * [데이터 추가 버튼 클릭 시 액션]
         * 1. 데이터 추가 상태 변경
         * 2. 수정창 초기화
         * 3. 수정창 생성
         */
        $('#addBtn').on('click', function () {
            $('#status').val('add');
            $('#DataModalLabel').text('추가');
            $('#dataForm').find('input').val('');
            if("${code.code}"!=""){
                $('#${code.code_column}').val("${code.code}");
            }
            $("#DataModal").modal("show");
        });

        /**
         * [데이터 삭제 버튼 클릭 시 액션]
         */
        $('#deleteBtn').click(function () {
            deleteData();
        });

        /**
         * [데이터 저장 버튼 클릭 시 액션]
         */
        $('#saveBtn').click(function () {
            saveData();
        });
    });

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
        var table_name = $('#tablename').val();
        var data = {
            key_column: key_column,
            selected: selected,
        };

        // 2. 삭제 데이터 DB에 반영
        var url = "/api/data/delete/" + table_name;
        $.ajax({
            type: 'Delete',
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if (data.statusCode == 200) {
                    $("#DataModal").modal("hide");
                    // location.reload();
                    // show_content();
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
        // if (!chk_requried()) return;

        // 2. 데이터 저장 상태 확인(추가: POST, 수정: PUT)
        var status = $('#status').val();
        var tablename = $('#tablename').val();
        var url = "/api/data/save/" + tablename;
        var type = "POST";
        if (status == 'edit') {
            url = "/api/data/update/" + tablename;
            type = "PUT";
        }

        // 3. 데이터 처리
        var column = "";
        var value = "";
        var key_column = $('#key_column').val();
        var key_value = $('#key_value').val();

        validation = true;
        $('#dataForm').find('input,select').each(function () {
            if (!value_validation($(this).val())) {
                validation = false;
                return;
            }
            if ($(this).val() != '') {
                column += $(this).attr('id') + ",";
                code = "${code.code}";
                code_column = "${code.code_column}";
                info_column = $(this).attr('id');
                if (code != "" && code_column == info_column) {
                    value += "NewCode('" + code + "'),";
                } else {
                    value += "'" + $(this).val() + "',";
                }

            }
        });
        if (!validation) return;

        column = column.substring(0, column.length - 1);
        value = value.substring(0, value.length - 1);

        var data = {
            key_column: key_column,
            key_value: key_value,
            column: column,
            value: value
        };

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
                    // location.reload();
                    // show_content();
                }
                $('#ResultModal').modal('show');
                $('#result').html(data.responseMessage);
            }
        });
    };//e:saveData()


</script>
