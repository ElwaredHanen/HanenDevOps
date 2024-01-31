package com.coeurious.sublimation.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.coeurious.sublimation.dto.AuthDto;
import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService implements UserDetailsService {

    private final UsersRepository repository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthService(UsersRepository repository, PasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return repository.findByEmail(email);
    }

    public UserDetails signUp(AuthDto data) throws JWTVerificationException {
        if (repository.findByEmail(data.getEmail()) != null) {
            throw new JWTVerificationException("Email already exists");
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(data.getPassword());
        Users newUser = new Users(data.getEmail(), encryptedPassword, Instant.now(), Instant.now());
        return repository.save(newUser);
    }
}