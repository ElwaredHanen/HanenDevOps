package com.coeurious.sublimation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {

	@GetMapping("/About")
	public String about() {
		return "About";
	}
}
