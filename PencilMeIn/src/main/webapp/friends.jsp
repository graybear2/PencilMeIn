<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="pencilmein.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Pencil Me In</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
	</head>
	
	<body class="friends">
		<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null){
			response.sendRedirect("/index.jsp");
			return;
		}
		%>
	
		<div class="lines"></div>
		<p class="title">Manage Friends</p>
		
		<a href="home.jsp" class="homelink">Home</a>
		<a href="<%= userService.createLogoutURL("/index.jsp") %>" class="outlink">Log Out</a>
		
		
		
		<ul class="list">
			<li> </li>
			<li class="subtitle">Add a friend:</li>
			<li> 
				<div class="addfriend"><form action="/addfriend" method="post">
					Friend's Email: <div class="eventinput"><textarea name="email" rows="1" cols="30"></textarea></div>
					<div class="eventinput"><input type="submit" value="Post" /></div>
				</form></div>
			</li>
			<li class="msg">${message}</li>
			<li class="subtitle">Friend Requests:</li>
				<%
				Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
				ArrayList<User> requests = student.getRequests();
				for (User friend : requests){ 
				%>
					<li><div class="friendreq"><form action="/addfriend" method="post">
					<%
					pageContext.setAttribute("name", friend.getNickname());
					pageContext.setAttribute("email", friend.getEmail());
					%>
					<c:out value="${name}" escapeXml="false" />
					<button type="submit" formmethod="post" name="accept" value="${email}">Accept</button>
					<button type="submit" formmethod="post" name="decline" value="${email}">Decline</button>
					</form></div></li>
				<%}
				%>	
		    <li> </li>
		    <li class="subtitle">Friends:</li>
		    		<%ArrayList<User> friends = student.getFriends();%>
		    		<% 
				for (User friend : friends){   
				pageContext.setAttribute("name", friend.getNickname());
				%>
		    		<li> <c:out value="${name}" escapeXml="false" /> </li>
		    		<%
				}
				%>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		    <li> </li>
		</ul>
		
	
	
			
	
	</body>
</html>