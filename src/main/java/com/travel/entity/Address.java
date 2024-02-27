package com.travel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	@Column(name = "address_door_num")
	private String doorNum;
	@Column(name = "address_street")
	private String street;
	@Column(name = "address_city")
	private String city;
	@Column(name = "address_state")
	private String state;
	@Column(name = "address_country")
	private String country;
	@Column(name = "address_zipcode")
	private long zipCode;
	
	@OneToOne(mappedBy = "address")
	private Users user;
	
	@OneToOne(mappedBy = "address")
	private Driver driver;
	
	public Address() {}
	
	public Address(String doorNum, String street, String city, String state, String country, long zipCode) {
		super();
		this.doorNum = doorNum;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}
	public String getDoorNum() {
		return doorNum;
	}
	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getZipCode() {
		return zipCode;
	}
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
	

}
