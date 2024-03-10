package com.example.fitbull.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitbull.entities.User;
import com.example.fitbull.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        for (User user : getAllUsers()) {
            if (user.getEmail().equals(newUser.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                     .body("Username already exists");
            }
        }

        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }
	

	@PostMapping("/{userId}")
	public User updateOneUser(@PathVariable Long userId ,@RequestBody User newUser){
		Optional<User> oldUser = userRepository.findById(userId);
		
		//There is customer or not
		if(oldUser.isPresent()) {
			User foundUser = oldUser.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setPassword(newUser.getPassword());	
			foundUser.setEmail(newUser.getEmail());
			return userRepository.save(foundUser);
			}else {
			return null;
		}
	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
	    userRepository.deleteById(userId);
	}

	
	

	
	
	
}
