package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Educator;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.repo.EducatorRepository;
import com.example.fitbull.request.EducatorRequest;
import com.example.fitbull.response.EducatorResponse;




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


	public List<EducatorResponse> getAllEducators(Optional<Long> gymOwnerId, Optional<Long> gymId) {
	    List<Educator> educators;

	    if (gymOwnerId.isPresent() && gymId.isPresent()) {
	        educators = educatorRepository.findByGymOwnerIdAndGymId(gymOwnerId.get(), gymId.get());
	    } else if (gymOwnerId.isPresent()) {
	        // Assuming you have a method to find by GymOwnerId
	        educators = educatorRepository.findByGymOwnerId(gymOwnerId.get());
	    } else if (gymId.isPresent()) {
	        educators = educatorRepository.findByGymId(gymId.get());
	    } else {
	        educators = educatorRepository.findAll();
	    }

	    // Convert each Educator to EducatorResponse
	    return educators.stream()
	            .map(this::convertToEducatorResponse)
	            .collect(Collectors.toList());
	}

	private EducatorResponse convertToEducatorResponse(Educator educator) {
	    EducatorResponse response = new EducatorResponse();
	    response.setId(educator.getId());
	    response.setName(educator.getName());
	    response.setImagePath(educator.getImagePath());
	    response.setPhoneNumber(educator.getPhoneNumber());
	    response.setBranch(educator.getBranch());
	    // Here, make sure you handle the potential null value of getGym()
	    if (educator.getGym() != null) {
	        response.setGymId(educator.getGym().getId());
	    }
	    return response;
	}




public EducatorResponse createEducator(EducatorRequest educatorRequest) {
    GymOwner gymOwner = gymOwnerService.getOneUserById(educatorRequest.getGymOwnerId());
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
    Educator educator = new Educator();
    educator.setName(educatorRequest.getName());
    educator.setImagePath(educatorRequest.getImagePath());
    educator.setPhoneNumber(educatorRequest.getPhoneNumber());
    educator.setBranch(educatorRequest.getBranch());
    educator.setGym(gym);
    educator.setGymOwner(gymOwner);

    Educator savedEducator = educatorRepository.save(educator);

    EducatorResponse response = new EducatorResponse();
    response.setId(savedEducator.getId());
    response.setName(savedEducator.getName());
    response.setImagePath(savedEducator.getImagePath());
    response.setPhoneNumber(savedEducator.getPhoneNumber());
    response.setBranch(savedEducator.getBranch());
    response.setGymId(savedEducator.getGym().getId()); 

    // Return the DTO
    return response;
}


	public List<Educator> getEducatorsByGymId(Long gymId) {
		return educatorRepository.findByGymId(gymId);
	}

}
