package com.coeurious.sublimation.controller;

import com.coeurious.sublimation.config.TokenProvider;
import com.coeurious.sublimation.entities.RoleEnum;
import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.dto.AuthDto;
import com.coeurious.sublimation.exception.SublimationException;
import com.coeurious.sublimation.mapper.UsersMapperUsersRegisterDto;
import com.coeurious.sublimation.repository.UsersRepository;
import com.coeurious.sublimation.service.AuthService;
import com.coeurious.sublimation.utils.SublimationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.Charset;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
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
    @Mock
    SublimationUtils sublimationUtils;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    TokenProvider tokenProvider;
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
	void testSignup_Ok() throws SublimationException {
        //setup
        Mockito.when(authService.signUp(Mockito.any())).thenReturn(Mockito.any());
        response = loginController.signUp(userDto);
		assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
	}

    @Test
    void testSignup_KO() throws SublimationException{
        Mockito.when(authService.signUp(Mockito.any()))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null,
                        Charset.defaultCharset()));
        response = loginController.signUp(userDto);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is5xxServerError()).isTrue();
    }

    @Test
    void testSignin_Ok() {
        //setup
        Users users = new Users("test@test.pro", "12345");
        users.setId(1L);
        ReflectionTestUtils.setField(loginController, "tokenProvider", tokenProvider);
        ReflectionTestUtils.setField(tokenProvider, "jwtSecretUri", "2024");
        ReflectionTestUtils.setField(tokenProvider, "expireIn", 2);
        //Mock
        var authentication = new UsernamePasswordAuthenticationToken(users, "123456", users.getAuthorities());
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        response = loginController.signIn(userDto);

        //Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void testSignin_KO() {
        //setup
        Users users = new Users("test@test.pro", "12345");
        users.setId(1L);
        ReflectionTestUtils.setField(loginController, "tokenProvider", tokenProvider);
        ReflectionTestUtils.setField(tokenProvider, "jwtSecretUri", "2024");
        ReflectionTestUtils.setField(tokenProvider, "expireIn", 2);
        //Mock
        var authentication = new UsernamePasswordAuthenticationToken(users, "123456", users.getAuthorities());
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        response = loginController.signIn(userDto);

        //Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }


}
