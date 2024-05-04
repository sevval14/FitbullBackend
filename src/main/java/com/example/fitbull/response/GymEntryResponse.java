package com.example.fitbull.response;

import java.time.LocalDateTime;
import java.util.List;

public class GymEntryResponse {
	private long id;
	private long userId;
	private long gymId;
	private LocalDateTime entryTime;
	private String startWeight;
	private String goalWeight;
	private List<LocalDateTime> selectedDays;
	private String gymName;
	private String gymLocation;
	private String startHour;
	private String endHour;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGymId() {
		return gymId;
	}

	public void setGymId(long gymId) {
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

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public String getGymLocation() {
		return gymLocation;
	}

	public void setGymLocation(String gymLocation) {
		this.gymLocation = gymLocation;
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

}