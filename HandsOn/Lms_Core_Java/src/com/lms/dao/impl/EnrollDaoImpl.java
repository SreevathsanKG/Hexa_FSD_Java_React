package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.dao.EnrollDao;
import com.lms.model.Enroll;
import com.lms.utility.DBUtility;

public class EnrollDaoImpl implements EnrollDao {
	
	DBUtility db = DBUtility.getInstance();

	@Override
	public void insert(Enroll enroll) {
		Connection con = db.connect();
		String sql = "insert into enroll values (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, enroll.getDateOfEnroll().toString());
			pstmt.setString(2, String.valueOf(enroll.getCouponUsed()));
			pstmt.setString(3, enroll.getFeePaid());
			pstmt.setInt(4, enroll.getLearner().getId());
			pstmt.setInt(5, enroll.getCourse().getId());
			
			
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

}
