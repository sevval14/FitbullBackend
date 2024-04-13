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

import com.example.fitbull.entities.Services;
import com.example.fitbull.request.ServiceRequest;
import com.example.fitbull.response.ServiceResponse;
import com.example.fitbull.service.ServicesService;

@RestController
@RequestMapping("/services")
@CrossOrigin
public class ServicesController {	

    private ServicesService servicesService;
    
    
    public ServicesController(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

    @GetMapping
    public List<ServiceResponse> getAllServices(@RequestParam Optional<Long> gymOwnerId, @RequestParam Optional<Long> gymId) {
    	return servicesService.getAllServices(gymOwnerId,gymId);
    }

    @PostMapping
    public ServiceResponse createService(@RequestBody ServiceRequest serviceRequest) {
    	return servicesService.createServices(serviceRequest);
    }
    
	@GetMapping("/{gymId}")
	public List<Services> getOneGymService(@PathVariable Long gymId ){
		return servicesService.getServiceByGymId(gymId);
	}

}
