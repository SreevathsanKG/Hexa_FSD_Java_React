package com.springboot.bankDemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String first_name;
	@Column(nullable = false)
	private String last_name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String phone_number;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private LocalDate registration_date;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getfirst_name() { return first_name; }
	public void setfirst_name(String first_name) { this.first_name = first_name; }
	public String getlast_name() { return last_name; }
	public void setlast_name(String last_name) { this.last_name = last_name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getphone_number() { return phone_number; }
	public void setphone_number(String phone_number) { this.phone_number = phone_number; }
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public LocalDate getregistration_date() { return registration_date; }
	public void setregistration_date(LocalDate registration_date) { this.registration_date = registration_date; }
	
	
}
