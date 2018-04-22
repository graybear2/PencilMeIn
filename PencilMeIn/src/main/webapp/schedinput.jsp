<%@ page import="pencilmein.Student" %>
<%@ page import="pencilmein.Schedule" %>
<%@ page import="pencilmein.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
	<head>
		<link rel="stylesheet" href="/style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
		
	</head>

	<body>
		<div class="lines"></div>
		<p class="title">Edit Schedule</p>
		
		<ul class="list"> 
			<li> </li>
			<li class="subtitle">Add Event:</li>
			<li>
				<div class="in"><form action="/enterevent" method="post">
				Name: <div class="eventinput"><textarea name="name" rows="1" cols="30"></textarea></div></li>
			
				<li> Day: <div class="eventinput">
				    <select name="day" >      
				    <option>Monday</option>      
				    <option>Tuesday</option>      
				    <option>Wednesday</option>  
				    <option>Thursday </option>
				    <option>Friday </option>
				    <option>Saturday </option>
				    <option>Sunday </option>
				    </select>   
					</div> </li>
				<li> Start time: <div class="eventinput"><input name="start" type="time" step="900"></div></li>
				<li>End time: <div class="eventinput"><input name="end" type="time" step="900"></div></li>
				<li><div><input type="submit" name="add" value="Post" /></div>
				</form></div>
			</li>
			<li> </li>
			<li class="subtitle">My Schedule:</li>
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
			
		
		
		</ul>
		
		<%
		Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
		Schedule sched =  student.getSchedule();
		ArrayList<Event> events = sched.getEvents();
		    		
				for (Event event : events){   
					%>
		    				<c:out value="${name} ${day}" escapeXml="false" />		    				
		    			<%
				}
		%> 
		
		
		
		
		<!-- LIST SCHEDULE EVENTS HERE -->
			<ul class="weekdays">
			<li>  </li>
			<li>Mo</li>
			<li>Tu</li>
			<li>We</li>
			<li>Th</li>
			<li>Fr</li>
			<li>Sa</li>
			<li>Su</li>
		</ul>
		
		
		<ul class="days">
		<%
			for (int hours = 0; hours < 24; hours++){
				%> <li>time</li>  <%
				for (int mins = 0; mins < 4; mins++){
					for (int days = 0; days < 7; days++){
						if (days==4) {	//if event at this time
							%> <li class="busy">day</li>
						<% }
						else{
							%> <li class="free">day</li>
						<% }
					}
					%> <li></li> <%
				}
				%> <!-- <hr> --> <%
			}
        %>
        
        </ul>

	</body>

</html>