<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if (session.getAttribute("user") == null) {
	response.sendRedirect("login.html");
}

String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		userName = cookie.getName();
	}
}
%>

<h4>Hi <%= userName %>, do the checkout!!</h4>
<form action="LogoutServlet" method=post>
	<input type="submit" value="logout">
</form>
</body>
</html>