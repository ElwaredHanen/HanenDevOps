package com.coeurious.sublimation.controller;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.entities.dto.UsersRegisterDto;
import com.coeurious.sublimation.entities.mapper.UsersMapperUsersRegisterDto;
import com.coeurious.sublimation.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@Mock
    LoginService loginService;
    @Mock
    UsersMapperUsersRegisterDto usersMapperUsersRegisterDto;
    private UsersRegisterDto userDto;
    private ResponseEntity<String> response;
	@BeforeEach
	void setup() {
        userDto = new UsersRegisterDto();
		userDto.setPassword("12345");
        userDto.setMail("test@test.com");
	}

	@Test
	void testRegister_Ok() {
        //setup
        Users user = new Users();
        user.setId(1L);
        user.setPassword("12345");
        user.setMail("test@test.com");
        Mockito.when(usersMapperUsersRegisterDto.usersRegisterDtoToUsers(userDto)).thenReturn(user);
        Mockito.when(loginService.register(user)).thenReturn(user);
        response = loginController.registerUser(userDto);
		assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
	}

    @Test
    void testRegister_ID_0() {
        //setup
        Users user = new Users();
        user.setId(0L);
        user.setPassword("12345");
        user.setMail("test@test.com");
        Mockito.when(usersMapperUsersRegisterDto.usersRegisterDtoToUsers(userDto)).thenReturn(user);
        Mockito.when(loginService.register(user)).thenReturn(user);
        response = loginController.registerUser(userDto);
        assertThat(response).isNull();
    }

    @Test
    void testRegister_Exception() {
        //setup
        Users user = new Users();
        user.setId(0L);
        user.setPassword("12345");
        user.setMail("test@test.com");
        Mockito.when(usersMapperUsersRegisterDto.usersRegisterDtoToUsers(userDto)).thenReturn(user);
        Mockito.when(loginService.register(user))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null,
                        Charset.defaultCharset()));

        response = loginController.registerUser(userDto);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is5xxServerError()).isTrue();
    }


}
