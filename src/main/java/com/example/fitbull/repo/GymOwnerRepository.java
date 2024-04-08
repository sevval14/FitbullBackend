package com.example.fitbull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitbull.entities.GymOwner;

@Repository
public interface GymOwnerRepository extends JpaRepository<GymOwner,Long>{

	GymOwner findByUsername(String username);

	GymOwner findByEmail(String email);

}
