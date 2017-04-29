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

<script type="text/javascript">
	function handleRadioClick(id) {
		console.log(id);
		if (id == "radiobtnPerson") {
			document.getElementById("personEditorArea").style.display = "block";
			document.getElementById("roleEditorArea").style.display = "none";
		} else if (id == "radiobtnRole") {
			document.getElementById("personEditorArea").style.display= "none";
			document.getElementById("roleEditorArea").style.display = "block";
		}
	}
	
	window.onload = this.handleRadioClick("personEditorArea");
	
</script>
</head>
<body style="background: white;">
	<h2>Choose editor</h2>
	<input name="editorSelector" id="radiobtnPerson" type=radio value="person" onclick="handleRadioClick(this.id);">Person and login
	<br>
	<input name="editorSelector" id="radiobtnRole" type=radio value="role" onclick="handleRadioClick(this.id);">Role
	<br>
	<br>
	
	<div id="personEditorArea" style="display: none;">
		<form action="/T4ClientProject/T4AdminServlet" method="post">
			<select name="selectedPerson">
				<c:forEach items="${allPersons}" var="person">
					<option value="${person.getId()}">Id: "${person.getId()}", Name: "${person.getName()}", Role: "${person.getRole()}"</option>
				</c:forEach>
			</select>
			
			<input class="btn" name="editing" type=submit value="Edit selected person" />
			<input class="btn" name="creating" type=submit value="Create new person" />
			<input name="operation" value="goToPersonEditPage" type="hidden" />
		</form>
		<br>
	</div>
	
	<div id="roleEditorArea" style="display: none;">
	<form action="/T4ClientProject/T4AdminServlet" method="post">
			<select name="selectedRole">
				<c:forEach items="${allRoles}" var="role">
					<option value="${role.getName()}">Role: ${role.getName()}</option>
				</c:forEach>
			</select>
			<input class="btn" name="editing" type=submit value="Edit selected role" />
			<input class="btn" name="creating" type=submit value="Create new role" />
			<input name="operation" value="goToRoleEditPage" type="hidden" />
		</form>
		<br>
	</div>
</body>
</html>