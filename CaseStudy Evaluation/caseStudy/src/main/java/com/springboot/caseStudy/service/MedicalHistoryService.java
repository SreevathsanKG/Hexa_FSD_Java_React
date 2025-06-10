package com.springboot.caseStudy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.caseStudy.dto.MedicalHistoryDto;
import com.springboot.caseStudy.model.MedicalHistory;
import com.springboot.caseStudy.model.Patient;
import com.springboot.caseStudy.repository.MedicalHistoryRepository;
import com.springboot.caseStudy.repository.PatientRepository;

@Service
public class MedicalHistoryService {

	private MedicalHistoryRepository medicalHistoryRepository;
	private PatientRepository patientRepository;

	public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository) {
		this.medicalHistoryRepository = medicalHistoryRepository;
		this.patientRepository = patientRepository;
	}
	
	// insert medical history of patient
	public MedicalHistory postMedicalHistory(String username, MedicalHistory medicalHistory) {
		Patient patient = patientRepository.getPatientByUsername(username);
		medicalHistory.setPatient(patient);
		return medicalHistoryRepository.save(medicalHistory);
	}

	// fetch all history by patient Id
	public List<MedicalHistoryDto> getByPatientId(int patientId) {
		patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient ID is Invalid"));
		List<MedicalHistory> list =  medicalHistoryRepository.getByPatientId(patientId);
		return list.stream().map(l -> {
			MedicalHistoryDto dto = new MedicalHistoryDto();
			dto.setIllness(l.getIllness());
			dto.setNumOfYears(l.getNumOfYears());
			dto.setCurrentMedication(l.getCurrentMedication());
			return dto;
		}).toList();
	}
}
