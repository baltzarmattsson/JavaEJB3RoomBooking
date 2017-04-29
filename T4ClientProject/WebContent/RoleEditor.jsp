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
			Object roleObj = request.getAttribute("roleSubject");
		
			Role r;
			if (roleObj != null)
				r = (Role)roleObj;
			else
				r = new Role();

			request.setAttribute("roleSubject", r);	
		%>
		
		<label>Name:</label><br>
		<input name="roleName" type=text value="${roleSubject.getName()}" ${ editing ? "readonly style='background: lightgray;'"  : "" }><br>

		<input type="submit" name=${editing ? "" : "createRole" } ${ editing ? 'disabled' : ''} value="Save" />
		<div style="display:${editing ? 'inline' : 'none'}">
			<input type="submit" name="deleteRole" value="Delete" />
		</div>
		<input name="operation" value="roleModification" type="hidden"/>

	</form>
	
	<form action="/T4ClientProject/T4AdminServlet" method="post">
		<input class="btn" type="submit" value="Go back"/>
		<input name="operation" value="goToEditorSelectorPage" type="hidden"/>
	</form>
</body>
</html>