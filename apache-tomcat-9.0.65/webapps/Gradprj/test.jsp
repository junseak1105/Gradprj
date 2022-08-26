<%--
  Created by IntelliJ IDEA.
  User: junse
  Date: 2022-08-19
  Time: 오전 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<input type="button" value="test" onclick="GET_ids()">
    <div id ="test"></div>
</body>
</html>

<script>
    $(document).ready(function () {//시작시 세팅
        GET_ids();
    });

    function GET_ids() {
        // json 형식으로 데이터 set

        // ajax 통신
        //get인 경우 데이터 없이 url에 get방식으로 넣어준다.
        $.ajax({
            type: "GET",            // HTTP method type(GET, POST) 형식이다.
            url: "/Gradprj/test",      // 컨트롤러에서 대기중인 URL 주소이다.
            dataType: 'json',
            success: function (data) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                var test = document.getElementById('test');
                test.innerHTML="";
                for (key in data) {
                    var temp = document.createElement("p");

                    userid = data[key].userid;
                    passwd = data[key].passwd;
                    email = data[key].email;
                    alert(userid);

                    temp.innerHTML="id:"+userid+" pw:"+passwd+" email:"+email+"<br>"

                    test.appendChild(temp);

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
                alert("통신 실패.")
            }
        });
    }
</script>
