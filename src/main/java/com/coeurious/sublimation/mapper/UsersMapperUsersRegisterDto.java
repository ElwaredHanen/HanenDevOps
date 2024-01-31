package com.coeurious.sublimation.mapper;

import com.coeurious.sublimation.dto.UsersRegisterDto;
import org.mapstruct.Mapper;

import com.coeurious.sublimation.entities.Users;

@Mapper
public interface UsersMapperUsersRegisterDto {
	
	UsersRegisterDto usersToUsersRegisterDto(Users user);
	Users usersRegisterDtoToUsers(UsersRegisterDto usersRegisterDto);
}
