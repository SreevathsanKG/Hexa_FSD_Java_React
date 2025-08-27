package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.CModule;
import com.springboot.lms.service.CModuleService;

@RestController
@RequestMapping("/api/cmodule")
public class CModuleController {

	@Autowired
	private CModuleService cmoduleService;
	
	/*
	 * AIM: add module to db <-- need course id
	 * Method: POST
	 * Path: /api/cmodule/add/{courseid}
	 * Response: module with course_id of it
	 * Input: Module as request body, course id as pathvariable
	 * */
	@PostMapping("/add/{courseId}")
	public CModule postCModule(@PathVariable int courseId, @RequestBody CModule cmodule) {
		return cmoduleService.postModule(courseId, cmodule);
	}
	
	/*
	 * AIM: fetch all module by given course ID
	 * PARAM: courseId as request
	 * METHOD: GET
	 * PATH: /api/cmodule/get?courseId=1
	 * RESPONSE: List<CModule>
	 * */
	@GetMapping("/get")
	public List<CModule> getCModuleByCourseId(@RequestParam int courseId) {
		return cmoduleService.getCModuleByCourseId(courseId);
	}
}
