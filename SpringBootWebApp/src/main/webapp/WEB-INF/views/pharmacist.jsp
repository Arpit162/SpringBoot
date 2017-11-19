<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
	<h1>Dashboard for pharmacist</h1>
	<hr>
	<div class="topright">
		<form action="logout" method="get">
			<button id="submitButton" type="submit">LOGOUT</button>
		</form>
	</div>
	<h2>
		Hello 
		<c:out value="${member.personName}" /> !!!
	</h2>
	<h2>Profiles to visit.</h2>
	<%!int i = 1;
	String requesType = null;%>
	<table>
		<tr>
			<td width="50">Id</td>
			<td width="150">PatientName</td>
			<td width="150">Treatment</td>
			<td width="100">Role</td>
			<td width="50">BirthYear</td>
			<td width="100">Record</td>
			<td width="50">Prescription</td>
			<td width="50">Action</td>
		</tr>
		<c:forEach items="${records}" var="ele">
			<%
				i++;
			%>
			<form id="userForm<%=i%>" action="userRequest" method="post">
				<tr>
					<td><%=i%></td>
					<td><input type="text" name="approverName"
						value=<c:out value="${ele.approverName}"/> readonly></td>
					<td><input type="text" name="speciality"
						value=<c:out value="${ele.speciality}"/> readonly></td>
					<td><input type="text" name="role"
						value=<c:out value="${ele.role}"/> readonly></td>
					<td><input type="text" name="yob"
						value=<c:out value="${ele.yob}"/> readonly></td>
					<c:choose>
						<c:when test="${ele.requestType eq 'requested'}">
							<td>*Blocked</td>
							<td>*Blocked</td>
							<td>Requested</td>
						</c:when>
						<c:when test="${ele.requestType eq 'approved'}">
							<td><input type="text" name="record"
								value=<c:out value="${ele.record}"/> readonly></td>
							<td><input type="text" name="prescription"
								value=<c:out value="${ele.prescription}"/> readonly></td>
							</td>
							<td>Approved</td>
						</c:when>
						<c:otherwise>
							<td>*Blocked</td>
							<td>*Blocked</td>
							<td><button id="submitButton" type="submit">Request</button></td>
						</c:otherwise>
					</c:choose>
					<td><input type="hidden" name="requesterName"
						value=<c:out value="${member.personName}" />></td>
				</tr>
			</form>
		</c:forEach>
	</table>
	<i>*To see the record and prescription you have request the person.</i>

</body>
</html>