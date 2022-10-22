<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="includes/injection.jsp" %>
<script src="/js/table.js"></script>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }

    table td, table th {
        border: 1px solid #000;
        padding: 5px;
    }

</style>
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
                <div>
                    <button type="button" class="btn btn-primary" id="addHeadBtn">헤더 추가</button>
                    <button type="button" class="btn btn-primary" id="addFootBtn">푸터 추가</button>
                    <button type="button" class="btn btn-primary" id="mergeBtn">병합</button>
                    <button type="button" class="btn btn-danger" id="deleterowBtn">열삭제</button>
                </div>
                <table id="table">
                    <thead>
                    <tr>
                        <th colspan="100%">
                            <table  id="thead" style="width: 100%;border-collapse: collapse">
                            </table>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="100%">내용이 들어갑니다.</td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="100%">
                            <table  id="tfoot" style="width: 100%;border-collapse: collapse">
                            </table>
                        </th>
                    </tr>
                    </tfoot>
                </table>
                <input type="text" id="result_thead" style="width: 100%;height: 100px" hidden>
                <input type="text" id="result_tfoot" style="width: 100%;height: 100px" hidden>
            </div>

            <!--  end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->


<%--
    [테이블 생성용 Modal]
    id : TableCreateModal
--%>
<div class="modal fade" id="TableCreateModal" tabindex="-1" role="dialog"
     aria-labelledby="TableCreateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="TableCreateModalLabel">형식 생성</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="createstatus" value="">
                세로: <input type="number" id="col" value="0">
                가로: <input type="number" id="row" value="0">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="createBtn">생성</button>
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
