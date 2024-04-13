package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.entities.Services;
import com.example.fitbull.repo.ServiceRepository;
import com.example.fitbull.request.ServiceRequest;
import com.example.fitbull.response.ServiceResponse;

@Service
public class ServicesService {
	
	private ServiceRepository serviceRepository;
    private GymOwnerService gymOwnerService;
    private GymService gymService;

	public ServicesService(ServiceRepository serviceRepository, GymOwnerService gymOwnerService,
			GymService gymService) {
		this.serviceRepository = serviceRepository;
		this.gymOwnerService = gymOwnerService;
		this.gymService = gymService;
	}

	public List<ServiceResponse> getAllServices(Optional<Long> gymOwnerId, Optional<Long> gymId) {
		List<Services> services;

	 	if(gymOwnerId.isPresent() && gymId.isPresent()) {
	 		services = serviceRepository.findByGymOwnerIdAndGymId(gymOwnerId.get(),gymId.get());
		}else if(gymOwnerId.isPresent()) {
			services = serviceRepository.findByGymId(gymOwnerId.get());
		}else if(gymId.isPresent()) {
			services = serviceRepository.findByGymId(gymId.get());
		}else {
			services=serviceRepository.findAll();
		}
	 	  return services.stream()
		            .map(this::convertToServiceResponse)
		            .collect(Collectors.toList());
	}
	private ServiceResponse convertToServiceResponse(Services service) {
		ServiceResponse response = new ServiceResponse();
	    response.setId(service.getId());
	    response.setName(service.getName());
	  
	    if (service.getGym() != null) {
	        response.setGymId(service.getGym().getId());
	    }
	    return response;
	}

	public ServiceResponse createServices(ServiceRequest serviceRequest) {
		GymOwner gymOwner =gymOwnerService.getOneUserById(serviceRequest.getGymOwnerId());
		System.out.println(serviceRequest.getGymId());


		Gym gym = gymService.getOneGymId(serviceRequest.getGymId());

		System.out.println(gym);

		System.out.println(gymOwner);

		
	   if (gymOwner == null) {
	       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GymOwner is null.");
	   }
	   if (gym == null) {
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gym is null.");
	    }
	   Services updateService = new Services();

		System.out.println(serviceRequest.getId());
		updateService.setName(serviceRequest.getName());
		updateService.setGym(gym);
		updateService.setGymOwner(gymOwner);
		
		Services savedService = serviceRepository.save(updateService);
		
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setId(savedService.getId());
		serviceResponse.setName(savedService.getName());

		serviceResponse.setGymId(savedService.getGym().getId());

		return serviceResponse;
	}

	public List<Services> getServiceByGymId(Long gymId) {
		return serviceRepository.findByGymId(gymId);

	}

}
