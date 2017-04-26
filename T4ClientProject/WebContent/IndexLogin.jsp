<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body style="background: white;">
	<h2>Admin login</h2>
	<form action="/T4ClientProject/T4AdminServlet" method="post">
		<label>Username</label> <br> <input name="username" value="1"/> <br>
		<label>Password</label> <br> <input name="password" value="1" /> <br>
		<input class="btn" name="login" type=submit
			value="Log in" /> 
		<input name="operation" value="loginUser"
			type="hidden" />
	</form>
	
	<% if (request.getAttribute("responseLabel") != null) { %>
	<center><%=request.getAttribute("responseLabel")%></center>
	<% } else { %>
	<center>Responselabel has nuthin</center>
	<% } %>

</body>
</html>