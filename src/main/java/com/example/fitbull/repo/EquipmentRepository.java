package com.example.fitbull.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment,Long>{


	List<Equipment> findByGymOwnerIdAndGymId(Long gymOwnerId, Long gymId);

	List<Equipment> findByGymOwnerId(Long gymOwnerId);

    List<Equipment> findByGymId(Long gymId);


}
