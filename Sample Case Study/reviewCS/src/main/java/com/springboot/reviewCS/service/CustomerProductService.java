package com.springboot.reviewCS.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.springboot.reviewCS.model.Customer;
import com.springboot.reviewCS.model.CustomerProduct;
import com.springboot.reviewCS.model.Product;
import com.springboot.reviewCS.repository.CustomerProductRepository;
import com.springboot.reviewCS.repository.CustomerRepository;
import com.springboot.reviewCS.repository.ProductRepository;

@Service
public class CustomerProductService {

	private CustomerProductRepository customerProductRepository;
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;
	
	public CustomerProductService(CustomerProductRepository customerProductRepository,
			CustomerRepository customerRepository, ProductRepository productRepository) {
		this.customerProductRepository = customerProductRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}
	
	public CustomerProduct postPurchase(String username, int productId, CustomerProduct customerProduct) {
		Customer customer = customerRepository.getCustomerByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Invalid product ID"));
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		customerProduct.setPurchaseDate(LocalDate.now());
		return customerProductRepository.save(customerProduct);
	}
}
