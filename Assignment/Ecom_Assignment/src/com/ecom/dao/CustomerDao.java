package com.ecom.dao;

import java.util.List;

import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;

public interface CustomerDao {
	
	Customer getById(int id) throws InvalidIdException; 
	void insert(Customer customer) throws InvalidInputException;
	List<Customer>getAll();
}
