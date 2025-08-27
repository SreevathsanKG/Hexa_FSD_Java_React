package com.ecom.exception;

public class InvalidCouponException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidCouponException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
}
