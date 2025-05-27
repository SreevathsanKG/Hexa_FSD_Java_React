package com.springboot.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
