package com.springboot.lms.service;

import org.springframework.stereotype.Service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;

@Service
public class AuthorService {

	private AuthorRepository authorRepository;
	private UserService userService;
	
	public AuthorService(AuthorRepository authorRepository, UserService userService) {
		this.authorRepository = authorRepository;
		this.userService = userService;
	}
	
	// add author
	public Author postAuthor(Author author) {
		User user = author.getUser();
		user.setRole("AUTHOR");
		user = userService.signUp(user);
		author.setUser(user);
		/* Activate Author - later let executive do this.. */
        author.setActive(true);
		return authorRepository.save(author);
	}
}
