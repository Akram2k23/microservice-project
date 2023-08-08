package com.tap.user.service.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tap.user.service.entities.User;
import com.tap.user.service.serviceimpl.UserServiceImpl;
import com.tap.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	// save user
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	// get user by id
	int retryCount = 1;
	
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "UserRateLimiter", fallbackMethod = "ratingHotelFallback")
	@GetMapping("/{userid}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userid){
		logger.info("Get Single User Handler : UserController");
		logger.info("Retry count : {}", retryCount);
		retryCount++;
		
		User user = userService.getUser(userid);
		return ResponseEntity.ok(user);
	}
	
	
	// creating fallback method for circuit breaker
	
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//		logger.info("Fallback is executed because service is down : "+ex.getMessage());
		
		ex.printStackTrace();
		
		User user = User.builder()
				.setUserId(userId)
				.setName("dummy")
				.setEmail("dummy@gmail.com")
				.setAbout("This user is created dummy because some service is down")
				.build();
//		User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

	
	
	
	// get all user
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		
	}
	
	
	
	
	
	
	
	
	

}
