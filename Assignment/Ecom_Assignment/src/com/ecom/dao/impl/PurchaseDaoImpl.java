package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecom.dao.PurchaseDao;
import com.ecom.model.Purchase;
import com.ecom.utility.DBUtility;

public class PurchaseDaoImpl implements PurchaseDao {
	
	DBUtility db = DBUtility.getInstance();

	@Override
	public void insert(Purchase purchase) {
		Connection con = db.connect();
		String sql = "insert into purchase values(?,?,?,?,?,?)";
		int id = (int)(Math.random()*10000);
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, purchase.getPurchaseDate().toString());
			pstmt.setDouble(3, purchase.getAmountPaid());
			pstmt.setString(4, String.valueOf(purchase.getCoupon()));
			pstmt.setInt(5, purchase.getCustomer().getId());
			pstmt.setInt(6, purchase.getProduct().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
	}


}
