package com.example.fitbull.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.Services;

public interface ServiceRepository extends JpaRepository<Services,Long>{


	List<Services> findByGymOwnerIdAndGymId(Long gymOwnerId, Long gymId);

	List<Services> findByGymOwnerId(Long gymOwnerId);

    List<Services> findByGymId(Long gymId);


}
