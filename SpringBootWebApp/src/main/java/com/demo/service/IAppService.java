package com.demo.service;

import java.util.List;

import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.type.RoleType;

public interface IAppService {

	public List<UserDetails> getdetails(String name, String yob, RoleType role);

	public void createUser(Person person);

	public void createRequest(UserDetails userDetail);

	public void userApproval(UserDetails userDetail);

	boolean validateUser(String name, String yob, String password);

}
