package com.agile.demo.request;

public class AuthRequest {
	//TODO : this should be in a property
	private String apiKey = "23567b218376f79d9415";
	
	public AuthRequest() {
		super();
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}