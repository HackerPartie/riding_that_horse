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

<h4>Hi <%= request.getAttribute("userName") %>, ois passt!! Your Session ID: <%= request.getAttribute("sessionID") %></h4>
User<br>

<span> <%= request.getCookies() %></span>

<a href="checkout.jsp">checkout</a>
<form action="logout" method="get">
	<input type="submit" value="logout">
</form>

</body>
</html>