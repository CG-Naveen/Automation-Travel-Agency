package com.travel.dto;

import com.travel.entity.Address;

public class UserDto {
	
	private String userName;
	private String userPassword;
	private String confirmPassword;
	private String userDOB;
	private String userGender;
	private String userMail;
	private String userMobileNumber;
	private Address address;
	
	public UserDto() {}

	public UserDto(String userName, String userPassword, String userDOB, String userGender, String userMail,
			String userMobileNumber, Address address, String confirmPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userDOB = userDOB;
		this.userGender = userGender;
		this.userMail = userMail;
		this.userMobileNumber = userMobileNumber;
		this.address = address;
		this.confirmPassword = confirmPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
