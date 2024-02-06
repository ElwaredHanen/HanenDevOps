package com.coeurious.sublimation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonResponse {

    private String statusCode;
    private String message;
}
