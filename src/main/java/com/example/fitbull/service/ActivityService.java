package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.repo.ActivityRepository;
import com.example.fitbull.request.ActivityRequest;



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


	public List<Activity> getAllActivity(Optional<Long> gymOwnerId, Optional<Long> gymId) {
	 	if(gymOwnerId.isPresent() && gymId.isPresent()) {
			return activityRepository.findByGymOwnerIdAndGymId(gymOwnerId.get(),gymId.get());
		}else if(gymOwnerId.isPresent()) {
			return activityRepository.findByGymId(gymOwnerId.get());
		}else if(gymId.isPresent()) {
			return activityRepository.findByGymId(gymId.get());
		}
	 	return activityRepository.findAll();
	}




	public Activity createActivity(ActivityRequest activityRequest) {
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
		return activityRepository.save(updateActivity);
	}


	public List<Activity> getActivitiesByGymId(Long gymId) {
		return activityRepository.findByGymId(gymId);
	}

}
