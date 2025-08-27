package com.lms.service;

public class MyService {
	
	public double computePercent(double totalMarks, double marksSecured) {
		
		if(marksSecured > totalMarks)
			throw new RuntimeException("marks scored cannot be more than total marks");
		
		if(marksSecured < 0)
			throw new RuntimeException("marks scored cannot be negative");
		
		double percent = (marksSecured*100)/totalMarks;
		return percent;
	}
	
	public String computeGrade(double percent) {
		
		if(percent > 100)
			throw new RuntimeException("percent cannot be more than 100");
		
		if(percent > 75)
			return "A";
		
		if(percent > 60)
			return "B";
		
		return "C";
	}
	
}
