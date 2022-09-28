<%@ page import="java.io.*, java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- 캐싱 방지 --%>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
	if (request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");
%>
<%
	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	String formatedNow = formatter.format(now);
%>

<%-- JQuery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%--부트스트랩--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	  rel="stylesheet"
	  integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	  crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<%--js공통기능--%>
<script src="/js/commonfunction.js"></script>


<!-- install easyui-->
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/demo/demo.css">
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.min.js"></script>
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>

<%--jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>