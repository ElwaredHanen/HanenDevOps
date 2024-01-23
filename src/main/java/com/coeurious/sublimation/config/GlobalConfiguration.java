package com.coeurious.sublimation.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GlobalConfiguration {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
