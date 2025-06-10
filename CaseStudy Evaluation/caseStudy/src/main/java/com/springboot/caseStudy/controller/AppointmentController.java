package com.springboot.caseStudy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.caseStudy.model.Appointment;
import com.springboot.caseStudy.service.AppointmentService;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	// 	2nd API Question
	/*
	 * AIM: to insert doctor
	 * PARAM: RequestParam -> AppointmentDate, PathVariable -> patientId, doctorId
	 * METHOD: POST
	 * RESPONSE: appointment
	 * PATH: /api/appointment/post/{patientId}/{doctorId}?appointmenDate=2025-06-07
	 * */
	@PostMapping("/post/{patientId}/{doctorId}")
	public Appointment postAppointment(@PathVariable int patientId, @PathVariable int doctorId, @RequestParam LocalDate appointmentDate) {
		return appointmentService.postAppointment(patientId, doctorId, appointmentDate);
	}
	
	// 3rd API Question
	/*
	 * AIM: to insert doctor
	 * PARAM: RequestBody -> Doctor
	 * METHOD: POST
	 * RESPONSE: appointment
	 * PATH: /api/appointment/get-by/{doctorId}
	 * */
	@GetMapping("/get-by/{doctorId}")
	public List<Appointment> getByDoctorId(@PathVariable int doctorId) {
		return appointmentService.getByDoctorId(doctorId);
	}
}
