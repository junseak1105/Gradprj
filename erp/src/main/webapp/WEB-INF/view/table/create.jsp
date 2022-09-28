<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <%@include file="../injection.jsp" %>
    <script src="/js/table/create.js"></script>
</head>
<body>
<form id="table_data_frm">

    <div class="form-group">
        <label for="name">테이블 명</label>
        <input class="form-control" type="text" name="name" id="name"/>
        <button class="btn btn-default" type="button" onclick="Table_dup_chk_func()">중복확인</button>
    </div>
    <div class="form-group">
        <label for="tablecomment">테이블 설명</label>
        <input class="form-control" type="text" name="tablecomment" id="tablecomment"/>
    </div>
    <div class="form-group">
        <button class="btn btn-default" type="button" id="btn_addcol" onclick="AddCol()">추가</button>
        <button class="btn btn-default" type="button" id="btn_clearcol" onclick="ClearCol()">전체삭제</button>
        <input type="hidden" name="colnumcnt" id="colnumcnt"/>
    </div>
    <div class="form-group">
        <table id="New_table"></table>
    </div>
    <button class="btn btn-default" type="button" onclick="Submit()" >테이블 생성</button>
</form>
</body>
</html>
