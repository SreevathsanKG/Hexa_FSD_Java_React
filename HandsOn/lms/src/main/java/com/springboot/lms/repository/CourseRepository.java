package com.springboot.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.lms.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	@Query("select c from Course c where c.author.user.username=?1")
	List<Course> getCourseByAuthor(String Username);
}
