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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/editorselector.js"></script>
<script>

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
	
	window.onload = function() {
		this.handleRadioClick("radiobtnPerson");
		document.getElementById("radiobtnPerson").checked = true;
	}
	
</script>
</head>
<body style="background: white;">
	<jsp:include page="Header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h4>Choose person</h4>
				<div class="table-responsive">
					
	<table id="persontable" class="table table-bordered table-striped m-t-5">
	<thead>
	
		<th>Id</th>
		<th>Name</th>
		<th>Email</th>
		<th>Phone number</th>
		<th>Role</th>
		<th>Edit</th>
		<th>Delete</th>
	</thead>
	<tbody>
		<c:forEach items="${allPersons}" var="person">
			<tr>
				<td>${person.getId()}</td>
				<td>${person.getName()}</td>
				<td>${person.getEmail()}</td>
				<td>${person.getPhoneNbr()}</td>
				<td>${person.getRole() == null ? "" : person.getRole().getName()}</td>
				<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button id="${person.getId()}"  class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span></button></p></td>
				<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></button></p></td>	
			</tr>
		</c:forEach>
	</tbody>
	</table>
					
				</div>
			</div>	
		</div>
	</div>
	
	<!-- Trigger the modal with a button -->
<button type="button" id="bubbbntotn" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <p>Some text in the modal.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	
	
	
	
	
	
	
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