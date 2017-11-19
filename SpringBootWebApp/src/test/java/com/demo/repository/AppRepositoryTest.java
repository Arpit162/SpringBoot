
package com.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.MainApp;
import com.demo.entity.UserDetails;
import com.demo.repository.impl.AppRepository;
import com.demo.type.RoleType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainApp.class)
public class AppRepositoryTest {

	@Autowired
	private AppRepository repo;

	@Test
	public void test_execute() {
		List<UserDetails> list = repo.execute("Reddy", "1975", RoleType.PATIENT);
		assertNotNull(list);
		assertEquals(list.size(), 2);
		assertEquals(list.get(0).getRequesterName(), "ReddyDoc");
	}

	@Test
	public void test_execute_notFound() {
		List<UserDetails> list = repo.execute("ReddyMM", "32", RoleType.PATIENT);
		assertTrue(list.isEmpty());
	}

}
