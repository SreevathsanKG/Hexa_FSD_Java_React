package com.springboot.caseStudy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.caseStudy.model.Doctor;
import com.springboot.caseStudy.model.User;
import com.springboot.caseStudy.repository.DoctorRepository;

@Service
public class DoctorService {

	private DoctorRepository doctorRepository;
	private UserService userService;

	public DoctorService(DoctorRepository doctorRepository, UserService userService) {
		this.doctorRepository = doctorRepository;
		this.userService = userService;
	}
	
	// insert doctor
	public Doctor postDoctor(Doctor doctor) {
		User user = doctor.getUser();
		user.setRole("DOCTOR");
		user = userService.signUp(user);
		doctor.setUser(user);
		return doctorRepository.save(doctor);
	}
	
	// fetch all doctor
	public List<Doctor> getAll() {
		return doctorRepository.findAll();
	}
}
