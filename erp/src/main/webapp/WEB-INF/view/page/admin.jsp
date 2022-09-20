<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>페이지</title>
    <%@include file="../header.jsp" %>
</head>
<body>
<%--스크롤 메뉴 제작 필요--%>
<div id="scroll_menu"></div>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">테이블 목록</h3>
                </div>

                <div class="panel-body">
                    <table id="dg">
                        <thead>
                        <tr>
                            <th data-options="field:'페이지명'">Code</th>
                            <th data-options="field:'설명'">Name</th>
                            <th data-options="field:'경로'">Price</th>
                            <th data-options="field:'사용테이블'">Price</th>
                        </tr>
                        </thead>
<%--                        <tbody>--%>
<%--                        <c:forEach items="${tablelist}" var="table">--%>
<%--                                <tr>--%>
<%--                                    <td>${table.page_name}</td>--%>
<%--                                    <td>${table.page_desc}</td>--%>
<%--                                    <td>${table.page_url}</td>--%>
<%--                                    <td>${table.page_table}</td>--%>
<%--                                </tr>--%>
<%--                        </c:forEach>--%>
                        </tbody>
                    </table>

                    <table>

                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">패널 제목</h3>
                </div>
                <div class="panel-body">
                    패널 내용
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">패널 제목</h3>
                </div>
                <div class="panel-body">
                    패널 내용
                </div>
            </div>
        </div>
    </div>


</body>
<footer>
    <%@include file="../footer.jsp" %>
</footer>
</html>
<script>
    $(document).ready(function () {
    });



</script>