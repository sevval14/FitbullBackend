package com.example.fitbull.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fitbull.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long>{


	List<Activity> findByUserIdAndGymId(Long userId, Long gymId);

	List<Activity> findByUserId(Long userId);

    List<Activity> findByGymId(Long gymId);


}
