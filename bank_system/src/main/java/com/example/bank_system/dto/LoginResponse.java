package com.example.bank_system.dto;

public class LoginResponse {

	private final String tokenType = "Bearer";
	private String token;

	public LoginResponse(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}