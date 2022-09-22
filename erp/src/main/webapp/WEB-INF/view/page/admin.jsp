<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<style>
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>관리자 페이지</title>
    <%@include file="../header.jsp" %>
    <script src="/js/page/admin.js"></script>
</head>
<body>
<%--스크롤 메뉴 제작 필요--%>
<div id="scroll_menu"></div>

<div class="container">
    <div class="pagelist" style="height: 30%">
        <div class="col-md-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">페이지 목록</h3>
                </div>
                <div class="panel-body">
                    <table id="dg_pagelist"></table>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">페이지 정보</h3>
                </div>
                <div class="panel-body">
                    <form name="fr_pagelist" id ="fr_pagelist">
                        <input type="hidden" name="idx" id="fr_pagelist_idx" value="-1">
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">페이지명</span>
                            <input type="text" class="form-control" name="page_name" id="fr_pagelist_page_name">
                        </div>
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">설명</span>
                            <input type="text" class="form-control" name="page_desc" id="fr_pagelist_page_desc">
                        </div>
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">경로</span>
                            <input type="text" class="form-control" name="page_url" id="fr_pagelist_page_url">
                        </div>
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">사용테이블</span>
                            <input type="text" class="form-control" name="page_table" id="fr_pagelist_page_table">
                        </div>
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">분류</span>
                            <input type="text" class="form-control" name="page_category" id="fr_pagelist_page_category">
                        </div>
                        <button type="button" class="btn btn-primary" id="btn_page_save" onclick="fr_pagelist_save()">저장</button>
                        <button type="button" class="btn btn-success" id="btn_page_add" onclick="fr_pagelist_clear()">
                            추가
                        </button>

                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="pagecategory" style="height: 30%">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">카테고리 목록</h3>
                </div>
                <div class="panel-body">
                    <table id="dg_pagecategory"></table>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">카테고리 추가</h3>
                </div>
                <div class="panel-body">
                    <form name="fr_pagecategory">
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">분류명</span>
                            <input type="text" class="form-control" name="category_name" id="fr_pagecategory_name">
                        </div>
                        <div class="input-group input-group-lg">
                            <span class="input-group-text">경로</span>
                            <input type="text" class="form-control" name="base_url" id="fr_pageurl_name">
                        </div>
                        <button type="button" class="btn btn-default" id="btn_pagecategory_dupchk">중복확인</button>
                        <button type="button" class="btn btn-primary" id="btn_pagecategory_save">저장</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<footer>
    <%@include file="../footer.jsp" %>
</footer>
</html>