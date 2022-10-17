<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@include file="includes/header.jsp" %>
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
                name
                <%--				<button id='regBtn' type="button" class="btn btn-xs pull-right">Register--%>
                <%--					New Board</button>--%>
            </div>
            <button id="deleteBtn" type="button" class="btn btn-xs pull-right">Delete
                Selected
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
                                <td><c:out value="${data[info.column_Name]}"/></td>
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
            <!--  end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->

<%@include file="includes/footer.jsp" %>
<script>
    $(document).ready(function () {
        $('#dg').DataTable({
            paging: true,
            searching: false,
            info: true,
            autoWidth: false,
            responsive: true,
            lengthChange: true,
            // "ajax": {
            //     url: "/ajax/getPageList",
            //     data: function (d) {
            //         // d.page = $('#table').DataTable().page.info().page + 1;    // 페이지 번호, DataTable().page.info().page은 0임
            //         // d.pageSize = $('#table').DataTable().page.len();            // 페이지 사이즈, 한 페이지에 몇개의 row인지
            //         // d.orderBy = orderColumn[$('#table').DataTable().order()[0][0]];        // 정렬조건 컬럼명
            //         // d.orderCondition = $('#table').DataTable().order()[0][1];            // 오름 또는
            //     },
            // },
            dom : 'Bfrtip',
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

        var table = $('#example').DataTable();

        $('#dg tbody').on('click', 'tr', function () {
            console.log($(this).find("td").eq(0).text());
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                $(this).css("background-color", "");
            } else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                $(this).css("background-color", "#7978FF");
            }
        });

        $('#deleteBtn').click(function () {
            $('.selected').each(function () {
                console.log($(this).find("td").eq(0).text());
            });
        });
    });
</script>
