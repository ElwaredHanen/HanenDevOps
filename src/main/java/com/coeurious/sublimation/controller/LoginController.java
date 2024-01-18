package com.coeurious.sublimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;

@RestController
public class LoginController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Users user){
		Users savedUser = null;
		
		ResponseEntity<String> response = null;
		
		try {
			savedUser = usersRepository.save(user);
			if (savedUser.getId() > 0) {
				response = ResponseEntity
						.status(HttpStatus.CREATED)
						.body("User created");
			}
		} catch (Exception ex) {
			response = ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to : " + ex.getMessage());
		}
		
		return response;
	}
}
