package com.coeurious.sublimation.entities;


public enum RoleEnum {
	ADMIN("ADMIN"),
	USER("USER");

	private String role;

	RoleEnum(String role) {
		this.role = role;
	}

	public String getValue() {
		return role;
	}
}
