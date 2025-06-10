package com.springboot.reviewCS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.reviewCS.model.Customer;
import com.springboot.reviewCS.model.CustomerProduct;
import com.springboot.reviewCS.model.Product;
import com.springboot.reviewCS.model.Review;
import com.springboot.reviewCS.model.User;
import com.springboot.reviewCS.repository.CustomerProductRepository;
import com.springboot.reviewCS.repository.CustomerRepository;
import com.springboot.reviewCS.repository.ProductRepository;
import com.springboot.reviewCS.repository.ReviewRepository;
import com.springboot.reviewCS.service.ReviewService;

@SpringBootTest
public class ReviewServiceTest {

	@InjectMocks
	private ReviewService reviewService;
	@Mock
	private ReviewRepository reviewRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private CustomerProductRepository customerProductRepository;
	
	private Review review;
	private Customer customer;
	private Product product;
	private User user;
	private CustomerProduct customerProduct;
	
	@BeforeEach
	public void init() {
		user = new User();
		user.setId(1);
		user.setUsername("harry@gmail.com");
		user.setPassword("harry@123");
		user.setRole("CUSTOMER");
		
		customer = new Customer();
		customer.setId(1);
		customer.setName("Harry");
		customer.setContact("9876543210");
		customer.setUser(user);
		
		product = new Product();
		product.setId(1);
		product.setTitle("Phone");
		product.setPrice(10000);
		
		review = new Review();
		review.setId(1);
		review.setComment("Good Quality");
		review.setRating("4");
		review.setCustomer(customer);
		review.setProduct(product);
		
		customerProduct = new CustomerProduct();
		customerProduct.setId(1);
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		customerProduct.setQuantity(2);
		customerProduct.setCoupon("BUY1GET1");
		customerProduct.setPurchaseDate(LocalDate.now());
	}
	
	@Test
	public void postReviewTest() {
		Review r = new Review();
		when(customerRepository.getCustomerByUsername("harry@gmail.com")).thenReturn(Optional.of(customer));
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		when(customerProductRepository.getPurchaseByCustomerProductID(1, 1)).thenReturn(Optional.of(customerProduct));
		when(reviewRepository.save(r)).thenReturn(review);
		
		assertEquals(review, reviewService.postReview("harry@gmail.com", 1, r));
		
		RuntimeException e = assertThrows(RuntimeException.class,() -> reviewService.postReview("harry@gmail.com", 2, r));
		assertEquals("Product ID is Invalid".toLowerCase(), e.getMessage().toLowerCase());
		
		e = assertThrows(RuntimeException.class,() -> reviewService.postReview("john@gmail.com", 2, r));
		assertEquals("Username not found".toLowerCase(), e.getMessage().toLowerCase());
	}
	
//	@Test
	public void getReviewsByProductId() {
		List<Review> expectedList =List.of(review);
		
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		when(reviewRepository.getReviewsByProduct(1)).thenReturn(expectedList);
		
		List<Review> actualList = reviewService.getReviewsByProductId(1);
		
		assertEquals(expectedList, actualList);
	}
	
	@AfterEach
	public void afterTest() {
		customer = null;
		product = null;
		review = null;
	}
	
}
