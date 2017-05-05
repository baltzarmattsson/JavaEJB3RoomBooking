<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Test</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="CSS/Main.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<%
		HashMap<String, String> tests = new HashMap<String, String>();
		tests.put("t4.tests.PersonBeanTest", "t4.tests.PersonBeanTest");
		tests.put("t4.tests.RoleBeanTest", "t4.tests.RoleBeanTest");
		tests.put("t4.tests.LoginBeanTest", "t4.tests.LoginBeanTest");

		request.setAttribute("tests", tests);
		
	%>

	<div class="container">
	<jsp:include page="Header.jsp" />
	<h3>Choose testsuite</h3>
	
	<div class="table-responsive">
	<table id="testtable" class="table table-bordered table-striped">
	<thead>
		<th>Testbean</th>
		<th>Run</th>
	</thead>
	<tbody>
		<c:forEach items="${tests}" var="test">
			<tr>
				<td>${test.getKey()}</td>
				<td>
					<div align="center">
					<form action="TestServlet" method="get" name="SelectTest">
						<button class="btn btn-primary" type="submit">Run test</button>
						<input name="suite" value="${test.getValue()}" type="hidden" />
					</form>
					</div>
				</td>
			</tr>
		</c:forEach>
		<tr>
		
		<tr>
	</tbody>
	</table>
	</div>
	<form action="TestServlet" method="get" name="SelectTest">
		<c:forEach items="${tests}" var="test">
			<input name="suite" value="${test.getValue()}" type="hidden" />
		</c:forEach>
		<input class="btn btn-success" type=submit value="Run all tests" />
	</form>
	
	</div>
</body>
</html>