package com.springboot.reviewCS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reviewCS.model.User;
import com.springboot.reviewCS.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/*
	 * */
	@PostMapping("/signup")
	public User postSignUp(@RequestBody User user) {
		return userService.postSignUp(user);
	}
}
