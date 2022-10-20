<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@include file="../includes/header.jsp" %>
    <script>var pagename = "${pagename}"</script>
    <script src="/js/page/DataGrid.js"></script>
<div id="container">
    <div id="header"></div>
    <div id="content">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">${pagename}</h3>
                </div>
                <div id="dg"></div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">데이터 수정</h3>
                </div>
                <div class="panel-body">
                    <form name="fr_data" id="fr_data">
                        <input type="hidden" name="fr_data_status" id="fr_data_status" value="new">
                        <input type="hidden" name="idx" id="fr_data_idx" value="-1">
                        <input type="hidden" name="pagename" id="fr_data_pagename"
                               value="<%=request.getParameter("pagename")%>">
                        <input type="hidden" name="sort_lv1" id="fr_data_sort_lv1">
                        <input type="hidden" name="sort_lv2" id="fr_data_sort_lv2">
                        <input type="hidden" name="sort_code" id="fr_data_sort_code">

                        <div id="fr_inputlist"></div>
                        <button type="button" class="btn btn-primary" id="btn_page_save" onclick="fr_data_save()">저장
                        </button>
                        <button type="button" class="btn btn-success" id="btn_page_add" onclick="fr_data_clear()">
                            추가
                        </button>
                        <button type="button" class="btn btn-danger" id="btn_page_delete" onclick="fr_data_delete()">
                            삭제
                        </button>
                    </form>
                </div>
            </div>
        </div>


    </div>
</div>
    <%@include file="../includes/footer.jsp" %>
