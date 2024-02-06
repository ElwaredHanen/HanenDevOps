package com.coeurious.sublimation.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.coeurious.sublimation.constants.SublimationConstants.*;

@RestControllerAdvice
public class SublimationExceptionHandler {

    @ExceptionHandler({Exception.class})
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidParameterException(Exception ex) {
        String message = "";
        String description = "";
        Error error = null;
        if (ex instanceof MethodArgumentNotValidException validateException) {
            FieldError fieldError = validateException.getFieldError();
            String field = fieldError != null ? fieldError.getField() : StringUtils.EMPTY;
            Object rejectedValue = fieldError != null ? fieldError.getRejectedValue() : null;
            String msg = fieldError != null && rejectedValue != null && StringUtils.isNotBlank(rejectedValue.toString()) ? "is invalid!" : "is required!";
            message = getMessage("Field %s : %s", field, msg);
            if (field.equalsIgnoreCase("email")) {
                description = "Email must contain the character @ !";
            } else {

                description = THE_PASSWORD_MUST_CONTAIN_A_MINIMUM_OF_8_CHARACTERS + LINE_BREAK + ONE_UPPER_CASE
                        + LINE_BREAK + ONE_LOWER_CASE + LINE_BREAK + ONE_DIGIT + LINE_BREAK + ONE_SPECIAL_CHARACTER;
            }
            error = Error.builder()
                    .errorCode(((MethodArgumentNotValidException) ex).getStatusCode().value())
                    .description(description)
                    .message(message)
                    .build();
            return createErrorResponse(HttpStatus.BAD_REQUEST, error);
        }
        return null;
    }

    private String getMessage(String str, String field, String msg) {
        return String.format(DESC_ERROR_INVALID_BODY.concat(str), field, msg);
    }

    public ResponseEntity<Object> createErrorResponse(HttpStatus status, Error error) {

        ResponseEntity<Object> response = null;
        if (status != null) {
            response = new ResponseEntity<>(error, status);
        }

        return response;
    }

}
