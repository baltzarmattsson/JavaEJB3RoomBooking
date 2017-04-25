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
		<label>Username</label> <br> <input class="usernameInput" /> <br>
		<label>Password</label> <br> <input class="passwordInput" />
		<br>
		<input class="adminLoginButton" name=login type=submit value="Log in"/>
		
		<input name="operation" value="loginUser" type="hidden"/>
	</form>
</body>
</html>