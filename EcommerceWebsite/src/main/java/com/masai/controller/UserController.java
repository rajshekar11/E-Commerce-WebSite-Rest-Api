package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.User;
import com.masai.model.UserSigninDTO;
import com.masai.repository.UserSessionRepo;
import com.masai.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService user;
	
	@Autowired
	private UserSessionRepo urep;
	
	@PostMapping("/addUser")
	public User saveCustomer(@RequestBody User userr) {
		return user.createUser(userr);
	}
}
