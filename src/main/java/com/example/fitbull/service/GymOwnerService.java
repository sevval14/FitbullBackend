package com.example.fitbull.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.repo.GymOwnerRepository;

@Service
public class GymOwnerService {
	GymOwnerRepository gymOwnerRepository;

	public GymOwnerService(GymOwnerRepository userRepository) {
		this.gymOwnerRepository = userRepository;
	}

	public GymOwner getOneUserByUserName(String userName) {
		return gymOwnerRepository.findByUsername(userName);
	}
	
	public GymOwner getOneUserByEmail(String email) {
		return gymOwnerRepository.findByEmail(email);
	}

	public GymOwner saveOneUser(GymOwner newUser) {
		return gymOwnerRepository.save(newUser);
	}

	public void deleteById(Long userId) {
		gymOwnerRepository.deleteById(userId);
	}
	
	public void getById(Long userId) {
		gymOwnerRepository.findById(userId);
	}

	public List<GymOwner> getAll() {
		return gymOwnerRepository.findAll();
	}

	public GymOwner getOneUserById(Long userId) {
		return gymOwnerRepository.findById(userId).orElse(null);
	}

}
