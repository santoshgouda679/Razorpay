package com.employee_management_system.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> invalidOtp(InvalidOtpException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<String> otpExpired(OtpExpiredException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.GONE);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidOtp(MethodArgumentNotValidException exception) {
//    	String message =exception.getBindingResult().getFieldError().getDefaultMessage();
//    	 return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST); 
    	Map<String, String> map=new  HashMap<String, String>();
    	List<FieldError> fe= exception.getBindingResult().getFieldErrors();
    	for( FieldError f:fe) {
    		map.put(f.getField(), f.getDefaultMessage());
    	}
    	return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST); 
//    	
    }
}

