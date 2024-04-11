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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "educator")
@Data
public class Educator {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long id;
	    
		String name;
		String imagePath;
	    String phoneNumber;
	    String branch;
	    
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="gym_owner_id",nullable=false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    GymOwner gymOwner;
	    

	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="gym_id",nullable=false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    Gym gym;

	
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

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public String getPhoneNumner() {
			return phoneNumber;
		}

		public void setPhoneNumner(String phoneNumner) {
			this.phoneNumber = phoneNumner;
		}

		public GymOwner getGymOwner() {
			return gymOwner;
		}

		public void setGymOwner(GymOwner gymOwner) {
			this.gymOwner = gymOwner;
		}

		public Gym getGym() {
			return gym;
		}

		public void setGym(Gym gym) {
			this.gym = gym;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
		}
		
		


	    
}
