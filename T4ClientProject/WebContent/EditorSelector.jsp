<%@page import="t4.entities.Person"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="CSS/Main.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: white;">
	
	<div class="container">
	<jsp:include page="Header.jsp" />
	<ul class="nav nav-pills">
		<li class="active"><a data-toggle="pill" href="#personEditorArea">Edit persons and logins</a></li>
		<li><a data-toggle="pill" href="#roleEditorArea">Edit roles</a></li>
	</ul>
		
	<div class="tab-content">
	
		<div id="personEditorArea" class="tab-pane fade in active">
			<h4>Choose person</h4>
			<div class="table-responsive">
			<table id="persontable" class="table table-bordered table-striped">
			<thead>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone number</th>
				<th>Role</th>
				<th>Edit</th>
			</thead>
			<tbody>
			<c:forEach items="${allPersons}" var="person">
				<tr>
					<td>${person.getId()}</td>
					<td>${person.getName()}</td>
					<td>${person.getEmail()}</td>
					<td>${person.getPhoneNbr()}</td>
					<td>${person.getRole() == null ? "" : person.getRole().getName()}</td>
					<td>
						<div align="center">
							<form action="/T4ClientProject/T4AdminServlet" method="post">
								<p data-placement="top" data-toggle="tooltip" title="Edit">
								<button class="btn btn-primary" type="submit" name="editing"><span class="glyphicon glyphicon-pencil"></span></button>
								<input name="selectedPerson" value="${person.getId()}" type="hidden" />
								<input name="operation" value="goToPersonEditPage" type="hidden" />
							</form>
						</div>
				</tr>
			</c:forEach>
			</tbody>
			</table>
				<form action="/T4ClientProject/T4AdminServlet" method="post">
					<input class="btn btn-success" name="creating" type=submit value="Create new person" />
					<input name="operation" value="goToPersonEditPage" type="hidden" />
				</form>
			</div>
		</div>	
		
				
		<div id="roleEditorArea" class="tab-pane">
			<h4>Choose role</h4>
			<div class="table-responsive">
			<table id="roletable" class="table table-bordered table-striped">
			<thead>
				<th>Rolename</th>
				<th>Edit</th>
			</thead>
			<tbody>
			<c:forEach items="${allRoles}" var="role">
				<tr>
				<td>${role.getName()}</td>
				<td>
					<div align="center">
						<form action="/T4ClientProject/T4AdminServlet" method="post">
							<p data-placement="top" data-toggle="tooltip" title="Edit">
							<button class="btn btn-primary" type="submit" name="editing"><span class="glyphicon glyphicon-pencil"></span></button>
							<input name="selectedRole" value="${role.getName()}" type="hidden" />
							<input name="operation" value="goToRoleEditPage" type="hidden" />
						</form>
					</div>
				</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
				<form action="/T4ClientProject/T4AdminServlet" method="post">
					<input class="btn btn-success" name="creating" type=submit value="Create new role" />
					<input name="operation" value="goToRoleEditPage" type="hidden" />
				</form>
				<form action="/T4ClientProject/T4AdminServlet" method="post">
					<input name="operation" value="goToAboutPage" type="hidden" />
				</form>
			</div>
		</div>
	
<!-- Tab content -->
	</div>
		
<!-- Container -->
	</div>
</body>
</html>