package com.coeurious.sublimation.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with", toBuilder = true)
public class UsersRegisterDto {
	
	@NotNull
	private String email;
	@NotNull
	private String password;
	private Instant creationDate = Instant.now();
	private Instant modificationDate = Instant.now();
}
