package com.example.fitbull.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitbull.entities.Educator;

@Repository
public interface EducatorRepository extends JpaRepository<Educator,Long>{

	List<Educator> findByGymOwnerIdAndGymId(Long gymOwnerId, Long gymId);

	List<Educator> findByGymOwnerId(Long gymOwnerId);

    List<Educator> findByGymId(Long gymId);


}
