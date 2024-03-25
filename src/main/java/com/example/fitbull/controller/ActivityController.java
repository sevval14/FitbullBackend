package com.example.fitbull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.repo.ActivityRepository;
import com.example.fitbull.repo.GymRepository;

@RestController
@RequestMapping("/gyms/activities")
public class ActivityController {
	
	
	
    @Autowired
	GymRepository gymRepository;
    
    @Autowired
    ActivityRepository activityRepository;
    
   
	
	public ActivityController(GymRepository gymRepository,ActivityRepository activityRepository) {
		this.gymRepository=gymRepository;
		this.activityRepository=activityRepository;
	}
	
	@GetMapping
	public List<Activity> getAllActivity() {
		return activityRepository.findAll();
	}

	

	



	
}
