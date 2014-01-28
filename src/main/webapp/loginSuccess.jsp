<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login success page</title>
</head>
<body>

<%@include file="include/auth.jsp" %>
<%@include file="include/navigation.jsp" %>

<h4>Hi <%= userName %>, ois passt!! Your Session ID: <%= sessionID %></h4>
User = <%= user %><br>
<a href="checkout.jsp">checkout</a>
<form action="LogoutServlet" method="post">
	<input type="submit" value="logout">
</form>

</body>
</html>