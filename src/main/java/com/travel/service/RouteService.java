package com.travel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.dto.RouteDto;
import com.travel.entity.Route;
import com.travel.repository.RouteRepository;

@Service
public class RouteService {
	
	@Autowired
	RouteRepository routeRepository;
	
	@Transactional
	public String addRoute(RouteDto routeDto) {
		Optional<Route> optRoute=routeRepository.findByRouteKey(routeDto.getRouteKey());
		if(optRoute.isPresent()) {
			throw new RuntimeException("Route Aready Exist");
		}
		Route r=new Route();
		r.setSource(routeDto.getSource());
		r.setDestination(routeDto.getDestination());
		r.setKiloMetere(routeDto.getKiloMetere());
		r.setRouteKey(routeDto.getRouteKey());
		r.setDuration(routeDto.getDuration());
		routeRepository.save(r);
		return "Route Added Successfully";
	}
	@Transactional
	public String viewRoute(RouteDto routeDto) {
		return null;
	}
	@Transactional
	public String deleteRoute(RouteDto routeDto) {
		return null;
	}
}
