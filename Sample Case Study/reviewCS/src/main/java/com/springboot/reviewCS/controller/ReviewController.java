package com.springboot.reviewCS.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reviewCS.model.Review;
import com.springboot.reviewCS.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/post/{productId}")
	public Review postReview(Principal principal, @PathVariable int productId, @RequestBody Review review) {
		String username = principal.getName();
		return reviewService.postReview(username, productId, review);
	}
	
	@GetMapping("/get-all/{productId}")
	public List<Review> getReviewsByProductId(@PathVariable int productId) {
		return reviewService.getReviewsByProductId(productId);
	}
}
