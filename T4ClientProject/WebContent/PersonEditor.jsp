<%@page import="t4.entities.Role"%>
<%@page import="t4.entities.Person"%>
<%@page import="t4.entities.Login"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Person editor</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: white;">	
		<% 
			// Person
			Object personObj = request.getAttribute("personSubject");
			Person p;
			if (personObj != null)
				p = (Person)personObj;
			else
				p = new Person();
			request.setAttribute("personSubject", p);
			
			// Login
			Object loginObj = request.getAttribute("loginSubject");
			Login l;
			if (loginObj != null)
				l = (Login)loginObj;
			else 
				l = new Login();
			request.setAttribute("loginSubject", l);
			
		%>
	
	<div class="container">
	<jsp:include page="Header.jsp" />
	<form class="form-group" action="/T4ClientProject/T4AdminServlet" method="post">
		<h3 class="form-heading">${editing ? "Edit" : "Create"} Person</h3>
		
		<label>Id:</label><br>
		<input name="personId" class="form-control" type=text required value="${personSubject.getId()}" ${ editing ? "readonly style='background: lightgray;'"  : "" }
				oninvalid="this.setCustomValidity('ID required')"
				oninput="this.setCustomValidity('')"
		/><br>
		
		<label>Name:</label><br>
		<input name="personName" class="form-control" type=text value="${personSubject.getName()}"/><br>
		
		<label>Email:</label><br>
		<input name="personEmail" class="form-control" type=text value="${personSubject.getEmail()}"/><br>
		
		<label>Phone Number:</label><br>
		<input name="personPhoneNbr" class="form-control" type=text value="${personSubject.getPhoneNbr()}"/><br>
		
		<label>Role</label> <br> 
		<select name="roleName" class="form-control" >
			<option value=null selected></option>
			<c:forEach items="${allRoles}" var="role">
				<option value="${role.getName()}"
					${personSubject.getRole() != null && role.equals(personSubject.getRole()) ? 'selected' : '' }
				>${role.getName()}</option>
			</c:forEach>
		</select> 
		<br> 
		
		<input class="btn btn-primary" type="submit" name=${editing ? "updatePerson" : "createPerson" } value="Save" />
		<div style="display:${editing ? 'inline' : 'none'}">
			<input class="btn btn-danger" type="submit" name="deletePerson" value="Delete" />
		</div>
		<input name="operation" value="personModification" type="hidden"/>

	</form>
	
	<!-- *********************** -->
	<hr>
	<!-- *********************** -->
	
	<div id="loginArea" style="display:${ editing ? 'block' : 'none' }">
		<form id="loginForm" class="form-group" action="/T4ClientProject/T4AdminServlet" method="post">

			<h4 class="form-heading">Login credentials</h4>
			
			<label>Username</label><br>
			<input class="form-control" name="personId" type=text readonly style="background: lightgray;" value="${ personSubject.getId() }" />
						<br>			
			<label>Current password</label>
			<br>
			<input class="form-control" id="password" type=password readonly value="${ loginSubject.getPassword() }">
			<br>			
			<label>New password</label>
			<br>
			<input class="form-control" id="confirmPassword" name="password" type=password required 
					oninvalid="this.setCustomValidity('Password required')"
					oninput="this.setCustomValidity('')"
			/><br>
			
			<label>Confirm password</label>
			<br>
			<input class="form-control" name="confirmPassword" type=password required
					oninvalid="this.setCustomValidity('Password required')"
					oninput="this.setCustomValidity('')"
			/><br>
			
			<input class="btn btn-primary" type="submit" name=${loginExists ? "updateLogin" : "createLogin" } value="Save" />
			<div style="display:${loginExists ? 'inline' : 'none'}">
				<input class="btn btn-danger" type="submit" name="deleteLogin" value="Delete" />
			</div>
			<input name="operation" value="loginModification" type="hidden"/>
		
		</form>
	</div>

	<div class="m-t-2">
		<form action="/T4ClientProject/T4AdminServlet" method="post">
			<input class="btn btn-secondary" type="submit" value="Go back"/>
			<input name="operation" value="goToEditorSelectorPage" type="hidden"/>
		</form>
	</div>
	</div>
</body>
</html>