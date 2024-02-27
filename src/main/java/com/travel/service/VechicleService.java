package com.travel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.dto.VechicleDto;
import com.travel.entity.Vehicle;
import com.travel.repository.VehicleRepository;

@Service
public class VechicleService {
	
    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public String addVehicle(VechicleDto vehicleDto) {
        Optional<Vehicle> existingVehicle = vehicleRepository .findByvehicleNumber(vehicleDto.getVehicleNumber());
        if (existingVehicle.isPresent()) {
            throw new RuntimeException("This Vehicle number  already exists");
        }

        if (vehicleDto.getVehicleName() == null || !vehicleDto.getVehicleName().matches("^[a-zA-Z0-9\\s]*$")) {
            throw new RuntimeException("Invalid vehicle name");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
        vehicle.setVehicleType(vehicleDto.getVehicleType());
        vehicle.setVehicleName(vehicleDto.getVehicleName());
        vehicle.setFare(vehicleDto.getFare());
        vehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
        vehicleRepository.save(vehicle);

        return "Vehicle added successfully";
    }

    public String deleteVehicle(VechicleDto vechicleDto) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(vechicleDto.getVehicleId());
        if (!existingVehicle.isPresent()) {
            throw new RuntimeException("Vehicle not found");
        }
        vehicleRepository.deleteById(vechicleDto.getVehicleId());
        return "Vehicle removed successfully";
    }

    public VechicleDto viewVehicle(VechicleDto vechicleDto) {
    	Optional<Vehicle> optVeh=vehicleRepository.findById(vechicleDto.getVehicleId());
    	if(!optVeh.isPresent()) {
    		throw new RuntimeException("Vehicle not found");
    	}
    	Vehicle vVehicle=optVeh.get();
		VechicleDto vDto=new VechicleDto();
		vDto.setVehicleName(vVehicle.getVehicleName());
		vDto.setVehicleNumber(vVehicle.getVehicleNumber());
		vDto.setVehicleType(vVehicle.getVehicleType());
		vDto.setSeatingCapacity(vVehicle.getSeatingCapacity());
		vDto.setFare(vVehicle.getFare());
		vDto.setVehicleId(vVehicle.getVehicleId());
		return vDto;
        
    }

    public VechicleDto modifyVehicle(VechicleDto vehicleDto) {
    	Optional<Vehicle> optVech=vehicleRepository.findById(vehicleDto.getVehicleId());
    	if(optVech.isPresent()) {
    		Vehicle v=optVech.get();
    		v.setVehicleType(vehicleDto.getVehicleType());
    		v.setVehicleNumber(vehicleDto.getVehicleNumber());
    		v.setVehicleName(vehicleDto.getVehicleName());
    		v.setSeatingCapacity(vehicleDto.getSeatingCapacity());
    		v.setFare(vehicleDto.getFare());
    		vehicleRepository.save(v);
    		return vehicleDto;
    	//	return "Vehicle Details Updated Successfully";
    	}
        //return "Vehicle with mentioned id "+optVech+" is not found";
        throw new RuntimeException("Vehicle with mentioned id \"+optVech+\" is not found");
    }

}
