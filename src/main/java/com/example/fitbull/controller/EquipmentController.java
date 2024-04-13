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

import com.example.fitbull.entities.Equipment;
import com.example.fitbull.request.EquipmentRequest;
import com.example.fitbull.response.EquipmentResponse;
import com.example.fitbull.service.EquipmentService;

@RestController
@RequestMapping("/equipments")
@CrossOrigin
public class EquipmentController {	

    private EquipmentService equipmentService;
    
    
    public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

    @GetMapping
    public List<EquipmentResponse> getAllEquipments(@RequestParam Optional<Long> gymOwnerId, @RequestParam Optional<Long> gymId) {
    	return equipmentService.getAllEquipments(gymOwnerId,gymId);
    }

    @PostMapping
    public EquipmentResponse createEquipment(@RequestBody EquipmentRequest equipmentRequest) {
    	return equipmentService.createEquipments(equipmentRequest);
    }
    
	@GetMapping("/{gymId}")
	public List<Equipment> getOneGymEquipment(@PathVariable Long gymId ){
		return equipmentService.getEquipmentByGymId(gymId);
	}

}

