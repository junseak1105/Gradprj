<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../injection.jsp" %>
<script src="/js/table/table.js?<%=formatedNow%>"></script>
<div id="tablelist" style="height: 30%">
    <div class="col-md-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">테이블 목록</h3>
            </div>
            <div class="panel-body">
                <table id="dg_tablelist"></table>
            </div>
        </div>
    </div>
    <div class="col-md-7">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">테이블 정보 </h3>
            </div>
            <div class="panel-body">
                <form id="fr_table_create" method="post">
                    <div class="input-group input-group-lg">
                            <span class="input-group-text">테이블 명 &nbsp
                            <button class="btn btn-default" type="button" onclick="Table_dup_chk_func()">중복확인</button>
                            </span>
                        <input class="form-control" type="text" name="name" id="fr_table_create_name"/>
                    </div>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text">테이블 설명</span>
                        <input class="form-control" type="text" name="tablecomment" id="fr_table_create_tablecomment"/>
                    </div>


                    <div class="input-group input-group-lg">
                            <span class="input-group-text">컬럼 &nbsp;
                            <button class="btn btn-default" type="button" id="btn_addcol" onclick="AddCol()">컬럼추가</button>
                            <button class="btn btn-default" type="button" id="btn_clearcol" onclick="ClearCol()">컬럼 전체삭제</button>
                            </span>
                        <input type="hidden" name="colnumcnt" id="fr_table_create_colnumcnt"/>
                        <table id="fr_table_create_New_table"></table>
                    </div>

                    <div class="input-group input-group-lg">
                            <span class="input-group-text">
                                <button type="button" class="btn btn-primary" onclick="Submit()">저장</button>
                                <button type="button" class="btn btn-success" onclick="fr_table_create_clear()">추가</button>
                            </span>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>