<%@page import="java.util.ArrayList"%>
<%@page import="t4.entities.Person"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<form action="/T4ClientProject/T4AdminServlet" method="post">
	
		<select name="selectedPerson">
			<c:forEach items="${allPersons}" var="person">
				<option value="${person.getId()}">Id: "${person.getId()}", Name: "${person.getName()}", Role: "${person.getRole()}"</option>
			</c:forEach>
		</select>
		
		<input class="btn" type=submit value="Edit selected person" />
		<input name="operation" value="editPerson" type="hidden" />
	</form>
	<br>
	<form action="/T4ClientProject/T4AdminServlet" method="post">
		<input class="btn" type=submit value="Create new person" />
		<input name="operation" value="createNewPerson" type="hidden" />
	</form>
</body>
</html>