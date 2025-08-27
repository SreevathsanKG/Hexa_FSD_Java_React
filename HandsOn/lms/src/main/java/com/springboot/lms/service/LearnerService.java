package com.springboot.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lms.dto.LearnerDto;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.CourseRepository;
import com.springboot.lms.repository.LearnerCourseRepository;
import com.springboot.lms.repository.LearnerRepository;

@Service
public class LearnerService {

	private LearnerRepository learnerRepository;
	private UserService userService;
	private CourseRepository courseRepository;
	private LearnerCourseRepository learnerCourseRepository;
	
	@Autowired
	private LearnerDto learnerDto;

	public LearnerService(LearnerRepository learnerRepository, UserService userService, CourseRepository courseRepository
							, LearnerCourseRepository learnerCourseRepository) {
		this.learnerRepository = learnerRepository;
		this.userService = userService;
		this.courseRepository = courseRepository;
		this.learnerCourseRepository = learnerCourseRepository;
	}

	public Learner insertLearner(Learner learner) {
		User user = learner.getUser();
		user.setRole("LEARNER");
		user = userService.signUp(user);
		learner.setUser(user);
		return learnerRepository.save(learner);
	}

	public List<Learner> getAll() {
		return learnerRepository.findAll();
	}

	public void deleteLearner(int id) {
		learnerRepository.deleteById(id);	
	}
	
	public Learner getLearnerById(int id) {
		return learnerRepository.findById(id)
				.orElseThrow(()->new RuntimeException("ID is invalid"));
	}
	
	public Learner updateLearner(int id, Learner updatedLearner) {
		Learner dbLearner = learnerRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid ID given"));
		if(updatedLearner.getName() != null)
			dbLearner.setName(updatedLearner.getName());
		if(updatedLearner.getContact() != null)
			dbLearner.setContact(updatedLearner.getContact());
		return learnerRepository.save(dbLearner);
	}

	public Learner getLearnerByUsername(String username) {
		return learnerRepository.getLearnerByUsername(username);
	}

	public List<LearnerDto> getLearnerByCourseId(int courseId) {
		courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course ID invalid"));
		List<Learner> list = learnerCourseRepository.getLearnerByCourseId(courseId);
		return learnerDto.convertLearnerIntoDto(list);
	}
}
