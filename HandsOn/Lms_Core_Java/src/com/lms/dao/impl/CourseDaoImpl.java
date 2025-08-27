package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.CourseDao;
import com.lms.exception.InvalidIdException;
import com.lms.model.Course;
import com.lms.model.Track;
import com.lms.utility.DBUtility;

public class CourseDaoImpl implements CourseDao{
	
	DBUtility db = DBUtility.getInstance();

	@Override
	public void insert(Course course, int trackId) {
		Connection con = db.connect();
		String sql = "insert into course values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getId());
			pstmt.setString(2, course.getTitle());
			pstmt.setDouble(3, course.getFee());
			pstmt.setDouble(4, course.getDiscount());
			pstmt.setString(5, course.getPublishDate().toString());
			pstmt.setInt(6,trackId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<Course> getAll() {
		Connection con = db.connect();
		String sql = "Select * from course c join track t ON c.track_id=t.id";
		List<Course> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()==true) {
				Course course = new Course();
				course.setId(rst.getInt("id"));
				course.setTitle(rst.getString("title"));
				course.setFee(rst.getDouble("fee"));
				course.setDiscount(rst.getDouble("discount"));
				Track track = new Track();
				track.setId(rst.getInt("track_id"));
				track.setName(rst.getString("name"));
				course.setTrack(track);
				list.add(course);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Course> getByTrackId(int trackId) {
		Connection con = db.connect();
		String sql = "Select * from course c join track t ON c.track_id=t.id where c.track_id=?";
		List<Course> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,trackId);
			ResultSet rst = pstmt.executeQuery();	
			while(rst.next()==true) {
				Course course = new Course();
				course.setId(rst.getInt("id"));
				course.setTitle(rst.getString("title"));
				course.setFee(rst.getDouble("fee"));
				course.setDiscount(rst.getDouble("discount"));
				Track track = new Track();
				track.setId(rst.getInt("track_id"));
				track.setName(rst.getString("name"));
				course.setTrack(track);
				list.add(course);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public Course getById(int courseId) throws InvalidIdException {
		Connection con = db.connect();
		String sql = "select * from course where id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Course course = new Course();
				course.setId(rst.getInt("id"));
				course.setTitle(rst.getString("title"));
				course.setFee(rst.getDouble("fee"));
				course.setDiscount(rst.getDouble("discount"));
				
				return course; 
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		throw new InvalidIdException("Course ID given is Invalid");
	}

}
