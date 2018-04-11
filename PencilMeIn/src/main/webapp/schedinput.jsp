<html>
	<head>
		<link rel="stylesheet" href="/style.css">
	</head>

	<body>



		<div class="in"><form action="/enterevent" method="post">
			Name: <div class="eventinput"><textarea name="name" rows="1" cols="30"></textarea></div>
			Day: <div class="eventinput">
				    <select name="day" >      
				    <option>Monday</option>      
				    <option>Tuesday</option>      
				    <option>Wednesday</option>  
				    <option>Thursday </option>
				    <option>Friday </option>
				    <option>Saturday </option>
				    <option>Sunday </option>
				    </select>   
			</div>
			Start time: <div class="eventinput"><input name="start" type="time" step="1800"></div>
			End time: <div class="eventinput"><input name="end" type="time" step="1800"></div>
			<div><input type="submit" value="Post" /></div>
		</form></div>
		
		<br>
		
		<a href="home.jsp">home</a>

	</body>

</html>