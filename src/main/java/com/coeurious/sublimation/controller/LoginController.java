package com.coeurious.sublimation.controller;

import com.coeurious.sublimation.config.TokenProvider;
import com.coeurious.sublimation.entities.Users;
import com.coeurious.sublimation.dto.JwtDto;
import com.coeurious.sublimation.dto.AuthDto;
import com.coeurious.sublimation.model.JsonResponse;
import com.coeurious.sublimation.service.AuthService;
import com.coeurious.sublimation.utils.SublimationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, AuthService authService, TokenProvider tokenService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenProvider = tokenService;
    }

    @PostMapping(value = "/signup", produces = { "application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
    public ResponseEntity<JsonResponse> signUp(@RequestBody @Valid AuthDto data) {
        JsonResponse json = null;
        try {
            authService.signUp(data);
            json = SublimationUtils.prepareResponse(HttpStatus.CREATED.toString(), "User Created");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(json);
        } catch (Exception ex) {
            json = SublimationUtils.prepareResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(json);
        }
    }

    @PostMapping(value = "/signin", produces = { "application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
    public ResponseEntity<JsonResponse> signIn(@RequestBody @Valid AuthDto data) {
        JsonResponse json = null;
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
            var authUser = authenticationManager.authenticate(usernamePassword);
            var accessToken = tokenProvider.generateAccessToken((Users) authUser.getPrincipal());
            HttpHeaders headers = SublimationUtils.generateHeader(accessToken);
            json = SublimationUtils.prepareResponse(HttpStatus.OK.toString(), "User Connected");
            return new ResponseEntity<>(json, headers, HttpStatus.OK);

        } catch (Exception ex) {
            json = SublimationUtils.prepareResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(json);
        }
    }

    @PostMapping("/testCreate")
    public ResponseEntity<JwtDto> testCreate() {

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}