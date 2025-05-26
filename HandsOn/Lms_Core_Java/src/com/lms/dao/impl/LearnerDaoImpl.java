package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.LearnerDao;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;
import com.lms.utility.DBUtility;
import com.lms.utility.LearnerUtility;

public class LearnerDaoImpl implements LearnerDao{
	
	private LearnerUtility learnerUtility = new LearnerUtility();
	DBUtility db = DBUtility.getInstance();
	
	@Override
	public List<Learner> getAll() throws SQLException{
		Connection con = db.connect();
		List<Learner> list = new ArrayList<>();
		String sql = "Select * from learner";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()==true) {
				Learner learner = new Learner(
										rst.getInt("id"),
										rst.getString("name"),
										rst.getString("email"),null);
				list.add(learner);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		db.close();
		return list;
	}

	@Override
	public Learner getById(int id) throws InvalidIdException {
		Connection con = db.connect();
	    String sql = "SELECT * FROM learner WHERE id = ?";
	    Learner learner = null;
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            learner = new Learner();
	            learner.setId(rs.getInt("id"));
	            learner.setName(rs.getString("name"));
	            learner.setEmail(rs.getString("email"));
	        } else {
	            throw new InvalidIdException("Id given is Invalid");
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        db.close();
	    }
 
	    return learner;
	}

	@Override
	public void deleteById(int id) throws InvalidIdException {
		List<Learner> list = learnerUtility.getSampleData();
		int size=list.size();
		list =list.stream().filter(l->l.getId()!=id).toList();
		int resetSize=list.size();
		if(resetSize==size)
			throw new InvalidIdException("Could not find ID");
		LearnerUtility.setList(list);
	}

	@Override
	public Learner update(int id, Learner learner) throws InvalidIdException, InvalidInputException, SQLException {
		deleteById(id);
		List<Learner> list = getAll();
		List<Learner> newList = new ArrayList<>(list);
		newList.add(learner);
		LearnerUtility.setList(newList);
		return learner;
	}

	@Override
	public void insert(Learner learner) throws InvalidInputException, SQLException {
		Connection con = db.connect();
		int id = (int) (Math.random()*10000000);
		String sql = "insert into learner(id,name,email) values(?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setString(2, learner.getName());
			pstmt.setString(3, learner.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
	}
	
//	public static void main(String[] args) {
//		LearnerDaoImpl dao = new LearnerDaoImpl();
//		System.out.println("------------------------------------");
//		dao.getAll().stream()
//					.forEach(l-> System.out.println(l));
//		System.out.println("------------------------------------");
//		try {
//			int id =3; 
//			System.out.println("ID given " + id);
//			System.out.println(dao.getById(id)); 
//			
//			id =7; 
//			System.out.println("ID given " + id);
//			System.out.println(dao.getById(id));
//		} catch (InvalidIdException e) {
//			 System.out.println(e.getMessage());
//		}
//	}
}
