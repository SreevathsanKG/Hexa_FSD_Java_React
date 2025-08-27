package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.lms.exception.InvalidInputException;
import com.springboot.lms.model.CModule;
import com.springboot.lms.model.Video;
import com.springboot.lms.repository.CModuleRepository;
import com.springboot.lms.repository.VideoRepository;

@Service
public class VideoService {

	public VideoRepository videoRepository;
	public CModuleRepository cModuleRepository;
	
	public VideoService(VideoRepository videoRepository, CModuleRepository cModuleRepository) {
		this.videoRepository = videoRepository;
		this.cModuleRepository = cModuleRepository;
	}
	
	@Transactional
    public void batchInsert(List<Video> list, int moduleId) {
        CModule module = cModuleRepository.findById(moduleId)
                .orElseThrow(() -> new InvalidInputException("Module Id Invalid"));
        if (list.isEmpty())
            throw new InvalidInputException("No Data Found");

        list.parallelStream().forEach(v -> v.setCmodule(module));
        videoRepository.saveAll(list);
    }
	
	public List<Video> getVideoByCourseId(int courseId) {
		return videoRepository.getVideoByCourseId(courseId);
	}
}
