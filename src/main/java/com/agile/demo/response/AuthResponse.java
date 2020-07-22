package com.agile.demo.response;

public class AuthResponse {
	private boolean auth;
	private String token;
	
	public AuthResponse() {
		super();
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
