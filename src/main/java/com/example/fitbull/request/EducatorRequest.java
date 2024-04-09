package com.example.fitbull.request;

import lombok.Data;

@Data
public class EducatorRequest {
	
    Long id;
	String name;
	String imagePath;
	String phoneNumber;
    Long gymOwnerId;
    Long gymId;
    
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getGymOwnerId() {
		return gymOwnerId;
	}
	public void setGymOwnerId(Long gymOwnerId) {
		this.gymOwnerId = gymOwnerId;
	}
	public Long getGymId() {
		return gymId;
	}
	public void setGymId(Long gymId) {
		this.gymId = gymId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
}
