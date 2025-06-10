package com.springboot.reviewCS.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.reviewCS.model.User;
import com.springboot.reviewCS.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// insert user
	public User postSignUp(User user) {
		String password = user.getPassword();
		String encodedPass = passwordEncoder.encode(password);
		user.setPassword(encodedPass);
		return userRepository.save(user);
	}
}
