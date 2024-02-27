package com.travel.dto;

import com.travel.entity.Route;
import com.travel.entity.Users;
import com.travel.entity.Vehicle;

public class BookingDto {
	private int bookingId;
	private String bookingDate;
	private String journeyDate;
	private String boardingPoint;
	private String dropPoint;
	private int passengersCount;
	private String bookingStatus;   
	
	private Vehicle vehicle;
	private Route route;	
	private Users user;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDropPoint() {
		return dropPoint;
	}
	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}
	public int getPassengersCount() {
		return passengersCount;
	}
	public void setPassengersCount(int passengersCount) {
		this.passengersCount = passengersCount;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public BookingDto(int bookingId, String bookingDate, String journeyDate, String boardingPoint, String dropPoint,
			int passengersCount, String bookingStatus, Vehicle vehicle, Route route, Users user) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.journeyDate = journeyDate;
		this.boardingPoint = boardingPoint;
		this.dropPoint = dropPoint;
		this.passengersCount = passengersCount;
		this.bookingStatus = bookingStatus;
		this.vehicle = vehicle;
		this.route = route;
		this.user = user;
	}
	public BookingDto() {
		super();
	}
	
}
