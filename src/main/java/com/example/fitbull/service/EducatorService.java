package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Educator;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.repo.EducatorRepository;
import com.example.fitbull.request.EducatorRequest;




@Service
public class EducatorService {

    private EducatorRepository educatorRepository;
    private GymOwnerService gymOwnerService;
    private GymService gymService;

	public EducatorService(EducatorRepository educatorRepository, GymOwnerService gymOwnerService, GymService gymService) {
		this.educatorRepository = educatorRepository;
		this.gymOwnerService = gymOwnerService;
		this.gymService = gymService;
	}


	public List<Educator> getAllEducators(Optional<Long> gymOwnerId, Optional<Long> gymId) {
	 	if(gymOwnerId.isPresent() && gymId.isPresent()) {
			return educatorRepository.findByGymOwnerIdAndGymId(gymOwnerId.get(),gymId.get());
		}else if(gymOwnerId.isPresent()) {
			return educatorRepository.findByGymId(gymOwnerId.get());
		}else if(gymId.isPresent()) {
			return educatorRepository.findByGymId(gymId.get());
		}
	 	return educatorRepository.findAll();
	}




	public Educator createEducator(EducatorRequest educatorRequest) {
		GymOwner gymOwner =gymOwnerService.getOneUserById(educatorRequest.getGymOwnerId());
		System.out.println(educatorRequest.getGymId());


		Gym gym = gymService.getOneGymId(educatorRequest.getGymId());

		System.out.println(gym);

		System.out.println(gymOwner);

		
	   if (gymOwner == null) {
	       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GymOwner is null.");
	   }
	   if (gym == null) {
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gym is null.");
	    }
		Educator educatorUpdate = new Educator();

		System.out.println(educatorRequest.getId());
		educatorUpdate.setName(educatorRequest.getName());
		educatorUpdate.setImagePath(educatorRequest.getImagePath());
		educatorUpdate.setPhoneNumner(educatorRequest.getPhoneNumber());
		educatorUpdate.setGym(gym);
		educatorUpdate.setGymOwner(gymOwner);
		return educatorRepository.save(educatorUpdate);
	}


	public List<Educator> getEducatorsByGymId(Long gymId) {
		return educatorRepository.findByGymId(gymId);
	}

}
