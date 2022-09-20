<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

ModelAndView 테스트 페이지입니다.
<br>
${ObjectTest}
<br>
${listTest}
<br><br>
<c:forEach var="listTest" items="${listTest}">
    ${listTest}<br>
</c:forEach>

