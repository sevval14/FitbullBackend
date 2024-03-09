package com.example.fitbull.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitbull.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	//findall save findbyıd falan entiti ile datasbe arasında etilşimi kolaylaştırı

}
