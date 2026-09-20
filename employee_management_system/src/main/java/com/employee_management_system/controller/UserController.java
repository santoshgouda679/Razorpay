package com.employee_management_system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_management_system.dto.RegisterRequest;
import com.employee_management_system.dto.VerifyOtpRequest;
import com.employee_management_system.service.OtpVerifyService;
import com.employee_management_system.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
 private UserService userService;
 private OtpVerifyService otpVerifyService;

 
 public UserController(UserService userService, OtpVerifyService otpVerifyService) {
	
	this.userService = userService;
	this.otpVerifyService = otpVerifyService;
}
 @PostMapping("/register")
 public String registerRequest(@RequestBody  RegisterRequest registerRequest) {
	 userService.registerRequest(registerRequest);
	 return "registered";
	
}
 @PostMapping("/verify")
 public String otpVerification(@RequestBody VerifyOtpRequest registerRequest) {
	otpVerifyService.otpVerification(registerRequest);
	 return "verified";
	
}
 
 
}
