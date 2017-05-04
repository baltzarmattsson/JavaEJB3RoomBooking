<%@page import="t4.entities.Role"%>
<%@page import="t4.entities.Person"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Role editor</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: white;">			
	<% 		
		Object roleObj = request.getAttribute("roleSubject");
	
		Role r;
		if (roleObj != null)
			r = (Role)roleObj;
		else
			r = new Role();

		request.setAttribute("roleSubject", r);	
	%>
	
	<div class="container">
	
	<jsp:include page="Header.jsp" />
	<form class="form-group" action="/T4ClientProject/T4AdminServlet" method="post">
		<h3 class="form-heading">${editing ? "Edit" : "Create"} Role</h3>
		
		<label>Name:</label><br>
		<input class="form-control" name="roleName" type=text required value="${roleSubject.getName()}" ${ editing ? "readonly style='background: lightgray;'"  : "" }
				oninvalid="this.setCustomValidity('Name required')"
				oninput="this.setCustomValidity('')"
		/><br>

		<input class="btn btn-primary" type="submit" name=${editing ? "" : "createRole" } ${ editing ? 'disabled' : ''} value="Save" />
		<div style="display:${editing ? 'inline' : 'none'}">
			<input class="btn btn-danger" type="submit" name="deleteRole" value="Delete" />
		</div>
		<input name="operation" value="roleModification" type="hidden"/>

	</form>
	
	<form action="/T4ClientProject/T4AdminServlet" method="post">
		<input class="btn" type="submit" value="Go back"/>
		<input name="operation" value="goToEditorSelectorPage" type="hidden"/>
	</form>
	</div>
</body>
</html>