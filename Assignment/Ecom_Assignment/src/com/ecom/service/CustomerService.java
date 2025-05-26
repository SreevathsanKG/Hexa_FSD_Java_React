package com.ecom.service;

import java.util.List;

import com.ecom.dao.CustomerDao;
import com.ecom.dao.impl.CustomerDaoImpl;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;

public class CustomerService {
	
	CustomerDao customerDao = new CustomerDaoImpl();
	
	public Customer getById(int id) throws InvalidIdException{
		return customerDao.getById(id);
	}
	
	public void insert(Customer customer) throws InvalidInputException {
		if (customer == null) {
			throw new InvalidInputException("Customer should not be null");
		}

		customerDao.insert(customer);
	}

	public List<Customer> getAll() {
		return customerDao.getAll();
	}
	
}
