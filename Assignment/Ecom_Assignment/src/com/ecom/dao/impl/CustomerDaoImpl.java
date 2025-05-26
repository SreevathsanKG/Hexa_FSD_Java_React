package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public void insert(Customer customer) {
		Connection con = db.connect();
		String sql = "insert into customer values(?,?,?,?,?)";
		try {
			PreparedStatement stms = con.prepareStatement(sql);
			int id = (int) (Math.random() * 1000000);
			stms.setInt(1, id);
			stms.setString(2, customer.getName());
			stms.setString(3, customer.getCity());
			stms.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Customer> getAll() {
		Connection con = db.connect();
		List<Customer> list = new ArrayList<>();
		String sql = "select * from customer";
		try {
			PreparedStatement stms = con.prepareStatement(sql);
			ResultSet rst = stms.executeQuery();
			while (rst.next()) {
				Customer customer = new Customer(rst.getInt("id"),rst.getString("name"),rst.getString("city"));
				list.add(customer);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
}
