<%@page import="java.util.ArrayList"%>
<%@page import="t4.entities.Role"%>
<%@page import="t4.entities.Person"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background: white;">
	<h2>Create role</h2>
	<form action="/T4ClientProject/T4AdminServlet" method="post">
		
			
		<% 		
			Role r = new Role();
			request.setAttribute("roleSubject", r);	
		%>
		
		<label>Name:</label><br>
		<input name="personId" type=text value="${personSubject.getId()}" ${ editing ? "readonly style='background: lightgray;'"  : "" }><br>

		<input type="submit" name=${editing ? "" : "createRole" } disabled=${ editing } value="Save" />
		<div style="display:${editing ? 'inline' : 'none'}">
			<input type="submit" name="deleteRole" value="Delete" />
		</div>
		<input name="operation" value="roleModification" type="hidden"/>

	</form>
	
	<!-- *********************** -->
	<hr>
	<!-- *********************** -->

	<h2>Login password</h2>
	<label>New password</label>
	<br>
	<input type=password>
	<br>
	<label>Confirm password</label>
	<br>
	<input type=password>
	<br>
	<input type=button value=Save>
	<input type=button value=Delete>


	<form action="/T4ClientProject/T4AdminServlet" method="post">
		<input class="btn" type="submit" value="Go back"/>
		<input name="operation" value="goToPersonSelectorPage" type="hidden"/>
	</form>

</body>
</html>