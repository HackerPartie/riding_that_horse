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

<form action="/updatesinglepost" method="post">
	<input type="text" name="title" value="${post.title}"><br>
	<input type="hidden" name="id" value="${post.id}"><br>
	<textarea rows="5" cols="31" name="body">${post.body}</textarea><br>
	<input type="submit" value="update">
</form>

</body>
</html>