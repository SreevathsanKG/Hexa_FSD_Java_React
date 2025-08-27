package com.lms.service;

import com.lms.dao.UserDao;
import com.lms.dao.impl.UserDaoImpl;
import com.lms.exception.UserNotFoundException;
import com.lms.model.User;

public class UserService {
	
	UserDao userDao = new UserDaoImpl();
	
	public User login(String username, String password) throws UserNotFoundException{
		Integer isCredentialIsValid = userDao.login(username, password);
		if(isCredentialIsValid==1) {
			User user = userDao.getByUsername(username);
			if(user==null)
				throw new UserNotFoundException("Invalid Credentials");
			return user;
		}
		else
			throw new UserNotFoundException("Invalid Credentials");
	}
}
