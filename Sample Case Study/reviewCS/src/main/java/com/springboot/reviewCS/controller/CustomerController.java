package com.springboot.reviewCS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reviewCS.model.Customer;
import com.springboot.reviewCS.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/post")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerService.postCustomer(customer);
	}
}
