<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background: white;">
	<h2>Choose editor</h2>
	<input name=radiobtn type=radio value="person">Person and login
	<br>
	<input name=radiobtn type=radio value="role">Role
	<br>
	<br> Edit
	<br>
	<select name="selectObjects">
		<option value="" selected>Select...</option>
	</select>
	<br>
	<input name=btn type=button value="Create new">
</body>
</html>