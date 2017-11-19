<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<i style="color:red" >${AccessError}</i>
	<div class="form">
		<form action="dashboard" method="post" onsubmit="return validate()">
			<table>
				<tr>
					<td>Your name</td>
					<td><input id="name" name="name"></td>
				</tr>
				<tr>
					<td>Your password</td>
					<td><input id="password" name="password" type="password"></td>
				</tr>
				<tr>
					<td>Your year of birth</td>
					<td><input id="yob" name="yob"></td>
				</tr>
				<tr>
					<td><input type="radio" id="role" name="role" value="patient"
						checked> <label for="contactChoice1">PATIENT</label></td>

					<td><input type="radio" id="role" name="role" value="doctor">
						<label for="contactChoice2">DOCTOR</label></td>

					<td><input type="radio" id="role" name="role"
						value="pharmacist"> <label for="contactChoice3">PHARMACIST</label></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
				</tr>
				</tr>


			</table>
		</form>
		<div>
			<FORM NAME="form1" ACTION="createUser" METHOD="GET">
				<INPUT TYPE="SUBMIT" VALUE="Become Member!">
			</FORM>

		</div>
	</div>


</body>
</html>