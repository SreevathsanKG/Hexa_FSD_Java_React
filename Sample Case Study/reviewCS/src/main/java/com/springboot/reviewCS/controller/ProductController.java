package com.springboot.reviewCS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reviewCS.model.Product;
import com.springboot.reviewCS.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/post")
	public Product postMethodName(@RequestBody Product product) {
		return productService.postProduct(product);
	}
	
}
