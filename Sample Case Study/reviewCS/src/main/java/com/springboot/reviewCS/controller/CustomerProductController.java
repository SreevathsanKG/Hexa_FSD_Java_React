package com.springboot.reviewCS.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reviewCS.model.CustomerProduct;
import com.springboot.reviewCS.service.CustomerProductService;

@RestController
@RequestMapping("/api/cp")
public class CustomerProductController {

	@Autowired
	private CustomerProductService customerProductService;
	
	@PostMapping("/post/{productId}")
	public CustomerProduct postPurchase(Principal principal, 
			@PathVariable int productId, @RequestBody CustomerProduct customerProduct) {
		String username = principal.getName();
		return customerProductService.postPurchase(username, productId, customerProduct);
	}
}
