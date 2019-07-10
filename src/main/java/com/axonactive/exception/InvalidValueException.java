package com.axonactive.exception;

public class InvalidValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidValueException() {
		
	}
	public InvalidValueException(String message) {
		super(message);
	}
}