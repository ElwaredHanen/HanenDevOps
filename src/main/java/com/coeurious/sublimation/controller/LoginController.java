package com.coeurious.sublimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;

@RestController
public class LoginController {
	
	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public LoginController(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Users user){
		Users savedUser = null;
		
		ResponseEntity<String> response = null;
		
		try {
			String hashPassword = passwordEncoder.encode(user.getPassword());
			
			user.setPassword(hashPassword);
			
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
	
//	@GetMapping("/login")
//	public ResponseEntity<String> login(@RequestBody Users user){
//		
//	}
}
