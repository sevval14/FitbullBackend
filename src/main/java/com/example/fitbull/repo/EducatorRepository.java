package com.example.fitbull.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitbull.entities.Educator;
import com.example.fitbull.entities.Gym;

@Repository
public interface EducatorRepository extends JpaRepository<Educator,Long>{

	Educator findByName(Gym gym);

	void deleteByName(String name);
	

}
