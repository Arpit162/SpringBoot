package com.demo.entity;

public class UserDetails {
	private String approverName;
	private String speciality;
	private String role;
	private String yob;
	private String record;
	private String prescription;
	private String requestType;
	private String requestId;
	private String requesterName;

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getYob() {
		return yob;
	}

	public void setYob(String yob) {
		this.yob = yob;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	@Override
	public String toString() {
		return "UserDetails [approverName=" + approverName + ", speciality=" + speciality + ", role=" + role + ", yob="
				+ yob + ", record=" + record + ", prescription=" + prescription + ", requestType=" + requestType
				+ ", requestId=" + requestId + ", requesterName=" + requesterName + "]";
	}
}
