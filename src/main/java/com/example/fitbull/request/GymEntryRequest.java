package com.example.fitbull.request;

import java.time.LocalDateTime;
import java.util.List;

public class GymEntryRequest {
	Long id;
	Long userId;
	Long gymId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGymId() {
		return gymId;
	}

	public void setGymId(Long gymId) {
		this.gymId = gymId;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
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

}