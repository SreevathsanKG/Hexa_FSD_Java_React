package com.springboot.lms.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.LearnerRepository;
import com.springboot.lms.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private LearnerRepository learnerRepository;
	private AuthorRepository authorRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			LearnerRepository learnerRepository, AuthorRepository authorRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.learnerRepository = learnerRepository;
		this.authorRepository = authorRepository;
	}

	public User signUp(User user) {
		String plainPassoword = user.getPassword(); // plain password
		String encodedPassword = passwordEncoder.encode(plainPassoword); // encodes the plain password
		user.setPassword(encodedPassword); // encrypted password set to user password
		return userRepository.save(user);
	}

	public Object getUserInfo(String username) {
		User user = userRepository.getByUsername(username);
		switch(user.getRole().toUpperCase()) {
			case "LEARNER":
				Learner learner = learnerRepository.getLearnerByUsername(username);
				return learner;
			case "AUTHOR":
				Author author = authorRepository.getAuthorByUsername(username);
				if (author.isActive())
					return author;
				else
					throw new RuntimeException("Author Inactive");
			case "EXECUTIVE":
				return null;
			default:
				return null;
		}
	}
}
