package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import com.demo.entity.Member;
import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.service.IAppService;
import com.demo.type.RoleType;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest {

	@InjectMocks
	private AppController classUnderTest=new AppController() ;
	
	@Mock
	private IAppService service;

	@Test
	public void test_getDashboard(){
		List<UserDetails> list=createListOfUserDeatils();
		Model model = new ExtendedModelMap();
		when(service.validateUser("Reddy","1975","123")).thenReturn(true);
		when(service.getdetails("Reddy","1975",RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.getDashboard("Reddy", "1975", "patient","123", model);
		assertNotNull(role);
		assertEquals("patient",role);
	}
	
	@Test
	public void test_getDashboard_invalid(){
		List<UserDetails> list=new ArrayList<>();
		UserDetails ud=null;
		list.add(ud);
		Model model = new ExtendedModelMap();
		when(service.validateUser("Reddy","1975","123")).thenReturn(true);
		when(service.getdetails("Reddy","32",RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.getDashboard("Reddy", "1975", "patient","123", model);
		assertTrue(model.containsAttribute("records"));
		assertNotNull(role);
		assertEquals("patient",role);
	}
	
	@Test
	public void test_createUser(){
		List<UserDetails> list=createListOfUserDeatils();
		Person person=createPerson();
		Model model = new ExtendedModelMap();
		when(service.getdetails(person.getPersonName(),person.getPersonYob(),RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.createUser(person, model);
		assertNotNull(role);
		assertEquals("patient",role);
		verify(service).createUser(person);
	}
	
	@Test
	public void test_createUser_invalid(){
		List<UserDetails> list=new ArrayList<>();
		Person person=createPerson();
		UserDetails ud=null;
		list.add(ud);
		Model model = new ExtendedModelMap();
		when(service.getdetails(person.getPersonName(),person.getPersonYob(),RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.createUser(person, model);
		assertTrue(model.containsAttribute("records"));
		assertNotNull(role);
		assertEquals("patient",role);
		verify(service).createUser(person);
	}
	
	@Test
	public void test_userApproval(){
		List<UserDetails> list=createListOfUserDeatils();
		Member me=createMember();
		Model model = new ExtendedModelMap();
		when(service.getdetails("Reddy","32",RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.userApproval(me, list.get(0), model);
		assertNotNull(role);
		assertEquals("patient",role);
		verify(service).userApproval(list.get(0));
	}
	
	@Test
	public void test_userApproval_invalid(){
		List<UserDetails> list=new ArrayList<>();
		UserDetails ud=null;
		list.add(ud);
		Member me=createMember();
		Model model = new ExtendedModelMap();
		when(service.getdetails("Reddy","32",RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.userApproval(me, list.get(0), model);
		assertNotNull(role);
		assertEquals("patient",role);
		verify(service).userApproval(list.get(0));
	}
	
	@Test
	public void test_userRequest(){
		List<UserDetails> list=createListOfUserDeatils();
		Member me=createMember();
		Model model = new ExtendedModelMap();
		when(service.getdetails("Reddy","32",RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.userRequest(me,list.get(0), model);
		assertNotNull(role);
		assertEquals("patient",role);
		verify(service).createRequest(list.get(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void test_userRequest_invalid(){
		List<UserDetails> list=new ArrayList<>();
		UserDetails ud=null;
		list.add(ud);
		Member me=null;
		Model model = new ExtendedModelMap();
		when(service.getdetails("Reddy","32",RoleType.PATIENT)).thenReturn(list);
		String role=classUnderTest.userRequest(me,list.get(0), model);
		assertTrue(model.containsAttribute("records"));
		assertNotNull(role);
		assertEquals("patient",role);
		verify(service).createRequest(list.get(0));
	}
	
	
	@Test
	public void test_logout(){
		Model model = new ExtendedModelMap();
		String str=classUnderTest.logout(model);
		assertTrue(model.containsAttribute("member"));
		assertEquals("index",str);
	}
	
	private Member createMember(){
		Member me=new Member();
		me.setPersonName("Reddy");
		me.setPersonYob("1975");
		me.setPersonRole("patient");
		return me;
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
	
	private List<UserDetails> createListOfUserDeatils(){
		List<UserDetails> list=new ArrayList<>();
		UserDetails ud=new UserDetails();
		ud.setRequesterName("Reddy");
		ud.setYob("1975");
		ud.setRole("patient");
		ud.setSpeciality("neural");
		list.add(ud);
		return list;
	}
}
