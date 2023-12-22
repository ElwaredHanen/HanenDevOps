package com.coeurious.sublimation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SublimationApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SublimationApplication.class);

	public static void main(String[] args) {
		logger.debug("Start the application");
		SpringApplication.run(SublimationApplication.class, args);
	}

}
