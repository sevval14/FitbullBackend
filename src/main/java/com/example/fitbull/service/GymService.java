package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.GymRepository;
import com.example.fitbull.request.GymRequest;

@Service
public class GymService{
	
	private GymRepository gymRepository;
	private UserService userService;

	public GymService(GymRepository gymRepository,UserService userService) {
		this.gymRepository = gymRepository;
		this.userService=userService;
	}



	public List<Gym> getAllGym(Optional<Long> userId) {
		if(userId.isPresent()) {
			return gymRepository.findByUserId(userId.get());
		}
			
		return gymRepository.findAll();
	}
	
	public Gym getOneGymId(Long gymId) {
		return gymRepository.findById(gymId).orElse(null);
	}
	


	public Gym createOneGym(GymRequest newGymRequest) {
		
		User user =userService.getOneUserById(newGymRequest.getUserId());
		if(user ==null) {
			return null;
		}
	
		Gym toSave = new Gym();
		toSave.setId(newGymRequest.getId());
		toSave.setCapacity(newGymRequest.getCapacity());
		toSave.setImagePath(newGymRequest.getImagePath());
		toSave.setLocation(newGymRequest.getLocation());
		toSave.setName(newGymRequest.getName());
		toSave.setUser(user);
		return gymRepository.save(toSave);
	}



	
}