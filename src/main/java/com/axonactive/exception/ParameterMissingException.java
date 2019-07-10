package com.axonactive.exception;

public class ParameterMissingException extends RuntimeException {

	private static final long serialVersionUID = 8128292837012703774L;

	public ParameterMissingException() {
		super();
	}
	public ParameterMissingException(String message) {
		super(message);
	}
}