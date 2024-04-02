package com.example.fitbull.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.fitbull.entities.Activity;
import com.example.fitbull.request.ActivityRequest;
import com.example.fitbull.service.ActivityService;

@RestController
@RequestMapping("/activities")
@CrossOrigin
public class ActivityController {	

    private ActivityService activityService;
    
    
    public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

    @GetMapping
    public List<Activity> getAllActivities(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> gymId) {
    	return activityService.getAllActivity(userId,gymId);
    }

    @PostMapping
    public Activity createActivity(@RequestBody ActivityRequest activityRequest) {
    	return activityService.createActivity(activityRequest);
    }
    
	@GetMapping("/{gymId}")
	public List<Activity> getOneGymActivity(@PathVariable Long gymId ){
		return activityService.getActivitiesByGymId(gymId);
	}

}

