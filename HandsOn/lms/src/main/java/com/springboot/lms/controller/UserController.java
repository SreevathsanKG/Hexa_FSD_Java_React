package com.springboot.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.User;
import com.springboot.lms.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/*
	 * AIM: insert user in db with password encrypted
	 * METHOD: POST
	 * PARAM: User -> RequestBody
	 * RESPONSE: User
	 * PATH: /api/user/signup
	 * */
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) {
		return userService.signUp(user);
	}
}
