package com.travel;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.dto.UserDto;
import com.travel.entity.Address;
import com.travel.entity.Users;
import com.travel.repository.BookingRepo;
import com.travel.repository.UserRepository;
import com.travel.service.UserService;


@SpringBootTest
public class TestUserService {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private BookingRepo bookingRepository;
	
	@Test
	void insertUser() {
		
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserMobileNumber("+91 9382345761");
		userDto.setUserMail("test12@test.com");
		userDto.setUserDOB("13-08-1983");
		userDto.setUserGender("Male");
		userDto.setUserPassword("Test@12");
		userDto.setConfirmPassword("Test@12");
		userDto.setAddress(new Address("230-Q","sengunthar nagar","Erode","tamilnadu","india",293739));
		Users user = new Users();
		user.setUserName(userDto.getUserName());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserMobileNumber(userDto.getUserMobileNumber());
		user.setUserMail(userDto.getUserMail());
		user.setUserGender(userDto.getUserGender());
		user.setUserDOB(userDto.getUserDOB());
		user.setAddress(userDto.getAddress());
		Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);
		
		String savedUser = userService.insertUser(userDto);
		System.out.println(savedUser);
		
		Assertions.assertEquals("Registration Successful", savedUser);
	}
	
	@Test
	void insertUser_UserAlreadyExists() {
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserMail("test12@test.com");
		Users user = new Users();
		user.setUserName(userDto.getUserName());
		user.setUserMail(userDto.getUserMail());
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(user));
		Assertions.assertThrows(RuntimeException.class,() -> userService.insertUser(userDto));
	}
	
	@Test
	void insertUser_InvalidUserName() {
		UserDto userDto = new UserDto();
		userDto.setUserName(null);
		Assertions.assertThrows(RuntimeException.class,() -> userService.insertUser(userDto));
	}
	
	@Test
	void insertUser_InvalidPassword() {
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserPassword(null);
		Assertions.assertThrows(RuntimeException.class,() -> userService.insertUser(userDto));
	}
	
	@Test
	void insertUser_PasswordMisMatch() {
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserPassword("newpassword");
		userDto.setConfirmPassword("oldpassword");
		Assertions.assertThrows(RuntimeException.class,() -> userService.insertUser(userDto));
	}
	
	@Test
	void insertUser_InvalidEmail() {
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserPassword("newpassword");
		userDto.setConfirmPassword("oldpassword");
		userDto.setUserMail(null);
		Assertions.assertThrows(RuntimeException.class,() -> userService.insertUser(userDto));
	}
	
	@Test
	void insertUser_InvalidMobileNumber() {
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserPassword("newpassword");
		userDto.setConfirmPassword("oldpassword");
		userDto.setUserMail("Test12@test.com");
		userDto.setUserMobileNumber("132435");
		Assertions.assertThrows(RuntimeException.class,() -> userService.insertUser(userDto));
	}
	
	@Test
	void userLogin_SuccessfulLogin() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("Test12@test.com");
		userDto.setUserPassword("newpassword");
		Users user = new Users();
		user.setUserMail(userDto.getUserMail());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserName("initialname");
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(user));
		String userLogin = userService.userLogin(userDto);
		System.out.println(userLogin);
		Assertions.assertEquals("Logged in successfully", userLogin);
	}
	
	@Test
	void userLogin_InvalidPassword() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("Test12@test.com");
		userDto.setUserPassword("wrongpassword");
		Users user = new Users();
		user.setUserMail(userDto.getUserMail());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserName("initialname");
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(user));
		Assertions.assertThrows(RuntimeException.class,() -> userService.userLogin(userDto));
	}
	
	@Test
	void userLogin_UserNotFound() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("noexistuser@test.com");
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.empty());
		Assertions.assertThrows(RuntimeException.class,() -> userService.userLogin(userDto));
	}
	
	@Test
	void getUserProfileByMail() throws JsonProcessingException{
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserMobileNumber("+91 9382345761");
		userDto.setUserMail("test12@test.com");
		userDto.setUserDOB("13-08-1983");
		userDto.setUserGender("Male");
		userDto.setAddress(new Address("230-Q","sengunthar nagar","Erode","tamilnadu","india",293739));
		Users user = new Users();
		user.setUserName(userDto.getUserName());
		user.setUserMobileNumber(userDto.getUserMobileNumber());
		user.setUserMail(userDto.getUserMail());
		user.setUserGender(userDto.getUserGender());
		user.setUserDOB(userDto.getUserDOB());
		user.setAddress(userDto.getAddress());
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.of(user));
		
		UserDto retrivedUserDto = userService.viewUser(userDto.getUserMail());
		System.out.println(retrivedUserDto);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJson = objectMapper.writeValueAsString(userDto);
		String retrivedJson = objectMapper.writeValueAsString(retrivedUserDto);
		Assertions.assertEquals(expectedJson, retrivedJson);
	}
	
	@Test
	void getUserProfileByMail_UserNotFound() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("noexistuser@test.com");
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.empty());
		Assertions.assertThrows(RuntimeException.class,() -> userService.viewUser("noexistuser@test.com"));
	
	}
	
	@Test
	void updateUser() {
		UserDto userDto = new UserDto();
		userDto.setUserName("Test");
		userDto.setUserMobileNumber("+91 9382345761");
		userDto.setUserMail("test12@test.com");
		userDto.setAddress(new Address("230-Q","sengunthar nagar","Erode","tamilnadu","india",293739));
		Users user = new Users();
		user.setUserName("updatename");
		user.setUserMobileNumber("+91 9876453210");
		user.setUserMail(userDto.getUserMail());
		user.setAddress(new Address("updatehno","updatestreet","updatecity","updatestate","updatecountry",123456));
		
		Mockito.when(userRepository.findByUserMailIgnoreCase("test12@test.com")).thenReturn(Optional.of(user));
		
		String output = userService.updateUserProfile(userDto);
		System.out.println(output);
		
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(Users.class));
		Assertions.assertEquals("Profile updated successfully",output);

	}
	
	@Test
	void updateUder_UserNotFound() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("noexistuser@test.com");
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.empty());
		Assertions.assertThrows(RuntimeException.class,() -> userService.viewUser("noexistuser@test.com"));
	}
	
	@Test
	void updateuser_InvalidMobileNumber() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("test12@test.com");
		userDto.setUserMobileNumber("373791");
		Users user = new Users();
		user.setUserMail(userDto.getUserMail());
		Mockito.when(userRepository.findByUserMailIgnoreCase(userDto.getUserMail())).thenReturn(Optional.of(user));
		Assertions.assertThrows(RuntimeException.class,() -> userService.updateUserProfile(userDto));
	}
	
	@Test
	void updateuser_InvalidUserName() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("test12@test.com");
		userDto.setUserName("128dnjiq");
		Users user = new Users();
		user.setUserMail(userDto.getUserMail());
		Mockito.when(userRepository.findByUserMailIgnoreCase("test12@test.com")).thenReturn(Optional.of(user));
		Assertions.assertThrows(RuntimeException.class,() -> userService.updateUserProfile(userDto));
	}
	
	void deleteUser() {
		UserDto userDto = new UserDto();
		userDto.setUserMail("test12@test.com");
		userDto.setUserPassword("Test@12");
		Users user = new Users();
		user.setUserName("initialusername");
		user.setUserMail(userDto.getUserMail());
		user.setUserPassword("Test@12");
		user.setUserMobileNumber("+91 9382345761");
		user.setAddress(new Address("updatehno","updatestreet","updatecity","updatestate","updatecountry",123456));
		Mockito.when(userRepository.findByUserMailIgnoreCase("test12@test.com")).thenReturn(Optional.of(user));
		String output = userService.deleteUser(userDto);
		System.out.println(output);

		Mockito.verify(userRepository, Mockito.times(1)).delete(user);

		Assertions.assertEquals("Account deleted successfully", output);
	}
	
	@Test
	void deleteUser_InvalidPassword()
	{
		UserDto userDto = new UserDto();
		userDto.setUserMail("gmail.com");
		userDto.setUserPassword("wrongpassword");		
		Users user = new Users();
		user.setUserMail(userDto.getUserDOB());
		user.setUserPassword("correctpassword");		
		Mockito.when(userRepository.findByUserMailIgnoreCase(userDto.getUserDOB())).thenReturn(Optional.of(user));		
		Assertions.assertThrows(RuntimeException.class, () -> userService.deleteUser(userDto));

	}
	
	@Test
	void deleteUser_UserNotFound()
	{
	    // Create a userDto for a non-existent user
		UserDto userDto = new UserDto();
		userDto.setUserMail("gmail.com");
		Mockito.when(userRepository.findByUserMailIgnoreCase(Mockito.anyString())).thenReturn(Optional.empty());
		Assertions.assertThrows(RuntimeException.class,() -> userService.deleteUser(userDto));		
	}
	

}
