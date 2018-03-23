<html>
	<head>
		<link rel="stylesheet" href="/style.css">
	</head>

	<body>
		<div class="in">
			<form action="/enterevent" method="post">
			Name: <div class="eventinput"><textarea name="name" rows="1" cols="30"></textarea></div>
			Day: <div class="eventinput"><textarea name="day" rows="1" cols="10"></textarea></div>
			Start time: <div class="eventinput"><textarea name="start" rows="1" cols="10"></textarea></div>
			End time: <div class="eventinput"><textarea name="end" rows="1" cols="10"></textarea></div>
			<div><input type="submit" value="Post" /></div>
<!-- 		<input type="hidden" name="" value="${fn:escapeXml()}"/> -->
			</form>
		</div>
	</body>
</html>
