package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Booking;
import com.travel.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Integer>{
	Optional<Booking> findByRouteId(int routeId);
	Optional<Route> findByRouteKey(String routeKey);
}
