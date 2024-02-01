package com.coeurious.sublimation.utils;

import com.coeurious.sublimation.model.JsonResponse;
import org.springframework.http.HttpHeaders;

public class SublimationUtils {

    public static HttpHeaders generateHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return headers;
    }
    public static JsonResponse prepareResponse(String status, String message) {
        return JsonResponse.builder()
                .statusCode(status)
                .message(message)
                .build();
    }
}
