package com.hibernate.main.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "learner_course")
public class LearnerCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Learner learner;
	@ManyToOne
	private Course course;
	@Column(name = "enrollment_date")
	private LocalDate enrollmentDate;
	@Column(name = "coupon_code")
	private String couponCode;
	
	public LearnerCourse() {	}

	public LearnerCourse(int id, Learner learner, Course course, LocalDate enrollmentDate, String couponCode) {
		super();
		this.id = id;
		this.learner = learner;
		this.course = course;
		this.enrollmentDate = enrollmentDate;
		this.couponCode = couponCode;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public Learner getLearner() { return learner; }
	public void setLearner(Learner learner) { this.learner = learner; }
	public Course getCourse() { return course; }
	public void setCourse(Course course) { this.course = course; }
	public LocalDate getEnrollmentDate() { return enrollmentDate; }
	public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
	public String getCouponCode() { return couponCode; }
	public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

	@Override
	public String toString() {
		return "LearnerCourse [id=" + id + ", learner=" + learner + ", course=" + course + ", enrollmentDate="
				+ enrollmentDate + ", couponCode=" + couponCode + "]";
	}
	
}
