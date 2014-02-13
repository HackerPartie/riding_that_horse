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

id: ${post.id}<br>
<h4>${post.title}</h4>

${post.body}<br>
<br><br>
by ${post.username}


<c:if test="${u != null}">
    <c:choose>

	    <c:when test="${u.equals(post.username)}">
        	<a href="<c:url value="/updatesinglepost/?id=${post.id}"/>">update</a> |
		    <a href="<c:url value="/delete/?id=${post.id}"/>">delete</a>
	    </c:when>
        <c:otherwise>
            not authorized
        </c:otherwise>
    </c:choose>
</c:if>
<c:choose>
    <c:when test="${u == null}">
        login
    </c:when>

</c:choose>



</body>
</html>