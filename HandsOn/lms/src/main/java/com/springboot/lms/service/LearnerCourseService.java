package com.springboot.lms.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.Course;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;
import com.springboot.lms.repository.CourseRepository;
import com.springboot.lms.repository.LearnerCourseRepository;
import com.springboot.lms.repository.LearnerRepository;

@Service
public class LearnerCourseService {

	private LearnerCourseRepository learnerCourseRepository;
	private LearnerRepository learnerRepository;
	private CourseRepository courseRepository;

	public LearnerCourseService(LearnerCourseRepository learnerCourseRepository, LearnerRepository learnerRepository,
			CourseRepository courseRepository) {
		this.learnerCourseRepository = learnerCourseRepository;
		this.learnerRepository = learnerRepository;
		this.courseRepository = courseRepository;
	}

	public LearnerCourse enrollLearnerInCourse(int learnerId, int courseId, LearnerCourse learnerCourse) {
		// fetch learner by learnerId
		Learner learner = learnerRepository.findById(learnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Learner ID Invalid"));

		// fetch course by courseId
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course ID Invalid"));

		// generate and adds today date to learnerCourse object as enroll date
		learnerCourse.setEnrollDate(LocalDate.now());

		// set learner and course to learnerCourse object
		learnerCourse.setLearner(learner);
		learnerCourse.setCourse(course);

		// save learnerCourse in DB
		return learnerCourseRepository.save(learnerCourse);
	}

}
