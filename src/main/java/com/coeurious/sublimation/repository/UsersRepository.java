package com.coeurious.sublimation.repository;

import org.springframework.data.repository.CrudRepository;

import com.coeurious.sublimation.entities.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends CrudRepository<Users,Long>{
	UserDetails findByEmail(String email);
}
