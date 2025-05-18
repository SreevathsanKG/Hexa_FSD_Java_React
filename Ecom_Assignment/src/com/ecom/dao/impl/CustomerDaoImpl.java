package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.dao.CustomerDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Customer;
import com.ecom.utility.DBUtility;

public class CustomerDaoImpl implements CustomerDao{
	
	DBUtility db = DBUtility.getInstance();

	@Override
	public Customer getById(int id) throws InvalidIdException {
		Customer customer = null;
		Connection con = db.connect();
		String sql = "select * from customer where id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				customer = new Customer(
						rst.getInt("id"),
						rst.getString("name"),
						rst.getString("city"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
		return customer;
	}

}
