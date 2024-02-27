package com.travel.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.entity.Driver;



public interface DriverRepository extends JpaRepository<Driver,Integer>{
	
	Optional<Driver> findBylicenseNumber(String licenseNumber);

}
