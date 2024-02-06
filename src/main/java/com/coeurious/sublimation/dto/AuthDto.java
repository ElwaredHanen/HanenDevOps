package com.coeurious.sublimation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder()
public class AuthDto {

        @NotNull
        @JsonProperty("email")
        @Pattern(regexp = "^(.+)@(\\S+)$")
        private @Valid String email;
        @NotNull
        @JsonProperty("password")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
        private @Valid String password;
}