package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.repository.IAppRepository;
import com.demo.service.impl.AppService;
import com.demo.type.RoleType;

@RunWith(MockitoJUnitRunner.class)
public class AppServiceTest {

	@InjectMocks
	private AppService classUnderTest = new AppService();

	@Mock
	private IAppRepository repo;

	@Test
	public void test_getdetails() {
		List<UserDetails> list = createListOfUserDeatils();
		when(repo.execute("Reddy", "1975", RoleType.PATIENT)).thenReturn(list);
		list = classUnderTest.getdetails("Reddy", "1975", RoleType.PATIENT);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(list.get(0).getYob(), "1970");
	}

	@Test
	public void test_getdetails_negative() {
		List<UserDetails> list = new ArrayList<>();
		UserDetails ud = null;
		list.add(ud);
		when(repo.execute("Reddy", "1975", RoleType.PATIENT)).thenReturn(list);
		list = classUnderTest.getdetails("Reddy", "1975", RoleType.PATIENT);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertNull(list.get(0));
	}

	@Test
	public void test_createRequest() {
		List<UserDetails> list = createListOfUserDeatils();
		classUnderTest.createRequest(list.get(0));
		verify(repo).createRequest(list.get(0));
	}
	
	@Test
	public void test_createUser() {
		Person person=createPerson();
		classUnderTest.createUser(person);
		verify(repo).createUser(person);
	}

	@Test
	public void test_userApproval() {
		List<UserDetails> list = createListOfUserDeatils();
		classUnderTest.userApproval(list.get(0));
		verify(repo).update(list.get(0));
	}
	
	private Person createPerson(){
		Person p=new Person();
		p.setPersonName("Reddy");
		p.setPersonYob("1975");
		p.setPersonRole("patient");
		p.setRecord("record");
		p.setPrescription("prescription");
		p.setSpeciality("neural");
		p.setPassword("123");
		return p;
	}

	private List<UserDetails> createListOfUserDeatils() {
		List<UserDetails> list = new ArrayList<>();
		UserDetails ud = new UserDetails();
		ud.setRequesterName("ReddyDoc");
		ud.setYob("1970");
		ud.setRole("doctor");
		ud.setSpeciality("neural");
		list.add(ud);
		return list;
	}
}
