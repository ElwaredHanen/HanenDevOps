package com.coeurious.sublimation.controller;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.dto.AuthDto;
import com.coeurious.sublimation.mapper.UsersMapperUsersRegisterDto;
import com.coeurious.sublimation.repository.UsersRepository;
import com.coeurious.sublimation.service.AuthService;
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
    AuthService authService;
    @Mock
    UsersMapperUsersRegisterDto usersMapperUsersRegisterDto;
    @Mock
    UsersRepository usersRepository;
    private AuthDto userDto;
    private ResponseEntity<?> response;
	@BeforeEach
	void setup() {
        userDto = AuthDto.builder()
                .password("12345")
                .email("test@test.com")
                .build();
	}

	@Test
	void testSignup_Ok() {
        //setup
        Mockito.when(authService.signUp(Mockito.any())).thenReturn(Mockito.any());
        response = loginController.signUp(userDto);
		assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
	}

    @Test
    void testSignup_KO() {
        Mockito.when(authService.signUp(Mockito.any()))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null,
                        Charset.defaultCharset()));
        response = loginController.signUp(userDto);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is5xxServerError()).isTrue();
    }

    @Test
    void testRegister_Exception() {
        //setup
        Users user = new Users();
        user.setId(0L);
        user.setPassword("12345");
        user.setEmail("test@test.com");
       // Mockito.when(usersMapperUsersRegisterDto.usersRegisterDtoToUsers(userDto)).thenReturn(user);
        /*Mockito.when(authService.register(user))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null,
                        Charset.defaultCharset()));

        response = loginController.registerUser(userDto);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is5xxServerError()).isTrue();*/
    }


}
