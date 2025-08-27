package com.springboot.caseStudy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caseStudy.model.User;
import com.springboot.caseStudy.service.UserService;
import com.springboot.caseStudy.util.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	
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
	
	/*
	 * AIM: get token by username
	 * METHOD: GET
	 * PARAM: Principal
	 * RESPONSE: Token
	 * PATH: /api/user/token
	 * */
	@GetMapping("/token")
	public ResponseEntity<?> getToken(Principal principal) {
		try {
			String token = jwtUtil.createToken(principal.getName());
			return ResponseEntity.status(HttpStatus.OK).body(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	
}
