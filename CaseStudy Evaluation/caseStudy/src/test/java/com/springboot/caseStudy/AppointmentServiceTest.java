package com.springboot.caseStudy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.caseStudy.enums.Speciality;
import com.springboot.caseStudy.model.Appointment;
import com.springboot.caseStudy.model.Doctor;
import com.springboot.caseStudy.model.Patient;
import com.springboot.caseStudy.model.User;
import com.springboot.caseStudy.repository.AppointmentRepository;
import com.springboot.caseStudy.repository.DoctorRepository;
import com.springboot.caseStudy.service.AppointmentService;

@SpringBootTest
public class AppointmentServiceTest {

	@InjectMocks
	private AppointmentService appointmentService;
	@Mock
	private AppointmentRepository appointmentRepository;
	@Mock
	private DoctorRepository doctorRepository;
	
	private Appointment appointment;
	private Doctor doctor;
	private Patient patient;
	private User userPatient;
	private User userDoctor;
	
	@BeforeEach
	public void init() {
		userPatient = new User();
		userPatient.setId(1);
		userPatient.setUsername("john@gmail.com");
		userPatient.setPassword("john@123");
		userPatient.setRole("PATIENT");
		
		userDoctor = new User();
		userDoctor.setId(2);
		userDoctor.setUsername("david@gmail.com");
		userDoctor.setPassword("david@123");
		userDoctor.setRole("DOCTOR");
		
		patient = new Patient();
		patient.setId(1);
		patient.setName("John");
		patient.setAge(30);
		patient.setUser(userPatient);
		
		doctor = new Doctor();
		doctor.setId(1);
		doctor.setName("David");
		doctor.setSpeciality(Speciality.PHYSICIAN);
		
		appointment = new Appointment();
		appointment.setId(1);
		appointment.setAppointmentDate(LocalDate.parse("2025-06-09"));
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
	}
	
	@Test
	public void getByDoctorId() {
		List<Appointment> expectedList = List.of(appointment);
		when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
		when(appointmentRepository.getByDoctorId(1)).thenReturn(Optional.of(expectedList));
		
		List<Appointment> actualList = appointmentService.getByDoctorId(1);
		assertEquals(expectedList, actualList);
		
		RuntimeException e = assertThrows(RuntimeException.class, ()-> appointmentService.getByDoctorId(2));
		assertEquals("Doctor ID is Invalid".toLowerCase(), e.getMessage().toLowerCase());
	}
}
