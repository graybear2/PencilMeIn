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
<%@ page import="java.util.HashMap"%>


<html>
	<head>
		<link rel="stylesheet" href="/style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
		
	</head>

	<body>
		<% UserService userService = UserServiceFactory.getUserService(); %>
		<div class="linessched"></div>
		<p class="title"><a class="titlelink" href="/home.jsp">PencilMeIn</a></p>
		
		<ul class="list"> 
			<li> 
				<div class="menu">
					<a href="/home.jsp" class="homelink">Home</a>
					<a href="/schedinput.jsp" class="activesched">Edit Schedule</a>
					<a href="/friends.jsp" class="friendslink">Manage Friends</a>
					<a href="<%= userService.createLogoutURL("/index.jsp") %>" class="outlink">Log Out</a>
				</div>
			</li>
			<li> </li>
			<li class="subtitle">Add Event:</li>
			<li>
				<div class="in"><form action="/enterevent" method="post">
				Name:<div class="entername"><textarea name="name" rows="1" cols="30"></textarea></div> <div class="eventinput">&nbsp</div>
				Start time:<div class="eventinput"><input name="start" type="time" step="900"></div> <div class="eventinput">&nbsp</div>
				End time:<div class="eventinput"><input name="end" type="time" step="900"></div></li>
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
						<label for="saturday">Sa </label> <div class="eventinput">&nbsp</div>
				<input type="submit" name="add" value="Add Event" class="addeventbutton"/></div>
				</form></div>
			</li>
			<li> </li>
			<li class="subtitle">My Schedule:</li>
			<li> </li>	
		</ul>
		
		<%
			Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
			Schedule sched =  student.getSchedule();
			ArrayList<Event> events = sched.getEvents();
			
			HashMap<Integer, String> combinedEvents = new HashMap<Integer, String>();
			
			for(Event e: events) {
			    System.out.println(e.getName());
			    for(Integer i : e.getTimes()) {
			        combinedEvents.put(i, e.getName());
			        System.out.println(i);
			    }
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
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(new Date());
			int realTime = cal.get(Calendar.HOUR_OF_DAY);
			realTime -= 5;
			if (realTime < 0) realTime += 24;
			for (int hours = 0; hours < 24; hours++){
				if (realTime < 12){
					if(realTime == 0) {realTime = 12;}
					pageContext.setAttribute("time", "<li>" + realTime + ":00am</li>");
					if(realTime == 12) {realTime = 0;}
				}
				else{
					if(realTime != 12) {realTime -= 12;}
					pageContext.setAttribute("time", "<li>" + realTime + ":00pm</li>");
					if(realTime != 12) {realTime += 12;}
				}
				%> <c:out value="${time}" escapeXml="false"/>  <%
				        
      
				
				for (int mins = 0; mins < 4; mins++){
					for (int days = 0; days < 7; days++){
						if (combinedEvents.containsKey(days * 10000 + realTime * 100 + mins * 15)) {							//if event at this time
							%>
							<li class="busy">
								<%
								pageContext.setAttribute("eventName", combinedEvents.get(days * 10000 + realTime * 100 + mins * 15));
								%>
								<c:out value="${eventName}" escapeXml="false"/>
							</li>
						<% }
						else{
							%> <li class="free"></li>
						<% }
					}
					%> <li></li> <%
				}
				%> <hr> <%
				realTime = (realTime+1) % 24;
			}
        %>
        
        </ul>

	</body>

</html>