<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="includes/injection.jsp"%>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
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
                        <c:forEach items="${tableinfo.data}" var="info">
                            <th><c:out value="${info.column_Comment}"/></th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
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
                        <c:forEach items="${tableinfo.data}" var="info">
                            <th><c:out value="${info.column_Comment}"/></th>
                        </c:forEach>

                    </tr>

                    </tfoot>
                </table>
            </div>

            <!-- Modal  추가 -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">
                            <input type="text" name="tablename" id="tablename" value="${tablename}" hidden>
                            <input type="text" name="status" id="status" value="add" hidden>
                            <input type="text" id="key_column" value="${key_column}" hidden>
                            <form id="data" name="data">
                                <c:forEach items="${tableinfo.data}" var="info">
                                    <span>${info.column_Comment}</span>
                                    <c:if test="${info.ref_Table!=null}">
                                        <select id="${info.column_Name}" name="${info.column_Name}"
                                                class="form-control">
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
                                    <c:if test="${info.ref_Table==null}">
                                        <c:set var="type" value="text"/>
                                        <c:choose>
                                            <c:when test="${info.data_Type=='date'}">
                                                ${type='date'}
                                            </c:when>
                                            <c:when test="${info.data_Type=='int'}">
                                                ${type='number'}
                                            </c:when>
                                        </c:choose>
                                        <input type="${type}" id="${info.column_Name}" name="${info.column_Name}"
                                               class="form-control"/>
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
            <!-- /.modal -->

            <!--  end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->

<script>
    $(document).ready(function () {
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

        var table = $('#dg').DataTable();

        $('#dg tbody').on('click', 'tr', function () {
            // console.log($(this).find("td").eq(0).text());
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                $(this).css("background-color", "");
            } else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                $(this).css("background-color", "#7978FF");
            }
        });

        $('#dg tbody').on('dblclick', 'tr', function () {
            $("#myModal").modal("show");
            $('#status').val('edit');
            $('#myModalLabel').text('수정');
            $(this).find("td").each(function () {
                var name = $(this).attr("name");
                var value = $(this).text();
                $("#" + name).val(value);
            });
        });

        $('#addBtn').on('click', function () {
            $('#status').val('add');
            $('#myModalLabel').text('추가');
            $('#data').find('input').val('');
            $("#myModal").modal("show");
        });

        $('#deleteBtn').click(function () {
            var selected = "";
            $('.selected').each(function () {
                selected += $(this).find("td").eq(0).text() + ",";
            });
            if (selected == "") {
                alert("삭제할 데이터를 선택해주세요.");
                return;
            }
            selected = selected.substring(0, selected.length - 1);
            var key_column = $('#key_column').val();
            var table_name = $('#tablename').val();
            var data = {
                key_column: key_column,
                selected: selected,
            };

            var url = "/api/data/delete/" + table_name;
            $.ajax({
                type: 'Delete',
                url: url,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    console.log(data);
                }
            });

        });

        //on click submit ajax
        $('#saveBtn').click(function () {
            var status = $('#status').val();
            var tablename = $('#tablename').val();
            var url = "/api/data/save/" + tablename;
            var type = "POST";
            if (status == 'edit') {
                url = "/api/data/update/" + tablename;
                type = "PUT";
            }
            //save form data into column String and value String
            var column = "";
            var value = "";
            var key_column = $('#key_column').val();
            var key_value = $('#' + key_column).val();

            $('#data').find('input,select').each(function () {
                if ($(this).val() != '') {
                    column += $(this).attr('id') + ",";
                    value += "'" + $(this).val() + "',";
                }
            });

            column = column.substring(0, column.length - 1);
            value = value.substring(0, value.length - 1);

            var data = {
                key_column: key_column,
                key_value: key_value,
                column: column,
                value: value
            };
            console.log(data);
            $.ajax({
                type: type,
                url: url,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    console.log(data);
                }
            });
        });
    });
</script>
