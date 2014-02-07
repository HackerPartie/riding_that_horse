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

<div>
<c:forEach items="${posts}" var="posting">
	<a href="/post/?id=${posting.id}">${posting.id}</a><br>
	<b>${posting.title}</b><br>
	${posting.body}<br>
	${posting.userId}<br><br>
</c:forEach>
</div>

</body>
</html>