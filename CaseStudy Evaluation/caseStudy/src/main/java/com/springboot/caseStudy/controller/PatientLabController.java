package com.springboot.caseStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caseStudy.model.PatientLab;
import com.springboot.caseStudy.service.PatientLabService;

@RestController
@RequestMapping("/api/patientLab")
public class PatientLabController {

	@Autowired
	private PatientLabService patientLabService;
	
	/*
	 * AIM: to fetch floor by patient ID
	 * PARAM: PathVariable -> PatientId
	 * METHOD: GET
	 * RESPONSE: List<PatientLab>
	 * PATH: /api/patientLab/get-by/{patientId}
	 * */
	@PostMapping("/get-by/{patientId}")
	public List<PatientLab> getByPatientId(@PathVariable int patientId) {
		return patientLabService.getByPatientId(patientId);
	}
}
