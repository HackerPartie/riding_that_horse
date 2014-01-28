<%
String user = null;

if (session.getAttribute("user") == null) {
	response.sendRedirect("login.jsp");
} else user = (String) session.getAttribute("user");

String userName = null;
String sessionID = null;

Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals(user)) userName = cookie.getName();
		if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
	}
}
%>