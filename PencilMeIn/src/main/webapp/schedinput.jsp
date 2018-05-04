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

<%
	boolean[] daysOfWeek = {false, false, false, false, false, false, false, false};
	Student student = Student.getStudent(UserServiceFactory.getUserService().getCurrentUser());
	Schedule sched =  student.getSchedule();
	ArrayList<Event> events = sched.getEvents();
	
	HashMap<Integer, Event> combinedEvents = new HashMap<Integer, Event>();
	HashMap<Event, ArrayList<Boolean>> displayedEvents = new HashMap<Event, ArrayList<Boolean>>();
	
	for(Event e: events) {
	    //System.out.println(e);
	    for(Integer i : e.getTimes()) {
	        combinedEvents.put(i, e);
	        //System.out.println(i);
	    }
	    ArrayList<Boolean> arr = new ArrayList<Boolean>();
	    for(int day=0; day<7; day++)
	    	arr.add(false);
	    displayedEvents.put(e, arr);
	}
%> 
<html>
	<head>
		<link rel="stylesheet" href="/style.css">
		<link href="https://fonts.googleapis.com/css?family=Homemade+Apple|Raleway" rel="stylesheet">
		<script type="text/javascript">
			function editEvent(keyTime){
				var outString = "schedinput.jsp?keyTime=" + keyTime
                location.href = outString
                <%
                	Event editEvent = null;
                	if(request.getParameter("keyTime") != null){
	                	int editTime = Integer.parseInt(request.getParameter("keyTime"));
	                	//System.out.println("we out here: " + editTime);
	                	editEvent = combinedEvents.get(editTime);	                	
                	}
                	if(editEvent != null){
	                	pageContext.setAttribute("editEvent", String.valueOf(editEvent.getId()));
	                	System.out.println("set name as: " + String.valueOf(editEvent.getId()));
                	}
                %>
			}
			
			function cancelEdit(){
				location.href = "schedinput.jsp"
			}
		</script>
		
	</head>

	<body id="topOfPage">
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
				<%if(editEvent == null){ %>
					<div class="in"><form action="/enterevent" method="post">
					<input type="text" name="editEvent" style="display:none" value="${editEvent}" />
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
				<%}else{ %>
					<div class="in"><form action="/enterevent" method="post">
					<%
						pageContext.setAttribute("eventName", editEvent.getName());
						//System.out.println(editEvent.getName());
						int startTime=0;
						int endTime=0;
						for(int i=0; i<daysOfWeek.length; i++){
							for(Integer t: editEvent.getTimes()){
								if(i==0 && t<10000){
									daysOfWeek[i] = true;
									startTime = t%10000;
									break;
								}
								else if(i != 0 && t/(i*10000) == 1 && t%(i*10000) < 10000){
									daysOfWeek[i] = true;
									startTime = t%10000;
									break;
								}
							}
						}
						endTime = editEvent.getTimes().last()%10000+15;
						if(endTime%100 == 60){
							endTime += 100;
							endTime -= 60;
							if(endTime == 2400)
								endTime = 0;
						}
						//System.out.println("startTime: " + startTime);
						String startTimeString = "";
						String endTimeString = "";
						if(startTime/100 < 10)
							startTimeString = "0"+String.valueOf(startTime/100)+":"+String.valueOf(startTime%100);
						else
							startTimeString = String.valueOf(startTime/100)+":"+String.valueOf(startTime%100);
						if(startTime%100 == 0)
							startTimeString += "0";
						
						if(endTime/100 <10)
							endTimeString = "0"+String.valueOf(endTime/100)+":"+String.valueOf(endTime%100);
						else
							endTimeString = String.valueOf(endTime/100)+":"+String.valueOf(endTime%100);
						if(endTime%100 == 0)
							endTimeString += "0";
						
						pageContext.setAttribute("startTime", startTimeString);
						pageContext.setAttribute("endTime", endTimeString);
					%>
					<input type="text" name="editEvent" style="display:none" value="${editEvent}" />
					Name: <div class="eventinput"><input type="text" name="name" value="${eventName}"></input></div>					
					Start time: <div class="eventinput"><input name="start" type="time" step="900" value="${startTime}"></div>
					End time: <div class="eventinput"><input name="end" type="time" step="900" value="${endTime}"></div></li>
					<li> Day:<div class="eventinput">
						<%if(daysOfWeek[0]){ %>
					    <input type="checkbox" id="sunday" name="sunday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="sunday" name="sunday">
					   	<% }%>
							<label for="sunday">Su </label>
							
						<%if(daysOfWeek[1]){ %>
					    <input type="checkbox" id="monday" name="monday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="monday" name="monday">
					   	<% }%>
							<label for="monday">Mo </label>
							
						<%if(daysOfWeek[2]){ %>
					    <input type="checkbox" id="tuesday" name="tuesday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="tuesday" name="tuesday">
					   	<% }%>
							<label for="tuesday">Tu </label>
							
						<%if(daysOfWeek[3]){ %>
					    <input type="checkbox" id="wednesday" name="wednesday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="wednesday" name="wednesday">
					   	<% }%>
							<label for="wednesday">We </label>
							
						<%if(daysOfWeek[4]){ %>
					    <input type="checkbox" id="thursday" name="thursday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="thursday" name="thursday">
					   	<% }%>
							<label for="thursday">Th </label>
							
						<%if(daysOfWeek[5]){ %>
					    <input type="checkbox" id="friday" name="friday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="friday" name="friday">
					   	<% }%>
							<label for="friday">Fr </label>
							
						<%if(daysOfWeek[6]){ %>
					    <input type="checkbox" id="saturday" name="saturday" checked>
					   	<%}else{ %>
					   	<input type="checkbox" id="saturday" name="saturday">
					   	<% }%>
							<label for="saturday">Sa </label>
					<input type="submit" name="remove" value="Remove Event"/>
					<input type="submit" name="add" value="Overwrite Event" />
					<input type="button" onclick="cancelEdit();" value="Cancel"/></div>
					</form></div>
				<%} %>
			</li>
			<li> </li>
			<li class="subtitle">My Schedule:</li>
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
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
			<li> </li>
		</ul>
		
		<!-- LIST SCHEDULE EVENTS HERE -->
		<div class="scheduleforediting">
		<ul class="weekdays">
			<li>  </li>
			<li>Su</li>
			<li>Mo</li>
			<li>Tu</li>
			<li>We</li>
			<li>Th</li>
			<li>Fr</li>
			<li>Sa</li>
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
						if (combinedEvents.containsKey(days * 10000 + realTime * 100 + mins * 15)) { //if event at this time
							%>
							<li class="busy">
								<%
								int keyTime = days * 10000 + realTime * 100 + mins * 15;
								Event event = combinedEvents.get(keyTime);
								if(!displayedEvents.get(event).get(days)){
									pageContext.setAttribute("eventName", event.getName());
									displayedEvents.get(event).set(days, true);
								%>
								<a href="#topOfPage" onclick="editEvent(<%=keyTime%>);"> <c:out value="${eventName}" escapeXml="false"/> </a>
								<%} else{%> &nbsp <%} %>
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
        </div>

	</body>

</html>