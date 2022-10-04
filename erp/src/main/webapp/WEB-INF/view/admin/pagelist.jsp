<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../injection.jsp" %>
<script src="/js/admin/pagelist.js?<%=formatedNow%>"></script>
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
                <form name="fr_pagelist" id="fr_pagelist">
                    <input type="hidden" name="fr_pagelist_status" id="fr_pagelist_status" value="new">
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
                        <input type="text" class="form-control" name="page_url" id="fr_pagelist_page_url" value="/">
                    </div>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text">사용테이블</span>
                        <div id="fr_pagelist_page_table"></div>
                    </div>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text">분류</span>
                        <div id="fr_pagelist_page_category"></div>
                    </div>
                    <button type="button" class="btn btn-primary" id="btn_page_save" onclick="fr_pagelist_save()">저장
                    </button>
                    <button type="button" class="btn btn-success" id="btn_page_add" onclick="fr_pagelist_clear()">
                        추가
                    </button>

                </form>
            </div>
        </div>
    </div>
</div>