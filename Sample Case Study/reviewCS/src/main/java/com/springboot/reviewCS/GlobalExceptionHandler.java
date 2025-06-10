package com.springboot.reviewCS;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");
	
	@ExceptionHandler(exception = RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException e) {
		Map<String,String> map = new HashMap<>();
		logger.info(e.getMessage());
		map.put("msg", e.getMessage());
		logger.error(e.getMessage(), e.getClass());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
}
