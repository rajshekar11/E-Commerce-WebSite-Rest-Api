package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.UserDTO;
import com.masai.service.UserLoginServiceImpl;

@RestController

@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;

	// for user login
	@PostMapping("/userlogin")
	public String loginUser(@RequestBody UserDTO userDTO) throws Exception {
		return userLoginServiceImpl.logIntoAccount(userDTO);
	}

	// for user logout
	@PatchMapping("/userlogout")
	public String logOutUser(@RequestParam(required = false) String key) {
		return userLoginServiceImpl.logOutAccount(key);
	}
}