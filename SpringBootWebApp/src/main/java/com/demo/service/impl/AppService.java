package com.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.repository.IAppRepository;
import com.demo.service.IAppService;
import com.demo.type.RoleType;

@Service
@Transactional
public class AppService implements IAppService {

	@Autowired
	private IAppRepository repo;

	public List<UserDetails> getdetails(String name, String age, RoleType role) {
		return repo.execute(name, age, role);
	}

	public void createRequest(UserDetails userdetails) {
		repo.createRequest(userdetails);
	}

	public void createUser(Person person) {
		repo.createUser(person);
	}

	public void userApproval(UserDetails userDetail) {
		repo.update(userDetail);
	}

	public boolean validateUser(String name, String age, String password) {
		return repo.validate(name, age, password);
	}

}
