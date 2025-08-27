package com.springboot.caseStudy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.caseStudy.model.PatientLab;
import com.springboot.caseStudy.repository.PatientLabRepository;
import com.springboot.caseStudy.repository.PatientRepository;
@Service
public class PatientLabService {

	private PatientLabRepository patientLabRepository;
	private PatientRepository patientRepository;
	
	public PatientLabService(PatientLabRepository patientLabRepository, PatientRepository patientRepository) {
		super();
		this.patientLabRepository = patientLabRepository;
		this.patientRepository = patientRepository;
	}

	// fetch by patientID
	public List<PatientLab> getByPatientId(int patientId) {
		patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient ID is Invalid"));
		return patientLabRepository.getByPatientId(patientId);
	}

}
