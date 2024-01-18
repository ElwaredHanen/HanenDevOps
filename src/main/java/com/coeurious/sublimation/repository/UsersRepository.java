package com.coeurious.sublimation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coeurious.sublimation.entities.Users;

public interface UsersRepository extends CrudRepository<Users,Long>{
	Users findByMail(String mail);

}
