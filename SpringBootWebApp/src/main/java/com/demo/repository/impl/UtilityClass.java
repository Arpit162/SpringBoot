package com.demo.repository.impl;

import java.util.List;

import com.demo.entity.UserDetails;

public class UtilityClass {

	private UtilityClass() {

	}

	public static List<UserDetails> processDocAndPharmaDeatils(List<UserDetails> mainList,
			List<UserDetails> listToExcahnge) {

		for (UserDetails minor : listToExcahnge) {
			for (UserDetails major : mainList) {
				if (major.getApproverName().equals(minor.getApproverName()) && major.getYob().equals(minor.getYob())
						&& major.getSpeciality().equals(minor.getSpeciality()))
					major.setRequestType(minor.getRequestType());
			}
		}
		return mainList;
	}

}
