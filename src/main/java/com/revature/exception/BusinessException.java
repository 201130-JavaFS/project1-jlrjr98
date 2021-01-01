package com.revature.exception;

//Custom Exception class
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(final String message) {
		super(message);
	}		
	
}
