package com.springboot.lms.controller;

import java.security.Principal;
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

import com.springboot.lms.model.Course;
import com.springboot.lms.service.CourseService;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	/*
	 * AIM: to add course to db <-- should be done only by author
	 * Path: "/api/course/add"
	 * Method: POST
	 * Response: Course
	 * Input: Course <-- Request Body
	 * ACCESS: AUTHOR or EXECUTIVE
	 * */
	@PostMapping("/add")
	public Course postCourse(Principal principal, @RequestBody Course course) {
		String username = principal.getName();
		return courseService.postCourse(username, course);
	}
	
	@GetMapping("/get/all")
	public List<Course> getAllCourses(
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "1000000") Integer size) {

		return courseService.getAllCourses(page, size);
	}
	
	@GetMapping("/get-by/author")
	public List<Course> getCourseByAuthor(Principal principal) {
		String username = principal.getName();
		return courseService.getCourseByAuthor(username);
	}
	
	@GetMapping("/get-by/id/{id}")
	public Course getCourseById(@PathVariable int id) {
		return courseService.getCourseById(id);
	}
}
