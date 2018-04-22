<%@ page import="pencilmein.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy"%>


<html>
	<head>
		<title>Pencil Me In</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
		
	</head>
	
	<body>
		<div class="lines"></div>
		<p class="title">PencilMeIn</p>
	<%
	Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
	if (student == null){
		//put them in datastore
		student = Student.createStudent();
		student.save();
		System.out.println("put student in the data store");
	
		//TODO: what should we do with new users? redirect to schedule input?
	}
	
	
	%>
	
		<ul class="list">
			<li>Welcome!</li>
			<li><a href="friends.jsp">Manage Friends</a></li>
			<li><a href="schedinput.jsp">Edit Schedule</a></li>
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

		
		
	

		
		
		
	</body>
</html>