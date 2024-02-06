package com.coeurious.sublimation.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.coeurious.sublimation.dto.AuthDto;
import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UsersRepository usersRepository;

    private AuthDto userDto;
    @BeforeEach
    void setup() {
        userDto = AuthDto.builder()
                .password("12345")
                .email("test@test.com")
                .build();
    }
    @Test
    void signup_OK(){
        //Setup
        Users user = new Users();
        user.setId(1L);
        user.setPassword("12345");
        user.setEmail("test@test.com");
        Mockito.when(usersRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("12345");
        Mockito.when(usersRepository.save(Mockito.any())).thenReturn(user);

        user = (Users) authService.signUp(userDto);
        assertThat(user.getPassword()).isEqualTo("12345");
    }
    @Test
    void signup_KO() {
        //Setup
        Users user = new Users();
        user.setId(1L);
        user.setPassword("12345");
        user.setEmail("test@test.com");
        try {
            /*Mockito.when(usersRepository.findByEmail(user.getEmail()))
                    .thenThrow(new JWTVerificationException("Email already exists"));*/
            Mockito.doThrow(new JWTVerificationException("Email already exists"))
                    .when(usersRepository).findByEmail(user.getEmail());


            user = (Users) authService.signUp(userDto);
            assertThat(user).isNull();
        }catch (Exception e){

        }
    }
}
