package com.lms.model;

import com.lms.enums.Role;

public class User {
	
	private int id;
	private String username;
	private String passwrord;
	private Role role;
	
	public User() {	}

	public User(int id, String username, String passwrord, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.passwrord = passwrord;
		this.role = role;
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; } 
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPasswrord() { return passwrord; }
	public void setPasswrord(String passwrord) { this.passwrord = passwrord; }
	public Role getRole() { return role; }
	public void setRole(Role role) { this.role = role; }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwrord=" + passwrord + ", role=" + role + "]";
	}
	
}
