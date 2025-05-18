package com.lms.model;

public class Learner {
	private int id;
	private String name;
	private String email;
	
	/* Create Constructor */
	
	public Learner() {	}

	public Learner(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	/* Getter and Setter */

	public int getId() { return id;	}
	public void setId(int id) {	this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	/* to string */
	
	@Override
	public String toString() {
		return "Learner [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
