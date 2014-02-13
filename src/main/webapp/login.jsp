<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="include/navigation.jsp" %>

<form action="LoginServlet" method="post">
	username: <input type="text" name="user"><br>
	password: <input type="password" name="pwd"><br>
	<input type="submit" value="login"> 
</form>

<form action="register" method="post">
	username: <input type="text" name="user"><br>
	password: <input type="password" name="pwd"><br>
	<input type="submit" value="register">
</form>

</body>
</html>