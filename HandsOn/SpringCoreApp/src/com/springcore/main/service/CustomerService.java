package com.springcore.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcore.main.dao.CustomerDao;
import com.springcore.main.model.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	public CustomerService(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	public void createCustomerTable() {
		customerDao.createCustomerTable();
	}
	
	public void insertCustomer(String name, String city) {
		customerDao.insertCustomer(name, city);
	}

	public List<Customer> getAll() {
		return customerDao.getAll();
	}
	
	public List<Customer> getAllv2(){
		List<Map<String,Object>> list = customerDao.getAllv2();
		List<Customer> listCustomer = new ArrayList<>();
			for(Map<String,Object> map : list) {
				Customer c = new Customer();
				c.setId((int)map.get("id"));
				c.setName((String)map.get("name"));
				c.setCity((String)map.get("city"));
				listCustomer.add(c);
			}
			return listCustomer;
	}
	
	public Customer getById(int id) {
		Map<String,Object> map = customerDao.getById(id);
		Customer customer = new Customer( 
				(int)map.get("id") , 
				(String)map.get("name"),
				(String)map.get("city"));
		return customer;
	}
	
	public void update(Customer customer) {
		customerDao.update(customer);
	}

}