package com.demo.type;

public enum RoleType {
	PATIENT("patient"), DOCTOR("doctor"), PHARMACIST("pharmacist");

	private String role;

	private RoleType(String role) {
		this.role = role;
	}

	public String getRoleType() {
		return role;
	}

	public void setRoleType(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}

	public static RoleType fromAction(String role) {
		for (RoleType changeActionType : RoleType.values()) {
			if (changeActionType.getRoleType().equals(role))
				return changeActionType;
		}
		throw new RuntimeException("Unable to convert role " + role);
	}

}
