package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Video;
import com.springboot.lms.service.VideoService;

@RestController
@RequestMapping("/api/video")
@CrossOrigin(origins = "http://localhost:5173")
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@PostMapping("/add/{moduleId}")
	public ResponseEntity<?> batchInsert(@PathVariable int moduleId, @RequestBody List<Video> list){
		videoService.batchInsert(list, moduleId);
		return ResponseEntity.ok().body("Insert Completed!!");
	}
	
	@GetMapping("/get-by/courseId/{courseId}")
	public List<Video> getVideoByCourseId(@PathVariable int courseId) {
		return videoService.getVideoByCourseId(courseId);
	}
}
