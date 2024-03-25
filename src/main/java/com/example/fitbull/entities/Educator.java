package com.example.fitbull.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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

	    @ManyToOne
	    @JoinColumn(name = "gym_id")
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

		public Gym getGym() {
			return gym;
		}

		public void setGym(Gym gym) {
			this.gym = gym;
		}
	    
}
