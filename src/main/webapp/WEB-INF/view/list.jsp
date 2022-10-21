<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="includes/injection.jsp" %>
<script src="/js/list.js"></script>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header" id="page-header"></h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"></div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table id="dg" class="table table-striped table-bordered table-hover">
                    <thead id="table-header"></thead>
                    <tfoot id="table-footer"></tfoot>
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
                        <div class="modal-body" id="DataModalBody">
                            <%--
                                [데이터 처리용 hidden 값]
                                id : tablename : 사용중인 테이블명
                                id : status : 데이터 처리 상태 (edit,new)(수정,신규)
                                id : key_column : 테이블의 primary key column명
                            --%>
                            <input type="text" name="tablename" id="tablename" value="" hidden>
                            <input type="text" name="status" id="status" value="add" hidden>
                            <input type="text" id="key_column" value="" hidden>
                            <input type="text" id="key_value" value="" hidden>
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
</div>
