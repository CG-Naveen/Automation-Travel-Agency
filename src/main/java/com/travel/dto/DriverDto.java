package com.travel.dto;

import com.travel.entity.Address;

public class DriverDto {
	
	private String driverName;
	private String licenseNumber;
	private String driverMobileNumber;
	private String password;
	private Address address;
	
	public DriverDto() {}

	public DriverDto(String driverName, String licenseNumber, String driverMobileNumber, Address address) {
		super();
		this.driverName = driverName;
		this.licenseNumber = licenseNumber;
		this.driverMobileNumber = driverMobileNumber;
		this.address = address;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}

	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}
