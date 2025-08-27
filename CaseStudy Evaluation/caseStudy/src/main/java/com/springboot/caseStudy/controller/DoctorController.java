package com.springboot.caseStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caseStudy.model.Doctor;
import com.springboot.caseStudy.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	/*
	 * AIM: to insert doctor
	 * PARAM: RequestBody -> Doctor
	 * METHOD: POST
	 * RESPONSE: Doctor
	 * PATH: /api/doctor/post
	 * */
	@PostMapping("/post")
	public Doctor postDoctor(@RequestBody Doctor doctor) {
		return doctorService.postDoctor(doctor);
	}
	
	/*
	 * AIM: fetch all doctor
	 * METHOD: GET
	 * RESPONSE: List<Doctor>
	 * PATH: /api/doctor/get-all
	 * */
	@GetMapping("/get-all")
	public List<Doctor> getAll() {
		return doctorService.getAll();
	}
}
