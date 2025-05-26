package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecom.dao.CategoryDao;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.utility.DBUtility;

public class CategoryDaoImpl implements CategoryDao {

	DBUtility db = DBUtility.getInstance();

	@Override
	public void insert(Category category) throws InvalidInputException {
		Connection con = db.connect();
		String sql = "INSERT into category values(?,?)";
		try {
			PreparedStatement stms = con.prepareStatement(sql);
			int id = (int) (Math.random() * 1000000);
			stms.setInt(1, id);
			stms.setString(2, category.getName());

			stms.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Category> getAll() {
		Connection con = db.connect();
		List<Category> list = new ArrayList<>();
		String sql = "select * from category";
		try {
			PreparedStatement stms = con.prepareStatement(sql);
			ResultSet rst = stms.executeQuery();
			while (rst.next()) {
				Category category = new Category();
				category.setId(rst.getInt("id"));
				category.setName(rst.getString("name"));

				list.add(category);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

}
