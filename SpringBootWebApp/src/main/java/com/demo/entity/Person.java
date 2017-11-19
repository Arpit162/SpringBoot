package com.demo.entity;

public class Person extends Member{
private String speciality;
private String record;
private String prescription;
private String password;

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSpeciality() {
	return speciality;
}
public void setSpeciality(String speciality) {
	this.speciality = speciality;
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
}
