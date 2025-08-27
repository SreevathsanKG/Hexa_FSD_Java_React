package com.test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lms.service.MyService;

public class MyServiceTest {

	MyService myService; 
	
	@BeforeEach
	public void init() {
		myService = new MyService();
	}
	
	@Test
	public void computePercentTest() {
		
		//Use Case 1: valid values 
		
		/* preparing input */
		double totalMarks = 500; 
		double marksSecured = 435;
		
		/*Call Service method */
		double actualPercent =  myService.computePercent(totalMarks, marksSecured);
		
		double expectedPercent = 87; 
		
		Assertions.assertEquals(expectedPercent, actualPercent);
		
		//Use Case 2: invalid marksSecured - Exception thrown 
		
		/*Call Service method */
		RuntimeException e = assertThrows(
				RuntimeException.class, ()-> {
			myService.computePercent(500, 535);
		});
		assertEquals("marks Scored cannot be more than total marks".toLowerCase(), 
				e.getMessage().toLowerCase());
		
		 
		//Use Case 3: Failed Exception 
		assertDoesNotThrow(()-> {
			myService.computePercent(500, 435);
		} ,"");
		
		//Use Case 4: negative marks scored 
		e = assertThrows(
				RuntimeException.class, ()-> {
			myService.computePercent(500, -78);
		});
		assertEquals("marks scored cannot be negative".toLowerCase(), 
				e.getMessage().toLowerCase());
	}
	
	
	@Test
	public void computeGradeTest() {

	    // Use Case 1: percent > 75 should return "A"
	    String grade = myService.computeGrade(85);
	    assertEquals("A", grade);

	    // Use Case 2: percent between 60 and 75 should return "B"
	    grade = myService.computeGrade(65);
	    assertEquals("B", grade);

	    // Use Case 3: percent <= 60 should return "C"
	    grade = myService.computeGrade(55);
	    assertEquals("C", grade);

	    // Use Case 4: percent == 75 should return "B"
	    grade = myService.computeGrade(75);
	    assertEquals("B", grade);

	    // Use Case 5: percent == 60 should return "C"
	    grade = myService.computeGrade(60);
	    assertEquals("C", grade);

	    // Use Case 6: percent > 100 should throw RuntimeException
	    RuntimeException e = assertThrows(RuntimeException.class, () -> {
	        myService.computeGrade(105);
	    });
	    assertEquals("Percent cannot be more than 100".toLowerCase(), e.getMessage().toLowerCase());

	    // Use Case 7: valid percent should not throw
	    assertDoesNotThrow(() -> {
	        myService.computeGrade(70);
	    });

	}	
}