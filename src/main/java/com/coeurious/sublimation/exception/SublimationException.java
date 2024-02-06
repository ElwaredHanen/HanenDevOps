package com.coeurious.sublimation.exception;

import lombok.*;

@Data
@Builder()
public class SublimationException extends Exception {

	private static final long serialVersionUID = 1190213429769305288L;

	private final int errorCode;

	private final int httpStatusCode;

	private final String message;

	private final Exception exception;

	private final String description;

}