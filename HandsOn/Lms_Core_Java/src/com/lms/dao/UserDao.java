package com.lms.dao;

import com.lms.exception.UserNotFoundException;
import com.lms.model.User;

public interface UserDao {
	
	Integer login(String username, String password) throws UserNotFoundException;
	void signup(User user);
	User getByUsername(String username);
	
}
