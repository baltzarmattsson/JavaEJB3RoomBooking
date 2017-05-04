<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Test</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<jsp:include page="Header.jsp" />
	<h>Testing</h>
		<p> Please choose one of the following tests:<br></p>
		<form action="TestServlet" method="get" name="SelectTest">
			<select>
				<option value="t4.tests.PersonBeanTest">
				t4.tests.PersonBeanTest</option>
				<option value="t4.tests.RoleBeanTest">
				t4.tests.RoleBeanTest</option>
				<option value="t4.tests.LoginBeanTest">
				t4.tests.LoginBeanTest</option>
			</select>
			<div>
			<input class="btn" name="RunTestBean" type=submit value="Run" />
			</div>
		</form>
	</div>

</body>
</html>