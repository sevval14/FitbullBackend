package com.example.fitbull.response;

import java.time.LocalDateTime;

public class GymEntryResponse {
    private long id;
    private long userId;
    private long gymId;
    private LocalDateTime entryTime;
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
   
	
}
