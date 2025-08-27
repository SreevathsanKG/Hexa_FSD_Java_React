package com.springboot.caseStudy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caseStudy.dto.MedicalHistoryDto;
import com.springboot.caseStudy.model.MedicalHistory;
import com.springboot.caseStudy.service.MedicalHistoryService;

@RestController
@RequestMapping("/api/medicalHistory")
public class MedicalHistoryController {

	@Autowired
	private MedicalHistoryService medicalHistoryService;
	
	// 1st API Question
	/*
	 * AIM: to insert medical history by patient with logged-in credentials
	 * PARAM: RequestBody -> MedicalHistory | Principal
	 * METHOD: POST
	 * RESPONSE: MedicalHistory
	 * PATH: /api/medicalHistory/post
	 * */
	@PostMapping("/post")
	public MedicalHistory postMedicalHistory(Principal principal, @RequestBody MedicalHistory medicalHistory) {
		String username = principal.getName();
		return medicalHistoryService.postMedicalHistory(username, medicalHistory);
	}
	
	// 4th API Question
	/*
	 * AIM: fetch all history by patient id
	 * PARAM: Pathvariable -> PatientID
	 * METHOD: GET
	 * RESPONSE: List<MedicalHistoryDto>
	 * PATH: /api/medicalHistory/get-by/{patientId}
	 * */
	@GetMapping("/get-by/{patientId}")
	public List<MedicalHistoryDto> getByPatientId(@PathVariable int patientId) {
		return medicalHistoryService.getByPatientId(patientId);
	}
}
