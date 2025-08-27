package com.springboot.caseStudy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.caseStudy.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query("select a from Appointment a where a.doctor.id=?1")
	Optional<List<Appointment>> getByDoctorId(int doctorId);
}
