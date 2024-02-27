package com.travel.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.travel.dto.DriverDto;
import com.travel.entity.Driver;
import com.travel.entity.Vehicle;
import com.travel.repository.DriverRepository;
import com.travel.repository.VehicleRepository;

@Service
public class DriverService {
	
	 private int driverId;
	    private int vehicleId;
	    
	    
	public int getDriverId() {
			return driverId;
		}

		public void setDriverId(int driverId) {
			this.driverId = driverId;
		}

		public int getVehicleId() {
			return vehicleId;
		}

		public void setVehicleId(int vehicleId) {
			this.vehicleId = vehicleId;
		}

	@Autowired
	DriverRepository driverRepository;
	@Autowired
	VehicleRepository vehicleRepository;
	
	
	@Transactional(readOnly = false)
	public String insertDriver(DriverDto driverDto) {
		
		Optional<Driver> optDriver = driverRepository.findBylicenseNumber(driverDto.getLicenseNumber());
		if(optDriver.isPresent()) {
			throw new RuntimeException("DRIVER ALREADY EXISTS");
		}else {
			if(driverDto.getDriverName() == null /*|| !driverDto.getDriverName().matches("^[a-zA-Z]")*/) {
				throw new RuntimeException("INVALID NAME");
			}
			if(driverDto.getDriverMobileNumber() == null /*|| !driverDto.getDriverMobileNumber().matches("^(?:\\\\+91|0)?[6789]\\\\d{9}$")*/) {
				throw new RuntimeException("INVALID MOBILE NUMBER");
			}
			if(driverDto.getLicenseNumber() == null /*|| driverDto.getLicenseNumber().matches("^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$")*/) {
				throw new RuntimeException("INVALID LICENSE NUMBER");
			}
		}
		
		Driver driver = new Driver();
		driver.setDriverName(driverDto.getDriverName());
		driver.setDriverMobileNumber(driverDto.getDriverMobileNumber());
		driver.setLicenseNumber(driverDto.getLicenseNumber());
		driver.setAddress(driverDto.getAddress());
		driverRepository.save(driver);
		return "DRIVER REGISTRATION SUCCESSFULL";
	}

	@Transactional(readOnly = false)
	public String driverLogin(DriverDto driverDto) {
		
		Optional<Driver> optDriver = driverRepository.findBylicenseNumber(driverDto.getLicenseNumber());
		if(optDriver.isPresent()) {
			
			if(optDriver.get().getPassword().matches(driverDto.getPassword())) {
				return "LOGGED IN SUCCESSFULLY";
			}
			
			throw new RuntimeException("INVALID PASSWORD");
		}
		
		throw new RuntimeException("DRIVER NOT FOUND");	
	}
	
	
	@Transactional(readOnly = true)
	public DriverDto viewDriver(DriverDto driverDto) {
		
		Optional<Driver> optDriver = driverRepository.findBylicenseNumber(driverDto.getLicenseNumber());
		if(optDriver.isPresent()) {
			Driver viewDriver = optDriver.get();
			DriverDto driveDto = new DriverDto();
			
			driveDto.setDriverName(viewDriver.getDriverName());
			driveDto.setLicenseNumber(viewDriver.getLicenseNumber());
			driveDto.setDriverMobileNumber(viewDriver.getDriverMobileNumber());
			driveDto.setAddress(viewDriver.getAddress());
			
			return driveDto; 
		}
		else throw new RuntimeException("DRIVER NOT FOUND");
		
	}
	
	
	@Transactional(readOnly = false)
	public String updateDriverProfile(DriverDto driverDto) {
		
		Optional<Driver> optDriver = driverRepository.findBylicenseNumber(driverDto.getLicenseNumber());
		
		if(optDriver.isPresent()) {
			
			Driver driver = optDriver.get();
			
			if(driverDto.getDriverName()!= null && driverDto.getDriverName().matches("^[a-zA-Z]")) {
				driver.setDriverName(driverDto.getDriverName());
			}
			else {
				throw new RuntimeException("INVALID NAME");
			}
			
			if(driverDto.getLicenseNumber()!=null && driverDto.getLicenseNumber().matches("^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$")) {
				driver.setLicenseNumber(driverDto.getLicenseNumber());
			}
			else {
				throw new RuntimeException("INVALID LICENSE NUMBER");
			}
			if(driverDto.getDriverMobileNumber()!= null && driverDto.getDriverMobileNumber().matches("^(?:\\\\+91|0)?[6789]\\\\d{9}$")) {
				driver.setDriverMobileNumber(driverDto.getDriverMobileNumber());
			}
			else {
				throw new RuntimeException("INVALID MOBILE NUMBER");
			}
			if(driverDto.getAddress()!= null) {
				driver.setAddress(driverDto.getAddress());
			}
			 driverRepository.save(driver);
			 return "PROFILE UPDATED SUCCESSFULLY";
		}
		else {
			throw new RuntimeException("DRIVER NOT FOUND");
		}
		
	}
		
	@Transactional(readOnly = false)
	public String deleteDriver(DriverDto driverDto) {
		
		Optional<Driver> optDriver = driverRepository.findBylicenseNumber(driverDto.getLicenseNumber());
		
		if(optDriver.isPresent()) {
			Driver driver = optDriver.get();
				if(driverDto.getPassword() != null && driver.getPassword().equals(driverDto.getPassword())) 
				{
					driverRepository.delete(driver);
				    return "ACCOUNT DELETED SUCCESSFULLY";
				}else {
					throw new RuntimeException("INVALID PASSWORD, CAN'T DELETE ACCOUNT");
				}
		}
		throw new RuntimeException("DRIVER NOT FOUND");
	}
	
	 @Transactional
	 public String assignVehicleToDriver(int driverId, int vehicleId) {
		
	        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
	        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

	        if (optionalDriver.isPresent() && optionalVehicle.isPresent()) {
	            Driver driver = optionalDriver.get();
	            Vehicle vehicle = optionalVehicle.get();	            
	            driver.setVehicle(vehicle);
	            vehicle.setDriver(driver);
	            driverRepository.save(driver);
	            vehicleRepository.save(vehicle);
	            return "Driver is assigned for vehicle";
	        } else {
	            throw new IllegalArgumentException("Driver or vehicle not found");
	        }
	    }
}