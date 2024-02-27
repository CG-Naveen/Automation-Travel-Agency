package com.travel.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private int vehicleId;
	@Column(name = "vehicle_name")	
	private String vehicleName;
	@Column(name = "vehicle_number")
	private String vehicleNumber;
	@Column(name = "vehicle_seating_capacity")
	private int seatingCapacity;
	@Column(name = "vehicle_type")
	private String vehicleType;
	@Column(name = "vehicle_fare")
	private double fare;
	

//	
//	@OneToOne(mappedBy = "vehicle")
//	private Driver driver;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Route> route;
	
	@OneToMany(mappedBy = "vehicle",cascade = CascadeType.ALL)
	private List<Booking> booking;

	
	
	public Vehicle() {}



	public int getVehicleId() {
		return vehicleId;
	}



	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}



	public String getVehicleName() {
		return vehicleName;
	}



	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}



	public String getVehicleNumber() {
		return vehicleNumber;
	}



	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}



	public int getSeatingCapacity() {
		return seatingCapacity;
	}



	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}



	public String getVehicleType() {
		return vehicleType;
	}



	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}



	public double getFare() {
		return fare;
	}



	public void setFare(double fare) {
		this.fare = fare;
	}



	public Driver getDriver() {
		return driver;
	}



	public void setDriver(Driver driver) {
		this.driver = driver;
	}



	public List<Route> getRoute() {
		return route;
	}



	public void setRoute(List<Route> route) {
		this.route = route;
	}



	public List<Booking> getBooking() {
		return booking;
	}



	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}



	public Vehicle(int vehicleId, String vehicleName, String vehicleNumber, int seatingCapacity, String vehicleType,
			double fare, Driver driver, List<Route> route, List<Booking> booking) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.seatingCapacity = seatingCapacity;
		this.vehicleType = vehicleType;
		this.fare = fare;
		this.driver = driver;
		this.route = route;
		this.booking = booking;
	}
	
	
	

	
	
}