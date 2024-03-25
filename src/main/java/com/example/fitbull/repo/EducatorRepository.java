package com.example.fitbull.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.entities.Educator;
import com.example.fitbull.entities.Gym;

public interface EducatorRepository extends JpaRepository<Educator,Long>{

	Educator findByName(Gym gym);

	void deleteByName(String name);
	

}
