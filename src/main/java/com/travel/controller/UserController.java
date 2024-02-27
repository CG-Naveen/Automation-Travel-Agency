package com.travel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.travel.dto.BookingDto;
import com.travel.dto.UserDto;
import com.travel.entity.Booking;
import com.travel.service.BookingService;
import com.travel.service.UserService;

@RestController
@RequestMapping("travelagency")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	BookingService bookingService;
		
	@PostMapping("/registration")
		public String userRegistration(@RequestBody UserDto userDto) {
			userService.insertUser(userDto);
			return "REGISTRATION SUCCESSFULL";
		}
		
		@PostMapping("/login")
		public String userLogin(@RequestBody UserDto userDto) {
			return (userService.userLogin(userDto));
		}
		
		@GetMapping("/viewProfile")
		public ResponseEntity<UserDto> userView(@RequestBody UserDto userDto) {
		return new ResponseEntity<>( userService.viewUser(userDto.getUserMail()),HttpStatus.OK);
		}
		
		@PostMapping("/booking")
		    public  String createBooking(@RequestBody BookingDto  bookingDto) {
			 	return bookingService.booking(bookingDto);
		    }
		
		@GetMapping("/viewBookings")
		public List<Booking> viewBooking(@RequestBody BookingDto bookingDto) {
			return bookingService.viewBooking(bookingDto.getBookingId());
		}
}