package com.demo.repository;

import java.util.List;

import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.type.RoleType;

public interface IAppRepository {

	public List<UserDetails> execute(String name, String age, RoleType role);

	public void createRequest(UserDetails userDeatils);

	public void createUser(Person person);

	public void update(UserDetails userDeatils);

	public boolean validate(String name, String age, String password);

}
