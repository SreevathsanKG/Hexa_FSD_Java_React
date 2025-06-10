package com.springboot.caseStudy.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.caseStudy.model.User;
import com.springboot.caseStudy.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signUp(User user) {
		String plainPassoword = user.getPassword();							// plain password
		String encodedPassword = passwordEncoder.encode(plainPassoword);	// encodes the plain password
		user.setPassword(encodedPassword);									// encrypted password set to user password 
		return userRepository.save(user);
	}
	
}
