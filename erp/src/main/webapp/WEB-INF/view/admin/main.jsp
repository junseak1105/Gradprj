<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<style>
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>관리자 페이지</title>
    <%@include file="../injection.jsp" %>
    <script src="/js/admin/main.js?<%=formatedNow%>"></script>
</head>
<body>
<%@include file="../sidebar.jsp"%>
<div class="container" style="margin-left: 0px; margin-right: 0px;width: 100%">
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <button class="btn btn-outline-secondary" onclick="show_content($(this).val())" value="pagelist">페이지목록</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-outline-secondary" onclick="show_content($(this).val())" value="pagecategory">페이지 카테고리</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-outline-secondary" onclick="show_content($(this).val())" value="sortcategory">분류 수정</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-outline-secondary" onclick="show_content($(this).val())" value="../api/table/new">테이블 수정</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="content"></div>
</div>
</body>
<footer>
    <%@include file="../footer.jsp" %>
</footer>
</html>