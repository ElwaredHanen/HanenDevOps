package com.coeurious.sublimation.entities.mapper;

import org.mapstruct.Mapper;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.entities.dto.UsersRegisterDto;

@Mapper
public interface UsersMapperUsersRegisterDto {
	
	UsersRegisterDto usersToUsersRegisterDto(Users user);
	Users usersRegisterDtoToUsers(UsersRegisterDto usersRegisterDto);
}
