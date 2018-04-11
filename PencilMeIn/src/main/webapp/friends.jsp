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
	</head>
	
	<body class="friends">
		<h1> Manage Friends </h1>
		<br>
		
		<!--  ADD FRIENDS -->
		<div class="addfriend"><form action="/addfriend" method="post">
			Friend's Email: <div class="eventinput"><textarea name="email" rows="1" cols="30"></textarea></div>
			<div class="eventinput"><input type="submit" value="Post" /></div>
		</form></div>
		<p>${message}</p>
		
		
		
		<!--  if there are friend requests -->
		<%
		Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
		ArrayList<User> requests = student.getRequests();
		%>
		<h2>Friend requests:</h2>
		<% for (User friend : requests){   %>
			<div class="friendreq"><form action="/addfriend" method="post">
				<%
					pageContext.setAttribute("name", friend.getNickname());
					pageContext.setAttribute("email", friend.getEmail());
				%>
				<c:out value="${name}" escapeXml="false" />
				<button type="submit" formmethod="post" name="accept" value="${email}">Accept</button>
				<button type="submit" formmethod="post" name="decline" value="${email}">Decline</button>
			</form></div>
			
		<%}
		%>
		
		<h2>Pending requests:</h2>
		<!-- print pending friend requests -->
		
		<%ArrayList<User> friends = student.getFriends();%>
		<h2>List of friends:</h2>
		<% 
			for (User friend : friends){   
				pageContext.setAttribute("name", friend.getNickname());
		%>
		<c:out value="${name}" escapeXml="false" />
		<%
			}
		%>
			
	
	</body>
</html>