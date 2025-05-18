package com.lms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	
	private String url = "jdbc:mysql://localhost:3306/lms_db";
	private String userDB = "root";
	private String passDB = "MySQL_566@Sree";
	private String driver = "com.mysql.cj.jdbc.Driver";
	Connection con;
	
	public Connection connect() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, userDB, passDB);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
	
	public void close() {
		try {
			if(!con.isClosed())
				con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
