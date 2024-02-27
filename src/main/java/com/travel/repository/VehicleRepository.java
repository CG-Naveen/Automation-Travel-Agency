package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	Optional<Vehicle> findByvehicleNumber(String vehicleNumber);
	Optional<Vehicle> findBySeatingCapacity(int seatingCapacity);
}