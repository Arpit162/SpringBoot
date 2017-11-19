package com.demo.type;

public enum SpecialityType {
	NEURAL("neural"), ORTHOPAIEDIST("orthopaiedist"), DENTIST("dentist"), ALL("all");

	private String speciality;

	private SpecialityType(String speciality) {
		this.speciality = speciality;
	}

	public String getSpecialityType() {
		return speciality;
	}

	public void setSpecialityType(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return speciality;
	}

	public static SpecialityType fromAction(String speciality) {
		for (SpecialityType changeActionType : SpecialityType.values()) {
			if (changeActionType.getSpecialityType().equals(speciality))
				return changeActionType;
		}
		throw new RuntimeException("Unable to convert speciality " + speciality);
	}

}
