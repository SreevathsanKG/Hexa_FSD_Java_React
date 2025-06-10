package com.springboot.caseStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caseStudy.model.Patient;
import com.springboot.caseStudy.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	// 1st API Question
	/*
	 * AIM: to insert patient
	 * PARAM: RequestBody -> Patient
	 * METHOD: POST
	 * RESPONSE: Patient
	 * PATH: /api/patient/post
	 * */
	@PostMapping("/post")
	public Patient postPatient(@RequestBody Patient patient) {
		return patientService.postPatient(patient);
	}
	
	/*
	 * AIM: fetch all patient
	 * METHOD: GET
	 * RESPONSE: List<Patient>
	 * PATH: /api/patient/get-all
	 * */
	@GetMapping("/get-all")
	public List<Patient> getAll() {
		return patientService.getAll();
	}
}
