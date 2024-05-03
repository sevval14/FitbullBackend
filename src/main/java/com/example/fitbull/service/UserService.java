package com.example.fitbull.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.GymEntry;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.UserRepository;
import com.example.fitbull.response.GymEntryResponse;
import com.example.fitbull.response.UserResponse;

@Service
public class UserService {
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getOneUserByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	public User getOneUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}
	
	public void getById(Long userId) {
		userRepository.findById(userId);
	}

	public List<UserResponse> getAll() {
		List<User> userList =userRepository.findAll();

	    
  	  return userList.stream()
		            .map(this::convertToUserResponse)
		            .collect(Collectors.toList()); 	
  	  }
	private UserResponse convertToUserResponse(User user) {
		UserResponse response = new UserResponse();
	    response.setId(user.getId());
	    response.setUserName(user.getUsername());
	    response.setEmail(user.getEmail());
	    if(user.getGymEntry()!=null) {
		    response.setEntryId(user.getGymEntry().getId());
	    }else {
		    response.setEntryId(0);

	    }

	    return response;
	}
	

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateOneCustomer(Long userId, User updateUser) {
	    User user = userRepository.findById(userId)
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

	    if (updateUser.getEmail() != null) {
	        user.setEmail(updateUser.getEmail());
	    }
	    if (updateUser.getUsername() != null) {
	        user.setUsername(updateUser.getUsername());
	    }
	    if (updateUser.getPassword() != null) {
	        user.setPassword(updateUser.getPassword());
	    }

	    return userRepository.save(user);
	}

}
