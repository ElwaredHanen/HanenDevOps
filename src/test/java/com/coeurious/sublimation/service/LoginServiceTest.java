package com.coeurious.sublimation.service;

import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    LoginService loginService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UsersRepository usersRepository;
    @Test
    void register_OK(){
        //Setup
        Users user = new Users();
        user.setId(1L);
        user.setPassword("12345");
        user.setMail("test@test.com");
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("x4cdoftera2s8d3cfr");
        Mockito.when(usersRepository.save(user)).thenReturn(user);

        user = loginService.register(user);
        assertThat(user.getPassword()).isEqualTo("x4cdoftera2s8d3cfr");
    }
}
