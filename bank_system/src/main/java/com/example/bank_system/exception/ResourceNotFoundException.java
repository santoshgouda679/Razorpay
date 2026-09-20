package com.example.bank_system.exception;

/**
 * Thrown when a requested Bank account (or other resource) does not exist.
 * Mapped to HTTP 404 by GlobalExceptionHandler.
 */
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
