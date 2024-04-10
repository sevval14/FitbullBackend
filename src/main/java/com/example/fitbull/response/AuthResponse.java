package com.example.fitbull.response;

import lombok.Data;

@Data
public class AuthResponse {

	String message;
	long userId;
	String accessToken;
	long gymId;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getGymId() {
		return gymId;
	}
	public void setGymId(long gymId) {
		this.gymId = gymId;
	}
	

	
}