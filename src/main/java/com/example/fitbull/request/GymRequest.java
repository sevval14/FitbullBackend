package com.example.fitbull.request;

import lombok.Data;

@Data
public class GymRequest {

	Long id;
	String name;
    String location;
    String imagePath;
    String capacity;
    String startHour;
    String endHour;
    String taxNumber;
    String webSite;
    Long gymOwnerId;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public Long getGymOwnerId() {
		return gymOwnerId;
	}
	public void setgymOwnerId(Long gymOwnerId) {
		this.gymOwnerId = gymOwnerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
    
    
}
