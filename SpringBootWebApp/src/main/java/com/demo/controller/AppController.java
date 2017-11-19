package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.demo.entity.Member;
import com.demo.entity.Person;
import com.demo.entity.UserDetails;
import com.demo.service.IAppService;
import com.demo.type.RoleType;

@Controller
@SessionAttributes("member")
public class AppController {

	@Autowired
	IAppService services;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/createUser")
	public String redirect() {
		return "createUser";
	}

	@PostMapping("/dashboard")
	public String getDashboard(@RequestParam("name") String name, @RequestParam("yob") String yob,
			@RequestParam("role") String role,@RequestParam("password") String password, Model model) {
		if(!services.validateUser(name,yob,password)){
			model.addAttribute("AccessError","**Try with correct credentials.**");
			return "index";}
		getUserDetails(name, yob, role, model);
		return role;
	}

	private void getUserDetails(String name, String yob, String role, Model model) {
		List<UserDetails> reponse = null;
		switch (RoleType.fromAction(role)) {
		case PATIENT:
			reponse = services.getdetails(name, yob, RoleType.PATIENT);
			break;
		case DOCTOR:
			reponse = services.getdetails(name, yob, RoleType.DOCTOR);
			break;
		case PHARMACIST:
			reponse = services.getdetails(name, yob, RoleType.PHARMACIST);
			break;
		default:
			break;
		}
		Member member = new Member(name, yob, role);
		model.addAttribute("member", member);
		model.addAttribute("records", reponse);
	}

	@PostMapping("/adduser")
	public String createUser(Person person, Model model) {
		services.createUser(person);
		getUserDetails(person.getPersonName(), person.getPersonYob(), person.getPersonRole(), model);
		return person.getPersonRole();
	}

	@PostMapping("/userApproval")
	public String userApproval(@ModelAttribute("member") Member member, UserDetails userdeatils, Model model) {
		services.userApproval(userdeatils);
		getUserDetails(member.getPersonName(), member.getPersonYob(), member.getPersonRole(), model);
		return member.getPersonRole();
	}

	@PostMapping("/userRequest")
	public String userRequest(@ModelAttribute("member") Member member, UserDetails userdeatils, Model model) {
		services.createRequest(userdeatils);
		getUserDetails(member.getPersonName(), member.getPersonYob(), member.getPersonRole(), model);
		return member.getPersonRole();
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("member", new Member());
		return "index";
	}
}
