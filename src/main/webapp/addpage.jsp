<%--
  Created by IntelliJ IDEA.
  User: junse
  Date: 2022-08-18
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <%@include file="header.jsp" %>
</head>
<body>
<form id="table_data_frm">
    <select name="tablename" id="tablename">
    </select>
    <input type="button" value="테이블 선택" onclick="Get_Table_Info()"/>
</form>
<form action="">
    <div id="colarr"></div>
</form>

</body>
</html>
<script>
    $(document).ready(function () {//시작시 세팅
        Get_Table_List();
    });

    //테이블 정보 호출
    function Get_Table_Info() {
        // json 형식으로 데이터 set
        var formData = $("#table_data_frm").serialize();
        // ajax 통신
        //get인 경우 데이터 없이 url에 get방식으로 넣어준다.
        $.ajax({
            type: "GET",            // HTTP method type(GET, POST) 형식이다.
            url: "/Gradprj/Get_Table_Info",      // 컨트롤러에서 대기중인 URL 주소이다.
            data: formData,
            dataType: 'json',
            success: function (data) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                // document.getElementById("temp").innerHTML = JSON.stringify(data);
                Generate_Table_selector(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
                alert("통신 실패.")
            }
        });
    }

    function Generate_Table_selector(data) {
        var colarr = document.getElementById("colarr");
        colarr.innerHTML="";

        //column  선택 체크박스 추가
        for (key in data.columns) {
            let label = document.createElement('label');
            label.innerHTML=data.columns[key];
            let checkbox = document.createElement('input');
            checkbox.setAttribute("type","checkbox");
            checkbox.setAttribute("value",data.columns[key]);
            label.appendChild(checkbox);
            colarr.appendChild(label);
        }
        //submit 추가
        let temp = document.createElement('input');
        temp.setAttribute("type","button");
        temp.setAttribute("value","출력 값 선택 완료");
        temp.setAttribute("onclick","Test()");

        colarr.appendChild(temp);
        // data.table_name
        // data.table_id
    }

    function Test() {
        alert("test");
    }

    //테이블 목록 생성
    function Get_Table_List() {
        // json 형식으로 데이터 set

        // ajax 통신
        //get인 경우 데이터 없이 url에 get방식으로 넣어준다.
        $.ajax({
            type: "GET",            // HTTP method type(GET, POST) 형식이다.
            url: "/Gradprj/Get_Table_List",      // 컨트롤러에서 대기중인 URL 주소이다.
            dataType: 'json',
            success: function (data) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                var table_list = document.getElementById('tablename');
                table_list.innerHTML = "";
                for (key in data) {
                    var option = document.createElement("option");
                    option.value = key;
                    option.text = data[key].table_name;
                    table_list.appendChild(option);

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
                alert("통신 실패.")
            }
        });
    }
</script>