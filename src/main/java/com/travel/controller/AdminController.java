package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.dto.DriverDto;
import com.travel.dto.RouteDto;
import com.travel.dto.VechicleDto;
import com.travel.service.DriverService;
import com.travel.service.RouteService;
import com.travel.service.VechicleService;

@RestController
@RequestMapping("/admin")
public class AdminController {
			@Autowired
		    private VechicleService vehicleService;
		
			@Autowired
			private DriverService driverService;
			
			@Autowired
			private RouteService routeService;
			
		    @PostMapping("/addVehicle")
		    public ResponseEntity<String> addVehicle(@RequestBody VechicleDto vehicleDto) {
		        try {
		            String response = vehicleService.addVehicle(vehicleDto);
		            return new ResponseEntity<>(response, HttpStatus.OK);
		        } catch (Exception e) {
		            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		        }
		    }
		
		    @DeleteMapping("/deleteVehicle")
		    public ResponseEntity<String> deleteVehicle(@RequestBody VechicleDto vechicleDto) {
		        try {
		            String response = vehicleService.deleteVehicle(vechicleDto); // .deleteVehicle(vechicleDto);
		            return new ResponseEntity<>(response, HttpStatus.OK);
		        } catch (Exception e) {
		            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		        }
		    }
		    @GetMapping("/viewVehicle")
		    public VechicleDto viewVehicle(@RequestBody VechicleDto vechicleDto) {
		    	return(vehicleService.viewVehicle(vechicleDto));
		    }
		    
		    @PutMapping("/modifyVehicle")
		    public String viewModify (@RequestBody VechicleDto vechicleDto) {
		    	vehicleService.modifyVehicle(vechicleDto);
		    	return "Updated Successfully";
		    }
		    
		    @PostMapping("/registerDriver")
		    public String registerDriver(@RequestBody DriverDto driverDto) {
		    	return driverService.insertDriver(driverDto);
				//return "Driver Registered Successfully";
		    }
//		    
//		    @PostMapping("/assignVehicleToDriver")
//		    public ResponseEntity<String> assignVehicleToDriver(@RequestParam int driverId, @RequestParam int vehicleId) {
//		    	driverService.assignVehicleToDriver(driverId, vehicleId);
//		    	return ResponseEntity.ok("Vehicle assigned to driver successfully.");
//		    }
		    @PostMapping("/assignVehicleToDriver")
		    public ResponseEntity<String> assignVehicleToDriver(@RequestBody DriverService request) {
		        driverService.assignVehicleToDriver(request.getDriverId(), request.getVehicleId());
		        return ResponseEntity.ok("Vehicle assigned to driver successfully.");
		    }
		    
		    @PostMapping("/addRoute")
		    public ResponseEntity<String> addRoute(@RequestBody RouteDto routeDto){
		    	routeService.addRoute(routeDto);
		    	return ResponseEntity.ok("Route Added Successfully");
		    }
}
