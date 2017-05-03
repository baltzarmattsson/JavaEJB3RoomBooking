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
<title>Insert title here</title>
</head>
<body style="background: white;">
	<jsp:include page="Header.jsp" />
	<h2>${editing ? "Edit" : "Create"} Person</h2>
				
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
		
	<form action="/T4ClientProject/T4AdminServlet" method="post">
				
		<label>Id:</label><br>
		<input name="personId" type=text value="${personSubject.getId()}" ${ editing ? "readonly style='background: lightgray;'"  : "" }><br>
		
		<label>Name:</label><br>
		<input name="personName" type=text value="${personSubject.getName()}"><br>
		
		<label>Email:</label><br>
		<input name="personEmail" type=text value="${personSubject.getEmail()}"><br>
		
		<label>Phone Number:</label><br>
		<input name="personPhoneNbr" type=text value="${personSubject.getPhoneNbr()}"><br>
		
		<label>Role</label> <br> 
		<select name="roleName">
			<option value=null selected></option>
			<c:forEach items="${allRoles}" var="role">
				<option value="${role.getName()}"
					${personSubject.getRole() != null && role.equals(personSubject.getRole()) ? 'selected' : '' }
				>${role.getName()}</option>
			</c:forEach>
		</select> 
		<br> 
		
		<input type="submit" name=${editing ? "updatePerson" : "createPerson" } value="Save" />
		<div style="display:${editing ? 'inline' : 'none'}">
			<input type="submit" name="deletePerson" value="Delete" />
		</div>
		<input name="operation" value="personModification" type="hidden"/>

	</form>
	<!-- *********************** -->
	<hr>
	<!-- *********************** -->
	
	<div id="loginArea" style="display:${ editing ? 'block' : 'none' }">
		<form action="/T4ClientProject/T4AdminServlet" method="post">

			<h2>Login credentials</h2>
			
			<label>Username</label>
			<br>
				<input name="personId" type=text readonly style="background: lightgray;" value="${ personSubject.getId() }" />
						<br>			
			<label>Current password</label>
			<br>
			<input type=password readonly value="${ loginSubject.getPassword() }">
			<br>			
			<label>New password</label>
			<br>
			<input name="password" type=password>
			<br>
			<label>Confirm password</label>
			<br>
			<input name="confirmPassword" type=password>
			<br>
			
			<input type="submit" name=${loginExists ? "updateLogin" : "createLogin" } value="Save" />
			<div style="display:${loginExists ? 'inline' : 'none'}">
				<input type="submit" name="deleteLogin" value="Delete" />
			</div>
			<input name="operation" value="loginModification" type="hidden"/>
		
		</form>
	</div>

	<form action="/T4ClientProject/T4AdminServlet" method="post">
		<input class="btn" type="submit" value="Go back"/>
		<input name="operation" value="goToEditorSelectorPage" type="hidden"/>
	</form>

</body>
</html>