<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Static content -->
<link rel="stylesheet" href="/resources/css/style.css">
<script type="text/javascript" src="/resources/js/app.js"></script>

<title>Spring Boot</title>
</head>
<body>
	<h1>Spring Boot - MVC web application example</h1>
	<hr>

	<div class="form21">
		<form action="adduser" method="post" onsubmit="return validateForm()">
			<div>
				<h1>Personal Information</h1>
			</div>
			<div>
				<tabel>
				<tr>
					<label>Name</label>
					<input id="requesterName" name="personName" value="" type="text">
				</tr>
				<tr>
					<label>Birth year</label>
					<input id="yob" name="personYob" value="" type="text">
				</tr>
				<tr>
					<div>
						<tabel>
						<tr>
							<label>Role</label>
						</tr>
						<tr>
							<td><input type="radio" id="role" name="personRole"
								value="patient"
								checked> <label for="contactChoice1">PATIENT</label></td>

							<td><input type="radio" id="role" name="personRole" value="doctor"
								onclick="document.getElementById('record').disabled = true;document.getElementById('prescription').disabled = true;">
								<label for="contactChoice2">DOCTOR</label></td>

							<td><input type="radio" id="role" name="personRole"
								value="pharmacist"
								onclick="document.getElementById('record').disabled = true;document.getElementById('prescription').disabled = true;">
								<label for="contactChoice3">PHARMACIST</label></td>
						</tr>
						</tabel>
					</div>
				</tr>
				<tr>
					<label>Records</label>
					<input id="record" name="record" value="" type="text">
				</tr>
				<tr>
					<label>Prescriptions</label>
					<input id="prescription" name="prescription" value="" type="text">
				</tr>
				<tr>
					<label>Password</label>
					<input id="password" name="password" value="" type="password">
				</tr>
				<tr>
					<div>
						<label>Treatment</label> <select id="speciality" name="speciality">
							<option value="neural">NEURAL</option>
							<option value="dental">DENTAL</option>
							<option value="all">ALL</option>
						</select>
					</div>
			</div>
			</tr>
			<tr>
				<div>
					<button id="submitButton" type="submit">Create User</button>
				</div>
			</tr>
		</form>
	</div>

</body>
</html>