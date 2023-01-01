package com.tpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.tpe.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

	
	
}
