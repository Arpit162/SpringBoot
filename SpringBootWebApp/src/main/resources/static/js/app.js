function validate() {
	var name = document.getElementById("name").value;
	var userYob = document.getElementById("yob").value;
	var role = document.getElementById("role").value;
	var password = document.getElementById("password").value;

	if ((name == '') || (role == '') || (password == '')) {
		alert('Please enter a valid details');
		return false;
	} else {
		yobflag=yearValidation(userYob);
		if(yobflag==false)
			return false;
		else
		return true;
	}
}

function validateForm() {
	var userName = document.getElementById("requesterName").value;
	var userYob = document.getElementById("yob").value;
	var userRole = document.getElementById("role").value;
	var userRecord = document.getElementById("record").value;
	var userPrescription = document.getElementById("prescription").value;
	var userSpeciality = document.getElementById("speciality").value;
	if (userName == '' || yearValidation(userYob) || userRole == ''
			|| userSpeciality == '') {
		alert('Please enter a valid details');
		return false;
	} else {
		return true;
	}
}

function yearValidation(year) {
	var text = /^[0-9]+$/;
	if(year==''){
		alert("Plaese enter your of birth.");
		return false;
	}
	if (year != 0) {
		if ((year != "") && (!text.test(year))) {

			alert("Please Enter Numeric Values Only");
			return false;
		}

		if (year.length != 4) {
			alert("Year is not proper. Please check");
			return false;
		}
		var current_year = new Date().getFullYear();
		if ((year < 1920) || (year > current_year)) {
			alert("Year should be in range 1920 to current year");
			return false;
		}
		return true;
	}
}