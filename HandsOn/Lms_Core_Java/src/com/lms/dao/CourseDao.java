package com.lms.dao;

import java.util.List;

import com.lms.exception.InvalidIdException;
import com.lms.model.Course;

public interface CourseDao {
	void insert(Course course, int trackId);
	List<Course> getAll();
	List<Course> getByTrackId(int trackId);
	Course getById(int courseId) throws InvalidIdException;
}
