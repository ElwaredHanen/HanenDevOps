package com.coeurious.sublimation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder()
public class AuthDto {

        @NotNull
        private String email;
        @NotNull
        private String password;
}