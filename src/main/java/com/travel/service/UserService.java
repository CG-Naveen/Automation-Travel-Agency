package com.travel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.dto.UserDto;
import com.travel.entity.Users;
import com.travel.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional(readOnly = false)
	public String insertUser(UserDto userDto) {
		
		Optional<Users> optUser = userRepository.findByUserMailIgnoreCase(userDto.getUserMail());
		if(optUser.isPresent()) {
			throw new RuntimeException("USER ALREADY EXISTS");
		}else {
			if(userDto.getUserName()== null || !userDto.getUserName().matches("^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$")) {
				throw new RuntimeException("INVALID USER NAME");
			}
			if(userDto.getUserPassword() == null || !userDto.getUserPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
				throw new RuntimeException("PASSWORD MISMATCH WITH GIVEN CRITERIA");
			}else if(!userDto.getUserPassword().equals(userDto.getConfirmPassword())) {
				throw new RuntimeException("PASSWORD MISMATCH");
			}
			if(userDto.getUserMail() == null || !userDto.getUserMail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")) {
				throw new RuntimeException("INVALID EMAIL");
			}
			if(userDto.getUserMobileNumber() == null /*|| !userDto.getUserMobileNumber().matches("^(?:\\+91|0)?[6789]\\d{10}$")*/) {
				throw new RuntimeException("INVALID MOBILE NUMBER");
			}
			if(userDto.getUserDOB() == null) {
				throw new RuntimeException("INVALID D-O-B");
			}
			if(userDto.getUserGender() == null || !userDto.getUserGender().matches("^(Male|male|Female|female|other|Not prefer to say)$")) {
				throw new RuntimeException("INVALID GENDER ");
			}
		}
		
		Users user = new Users();
		user.setUserName(userDto.getUserName());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserDOB(userDto.getUserDOB());
		user.setUserGender(userDto.getUserGender());
		user.setUserMail(userDto.getUserMail());
		user.setUserMobileNumber(userDto.getUserMobileNumber());
		user.setAddress(userDto.getAddress());
		user.setConfirmPassword(userDto.getConfirmPassword());
		userRepository.save(user);
		return("REGISTRATION SUCCESSFULL");
	}
	
	@Transactional(readOnly = false)
	public String userLogin(UserDto userDto) {
		
		Optional<Users> optUser = userRepository.findByUserMailIgnoreCase(userDto.getUserMail());
		
		if(optUser.isPresent()) {
			
			if(optUser.get().getUserPassword().equals(userDto.getUserPassword())) {
				return "Welcome "+optUser.get().getUserName()+" !";
			}
			throw new RuntimeException("INVALID PASSWORD ");
		}
		throw new RuntimeException("USER NOT FOUND");
	}
		
	@Transactional(readOnly = true)
	public UserDto viewUser(String userMail) {
		Optional<Users> optUser = userRepository.findByUserMailIgnoreCase(userMail);
			if(optUser.isPresent()) {
				Users viewUser = optUser.get();
				UserDto useDto = new UserDto();
				
				useDto.setUserName(viewUser.getUserName());
				useDto.setUserMail(viewUser.getUserMail());
				useDto.setUserGender(viewUser.getUserGender());
				useDto.setUserDOB(viewUser.getUserDOB());
				useDto.setUserMobileNumber(viewUser.getUserMobileNumber());
				useDto.setAddress(viewUser.getAddress());
				
				return useDto;
			
		}else {
			throw new RuntimeException("USER NOT FOUND");
		}
	}
	
	@Transactional(readOnly = false)
	public String updateUserProfile(UserDto userDto) {
		
		Optional<Users> optUser = userRepository.findByUserMailIgnoreCase(userDto.getUserMail());
		
		if(optUser.isPresent()) {
			Users user = optUser.get();
			
			if(userDto.getUserName() != null && userDto.getUserName().matches("^[a-zA-Z]")) {
				user.setUserName(userDto.getUserName());
			}else {
				throw new RuntimeException("INVALID USER NAME");
			}
			if(userDto.getUserMobileNumber() != null && userDto.getUserMobileNumber().matches("^(?:\\+91|0)?[6789]\\d{9}$")) {
				user.setUserMobileNumber(userDto.getUserMobileNumber());;
			}else {
				throw new RuntimeException("INVALID MOBILE NUMBER");
			}
			
			if(userDto.getUserMail() != null && userDto.getUserMail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
				user.setUserMail(userDto.getUserMail());
			}else {
				throw new RuntimeException("INVALID EMAIL");
			}
			if(userDto.getAddress() != null) {
				user.setAddress(userDto.getAddress());
			}
			userRepository.save(user);
			return "ACCOUNT UPDATED SUCCESSFULLY";
		}
		throw new RuntimeException("USER NOT FOUND");
	}
	
	@Transactional(readOnly = false)
	public String deleteUser(UserDto userDto) {
		Optional<Users> optUser = userRepository.findByUserMailIgnoreCase(userDto.getUserMail());
		if(optUser.isPresent()) {
			Users user = optUser.get();
			if(userDto.getUserPassword() != null && user.getUserPassword().equals(userDto.getUserPassword())) {
				userRepository.delete(user);
				return "ACCOUNT DELETED SUCCESSFULLY";
			}
			throw new RuntimeException("INVALID PASSWORD, CAN'T DELETE ACCOUNT ");
		}
		throw new RuntimeException("USER NOT FOUND");
	}
}
