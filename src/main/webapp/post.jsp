<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="include/navigation.jsp" %>

id:<br>
${post.id}<br>
title:<br>
${post.title}<br>
body:<br>
${post.body}<br>
userId:<br>
${post.userId}<br>

<%-- <c:forEach items="${post}" var="p">
	${p.title}<br>
	${p.body}<br>
</c:forEach> --%>

</body>
</html>