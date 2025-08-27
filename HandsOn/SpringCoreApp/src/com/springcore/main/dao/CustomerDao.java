package com.springcore.main.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcore.main.model.Customer;
@Repository
public class CustomerDao {

	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CustomerDao(JdbcTemplate jdbcTemplate) { 
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createCustomerTable(){
		String sql="create table if not exists customer(id int primary key auto_increment, name varchar(255), city varchar(255))";
		jdbcTemplate.execute(sql);
	}
	
	public List<Customer> getAll() {
	    String sql = "select * from customer";
	    return jdbcTemplate.query(sql, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rst, int rowNum) throws SQLException {
				return new Customer(rst.getInt("id"),rst.getString("name"),rst.getString("city"));
			}
		});
	}
	
	public List<Map<String, Object>> getAllv2(){
		String sql = "select * from customer";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public void insertCustomer(String name, String city) {
		String sql="insert into customer(name,city) values (?,?)";
		jdbcTemplate.update(sql,name,city);
	}

	public Map<String, Object> getById(int id){
		String sql = "select * from customer where id=?";
		return jdbcTemplate.queryForMap(sql, id);
	}
	
	public void update(Customer customer) {
		String sql = "update customer set name=?,city=? where id=?";
		jdbcTemplate.update(sql,customer.getName(),customer.getCity(),customer.getId());
	}
}