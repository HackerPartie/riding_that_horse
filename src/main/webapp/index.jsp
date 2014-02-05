<%@page import="java.sql.ResultSet"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
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

	<br>
	
	<%= request.getAttribute("userName") %><br>
	
<form action="riding" method="get">
	<select id="horse" name="horsename">
		<c:forEach items="${horses}" var="horse">
			<option> 
				${horse.horseName}
			</option>	
		</c:forEach>
	</select>
	<input type="submit" value="click it!">
</form>	

<c:forEach items="${rides}" var="ride">
	${ride.horsemanName} hat ${ride.horseName} auf ${ride.place} am ${ride.day} geritten<br>
</c:forEach>
	
</body>
</html>