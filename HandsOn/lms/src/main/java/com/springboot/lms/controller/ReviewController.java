package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Review;
import com.springboot.lms.service.ReviewService;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	/*
	 * AIM: Create a POST API to insert review given by learner for a course 
	 * Method: POST
	 * PARAM: learnerId, courseId <- Path variable,|| comment,rating <- request body 
	 * Response: Review 
	 * API: /api/review/add/{learnerId}/{courseId}
	 * */
	@PostMapping("/add/{learnerId}/{courseId}")
	public Review postReview(@PathVariable int  learnerId, 
						   @PathVariable int  courseId, 
						   @RequestBody Review review) {
		
		return reviewService.postReview(learnerId,courseId, review);
	}
	
	/*
	 * AIM: fetch all reviews greater than given value
	 * METHOD: GET
	 * PARAM: Request rating
	 * API: /api/review/get/rating?rating=4.5
	 * RESPONSE: List<Review>
	 * */
	@GetMapping("/get/rating")
	public List<Review> getReviewByRating(@RequestParam String rating) {
		return reviewService.getReviewByRating(rating);
	}
	
	// get review by courseId
	@GetMapping("/get-by/courseId/{courseId}")
	public List<Review> getReviewByCourseID(@PathVariable int courseId) {
		return reviewService.getReviewByCourseId(courseId);
	}
}