package com.tap.user.service.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tap.user.service.entities.Hotel;
import com.tap.user.service.entities.Rating;
import com.tap.user.service.entities.User;
import com.tap.user.service.exceptions.ResourceNotFoundException;
import com.tap.user.service.externalservice.HotelService;
import com.tap.user.service.repositories.UserRepository;
import com.tap.user.service.services.UserService;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	//save user

	@Override
	public User saveUser(User user) {
//		Generate unique user id
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}
	
	//get all user

	@Override
	public List<User> getAllUser() {
		List<User> users = userRepository.findAll();
		
//		implementing rating service call : using Rest Template
//		http://localhost:8083/ratings/users/31e0dc16-4568-40ea-a13e-95373d6b1a21
		for(User user : users) {
			Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
			List<Rating> ratingList = Arrays.stream(ratings).toList();
			user.setRatings(ratingList);
			
			for(Rating rating : ratings) {
				Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
				rating.setHotel(hotel);
			}
			
		}
		
		return users;
	}
	
	
	//get single user
	
	@Override
	public User getUser(String userId) {
//		get user by user id from user database
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!"+userId));
		
//		fetch ratings by above user id from rating database
//		http://localhost:8083/ratings/users/31e0dc16-4568-40ea-a13e-95373d6b1a21
		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		
		List<Rating> ratingList = ratings.stream().map(rating ->{
			//api call to hotel service to get the hotel
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//			logger.info("response status code: {} ", forEntity.getStatusCode());
			
			//set the hotel to the rating
			rating.setHotel(hotel);
			
			//return rating
			return rating;
			
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}
	
	
	

}


//class java.util.LinkedHashMap cannot be cast to class com.tap.user.service.entities.Rating (java.util.LinkedHashMap is in module java.base of loader 'bootstrap'; com.tap.user.service.entities.Rating is in unnamed module of loader 'app')
//at com.tap.user.service.serviceimpl.UserServiceImpl.getAllUser



