package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.LearnerRepository;

@Service
public class LearnerService {

	private LearnerRepository learnerRepository;
	private UserService userService;

	public LearnerService(LearnerRepository learnerRepository, UserService userService) {
		this.learnerRepository = learnerRepository;
		this.userService = userService;
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
}
