<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="CSS/Main.css">
	<link rel="stylesheet" href="CSS/Header.css">
</head>
<body>
	
	<div class="table-responsive">
		<table class="table table-borderless" style="border-bottom: 1px solid black;">
		<thead><th></th><th></th></thead>
		<tbody>
		<tr>
		<!-- Logo -->
		<td>
			<img alt="DIBS Logo" src="logo.png" style="width:150px;">
		</td>
		<td></td>
		</tr>
		<form action="/T4ClientProject/T4AdminServlet" method="post" >
		<tr>
		
		
		<td>
		<!-- Home/About/Test -->
			<button class="btn btn-secondary" type="submit" name="goToHomePage">Home</button>
			<button class="btn btn-secondary" type="submit" name="goToAboutPage">About</button>
			<button class="btn btn-secondary" type="submit" name="goToTestPage">Test</button>
		</td>
		<td>
		<!-- Logout button -->
			<div align="right">
			<button class="btn btn-danger" type="submit" name="logoutUser">Logout</button>
			</div>
		</td>
		<input name="operation" value="navbarClick" type="hidden" />
		</form>
		</tr>
		</tbody>
		</table>
	</div>
	
	<!-- Alert danger -->
	<c:if test="${errorMessage != null}">
	<div class="alert alert-danger">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong>Error! </strong>${errorMessage}
	</div>
	</c:if>
	
	<!-- Alert success -->
	<c:if test="${successMessage != null}">
	<div class="alert alert-success">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong>Success! </strong>${successMessage}
	</div>
	</c:if>
	
</body>
</html>