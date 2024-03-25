package com.example.fitbull.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.Activity;
import com.example.fitbull.entities.Gym;

public interface ActivityRepository extends JpaRepository<Activity,Long>{

	Activity findByName(Gym gym);

	void deleteByName(String name);

	List<Activity> findByGymId(Long gymId);	

}
