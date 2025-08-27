package com.springboot.reviewCS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.reviewCS.model.Customer;
import com.springboot.reviewCS.model.Product;
import com.springboot.reviewCS.model.Review;
import com.springboot.reviewCS.repository.CustomerProductRepository;
import com.springboot.reviewCS.repository.CustomerRepository;
import com.springboot.reviewCS.repository.ProductRepository;
import com.springboot.reviewCS.repository.ReviewRepository;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;
	private CustomerProductRepository customerProductRepository;
	
	public ReviewService(ReviewRepository reviewRepository, CustomerRepository customerRepository,
			ProductRepository productRepository, CustomerProductRepository customerProductRepository) {
		this.reviewRepository = reviewRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.customerProductRepository = customerProductRepository;
	}
	
	public Review postReview(String username, int productId, Review review) {
		Customer customer = customerRepository.getCustomerByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
		Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product ID is Invalid"));
		customerProductRepository.getPurchaseByCustomerProductID(customer.getId(),productId).orElseThrow(() -> new RuntimeException("Customer not purchased the product"));
		review.setCustomer(customer);
		review.setProduct(product);
		return reviewRepository.save(review);
	}

	public List<Review> getReviewsByProductId(int productId) {
		productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product ID is Invalid"));
		return reviewRepository.getReviewsByProduct(productId);
	}
}
