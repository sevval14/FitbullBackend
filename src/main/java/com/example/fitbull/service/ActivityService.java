package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.entities.Educator;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.repo.ActivityRepository;
import com.example.fitbull.request.ActivityRequest;
import com.example.fitbull.response.ActivityResponse;
import com.example.fitbull.response.EducatorResponse;



@Service
public class ActivityService {

    private ActivityRepository activityRepository;
    private GymOwnerService gymOwnerService;
    private GymService gymService;

	public ActivityService(ActivityRepository activityRepository, GymOwnerService gymOwnerService, GymService gymService) {
		this.activityRepository = activityRepository;
		this.gymOwnerService = gymOwnerService;
		this.gymService = gymService;
	}


	public List<ActivityResponse> getAllActivity(Optional<Long> gymOwnerId, Optional<Long> gymId) {
	    List<Activity> activities;

	 	if(gymOwnerId.isPresent() && gymId.isPresent()) {
	 		activities = activityRepository.findByGymOwnerIdAndGymId(gymOwnerId.get(),gymId.get());
		}else if(gymOwnerId.isPresent()) {
			activities = activityRepository.findByGymId(gymOwnerId.get());
		}else if(gymId.isPresent()) {
			activities = activityRepository.findByGymId(gymId.get());
		}else {
			activities=activityRepository.findAll();
		}
	 	  return activities.stream()
		            .map(this::convertToActivityResponse)
		            .collect(Collectors.toList());
		}	


	private ActivityResponse convertToActivityResponse(Activity activity) {
		ActivityResponse response = new ActivityResponse();
	    response.setId(activity.getId());
	    response.setName(activity.getName());
	    response.setImagePath(activity.getImagePath());
	    response.setDescription(activity.getDescription());
	    
	    if (activity.getGym() != null) {
	        response.setGymId(activity.getGym().getId());
	    }
	    return response;
	}


	public ActivityResponse createActivity(ActivityRequest activityRequest) {
		GymOwner gymOwner =gymOwnerService.getOneUserById(activityRequest.getGymOwnerId());
		System.out.println(activityRequest.getGymId());


		Gym gym = gymService.getOneGymId(activityRequest.getGymId());

		System.out.println(gym);

		System.out.println(gymOwner);

		
	   if (gymOwner == null) {
	       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GymOwner is null.");
	   }
	   if (gym == null) {
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gym is null.");
	    }
		Activity updateActivity = new Activity();

		System.out.println(activityRequest.getId());
		updateActivity.setName(activityRequest.getName());
		updateActivity.setImagePath(activityRequest.getImagePath());
		updateActivity.setDescription(activityRequest.getDescription());
		updateActivity.setGym(gym);
		updateActivity.setGymOwner(gymOwner);
		
		Activity savedActivity = activityRepository.save(updateActivity);
		
		ActivityResponse activityResponse = new ActivityResponse();
		activityResponse.setId(savedActivity.getId());
		activityResponse.setName(savedActivity.getName());
		activityResponse.setImagePath(savedActivity.getImagePath());
		activityResponse.setDescription(savedActivity.getDescription());
		activityResponse.setGymId(savedActivity.getGym().getId());

		return activityResponse;
	}


	public List<Activity> getActivitiesByGymId(Long gymId) {
		return activityRepository.findByGymId(gymId);
	}

}
