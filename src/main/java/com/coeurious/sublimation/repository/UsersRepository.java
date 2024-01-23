package com.coeurious.sublimation.repository;

import org.springframework.data.repository.CrudRepository;

import com.coeurious.sublimation.entities.Users;

public interface UsersRepository extends CrudRepository<Users,Long>{
	Users findByMail(String mail);

}
