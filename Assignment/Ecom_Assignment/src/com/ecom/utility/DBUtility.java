package com.ecom.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	
	private String url = "jdbc:mysql://localhost:3306/ecom_ass";
	private String user = "root";
	private String pass = "MySQL_566@Sree";
	private String drive = "com.mysql.cj.jdbc.Driver";
	private Connection con;
	
	private static DBUtility db = new DBUtility();
	private DBUtility() { }
	public static DBUtility getInstance() {
		return db;
	}
	
	public Connection connect() {
		try {
			Class.forName(drive);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
