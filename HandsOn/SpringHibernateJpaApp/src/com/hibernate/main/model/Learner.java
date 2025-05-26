package com.hibernate.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity							// tell hibernate to create a table for this class in db
@Table(name = "learner")		// will create table with name learner
public class Learner {
	
	@Id							// this makes id a primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // make id auto increment
	private int id;
	@Column(nullable = false)	// this add NOT NULL constraint to name
	private String name;
	@Column(unique = true)		// this add UNIQUE constraint to email
	private String email;
	private String contact;
	
	public Learner() {	}

	public Learner(int id, String name, String email, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getContact() { return contact; }
	public void setContact(String contact) { this.contact = contact; }

	@Override
	public String toString() {
		return "Learner [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + "]";
	}
	
}
