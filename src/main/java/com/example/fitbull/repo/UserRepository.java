package com.example.fitbull.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);

	User findByEmail(String email);

}
