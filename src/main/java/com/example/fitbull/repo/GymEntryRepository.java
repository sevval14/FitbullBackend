package com.example.fitbull.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitbull.entities.GymEntry;




@Repository
public interface GymEntryRepository extends JpaRepository<GymEntry,Long>{


	GymEntry findByGymId(Long gymId);
	
	GymEntry findByUserId(Long userId);

	

}
	