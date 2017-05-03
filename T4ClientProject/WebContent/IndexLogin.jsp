<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="CSS/Login.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>

<body style="background: white;">

  <div class="wrapper">
    <form class="form-signin" action="/T4ClientProject/T4AdminServlet" method="post">       
      <h2 class="form-signin-heading">Admin login</h2>
	<p style="display:${responseLabel != null ? 'inline' : 'none'}">${responseLabel}</p>
      <input type="text" class="form-control" name="username" placeholder="Admin username" required autofocus="" />
      <input type="password" class="form-control" name="password" placeholder="Password" required/>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      <input name="operation" value="loginUser"	type="hidden" />   
    </form>
  </div>
</body>
</html>