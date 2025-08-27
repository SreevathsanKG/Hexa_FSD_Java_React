package com.springboot.reviewCS.service;

import org.springframework.stereotype.Service;

import com.springboot.reviewCS.model.Customer;
import com.springboot.reviewCS.model.User;
import com.springboot.reviewCS.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private UserService userService;
	
	public CustomerService(CustomerRepository customerRepository, UserService userService) {
		this.customerRepository = customerRepository;
		this.userService = userService;
	}
	
	public Customer postCustomer(Customer customer) {
		User user = customer.getUser();
		user.setRole("CUSTOMER");
		user = userService.postSignUp(user);
		customer.setUser(user);
		return customerRepository.save(customer);
	}
}
