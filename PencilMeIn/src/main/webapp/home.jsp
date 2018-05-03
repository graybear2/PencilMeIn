<%@ page import="pencilmein.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="static com.googlecode.objectify.ObjectifyService.ofy"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.HashMap"%>



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
	final boolean DEBUG = true;
	
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
		    
			<div class="selectfriends">
				<form action="/mergesched" method="post">
					<%
					ArrayList<User> friends = student.getFriends();
		    		int numFriends = 0;
					for (User friend : friends){  
						pageContext.setAttribute("name", friend.getNickname());
						%>
	    				<li>
	    					<input type="checkbox" name="<%=numFriends%>" value="<%=friend.getEmail()%>"/>
							<label for="friend"> <c:out value="${name}" escapeXml="false" /> </label>
	    				</li>
	    				<%
						numFriends++;
					}					
					%>
					<li> 
					<input type="hidden" name="numFriends" value="<%=numFriends%>" />
					<input type="submit" name="merge" value="Generate Schedule" /> </li>
				</form> </div>
					
				<%
		        HashMap<Integer, Integer> overlappingSchedule = (HashMap<Integer, Integer>)request.getAttribute("mergedMap");
				Integer numSelectedFriends = (Integer)request.getAttribute("numSelectedFriends");
				
				if(DEBUG) {
				    System.out.println("I'm here");
				}
				
		        if (overlappingSchedule != null) {
		            for(Integer i : overlappingSchedule.values()) {
		                System.out.println(i);
		            }
		            System.out.println(overlappingSchedule.values());
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
					    if(overlappingSchedule != null) {
					        if (overlappingSchedule.containsKey(days * 10000 + realTime * 100 + mins * 15)) {							//if event at this time
								%>
								<li class="<%= pencilmein.Schedule.howBusy(numSelectedFriends, overlappingSchedule.get(days * 10000 + realTime * 100 + mins * 15)) %>">
									<%
									if(DEBUG) {
									    System.out.println("num friends" + numSelectedFriends.intValue());
									}
									pageContext.setAttribute("eventName", overlappingSchedule.get(days * 10000 + realTime * 100 + mins * 15) + " busy");
									%>
									<c:out value="${eventName}" escapeXml="false"/>
								</li>
							<% }
							else{
								%> <li class="free"></li>
							<% }
					    }
						
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

