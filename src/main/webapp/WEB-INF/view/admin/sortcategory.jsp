<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../injection.jsp" %>
<script src="/js/admin/sortcategory.js?<%=formatedNow%>"></script>
<div id="sort_control" style="height: 30%">
    <div class="col-md-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">대분류</h3>
            </div>
            <div class="panel-body">
                <table id="dg_sort_lv1"></table>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">중분류</h3>
            </div>
            <div class="panel-body">
                <table id="dg_sort_lv2"></table>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">분류 코드</h3>
            </div>
            <div class="panel-body">
                <table id="dg_sort_code"></table>
            </div>
        </div>
    </div>
</div>


<%--<div class="col-md-6">--%>
<%--    <div class="panel panel-default">--%>
<%--        <div class="panel-heading">--%>
<%--            <h3 class="panel-title">분류 코드</h3>--%>
<%--        </div>--%>
<%--        <div class="panel-body">--%>
<%--            <table id="dg_sortcode"></table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div class="col-md-4">--%>
<%--    <div class="panel panel-default">--%>
<%--        <div class="panel-heading">--%>
<%--            <h3 class="panel-title">페이지 분류 추가</h3>--%>
<%--        </div>--%>
<%--        <div class="panel-body">--%>
<%--            <form name="fr_sortcategory">--%>
<%--                <div class="input-group input-group-lg">--%>
<%--                    <span class="input-group-text">분류명</span>--%>
<%--                    <input type="text" class="form-control" name="category_name" id="fr_sortcategory_name">--%>
<%--                </div>--%>
<%--                <div class="input-group input-group-lg">--%>
<%--                    <span class="input-group-text">경로</span>--%>
<%--                    <input type="text" class="form-control" name="base_url" id="fr_pageurl_name">--%>
<%--                </div>--%>
<%--                <button type="button" class="btn btn-default" id="btn_sortcategory_dupchk">중복확인</button>--%>
<%--                <button type="button" class="btn btn-primary" id="btn_sortcategory_save">저장</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>