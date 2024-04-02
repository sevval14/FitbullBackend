package com.example.fitbull.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitbull.entities.Gym;
import com.example.fitbull.request.GymRequest;
import com.example.fitbull.service.GymService;

@RestController
@RequestMapping("/gym")
public class GymController {
	
	private GymService gymService;

	public GymController(GymService gymService) {
		this.gymService = gymService;
	}
	
	@GetMapping
	public List<Gym> getAllGym(@RequestParam Optional<Long> userId){
		return gymService.getAllGym(userId);
	}
	
	@GetMapping("/{gymId}")
	public Gym getOneGym(@PathVariable Long gymId) {
		return gymService.getOneGymId(gymId);
	}

	
	
	@PostMapping
	public Gym createGym(@RequestBody GymRequest newGymRequest) {
		return gymService.createOneGym(newGymRequest);
	}
	
}