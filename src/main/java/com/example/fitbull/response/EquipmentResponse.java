package com.example.fitbull.response;


import lombok.Data;

@Data
public class EquipmentResponse {
	
	Long id;
	String name;
    Long gymId;
    
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
