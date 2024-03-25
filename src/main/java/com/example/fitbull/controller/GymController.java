package com.example.fitbull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.entities.Educator;
import com.example.fitbull.entities.Gym;
import com.example.fitbull.repo.ActivityRepository;
import com.example.fitbull.repo.EducatorRepository;
import com.example.fitbull.repo.GymRepository;

@RestController
@RequestMapping("/gym")
public class GymController {
	
    @Autowired
	GymRepository gymRepository;
    
    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    EducatorRepository educatorRepository;
	
	public GymController(GymRepository gymRepository,ActivityRepository activityRepository,EducatorRepository educatorRepository) {
		this.gymRepository=gymRepository;
		this.activityRepository=activityRepository;
		this.educatorRepository=educatorRepository;

	}
	
	@GetMapping
	public List<Gym> getAllGym() {
		return gymRepository.findAll();
	}
	
	@GetMapping("/{name}")
	public Object getOneGym(@PathVariable String name) {
		return gymRepository.findByName(name);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<?> deleteOneGym(@PathVariable String name) {
	    try {
	        System.out.println("merhaba" +name);

	        gymRepository.deleteByName(name);
	        System.out.println("selam" +name);
	        return ResponseEntity.ok().build(); // Başarılı ise 200 OK dön
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gym not found with name: " + name);
	        // veya başka bir uygun hata mesajı / durum kodu dön
	    }
	}

	
	@PostMapping
	public ResponseEntity<?> createGym(@RequestBody Gym newGym) {
	    for (Gym gym : getAllGym()) {
	        if (gym.getName().equals(newGym.getName())) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Gym already exists");
	        }
	    }

	    Gym savedGym = gymRepository.save(newGym);

	    if (newGym.getActivities() != null) {
	        for(Activity activity : newGym.getActivities()) {
	            activity.setGym(savedGym); 
	            activityRepository.save(activity);
	        }
	    }
	    

	    if (newGym.getEducators() != null) {
	        for(Educator educator : newGym.getEducators()) {
	            educator.setGym(savedGym); 
	            educatorRepository.save(educator);
	        }
	    }

	    return ResponseEntity.ok(savedGym);
	}
	

	
}
