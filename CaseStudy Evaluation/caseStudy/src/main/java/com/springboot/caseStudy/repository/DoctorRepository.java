package com.springboot.caseStudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.caseStudy.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

	@Query("select d from Doctor d where d.user.username=?1")
	Doctor getDoctorByUsername(String username);
}
