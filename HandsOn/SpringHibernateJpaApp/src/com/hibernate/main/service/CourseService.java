package com.hibernate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.main.dto.ContainerDto;
import com.hibernate.main.model.Module;
import com.hibernate.main.model.Video;
import com.hibernate.main.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public ContainerDto getById(int id) {
		List<Video> listVideo = courseRepository.getAllVideo();
		listVideo = listVideo.stream().filter(v-> v.getModule().getCourse().getId()==id).toList();
		List<Module> listModule = listVideo.stream().map(v-> v.getModule()).distinct().toList();
		ContainerDto dto = new ContainerDto();
		System.out.println(listVideo);
		System.out.println("..................");
		System.out.println(listModule);
		System.out.println("..................");
		dto.setCourse(listModule.get(0).getCourse());
		dto.setModule(listModule);
		dto.setVideo(listVideo);
		return dto;
	}
	
}
