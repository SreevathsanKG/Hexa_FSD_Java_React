package com.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;

public interface LearnerDao {
	
	List<Learner> getAll() throws SQLException;
	Learner getById(int id) throws InvalidIdException;
	void deleteById(int id) throws InvalidIdException;
	Learner update(int id, Learner learner) throws InvalidIdException, InvalidInputException, SQLException;
	void insert(Learner learner) throws InvalidInputException, SQLException;
}
