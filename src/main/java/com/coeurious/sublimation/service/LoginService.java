package com.coeurious.sublimation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;

@Service
public class LoginService {
	
	private final PasswordEncoder passwordEncoder;
	private final UsersRepository usersRepository;
	
	@Autowired
	public LoginService(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
		this.passwordEncoder = passwordEncoder;
		this.usersRepository = usersRepository;
	}
	
	public Users register(Users user) {
		Users savedUser = null;
		String hashPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(hashPassword);
		
		savedUser = usersRepository.save(user);
		
		return savedUser;
	}

}
