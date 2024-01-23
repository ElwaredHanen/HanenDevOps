package com.coeurious.sublimation.entities.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsersRegisterDto {
	
	@NotNull
	private String mail;
	
	@NotNull
	private String password;
	
	private Instant creationDate = Instant.now();
	
	private Instant modificationDate = Instant.now();
}
