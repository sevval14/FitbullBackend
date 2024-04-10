package com.example.fitbull.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
//generate for getter and setter 

@Entity
@Table(name = "gym_owner")
@Data
public class GymOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String email;
	String username;
	String password;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gym_id",nullable=true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Gym gym;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}
	
}
