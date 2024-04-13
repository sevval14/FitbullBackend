package com.example.fitbull.request;

import lombok.Data;

@Data
public class ServiceRequest {
	
	Long id;
	String name;
    Long gymOwnerId;
    Long gymId;
    
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
    
}
