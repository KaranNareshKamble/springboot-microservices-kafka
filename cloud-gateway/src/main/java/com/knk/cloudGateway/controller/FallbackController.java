package com.knk.cloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {


	@GetMapping("/userServiceFallback")
	public String userServiceFallback() {
		return "USER-SERVICE is not available";
	}
	
	@GetMapping("/departmentServiceFallback")
	public String departmentServiceFallback() {
		return "DEPARTMENT-SERVICE is not available";
	}

}
