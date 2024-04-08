package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.GymRepository;
import com.example.fitbull.request.GymRequest;

@Service
public class GymService{
	
	private GymRepository gymRepository;
	private GymOwnerService gymOwnerService;

	public GymService(GymRepository gymRepository,GymOwnerService gymOwnerService) {
		this.gymRepository = gymRepository;
		this.gymOwnerService=gymOwnerService;
	}



	public List<Gym> getAllGym(Optional<Long> gymOwnerId) {
		if(gymOwnerId.isPresent()) {
			return gymRepository.findByGymOwnerId(gymOwnerId.get());
		}
			
		return gymRepository.findAll();
	}
	
	public Gym getOneGymId(Long gymId) {
		return gymRepository.findById(gymId).orElse(null);
	}
	


	public Gym createOneGym(GymRequest newGymRequest) {
		
		GymOwner gymOwner =gymOwnerService.getOneUserById(newGymRequest.getGymOwnerId());

        if (gymOwner == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GymOwner is null.");
        }
	
		Gym toSave = new Gym();
		toSave.setId(newGymRequest.getId());
		toSave.setCapacity(newGymRequest.getCapacity());
		toSave.setImagePath(newGymRequest.getImagePath());
		toSave.setLocation(newGymRequest.getLocation());
		toSave.setName(newGymRequest.getName());
		toSave.setStartHour(newGymRequest.getStartHour());
		toSave.setEndHour(newGymRequest.getEndHour());
		toSave.setTaxNumber(newGymRequest.getTaxNumber());
		if(newGymRequest.getStartHour()==null){
			toSave.setWebSite(null);
		}else {
			toSave.setWebSite(newGymRequest.getStartHour());
		}
		toSave.setGymOwner(gymOwner);
		return gymRepository.save(toSave);
	}



	
}