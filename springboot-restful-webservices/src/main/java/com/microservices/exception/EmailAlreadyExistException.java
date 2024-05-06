package com.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class EmailAlreadyExistException extends RuntimeException {

	private String message;

	public EmailAlreadyExistException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
}
