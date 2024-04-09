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

import com.example.fitbull.entities.Educator;
import com.example.fitbull.request.EducatorRequest;
import com.example.fitbull.service.EducatorService;


@RestController
@RequestMapping("/educators")
@CrossOrigin
public class EducatorController {	

    private EducatorService educatorService;
    
    
    public EducatorController(EducatorService educatorService) {
		this.educatorService = educatorService;
	}

    @GetMapping
    public List<Educator> getAllEducators(@RequestParam Optional<Long> gymOwnerId, @RequestParam Optional<Long> gymId) {
    	return educatorService.getAllEducators(gymOwnerId,gymId);
    }

    @PostMapping
    public Educator createEducator(@RequestBody EducatorRequest educatorRequest) {
    	return educatorService.createEducator(educatorRequest);
    }
    
	@GetMapping("/{gymId}")
	public List<Educator> getOneGymEducator(@PathVariable Long gymId ){
		return educatorService.getEducatorsByGymId(gymId);
	}

}

