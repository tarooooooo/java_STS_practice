package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.data.entity.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.form.UserForm;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/newuser")
	public String registerUser(UserForm userForm) {
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/newuser")
	public String getNewUser(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "newuser";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "users";
	}
	
}
