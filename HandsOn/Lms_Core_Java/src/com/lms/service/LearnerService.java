package com.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.lms.dao.impl.LearnerDaoImpl;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;

public class LearnerService {
	LearnerDaoImpl dao = new LearnerDaoImpl();
	
	public List<Learner> getAllLearner() throws SQLException {
		return dao.getAll();
	}
	
	public Learner getById(int id) throws InvalidIdException{
		return dao.getById(id);
	}
	
	public void deleteById(int id) throws InvalidIdException{
		dao.deleteById(id);
	}

	public void update(Learner learner, String name, String email) throws InvalidInputException, InvalidIdException, SQLException {
		if(name==null||name.equals("null"))
			throw new InvalidInputException("Invalid name value given");
		if(email==null||email.equals("null"))
			throw new InvalidInputException("Invalid email value given");
		learner.setName(name);
		learner.setEmail(email);
		dao.update(learner.getId(),learner);
	}

	public void insert(Learner learner) throws InvalidInputException, SQLException{
		if(learner.getName()==null||learner.getName().equals("null"))
			throw new InvalidInputException("Invalid name value given");
		if(learner.getEmail()==null||learner.getEmail().equals("null"))
			throw new InvalidInputException("Invalid email value given");
		dao.insert(learner);
	}

}
