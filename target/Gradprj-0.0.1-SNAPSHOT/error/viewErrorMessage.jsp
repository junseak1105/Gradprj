<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
에러 타입: <%@=exception.getClass().getName()%><br>
에러 메세지: <b><%=exception.getMessage()%></b>