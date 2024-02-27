package com.travel.dto;

public class VechicleDto {
	private int vehicleId;
	private String vehicleName;
	private String vehicleNumber;
	private int seatingCapacity;
	private String vehicleType;
	private double fare;
	
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
	
	
	public VechicleDto(int vehicleId, String vehicleName, String vehicleNumber, int seatingCapacity, String vehicleType,
			double fare) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.seatingCapacity = seatingCapacity;
		this.vehicleType = vehicleType;
		this.fare = fare;
	}
	public VechicleDto() {
		super();
	}
		
}