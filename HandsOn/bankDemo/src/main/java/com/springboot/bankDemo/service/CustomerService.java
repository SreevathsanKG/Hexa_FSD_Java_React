package com.springboot.bankDemo.service;

import org.springframework.stereotype.Service;

import com.springboot.bankDemo.model.Customer;
import com.springboot.bankDemo.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Customer insertCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
