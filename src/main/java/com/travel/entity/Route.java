package com.travel.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "route_id")
	private int routeId;
	@Column(name = "route_source")
	private String source;
	@Column(name = "route_destination")
	private String destination;
	@Column(name = "route_kilometer")
	private double kiloMetere;
	@Column(name = "route_duration")
	private String duration;
	private String routeKey;
	@OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
	private List<Booking> booking;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Vehicle vehicle;
	
	public Route() {}

	

	public Route(int routeId, String source, String destination, double kiloMetere, String duration, String routeKey,
			List<Booking> booking, Vehicle vehicle) {
		super();
		this.routeId = routeId;
		this.source = source;
		this.destination = destination;
		this.kiloMetere = kiloMetere;
		this.duration = duration;
		this.routeKey = routeKey;
		this.booking = booking;
		//this.vehicle = vehicle;
	}



	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getKiloMetere() {
		return kiloMetere;
	}

	public void setKiloMetere(double kiloMetere) {
		this.kiloMetere = kiloMetere;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


	public List<Booking> getBooking() {
		return booking;
	}


	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}


	public int getRouteId() {
		return routeId;
	}


	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}


//	public Vehicle getVehicle() {
//		return vehicle;
//	}
//
//
//	public void setVehicle(Vehicle vehicle) {
//		this.vehicle = vehicle;
//	}


	public String getRouteKey() {
		return routeKey;
	}


	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}
	
	
}
