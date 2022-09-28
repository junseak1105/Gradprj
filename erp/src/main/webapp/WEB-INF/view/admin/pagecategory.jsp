<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../injection.jsp" %>
<script src="/js/admin/pagecategory.js?<%=formatedNow%>"></script>
<div id="pagecategory" style="height: 30%">
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">페이지 분류 목록</h3>
            </div>
            <div class="panel-body">
                <table id="dg_pagecategory"></table>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">페이지 분류 추가</h3>
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