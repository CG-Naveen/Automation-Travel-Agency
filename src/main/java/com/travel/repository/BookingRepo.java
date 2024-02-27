package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Booking;
import com.travel.entity.Route;

public interface BookingRepo extends JpaRepository<Booking, Integer>{


}
