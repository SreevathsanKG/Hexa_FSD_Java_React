package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.CModule;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.CModuleRepository;
import com.springboot.lms.repository.CourseRepository;

@Service
public class CModuleService {

	private CModuleRepository cmoduleRepository;
	private CourseRepository courseRepository;
	
	public CModuleService(CModuleRepository cmoduleRepository, CourseRepository courseRepository) {
		this.cmoduleRepository = cmoduleRepository;
		this.courseRepository = courseRepository;
	}

	public CModule postModule(int courseId, CModule cmodule) {
		//fetch course by course_id
		Course course = courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course not found, Id given is invalid"));
		// set course to the module object
		cmodule.setCourse(course);
		// save module in db
		return cmoduleRepository.save(cmodule);
	}

	public List<CModule> getCModuleByCourseId(int courseId) {
		return cmoduleRepository.getCModuleByCourseId(courseId);	// by user written JPQL
//		return cmoduleRepository.findByCourseId(courseId);			// by inbuilt JPA written
	}
}
