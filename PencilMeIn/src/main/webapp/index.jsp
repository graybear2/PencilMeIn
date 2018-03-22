<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
	<head>
		<title>Pencil Me In Log In</title>
		<link href="https://fonts.googleapis.com/css?family=Cuprum|Quicksand|Cookie" rel="stylesheet">
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
		<p> <a href="<%=userService.createLoginURL("/home.jsp") %>">Log in </a> bitch</p>	
		<%
		    }
		%>			
	</body>
</html>