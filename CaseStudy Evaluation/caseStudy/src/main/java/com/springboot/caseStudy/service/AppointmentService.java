package com.springboot.caseStudy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.caseStudy.exception.ResourceNotFoundException;
import com.springboot.caseStudy.model.Appointment;
import com.springboot.caseStudy.model.Doctor;
import com.springboot.caseStudy.model.Patient;
import com.springboot.caseStudy.repository.AppointmentRepository;
import com.springboot.caseStudy.repository.DoctorRepository;
import com.springboot.caseStudy.repository.PatientRepository;

@Service
public class AppointmentService {

	private AppointmentRepository appointmentRepository;
	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;

	public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository,
			PatientRepository patientRepository) {
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}

	// post or enroll appointment by id->patient,doctor
	public Appointment postAppointment(int patientId, int doctorId, LocalDate appointmentDate) {
		Appointment appointment = new Appointment();
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Patient ID is Invalid"));
		Doctor doctor = doctorRepository.findById(doctorId)
				.orElseThrow(() -> new RuntimeException("Doctor ID is Invalid"));
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		appointment.setAppointmentDate(appointmentDate);
		return appointmentRepository.save(appointment);
	}

	// fetch patients got check up by a doctor id
	public List<Appointment> getByDoctorId(int doctorId) {
		doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor ID is Invalid"));
		return appointmentRepository.getByDoctorId(doctorId).orElseThrow(
				() -> new ResourceNotFoundException("No Patient on the basis of given Doctor ID is found"));
	}
}
