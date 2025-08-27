package com.springboot.caseStudy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.caseStudy.model.Patient;
import com.springboot.caseStudy.model.User;
import com.springboot.caseStudy.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;
	private UserService userService;

	public PatientService(PatientRepository patientRepository, UserService userService) {
		this.patientRepository = patientRepository;
		this.userService = userService;
	}
	
	// insert patient
	public Patient postPatient(Patient patient) {
		User user = patient.getUser();									// get user_info data from patient
		user.setRole("PATIENT");										// set user role
		user = userService.signUp(user);								// signUp-post user_info
		patient.setUser(user);
		return patientRepository.save(patient);
	}
	
	// fetch all patients
	public List<Patient> getAll() {
		return patientRepository.findAll();
	}
}
