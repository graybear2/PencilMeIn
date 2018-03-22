<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
	<head>
		<title>Pencil Me In Log In</title>
		<link rel="stylesheet" href="/style.css">
	</head>
	
	<body>
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    String userName = "";
		    if (user != null) {
				response.sendRedirect("/home.jsp");
		    }
		    else{
		    	
		%>
		<p>Welcome to PencilMeIn</p>
		<p> <a href="<%=userService.createLoginURL("/home.jsp") %>">Log in </a> to see your home page</p>	
		<%
		    }
		%>			
	</body>
</html>