package com.example.fitbull.entities;

import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.OneToOne;

@Entity
public class GymEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	Gym gym;

	LocalDateTime entryTime;
	String startWeight;
	String goalWeight;
	List<LocalDateTime> selectedDays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public String getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(String startWeight) {
		this.startWeight = startWeight;
	}

	public String getGoalWeight() {
		return goalWeight;
	}

	public void setGoalWeight(String goalWeight) {
		this.goalWeight = goalWeight;
	}

	public List<LocalDateTime> getSelectedDays() {
		return selectedDays;
	}

	public void setSelectedDays(List<LocalDateTime> selectedDays) {
		this.selectedDays = selectedDays;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

}