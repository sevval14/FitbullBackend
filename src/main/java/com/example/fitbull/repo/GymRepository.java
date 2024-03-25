package com.example.fitbull.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.Gym;

import jakarta.transaction.Transactional;

public interface GymRepository extends JpaRepository<Gym,Long>{

	Gym findByName(String name);

	@Transactional
	void deleteByName(String name);
	

}
	