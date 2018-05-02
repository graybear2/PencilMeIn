<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
	<head>
		<title>Pencil Me In Log In</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
	</head>
	
	<body>
		<div class="linesshort"></div>
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    String userName = "";
		    if (user != null) {
				response.sendRedirect("/home");
		    }
		    else{
		    	
		%>
		<p class="title">PencilMeIn</p>
		<ul class="list">
			<li> </li>
		    <li><a href="<%=userService.createLoginURL("/home") %>">Log in</a> to see your home page	</li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
	  	</ul>
		<%
		    }
		%>			
	</body>
</html>