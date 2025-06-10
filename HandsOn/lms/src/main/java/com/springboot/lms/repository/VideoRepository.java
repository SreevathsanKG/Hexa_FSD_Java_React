package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Video;

public interface VideoRepsitory extends JpaRepository<Video, Integer>{

}
