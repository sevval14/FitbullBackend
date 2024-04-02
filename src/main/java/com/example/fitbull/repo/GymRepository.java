package com.example.fitbull.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitbull.entities.Gym;



@Repository
public interface GymRepository extends JpaRepository<Gym,Long>{


	List<Gym> findByUserId(Long userId);

	

}
	