<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../includes/injection.jsp" %>
<script src="/js/tablegenerator.js"></script>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }

    table td, table th {
        border: 1px solid #000;
        padding: 5px;
    }

    .highlighted {
        background-color:#999;
    }

    .currenttd {
        background-color:lightblue;
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
                <table id="table">
                    <thead>
                    <tr>
                        <th colspan="100%">
                            <table  id="thead" style="width: 100%;border-collapse: collapse">
                                <tr>
                                    <td class="colresize" style="width : 50px; height:50px;"></td>
                                </tr>
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
                                <tr>
                                    <td class="colresize" style="width : 50px; height:50px;"></td>
                                </tr>
                            </table>
                        </th>
                    </tr>
                    </tfoot>
                </table>
<%--                <input type="text" id="result_thead" style="width: 100%;height: 100px" readonly hidden>--%>
<%--                <input type="text" id="result_tfoot" style="width: 100%;height: 100px" readonly hidden>--%>
            </div>

            <!--  end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->


<%@include file="../contextmenu.jsp"%>
