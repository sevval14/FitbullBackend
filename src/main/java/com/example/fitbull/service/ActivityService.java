package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.ActivityRepository;
import com.example.fitbull.request.ActivityRequest;



@Service
public class ActivityService {

    private ActivityRepository activityRepository;
    private UserService userService;
    private GymService gymService;

	public ActivityService(ActivityRepository activityRepository, UserService userService, GymService gymService) {
		this.activityRepository = activityRepository;
		this.userService = userService;
		this.gymService = gymService;
	}


	public List<Activity> getAllActivity(Optional<Long> userId, Optional<Long> gymId) {
	 	if(userId.isPresent() && gymId.isPresent()) {
			return activityRepository.findByUserIdAndGymId(userId.get(),gymId.get());
		}else if(userId.isPresent()) {
			return activityRepository.findByUserId(userId.get());
		}else if(gymId.isPresent()) {
			return activityRepository.findByGymId(gymId.get());
		}
	 	return activityRepository.findAll();
	}




	public Activity createActivity(ActivityRequest activityRequest) {
		User user =userService.getOneUserById(activityRequest.getUserId());
		Gym gym = gymService.getOneGymId(activityRequest.getGymId());

		System.out.println(gym);
		System.out.println(user);

		
		if(user ==null && gym==null) {
			return null;
		}
		Activity updateActivity = new Activity();
		updateActivity.setName(activityRequest.getName());
		updateActivity.setImagePath(activityRequest.getImagePath());
		updateActivity.setDescription(activityRequest.getDescription());
		updateActivity.setGym(gym);
		updateActivity.setUser(user);
		return activityRepository.save(updateActivity);
	}


	public List<Activity> getActivitiesByGymId(Long gymId) {
		return activityRepository.findByGymId(gymId);
	}

}
