package com.travel.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "driver_id")
	private int driverId;
	@Column(name = "driver_name")
	private String driverName;
	@Column(name = "driver_license_number",unique = true)
	private String licenseNumber;
	@Column(name = "driver_mobile_number")
	private String driverMobileNumber;
	@Column(name = "driver_password")
	private String password;
	@Column(name = "driver_confirmpassword")
	private String Confimpassword;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
	private Vehicle vehicle;
	
	public Driver() {}

	public Driver(int driverId, String driverName, String licenseNumber, 
			String driverMobileNumber, Address address,Vehicle vehicle) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.licenseNumber = licenseNumber;
		this.driverMobileNumber = driverMobileNumber;
		this.address = address;
		this.vehicle = vehicle;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}			
	
}