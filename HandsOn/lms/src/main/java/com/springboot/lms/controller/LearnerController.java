package com.springboot.lms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Learner;
import com.springboot.lms.service.LearnerService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LearnerController {

	@Autowired
	private LearnerService learnerService;

	/*
	 * PATH : http://localhost:8080/api/lerner/add Response: Hello Rest API!!!
	 */
	@PostMapping("/api/learner/add")
	public Learner insertLearner(@RequestBody Learner learner) {
		return learnerService.insertLearner(learner);
	}

	/*
	 * AIM: To fetch all learner record
	 * PATH: /api/learner/get-all
	 * Method: GET
	 * Response: List<Learner>
	 * */
	@GetMapping("/api/learner/get-all")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(learnerService.getAll());
	}
	
	/*
	 * AIM: To delete Learner by Id
	 * PATH: /api/learner/delete/{id}
	 * Method: delete
	 * Response: void
	 *Input: id - pathVariable
	 * */
	@DeleteMapping("/api/learner/delete/{id}")
	public ResponseEntity<?> deleteLearner(@PathVariable int id) {
		learnerService.deleteLearner(id);
		return ResponseEntity.status(HttpStatus.OK).body("Learner deleted");
	}
	/*
	 * AIM: get learner by loggedIn credentials
	 * PATH: /api/learner/get-one
	 * Method: Get
	 * PARAM: Principal
	 * Response: Learner
	 * */
	@GetMapping("/api/learner/get-one")
	public Learner getLearnerById(Principal principal) {
		String username = principal.getName();
		return learnerService.getLearnerByUsername(username);
	}
	/*
	 * AIM: To update learner
	 * PATH: /api/learner/update/{id}
	 * Method: put
	 * Response: updated learner
	 * Input: id as path variable, learner as request body
	 * */
	@PutMapping("/api/learner/update/{id}")
	public Learner updateLearner(@PathVariable int id, @RequestBody Learner learner) {
		return learnerService.updateLearner(id, learner);
	}
	
	@GetMapping("/api/learner/course/{courseId}")
	public List<?> getLearnerByCourseId(@PathVariable int courseId){
		return learnerService.getLearnerByCourseId(courseId);
	}
	
	/* 
	 * {
	    "name":"Harry Potter",
	    "contact" : "9866746558"
		}
		
		Jackson Dependency 
		
		Learner learner = new Learner(); 
		learner.setName(name)
		learner.setContact(contact)
	 * 
	 */
}
