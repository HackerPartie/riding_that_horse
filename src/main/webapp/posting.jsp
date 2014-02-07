<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@include file="include/auth.jsp" %>
<%@include file="include/navigation.jsp" %>

<%= request.getAttribute("userName") %>

<form action="savepost" method="post" id="postingform">
	<input type="text" name="title"><br>
	<textarea rows="5" cols="31" name="body"></textarea><br>
	<input type="submit" value="post">
</form>

</body>
</html>