package com.coeurious.sublimation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Error {

    private int errorCode;
    private String message;
    private String description;

}
