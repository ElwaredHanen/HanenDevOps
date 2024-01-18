package com.coeurious.sublimation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {

	// Not secured REST API
	@GetMapping("/about")
	public String about() {
		return "About";
	}
	
	// Secure REST API
	@GetMapping("/secure_API")
	public String secure_API() {
		return "secure_API";
	}
	
	@GetMapping("/secure_API2")
	public String secure_API2() {
		return "secure_API";
	}
}
