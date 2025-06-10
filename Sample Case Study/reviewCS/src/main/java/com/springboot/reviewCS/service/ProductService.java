package com.springboot.reviewCS.service;

import org.springframework.stereotype.Service;

import com.springboot.reviewCS.model.Product;
import com.springboot.reviewCS.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product postProduct(Product product) {
		return productRepository.save(product);
	}
	
}
