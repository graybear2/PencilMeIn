<%@ page import="pencilmein.Student" %>
<%@ page import="pencilmein.Schedule" %>
<%@ page import="pencilmein.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar"%>


<html>
	<head>
		<link rel="stylesheet" href="/style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
		
	</head>

	<body>
		<% UserService userService = UserServiceFactory.getUserService(); %>
		<div class="lines"></div>
		<p class="title">Edit Schedule</p>
		
		<a href="home.jsp" class="homelink">Home</a>
		<a href="<%= userService.createLogoutURL("/index.jsp") %>" class="outlink">Log Out</a>
		
		<ul class="list"> 
			<li> </li>
			<li class="subtitle">Add Event:</li>
			<li>
				<div class="in"><form action="/enterevent" method="post">
				Name: <div class="eventinput"><textarea name="name" rows="1" cols="30"></textarea></div>
				Start time: <div class="eventinput"><input name="start" type="time" step="900"></div>
				End time: <div class="eventinput"><input name="end" type="time" step="900"></div></li>
				<li> Day:<div class="eventinput">
				    <input type="checkbox" id="sunday" name="sunday">
						<label for="sunday">Su </label>
					<input type="checkbox" id="monday" name="monday">
						<label for="monday">Mo </label>
					<input type="checkbox" id="tuesday" name="tuesday">
						<label for="tuesday">Tu </label>
					<input type="checkbox" id="wednesday" name="wednesday">
						<label for="wednesday">We </label>
					<input type="checkbox" id="thursday" name="thursday">
						<label for="thursday">Th </label>
					<input type="checkbox" id="friday" name="friday">
						<label for="friday">Fr </label>
					<input type="checkbox" id="saturday" name="saturday">
						<label for="saturday">Sa </label>
				<input type="submit" name="add" value="Add Event" /></div>
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
		/* Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
		Schedule sched =  student.getSchedule();
		ArrayList<Event> events = sched.getEvents(); */
		    		
	//			for (Event event : events){   
					%>
	<!--     				<c:out value="${name} ${day}" escapeXml="false" />		    				
	 -->		    			<%
	//			}
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
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(new Date());
			int start = cal.get(Calendar.HOUR_OF_DAY);
			int leftBound = 12-start;
			int rightBound = 23-start;
			for (int hours = 0; hours < 24; hours++){
				int t = (hours+start)%12;
				if (t==0)
					t=12;
				if (hours < leftBound || hours > rightBound){
					pageContext.setAttribute("time", "<li>" + t + ":00am</li>");
					if(t==12)
						t=0;
				}
				else{
					pageContext.setAttribute("time", "<li>" + t + ":00pm</li>");
					if(t != 12)
						t += 12;
				}
				%> <c:out value="${time}" escapeXml="false"/>  <%
				for (int mins = 0; mins < 4; mins++){
					for (int days = 0; days < 7; days++){
						if (days==4) {							//if event at this time
							%> <li class="busy">day</li>
						<% }
						else{
							%> <li class="free">day</li>
						<% }
					}
					%> <li></li> <%
				}
				%> <hr> <%
			}
        %>
        
        </ul>

	</body>

</html>