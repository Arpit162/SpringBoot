
package com.demo.entity;

public class Member {
	private String personName;
	private String personYob;
	private String personRole;
	
	public Member(){
		
	}
	
	public Member(String personName, String personYob, String personRole) {
		super();
		this.personName = personName;
		this.personYob = personYob;
		this.personRole = personRole;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonYob() {
		return personYob;
	}

	public void setPersonYob(String personYob) {
		this.personYob = personYob;
	}

	public String getPersonRole() {
		return personRole;
	}

	public void setPersonRole(String personRole) {
		this.personRole = personRole;
	}

}
