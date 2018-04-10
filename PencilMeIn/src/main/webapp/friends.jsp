<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="pencilmein.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

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
		
		
		
		<!--  if there are friend requests -->
		<%
		Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
		ArrayList<User> list = student.getRequests();
		%>
		<h2>Friend requests:</h2>
		<% for (User friend : list){   %>
			<div class="friendreq"><form action="/addfriend" method="post">
				<% friend.getNickname(); %>
				<button type="submit" formmethod="post">Accept</button>
				<button type="submit" formmethod="post">Decline</button>
			</form></div>
			
		<%}
		%>
		
		<h2>Pending requests:</h2>
		<!-- print pending friend requests -->
		
		
		<h2>List of friends:</h2>
		<!-- for each friend
			<p>Name of friend</p> -->
	
	</body>
</html>