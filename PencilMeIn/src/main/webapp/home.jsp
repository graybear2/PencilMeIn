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
	</head>
	
	<body>
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
		<h1>Welcome!</h1>
		<br>
		<a href="friends.jsp">Manage Friends</a>
		<br>
		
		<p>My Schedule:</p>
		<!-- LIST SCHEDULE EVENTS HERE -->
		
		<%
		//java
        %>
		
		
		
		
		
		<br>
		<a href="schedinput.jsp">Edit Schedule</a>
		<br>
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</body>
</html>