package com.example.fitbull.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gym")
@Data
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
	String name;
    String location;
    String imagePath;
    String capacity;
    String startHour;
    String endHour;
    String taxNumber;
    String webSite; 
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gym_owner_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    GymOwner gymOwner;
    

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public GymOwner getGymOwner() {
		return gymOwner;
	}
	public void setGymOwner(GymOwner gymOwner) {
		this.gymOwner = gymOwner;
	}
	
	
	
	

}