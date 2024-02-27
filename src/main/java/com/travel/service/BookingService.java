package com.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.dto.BookingDto;
import com.travel.entity.Booking;
import com.travel.entity.Route;
import com.travel.entity.Vehicle;
import com.travel.repository.BookingRepo;
import com.travel.repository.RouteRepository;
import com.travel.repository.VehicleRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingRepo bookingRepository;
	@Autowired
	RouteRepository routeRepository;
	Route route;
	@Autowired
	VehicleRepository vehicleRepository;

	@Transactional
    public String bookings(BookingDto bookingDto, int routeId, int vehicleId) {
        // Retrieve Route and Vehicle entities from database
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route with id " + routeId + " not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with id " + vehicleId + " not found"));

        Booking booking = new Booking();
        booking.setBoardingPoint(bookingDto.getBoardingPoint());
        booking.setDropPoint(bookingDto.getDropPoint());
        booking.setJourneyDate(bookingDto.getJourneyDate());
        booking.setPassengersCount(bookingDto.getPassengersCount());
        booking.setRoute(route);
        booking.setVehicle(vehicle);
        bookingRepository.save(booking);
        return "Booking created successfully";
    }
	
	public String booking(BookingDto bookingDto) {
		Optional<Vehicle> optVeh=vehicleRepository.findBySeatingCapacity(bookingDto.getVehicle().getSeatingCapacity());
		Optional<Route>  optRoute=routeRepository.findById(bookingDto.getRoute().getRouteId());
		Booking booking=new Booking();
		if(optVeh.isEmpty()) {
			throw new RuntimeException("Hi!"+booking.getUser().getUserName()+" Vehicle for your specification is unavailable Good Bye !");
			//return "Vehicle for your specification is unavailable Good Bye !";
		}
		if(optRoute.isEmpty()) {
			throw new RuntimeException("Hi route is not valid");
		}
		booking.setBoardingPoint(bookingDto.getBoardingPoint());
		booking.setBookingDate(bookingDto.getBoardingPoint());
		booking.setDropPoint(bookingDto.getDropPoint());
		booking.setJourneyDate(bookingDto.getJourneyDate());
		booking.setPassengersCount(bookingDto.getPassengersCount());
		booking.setRoute(bookingDto.getRoute());
		booking.setVehicle(bookingDto.getVehicle());
		booking.setUser(bookingDto.getUser());
		booking.setBookingStatus(bookingDto.getBookingStatus());
		bookingRepository.save(booking);
		return "Hi da user "+booking.getUser().getUserId()+ " your booking is confirm go and chill";
	}

	
	public String CancelBooking(Booking booking) {
		Optional<Booking> optBook=bookingRepository.findById(booking.getBookingId());
		String name=optBook.get().getUser().getUserName();
		if(optBook.isEmpty()) {
			throw new RuntimeException("Hi "+name+" ! ur booking with id is not valid");
		}else {
		bookingRepository.deleteById(booking.getBookingId());
		return "Hi "+name+" ! ur booking is canceled";
	}
	}
	public List<Booking> viewBooking(int bookingid) {
		return bookingRepository.findAll();
		
	}
}