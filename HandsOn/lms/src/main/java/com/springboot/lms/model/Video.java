package com.springboot.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "video_title")
	private String videoTitle;
	@Column(name = "video_time")
	private float videoTime;
	@Column(name = "video_code")
	private String videoCode;
	
	@ManyToOne			// creates cmodule_id as a foreign key in video table
	private CModule cmodule;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public float getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(float videoTime) {
		this.videoTime = videoTime;
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public CModule getCmodule() {
		return cmodule;
	}

	public void setCmodule(CModule cmodule) {
		this.cmodule = cmodule;
	}
	
}
