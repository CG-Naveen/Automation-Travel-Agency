package com.travel.dto;

import jakarta.persistence.Column;

public class RouteDto {
	private int routeId;
	private String source;
	private String destination;
	private double kiloMetere;
	private String duration;
	private String routeKey;
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
	
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	
	
	public String getRouteKey() {
		return routeKey;
	}
	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}
	
	public RouteDto(int routeId, String source, String destination, double kiloMetere, String duration,
			String routeKey) {
		super();
		this.routeId = routeId;
		this.source = source;
		this.destination = destination;
		this.kiloMetere = kiloMetere;
		this.duration = duration;
		this.routeKey = routeKey;
	}
	public RouteDto() {
		super();
	}
	
	
}
