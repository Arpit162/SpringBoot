<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/resources/js/app.js"></script>
<style>
.topright {
	position: absolute;
	top: 5px;
	right: 5px;
	text-align: right;
}
</style>
<title>Spring Boot</title>
</head>
<body>
	<h1>Dashboard for patient</h1>
	<hr>
	<div class="topright">
		<form action="logout" method="get">
			<button id="submitButton" type="submit">LOGOUT</button>
		</form>
	</div>
	<h2>
		Hello <c:out value="${member.personName}" /> !!!
	</h2>
	<h2>Requests to approve</h2>
	<%!int i = 1;%>
	<table>
		<tr>
			<td width="50">Id</td>
			<td width="150">RequesterName</td>
			<td width="150">Speciality</td>
			<td width="100">Role</td>
			<td width="50">BirthYear</td>
			<td width="50">Action</td>
		</tr>
		<c:forEach items="${records}" var="ele">
			<%
				i++;
			%>
			<form id="userForm<%=i%>" action="userApproval" method="post">
				<tr>
					<td><%=i%></td>
					<td><input type="text" name="requesterName"
						value=<c:out value="${ele.requesterName}"/> readonly></td>
					<td><input type="text" name="speciality"
						value=<c:out value="${ele.speciality}"/> readonly></td>
					<td><input type="text" name="role"
						value=<c:out value="${ele.role}"/>  readonly></td>
					<td><input type="text" name="yob"
						value=<c:out value="${ele.yob}"/> readonly></td>
					<td><button id="submitButton" type="submit">Approve</button></td>
					 <td><input type="hidden" name="approverName" value=<c:out value="${member.personName}" />></td>
				</tr>
			</form>
		</c:forEach>
	</table>


</body>
</html>