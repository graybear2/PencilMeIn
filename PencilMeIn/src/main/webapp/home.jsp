<%@ page import="pencilmein.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
	<head>
		<title>Pencil Me In</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
		
	</head>
	
	<body>
		<div class="linesshort"></div>
		<p class="title">PencilMeIn</p>
	<%
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();
	//System.out.println(user);
	if (user == null){
		response.sendRedirect("/index.jsp");
		return;
	}
	Student student = Student.getStudent(user);
	if (student == null){
		//put them in datastore
		student = Student.createStudent();
		student.save();
		System.out.println("put student in the data store");
	
		//TODO: what should we do with new users? redirect to schedule input?
	}
	
	
	%>
		
		<ul class="list">
			<li> 
				<div class="menu">
					<a href="/home.jsp" class="activehome">Home</a>
					<a href="/schedinput.jsp" class="schedlink">Edit Schedule</a>
					<a href="/friends.jsp" class="friendslink">Manage Friends</a>
					<a href="<%= userService.createLogoutURL("/index.jsp") %>" class="outlink">Log Out</a>
				</div>
			</li>
		    <li> </li>
			<li class="subtitle">Friends:</li>
		    		<%ArrayList<User> friends = student.getFriends();%>
		    		<% 
				for (User friend : friends){   
				pageContext.setAttribute("name", friend.getNickname());
				%>
		    		<li>
					<input type="checkbox" checked="checked">
  					<span class="checkmark"></span>
  					<c:out value="${name}" escapeXml="false" />
		    		</li>
		    		<%
				}
				%>
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

		
		
	

		
		
		
	</body>
</html>