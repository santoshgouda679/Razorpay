package com.example.bank_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.dto.LoginRequest;
import com.example.bank_system.dto.LoginResponse;
import com.example.bank_system.security.JwtUtil;

import jakarta.validation.Valid;

/**
 * Issues JWTs. This endpoint is the only one left open (permitAll) in
 * SecurityConfig - everything under /accounts requires a valid Bearer
 * token obtained from here first.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		// Throws BadCredentialsException on wrong username/password, which
		// GlobalExceptionHandler turns into a clean 401.
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new LoginResponse(token));
	}
}
