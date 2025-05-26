package com.lms.service;

import java.time.LocalDate;
import java.util.Scanner;

import com.lms.dao.CourseDao;
import com.lms.dao.EnrollDao;
import com.lms.dao.LearnerDao;
import com.lms.dao.impl.CourseDaoImpl;
import com.lms.dao.impl.EnrollDaoImpl;
import com.lms.dao.impl.LearnerDaoImpl;
import com.lms.enums.Coupon;
import com.lms.exception.InvalidCouponException;
import com.lms.exception.InvalidIdException;
import com.lms.model.Course;
import com.lms.model.Enroll;
import com.lms.model.Learner;

public class EnrollService {
	
	private EnrollDao enrollDao = new EnrollDaoImpl();
	private LearnerDao learnerDao = new LearnerDaoImpl();
	private CourseDao courseDao = new CourseDaoImpl();

	public void enroll(int learnerId, int courseId, Scanner sc) throws InvalidIdException, InvalidCouponException {
		Enroll enroll = new Enroll(); 
		 /*
		  * step 1: validate learnerId
		  * return Learner objet
		  * */
		Learner learner =  learnerDao.getById(learnerId);
		enroll.setLearner(learner);
		/*
		 * Step 2: Validate courseId
		 * return Course object
		 * */
		Course course =  courseDao.getById(courseId); 
		enroll.setCourse(course);
		/*
		 * Step 3: Ask if any coupon code is available 
		 * if yes, adjust the fee accordingly 
		 * */
		System.out.println("Do you have a coupon?(Y/N) ");
		String ans = sc.next();
		if(ans.equals("Y")) {
			System.out.println("Enter the code ");
			String couponCode = sc.next().toUpperCase();
			/* Check: if this couponCode is valid 
			 * throws Illegal Argument Exception*/ 
			try {				
				Coupon coupon = Coupon.valueOf(couponCode); 
				double discount = (double)coupon.getDiscount();
				System.out.println("Discount = " + discount);
				double discountedFee = course.getFee() - (course.getFee() * (discount/100)) ;
				System.out.println("After Discount, Fee is " + discountedFee);
				enroll.setCouponUsed(coupon);
				enroll.setFeePaid( String.valueOf(discountedFee));
			} catch (IllegalArgumentException e) {
				throw new InvalidCouponException("Invalid Coupon Code");
			}
		}
		else {
			System.out.println("No Coupon applied.....");
			enroll.setFeePaid(String.valueOf(course.getFee()));
		}
		
		enroll.setDateOfEnroll(LocalDate.now()); 
		
		/*
		 * Save 4: Enroll Object in DB via enrollDao
		 * */
		enrollDao.insert(enroll);
	}

}
