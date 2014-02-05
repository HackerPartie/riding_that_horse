


<div class="navigation">
	<a href="/horse">horse</a>	
	<a href="/posting.jsp">posting</a>
	<a href="/posts">posts</a>
	 | 
	<% if (request.getAttribute("u") == null) {  %>
		<a href="/login.jsp">login</a>
	<% } else { %>
		Hi, <%= request.getAttribute("u") %>
		<a href="/logout">logout</a>
	<% } %>
</div>