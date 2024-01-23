package com.coeurious.sublimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.entities.dto.UsersRegisterDto;
import com.coeurious.sublimation.entities.mapper.UsersMapperUsersRegisterDto;
import com.coeurious.sublimation.service.LoginService;

@RestController
public class LoginController {
	
    private final UsersMapperUsersRegisterDto usersMapperUsersRegisterDto;
    private final LoginService loginService;
	
	@Autowired
	public LoginController(UsersMapperUsersRegisterDto usersMapperUsersRegisterDto, LoginService loginService) {
		this.usersMapperUsersRegisterDto = usersMapperUsersRegisterDto;
		this.loginService = loginService;
	}
	
	/**
	 * Register a new user in the database
	 * 
	 * @param userDto : The user to register
	 * @return : 201 if the user is created, 500 otherwise
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UsersRegisterDto userDto){
		ResponseEntity<String> response = null;
		
		Users user = usersMapperUsersRegisterDto.usersRegisterDtoToUsers(userDto);
		
		try {
			Users registeredUser = loginService.register(user);
			
			if (registeredUser.getId() > 0) {
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
