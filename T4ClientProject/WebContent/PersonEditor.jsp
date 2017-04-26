<%@page import="java.util.ArrayList"%>
<%@page import="t4.entities.Role"%>
<%@page import="t4.entities.Person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background: white;">
	<h2>
	<%=request.getAttribute("mode")%> Person</h2>
	<form action="/T4ClientProject/T4AdminServlet" method="post">
		
			
		<% boolean editing = 
			((String)request.getAttribute("mode")).equalsIgnoreCase("edit");

			ArrayList<Role> allRoles = (ArrayList<Role>)request.getAttribute("allRoles");
		/**/
			Object personObj = request.getAttribute("personToEdit");
			Person p;
			if (personObj != null) {
				p = (Person)personObj;
			} else {
				p = new Person();
			}
			
			//Person p = new Person();
			String id = p.getId() == null ? "" : p.getId();
			String name = p.getName() == null ? "" : p.getName();
			String email = p.getEmail() == null ? "" : p.getEmail();
			String phoneNbr = p.getPhoneNbr() == null ? "" : p.getPhoneNbr();
			Role role = p.getRole();
			
		%>
		
		<label>Id:</label><br>
		<input name="personId" type=text value=<%=id%>><br>
		
		<label>Name:</label><br>
		<input name="personName" type=text value=<%=name%>><br>
		
		<label>Email:</label><br>
		<input name="personEmail" type=text value=<%=email%>><br>
		
		<label>Phone Number:</label><br>
		<input name="personPhoneNbr" type=text value=<%=phoneNbr%>><br>
		
		<label>Role</label> <br> 
		<select name="roleName">
			<% for (Role r : allRoles) { %>
				<option selected=<%=r.equals(role)%>
					<%=r.getName()%>><%=r.getName()%></option>			
			<% } %>
		</select> 
		<br> 
		
		<input type=button value=Save>
		<input type=button value=Delete>

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


</body>
</html>