package com.springboot.lms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.CourseRepository;

@Service
public class CourseService {

	Logger logger = LoggerFactory.getLogger("CourseService");
	
	private CourseRepository courseRepository;
	private AuthorRepository authorRepository;

	public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository) {
		this.courseRepository = courseRepository;
		this.authorRepository = authorRepository;
	}
	
	public Course postCourse(String username, Course course) {
		Author author = authorRepository.getAuthorByUsername(username);
		logger.info("Author record fetched by username");
		course.setAuthor(author);
		logger.info("Adding.. Author to Database");
		return courseRepository.save(course);
	}

	public List<Course> getAllCourses(Integer page, Integer size) {
		/** Activate Pageable Interface */
        Pageable pageable = PageRequest.of(page, size);
        /** Call findAll inbuilt method as pass this pageable interface ref */
        return courseRepository.findAll(pageable).getContent();
	}
	
	public List<Course> getCourseByAuthor(String username) {
		return courseRepository.getCourseByAuthor(username);
	}
	
	public Course getCourseById(int id) {
		return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("ID is Invalid"));
	}
}
