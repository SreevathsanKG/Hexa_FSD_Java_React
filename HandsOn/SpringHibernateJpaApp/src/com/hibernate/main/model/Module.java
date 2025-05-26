package com.hibernate.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "module")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false, name = "module_name")
	private String moduleName;
	@ManyToOne
	private Course course;
	
	public Module() {	}

	public Module(int id, String moduleName, Course course) {
		super();
		this.id = id;
		this.moduleName = moduleName;
		this.course = course;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getModuleName() { return moduleName; }
	public void setModuleName(String moduleName) { this.moduleName = moduleName; }
	public Course getCourse() { return course; }
	public void setCourse(Course course) { this.course = course; }

	@Override
	public String toString() {
		return "Module [id=" + id + ", moduleName=" + moduleName + ", course=" + course + "]";
	}
	
}
