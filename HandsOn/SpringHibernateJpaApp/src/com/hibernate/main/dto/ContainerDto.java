package com.hibernate.main.dto;

import java.util.List;

import com.hibernate.main.model.Course;
import com.hibernate.main.model.Module;
import com.hibernate.main.model.Video;

public class ContainerDto {

	private Course course;
	private List<Module> module;
	private List<Video> video;
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Module> getModule() {
		return module;
	}
	public void setModule(List<Module> module) {
		this.module = module;
	}
	public List<Video> getVideo() {
		return video;
	}
	public void setVideo(List<Video> video) {
		this.video = video;
	}
	@Override
	public String toString() {
		return "ContainerDto [course=" + course + ", module=" + module + ", video=" + video + "]";
	}
	
}
