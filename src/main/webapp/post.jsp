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


<% if (request.getAttribute("u") != null) { %>
	<% if (request.getAttribute("u").equals(request.getAttribute("pUsername"))) {  %>
		<a href="/updatesinglepost/?id=${post.id}">update</a> | 
		<a href="/delete/?id=${post.id}">delete</a>	
	<% } %>
<% } else if (request.getAttribute("u") == null){ %>
		<c:if test=""></c:if>
<% } %>



</body>
</html>