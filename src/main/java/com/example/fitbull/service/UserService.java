package com.example.fitbull.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fitbull.entities.User;
import com.example.fitbull.repo.UserRepository;

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

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
