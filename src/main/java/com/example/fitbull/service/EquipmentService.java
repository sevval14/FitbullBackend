package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Equipment;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.repo.EquipmentRepository;
import com.example.fitbull.request.EquipmentRequest;
import com.example.fitbull.response.EquipmentResponse;

@Service
public class EquipmentService {
	
    private EquipmentRepository equipmentRepository;
    private GymOwnerService gymOwnerService;
    private GymService gymService;

	public EquipmentService(EquipmentRepository equipmentRepository, GymOwnerService gymOwnerService, GymService gymService) {
		this.equipmentRepository = equipmentRepository;
		this.gymOwnerService = gymOwnerService;
		this.gymService = gymService;
	}

	public List<EquipmentResponse> getAllEquipments(Optional<Long> gymOwnerId, Optional<Long> gymId) {
	    List<Equipment> equipments;

		 	if(gymOwnerId.isPresent() && gymId.isPresent()) {
		 		equipments = equipmentRepository.findByGymOwnerIdAndGymId(gymOwnerId.get(),gymId.get());
			}else if(gymOwnerId.isPresent()) {
				equipments = equipmentRepository.findByGymId(gymOwnerId.get());
			}else if(gymId.isPresent()) {
				equipments = equipmentRepository.findByGymId(gymId.get());
			}else {
				equipments=equipmentRepository.findAll();
			}
		 	  return equipments.stream()
			            .map(this::convertToEquipmentResponse)
			            .collect(Collectors.toList());
	}
	private EquipmentResponse convertToEquipmentResponse(Equipment equipment) {
		EquipmentResponse response = new EquipmentResponse();
	    response.setId(equipment.getId());
	    response.setName(equipment.getName());
	  
	    if (equipment.getGym() != null) {
	        response.setGymId(equipment.getGym().getId());
	    }
	    return response;
	}

	public EquipmentResponse createEquipments(EquipmentRequest equipmentRequest) {
		GymOwner gymOwner =gymOwnerService.getOneUserById(equipmentRequest.getGymOwnerId());
		System.out.println(equipmentRequest.getGymId());


		Gym gym = gymService.getOneGymId(equipmentRequest.getGymId());

		System.out.println(gym);

		System.out.println(gymOwner);

		
	   if (gymOwner == null) {
	       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "GymOwner is null.");
	   }
	   if (gym == null) {
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gym is null.");
	    }
	   Equipment updateEquipment = new Equipment();

		System.out.println(equipmentRequest.getId());
		updateEquipment.setName(equipmentRequest.getName());
		updateEquipment.setGym(gym);
		updateEquipment.setGymOwner(gymOwner);
		
		Equipment savedEquipment = equipmentRepository.save(updateEquipment);
		
		EquipmentResponse equipmentResponse = new EquipmentResponse();
		equipmentResponse.setId(savedEquipment.getId());
		equipmentResponse.setName(savedEquipment.getName());

		equipmentResponse.setGymId(savedEquipment.getGym().getId());

		return equipmentResponse;
	}

	public List<Equipment> getEquipmentByGymId(Long gymId) {
		return equipmentRepository.findByGymId(gymId);
	}

}
