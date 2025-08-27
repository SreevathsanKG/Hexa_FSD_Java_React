package com.springboot.caseStudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.caseStudy.model.PatientLab;

public interface PatientLabRepository extends JpaRepository<PatientLab, Integer>{

	@Query("select pl from PatientLab pl where pl.patient.id=?1")
	List<PatientLab> getByPatientId(int patientId);

}
