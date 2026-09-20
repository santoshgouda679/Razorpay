package com.example.bank_system.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.JwtException;

/**
 * Central place that turns every exception into a consistent JSON error
 * response with the right HTTP status code, instead of leaking a raw 500
 * with a stack trace (or, as in the original code, a 200 OK with a plain
 * error String mixed in where an object was expected).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
		return build(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
		Map<String, String> fieldErrors = new LinkedHashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(fe -> fieldErrors.put(fe.getField(), fe.getDefaultMessage()));
		return build(HttpStatus.BAD_REQUEST, "Validation failed", request, fieldErrors);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		return build(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex, WebRequest request) {
		return build(HttpStatus.UNAUTHORIZED, "Invalid username or password", request, null);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
		return build(HttpStatus.FORBIDDEN, "You do not have permission to perform this action", request, null);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ErrorResponse> handleJwt(JwtException ex, WebRequest request) {
		return build(HttpStatus.UNAUTHORIZED, "Invalid or expired token", request, null);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, WebRequest request) {
		return build(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again later.", request, null);
	}

	private ResponseEntity<ErrorResponse> build(HttpStatus status, String message, WebRequest request,
			Map<String, String> fieldErrors) {
		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(status.value());
		error.setError(status.getReasonPhrase());
		error.setMessage(message);
		error.setPath(request.getDescription(false).replace("uri=", ""));
		error.setFieldErrors(fieldErrors);
		return ResponseEntity.status(status).body(error);
	}
}
